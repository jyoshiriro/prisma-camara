package hackathon2013

import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult


/**
 * Atualizar a tabela de Tipos de Proposicao. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
 */
@Log4j
class AtualizarTipoProposicaoService extends AtualizadorEntidade {

	@Override
	public String getSiglaDeParametro() {
		// 'http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarSiglasTipoProposicao'
		return 'url_listagem_tipos_proposicoes';
	}
	
	def atualizar() {
		
		GPathResult xmlr = getXML(getUrlAtualizacao(null))
		
		def chavesRecebidas = [] // coleta os Ids recebidos para saber quais deputados não são mais ativos 
		log.debug("${xmlr.childNodes().size()} tipos de proposições chegaram no XML")
		
		xmlr.sigla.each{ tipo->
			
			def tipoSiglaA = tipo.@tipoSigla.toString().trim()
			
			chavesRecebidas+=tipoSiglaA
			
			def atributos = [sigla:tipoSiglaA,descricao:tipo.@descricao.toString(), ativo:tipo.@ativa.toString().toLowerCase().toBoolean(), genero:tipo.@genero.toString()]
			
			TipoProposicao entidade = TipoProposicao.where {sigla==tipoSiglaA && ativo}.find()
			
			if (entidade) { // já existe o registro, atualize os dados
				entidade.properties=atributos
				log.debug("Tipo de proposição ${tipoSiglaA} possivelmente atualizado")
			} else { // ainda não existe. Persista agora
				entidade = new TipoProposicao(atributos)
				entidade.save()
				log.debug("Tipo de proposição ${tipoSiglaA} salvo no banco")
			}
			
		}
		
		int inativos = TipoProposicao.executeUpdate("update TipoProposicao set ativo=false where sigla not in (:ids)",[ids:chavesRecebidas])
		
		if (inativos)	
			log.debug("${chavesRecebidas} Tipos de proposição marcados como inativos")
	}
}
