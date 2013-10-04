package hackathon2013

import groovy.util.logging.Log4j

@Log4j
class AtualizarController {

	def atualizarDeputadoService
	def atualizarTipoProposicaoService
	def atualizarProposicaoService
	def atualizarVotacaoService

	def index() {

	}

	def executar(String id) {
		try {
			this."atualizar${id.capitalize()}Service".atualizar()
			def entidadeM = message(code:"${id}.label")
			flash.message="Cadastro de ${entidadeM} atualizado com Sucesso"
		} catch (Exception e) {
			e.printStackTrace()
			flash.error="Erro: ${e.message}"
		}
		redirect(action:'index')
	}

}
