package hackathon2013

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

@Log4j
class AtualizarController {
	
	RestBuilder rest = new RestBuilder()
	
	def atualizarDeputadosService

    def index() {
		
	}
	
	def deputados() {
		try {
			atualizarDeputadosService.atualizar()
			flash.message="Cadastro de Deputados atualizado com Sucesso"
		} catch (Exception e) {
			e.printStackTrace()
			flash.error="Erro: ${e.message}"
		}
		redirect(action:'index')
	}
		
}
