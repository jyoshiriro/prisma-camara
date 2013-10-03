package hackathon2013

import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

@Log4j
class AtualizarDeputadosService extends AtualizadorEntidade {
	
	@Override
	public String getSiglaDeParametro() {
		// 'http://www.camara.gov.br/SitCamaraWS/Deputados.asmx/ObterDeputados'
		return 'url_listagem_deputados';
	}
	
	/**
	 * Atualizar a tabela de Deputados. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
	 */
	private void atualizar() {
		
		GPathResult xmlr = getXML(getUrlAtualizacao(null))
		
		def chavesRecebidos = [] // coleta os Ids recebidos para saber quais deputados não são mais ativos 
		log.debug("${xmlr.childNodes().size()} deputados chegaram no XML")
		xmlr.deputado.each{ dep->
			
			def ideCadastroA = dep.ideCadastro.toInteger()
			chavesRecebidos+=ideCadastroA
			
			def atributos = [ideCadastro: ideCadastroA, condicao: dep.condicao.toString(), matricula: dep.matricula.toInteger(), nome: dep.nome.toString(), nomeParlamentar: dep.nomeParlamentar.toString(),  sexo: dep.sexo.toString(), uf:dep.uf.toString(), partido: dep.partido.toString(), fone: dep.fone.toString(), email:dep.email.toString(), ativo:true]
			
			Deputado entidade = Deputado.where {ideCadastro==ideCadastroA && ativo}.find()
			
			if (entidade) { // já existe o registro, atualize os dados
				entidade.properties=atributos
				log.debug("Deputado ${ideCadastroA} atualizado")
			} else { // ainda não existe. Persista agora
				entidade = new Deputado(atributos)
				entidade.save()
				log.debug("Deputado ${ideCadastroA} salvo no banco")
			}
			
		}
		
		Deputado.executeUpdate("update Deputado set ativo=false where ideCadastro not in (:ids)",[ids:chavesRecebidos])
			
		log.debug("${chavesRecebidos} deputados marcados como inativos")
	}
}
