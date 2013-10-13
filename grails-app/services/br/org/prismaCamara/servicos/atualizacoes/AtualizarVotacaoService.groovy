package br.org.prismaCamara.servicos.atualizacoes

import org.hibernate.SessionFactory;

import br.org.prismaCamara.servicos.UsuarioService;

import groovy.text.SimpleTemplateEngine
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult
import hackathon2013.Deputado;
import hackathon2013.Proposicao;
import hackathon2013.Votacao;
import hackathon2013.Voto;

@Log4j
class AtualizarVotacaoService extends AtualizadorEntidade {

	UsuarioService usuarioService
	
	SessionFactory sessionFactory
	
	@Override
	public String getSiglaDeParametro() {
		// "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ObterVotacaoProposicao?tipo=${tipo}&numero=${numero}&ano=${ano}"
		return 'url_votacoes_proposicoes';
	}

	/**
	 * Atualizar as tabela de Voto, Votacao e OrientacaoBancada.
	 * A atualização recupera somente votos de Proposições que são acompanhadas por 1 ou mais usuários 
	 */
	def atualizar() {
		
//		def proposicoes = usuarioService.proposicoesMapeadas
		def proposicoes = Proposicao.findAllByNumeroInList([190,300])

		log.debug("Um total de ${proposicoes.size()} terão votos verificados")
		
		for (proposicaoA in proposicoes) {
//			def tx = Votacao.withNewTransaction { tx ->
				
			def pTipo = proposicaoA.tipoProposicao.sigla
			def pNumero = proposicaoA.numero
			def pAno = proposicaoA.ano
			def desc = "${pTipo} ${pNumero}/${pAno}"
				
			def urlT = getUrlAtualizacao([tipo:pTipo,numero:pNumero.toString(),ano:pAno])
			GPathResult xmlr = null
			try {
				xmlr = getXML(urlT)
			} catch (Exception e) {
				log.error("A url ${urlT} não retornou XML válido ou não continha votação: ${e.message}")
				continue;
			}
			log.debug("Votações da proposição ${desc} chegaram no XML...")
			
			// pegando só o último voto (que é o mais recente)
			Integer idUltimo = xmlr.Votacoes.childNodes().size()-1
			def vot = xmlr.Votacoes.childNodes()[idUltimo]
			
			def dataHotaS = "${vot.attributes.Data} ${vot.attributes.Hora}"  
			def dataHoraA = Date.parse('d/M/yyyy HH:mm',dataHotaS)
			
			boolean existeNovaVotacao = (proposicaoA.ultimaVotacao)?proposicaoA.ultimaVotacao.before(dataHoraA):true
			if (!existeNovaVotacao) { // não há nova votação além da última já registrada
				log.debug("Última votação da proposição ${desc} já registrada e postada. Votação Ignorada")
				continue
			} else { // ainda não existe. Persista agora
				
				proposicaoA.ultimaVotacao=dataHoraA
				def atributos = [resumo:vot.attributes.Resumo, dataHoraVotacao:dataHoraA, objVotacao:vot.attributes.ObjVotacao, proposicao:proposicaoA]
				
				Votacao entidade = new Votacao(atributos)
				entidade.save()
				
				if (entidade.errors.errorCount>0) {
					log.error("Votações da Proposição ${desc} NÃO foram salvas devido a erros: ${entidade.errors}")
					continue
				} else {
					log.debug("Novas Votações da Proposição ${desc} e seus Votos salvos no banco")
				}

				// Votos individuais dos Deputados			
				for (ob in vot.childNodes()[1].childNodes()) {
					
					def nomeA=ob.attributes.Nome.trim().toUpperCase()
					def partidoA=ob.attributes.Partido.trim()
					def ufA=ob.attributes.UF.trim()
					def votoA=ob.attributes.Voto.trim()
					
					// SE não for econtrado o Deputado, salve no banco como "ativo=false"
					Deputado deputadoA = Deputado.findByNomeParlamentarAndUf(nomeA,ufA)
							
					if (!deputadoA) {
						deputadoA = new Deputado(nome:nomeA,nomeParlamentar: nomeA, siglaPartido:partidoA, uf:ufA, ativo:false)
						deputadoA.save()
						log.debug("Deputado ${deputadoA.descricao}) não existia na base. Salvo como 'inativo'")
					}
							
					Voto voto = Voto.where{votacao==entidade && deputado==deputadoA}.find()
					if (!voto) {
						try {
							voto = new Voto(deputado:deputadoA, votacao: entidade, voto:votoA)
							voto.save()
							log.debug("Voto (${deputadoA.nomeParlamentar} - votação ${desc}) salvo no banco")
						} catch (Exception e) {
							log.error("Erro ao tantar salvar Voto (${deputadoA.nomeParlamentar} - votação ${desc}) no banco: ${e.message}")
							e.printStackTrace()
							continue
						}
					}
					// o voto mudou na nova leitura?
					if (voto?.voto!=votoA)
						voto.voto=votoA
				}
			}
			
			def session = sessionFactory?.currentSession
			session?.transaction?.commit()
			session?.transaction?.begin()
			
		} // for de proposições
						
		
		log.debug("Atualização de Votações de Proposições concluída com sucesso")
	}

	

}
