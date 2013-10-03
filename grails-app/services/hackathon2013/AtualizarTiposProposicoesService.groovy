package hackathon2013

import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

@Log4j
class AtualizarTiposProposicoesService extends AtualizadorEntidade {

    static String URL='http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarSiglasTipoProposicao'

	/**
	 * Atualizar a tabela de Tipos de Proposicao. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
	 */
	private void atualizar() {
		
		GPathResult xmlr = getXML(URL)
		
		def chavesRecebidas = [] // coleta os Ids recebidos para saber quais deputados não são mais ativos 
		log.debug("${xmlr.childNodes().size()} tipos de proposições chegaram no XML")
		
		xmlr.sigla.each{ tipo->
			
			def tipoSiglaA = tipo.@tipoSigla.toString().trim()
			
			chavesRecebidas+=tipoSiglaA
			
			def atributos = [sigla:tipoSiglaA,descricao:tipo.@descricao.toString(), ativo:tipo.@ativa.toString().toLowerCase().toBoolean(), genero:tipo.@genero.toString()]
			
			TipoProposicao entidade = TipoProposicao.where {sigla==tipoSiglaA && ativo}.find()
			
			if (entidade) { // já existe o registro, atualize os dados
				entidade.properties=atributos
				log.debug("Tipo de proposição ${tipoSiglaA} atualizado")
			} else { // ainda não existe. Persista agora
				entidade = new TipoProposicao(atributos)
				entidade.save()
				log.debug("Tipo de proposição ${tipoSiglaA} salvo no banco")
			}
			
		}
		
		TipoProposicao.executeUpdate("update TipoProposicao set ativo=false where sigla not in (:ids)",[ids:chavesRecebidas])
			
		log.debug("${chavesRecebidas} Tipos de proposição marcados como inativos")
	}
}
