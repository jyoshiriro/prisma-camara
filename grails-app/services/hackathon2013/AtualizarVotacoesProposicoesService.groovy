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

		def tipos = TipoProposicao.list().collect{it.sigla} // ['PL','PEC']
		def anos = [2012,2013] // Proposicao.PRIMEIRO_ANO..(new Date().calendarDate.year)
		
		def proposicoes = Proposicao.where{ano=='2012'}.list(max:700)
		
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
			
			xmlr.Votacoes.each{ vot->
				
				def dataHotaS = "${vot.votacao.@Data} ${vot.votacao.@Hora}"  
				def atributos = [resumo:vot.votacao.@Resumo, dataHoraVotacao:Date.parse('d/M/yyyy',dataHotaS), objVotacao:vot.votacao.@ObjVotacao]
				
				Votacao entidade = Votacao.where {proposicao==proposicaoA}.find()
				
				// TODO: alimentar Voto e OrientacaoBancada
				if (entidade) { // já existe o registro, atualize os dados
					entidade.properties=atributos
					log.debug("Tipo de proposição ${idA} atualizado")
				} else { // ainda não existe. Persista agora
					entidade = new Votacao(atributos)
					entidade.save()
					
					if (entidade.errors.errorCount>0) {
						log.error("Votações da Proposição ${idA} NÃO foram salvas devido a erros: ${entidade.errors}")
					} else {
						log.debug("Votações da Proposição ${idA} salvas no banco")
					}
				}
				
			}
		} // for de proposicoes
		
	}

	

}
