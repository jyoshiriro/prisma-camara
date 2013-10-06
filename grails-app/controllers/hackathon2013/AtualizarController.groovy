package hackathon2013

import groovy.util.logging.Log4j

@Log4j
class AtualizarController {

	def atualizarDeputadoService
	def atualizarTipoProposicaoService
	def atualizarProposicaoService
	def atualizarVotacaoService
	def atualizarFrequenciaDiaService
	def atualizarDespesaService

	def index() {
		[deputadosA:Deputado.countByAtivo(true),
		 deputadosI:Deputado.countByAtivo(false),
		 tiposProp:TipoProposicao.countByAtivo(true),
		 proposicoes:Proposicao.countBySituacaoNot('Arquivada'),
		 proximaFrequencia:(new Date()-3)]
	}
	
	def vai() {
		Proposicao p = Proposicao.get(5473)
		p.delete()
		render('foi!')
	}

	def executar() {
		try {
			String id = params.id
			def nomeServico = "atualizar${id.capitalize()}Service"
			this."${nomeServico}".atualizar()
			def entidadeM = message(code:"${id}.label")
			flash.message="Cadastro de ${entidadeM} atualizado com Sucesso"
		} catch (Exception e) {
			e.printStackTrace()
			flash.error="Erro: ${e.message}"
		}
		redirect(action:'index')
		
	}

}
