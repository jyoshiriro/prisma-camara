package br.org.prismaCamara.controle

import groovy.util.logging.Log4j

import org.hibernate.TransactionException
import org.springframework.transaction.TransactionSystemException

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Proposicao;
import br.org.prismaCamara.modelo.TipoProposicao;
import br.org.prismaCamara.servicos.atualizacoes.AtualizarDiscursoService;

@Log4j
class AtualizarController {

	def atualizarDeputadoService
	def atualizarTipoProposicaoService
	def atualizarProposicaoService
	def atualizarVotacaoService
	def atualizarFrequenciaDiaService
	def atualizarDespesaService
	def atualizarDiscursoService
	
	def limparDespesaService
	def limparFrequenciaDiaService
	def limparVotacaoService
	def limparDiscursoService

	def getMapaAux() {
		[deputadosA:Deputado.countByAtivo(true),
			deputadosI:Deputado.countByAtivo(false),
			tiposProp:TipoProposicao.countByAtivo(true),
			proposicoes:Proposicao.countBySituacaoNot('Arquivada'),
			proximaFrequencia:(new Date()-3)]
	}
	
	def index() {
		return mapaAux
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
		} catch(TransactionException et) {
			
		} catch(TransactionSystemException et) {
			
		} catch (Exception e) {
			e.printStackTrace()
			flash.error="Erro: ${e.message}"
		}
		render(view:'index', model:mapaAux)
	}

	
	def limpar() {
		try {
			String id = params.id
			def nomeServico = "limpar${id.capitalize()}Service"
			this."${nomeServico}".limpar()
			def entidadeM = message(code:"${id}.label")
			flash.message="Cadastro de ${entidadeM} LIMPO com Sucesso"
			
		} catch(TransactionException et) {
			
		} catch(TransactionSystemException et) {
			
		} catch (Exception e) {
			e.printStackTrace()
			flash.error="Erro: ${e.message}"
		}
		render(view:'index', model:mapaAux)
	}
}
