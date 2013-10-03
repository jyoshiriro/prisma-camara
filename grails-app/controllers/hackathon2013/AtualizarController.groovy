package hackathon2013

import groovy.util.logging.Log4j

@Log4j
class AtualizarController {
	
	def atualizarDeputadosService
	def atualizarTiposProposicoesService
	def atualizarProposicoesService

    def index() {
		
	}
	
	def executar() {
		try {
			this."atualizar${params.id}Service".atualizar()
			flash.message="Cadastro de ${params.id} atualizado com Sucesso"
		} catch (Exception e) {
			e.printStackTrace()
			flash.error="Erro: ${e.message}"
		}
		redirect(action:'index')
	}
		
}
