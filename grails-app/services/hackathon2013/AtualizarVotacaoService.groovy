package hackathon2013

import groovy.text.SimpleTemplateEngine
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

@Log4j
class AtualizarVotacaoService extends AtualizadorEntidade {

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

		def proposicoes = []
/*		for (usuario in Usuario.list()) {
			proposicoes.addAll(usuario.proposicoes)
		}
*///		proposicoes = Proposicao.findAllByNumero(1992)
		proposicoes = Proposicao.list(max:20,offset:130)
		log.debug("Um total de ${proposicoes.size()} terão votos verificados")
		
		for (proposicaoA in proposicoes) {
			
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
			
			for (vot in xmlr.Votacoes.Votacao) { 
				
				def dataHotaS = "${vot.@Data} ${vot.@Hora}"  
				def dataHoraA = Date.parse('d/M/yyyy HH:mm',dataHotaS)
				
				def atributos = [resumo:vot.@Resumo.toString(), dataHoraVotacao:dataHoraA, objVotacao:vot.@ObjVotacao.toString()]
				atributos+=[proposicao:proposicaoA]
				
				Votacao entidade = Votacao.where {proposicao==proposicaoA && dataHoraVotacao==dataHoraA}.find()
				
				// TODO: alimentar Voto e OrientacaoBancada
				
				
				if (entidade) { // já existe o registro, atualize os dados
					entidade.properties=atributos
					log.debug("Votação de proposição ${i+1} da ${desc} e suas Orientações e Votos possivelmente atualizados")
				} else { // ainda não existe. Persista agora
					entidade = new Votacao(atributos)
					entidade.save()
					
					if (entidade.errors.errorCount>0) {
						log.error("Votações da Proposição ${desc} NÃO foram salvas devido a erros: ${entidade.errors}")
						continue
					} else {
						log.debug("Novas Votações da Proposição ${desc} e suas Orientações e Votos salvas no banco")
					}
				}
				
				def orientacoesBancada = []
				def votos = []
				
				// Orientações de bancadas
				for (ob in vot.childNodes()[0].childNodes()) { 
					
					def siglaA=ob.attributes.Sigla.trim()
					def orientacaoA=ob.attributes.orientacao.trim()
					OrientacaoBancada oBancada = OrientacaoBancada.where{votacao==entidade && sigla==siglaA}.find()
					if (!oBancada) {
						oBancada = new OrientacaoBancada(sigla: siglaA, orientacao: orientacaoA, votacao: entidade)
						try {
							oBancada.save()
							log.debug("Orientação de bancada (${siglaA} - votação ${desc}) salva no banco")
						} catch (Exception e) {
							log.error("Erro ao tantar salvar Orientação de bancada (${siglaA} - votação ${desc}) no banco: ${e.message}")
							e.printStackTrace()
							continue
						}
					} 
					// o voto mudou na nova leitura?
					if (oBancada?.orientacao!=orientacaoA)
						oBancada.orientacao=orientacaoA
						
					orientacoesBancada+=oBancada
				}
				
				// Votos dos deputados
				for (ob in vot.childNodes()[1].childNodes()) {
				
					def nomeA=ob.attributes.Nome.trim().toUpperCase()
					def partidoA=ob.attributes.Partido.trim()
					def ufA=ob.attributes.UF.trim()
					def votoA=ob.attributes.Voto.trim()
					
					// SE não for econtrado o Deputado, salve no banco como "ativo=false"
					Deputado deputadoA = Deputado.where {nomeParlamentar==nomeA && partido.sigla==partidoA && uf==ufA}.find()
					if (!deputadoA) {
						deputadoA = new Deputado(nome:nomeA,nomeParlamentar: nomeA, siglaPartido:partidoA, uf:ufA, ativo:false)
						deputadoA.save()
						log.debug("Deputado ${nomeA}(${partidoA}/${ufA}) não existia na base. Salvo como 'inativo'")
					}
					
					Voto voto = Voto.where{votacao==entidade && deputado==deputadoA}.find()
					if (!voto) {
						voto = new Voto(deputado:deputadoA, votacao: entidade, voto:votoA)
						try {
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
						
					votos+=voto
				}
				
				// caso a lista que chegue tenha menos elementos que os votos anteriormente registrados
				// - orientações
				entidade.orientacoesBancada.findAll{!orientacoesBancada.contains(it)}.each { obExcluir ->
					try {
						obExcluir.delete()
						log.debug("Orientação de bancada (${obExcluir.sigla} - votação ${desc}) removida no banco")
					} catch (Exception e) {
						log.debug("Erro ao excluir Orientação de bancada (${obExcluir.sigla} - votação ${desc}) do banco: ${e.message}")
						e.printStackTrace()
					}
				}
				// - votos
				entidade.votos.findAll{!votos.contains(it)}.each { votoExcluir ->
				try {
					votoExcluir.delete()
					log.debug("Voto (${votoExcluir.id} - votação ${desc}) removido no banco")
				} catch (Exception e) {
					log.debug("Erro ao excluir Voto (${votoExcluir.id} - votação ${desc}) do banco: ${e.message}")
					e.printStackTrace()
				}
				}

				
			}
		} // for de proposicoes
		
		log.debug("Atualização de Votações de Proposições concluída com sucesso")
	}

	

}
