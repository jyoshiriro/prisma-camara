package hackathon2013

import groovy.text.SimpleTemplateEngine
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

@Log4j
class AtualizarVotacoesProposicoesService extends AtualizadorEntidade {

	@Override
	public String getSiglaDeParametro() {
		// "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ObterVotacaoProposicao?tipo=${tipo}&numero=${numero}&ano=${ano}"
		return 'url_votacoes_proposicoes';
	}

	/**
	 * Atualizar as tabela de Voto, Votacao e OrientacaoBancada
	 */
	private void atualizar() {

		def proposicoes = Proposicao.where{ano=='2007' && numero==1992}.list(max:20)
		l1:for (proposicaoA in proposicoes) {
			
			def pTipo = proposicaoA.tipoProposicao.sigla
			def pNumero = proposicaoA.numero
			def pAno = proposicaoA.ano
				
			def urlT = getUrlAtualizacao([tipo:pTipo,numero:pNumero.toString(),ano:pAno])
			GPathResult xmlr = null
			try {
				xmlr = getXML(urlT)
			} catch (Exception e) {
//					log.error("A url ${urlT} não retornou XML válido: ${e.message}")
				println("A url ${urlT} não retornou XML válido: ${e.message}")
				continue;
			}
			log.debug("${xmlr.childNodes().size()} proposições chegaram no XML")
			
			xmlr.Votacoes.Votacao.each{ vot->
				
				def dataHotaS = "${vot.@Data} ${vot.@Hora}"  
				def dataHoraA = Date.parse('d/M/yyyy HH:mm',dataHotaS)
				def atributos = [resumo:vot.@Resumo.toString(), dataHoraVotacao:dataHoraA, objVotacao:vot.@ObjVotacao.toString()]
				atributos+=[proposicao:proposicaoA]
				
				Votacao entidade = Votacao.where {proposicao==proposicaoA && dataHoraVotacao==dataHoraA}.find()
				
				// TODO: alimentar Voto e OrientacaoBancada
				
				def desc = "${pTipo} ${pNumero}-${pAno}"
				
				if (entidade) { // já existe o registro, atualize os dados
					entidade.properties=atributos
					log.debug("Votação de proposição ${desc} atualizado")
				} else { // ainda não existe. Persista agora
					entidade = new Votacao(atributos)
					entidade.save()
					
					if (entidade.errors.errorCount>0) {
						log.error("Votações da Proposição ${desc} NÃO foram salvas devido a erros: ${entidade.errors}")
					} else {
						log.debug("Votações da Proposição ${desc} salvas no banco")
					}
				}
				
			}
		} // for de proposicoes
		
	}

	

}
