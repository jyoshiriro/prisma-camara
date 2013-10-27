package br.org.prismaCamara.controle

import groovy.util.logging.Log4j

import org.hibernate.TransactionException
import org.springframework.transaction.TransactionSystemException

import br.org.prismaCamara.job.AtualizarDespesaJob;
import br.org.prismaCamara.job.AtualizarDiscursoJob;
import br.org.prismaCamara.job.AtualizarFrequenciaDiaJob;
import br.org.prismaCamara.job.AtualizarVotacaoJob;
import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Proposicao;
import br.org.prismaCamara.modelo.TipoProposicao;
import br.org.prismaCamara.servico.atualizacoes.AtualizarDiscursoService;

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
	
	AtualizarFrequenciaDiaJob atualizarFrequenciaDiaJob
	AtualizarDiscursoJob atualizarDiscursoJob
	AtualizarDespesaJob atualizarDespesaJob
	AtualizarVotacaoJob atualizarVotacaoJob

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
	
	def geral() {
		try {
			iniciarJobs()
			String id = params.id
			def nomeJob = "atualizar${id.capitalize()}Job"
			this."${nomeJob}".execute()
			def entidadeM = message(code:"${id}.label")
			flash.message="Cadastro de ${entidadeM} LIMPO E ATUALIZADO com Sucesso"
					
		} catch(TransactionException et) {
			
		} catch(TransactionSystemException et) {
			
		} catch (Exception e) {
			e.printStackTrace()
			flash.error="Erro: ${e.message}"
		}
		render(view:'index', model:mapaAux)
	}
	
	def iniciarJobs() {
		atualizarFrequenciaDiaJob = new AtualizarFrequenciaDiaJob(atualizarFrequenciaDiaService: atualizarFrequenciaDiaService, limparFrequenciaDiaService:limparFrequenciaDiaService)
		atualizarDiscursoJob = new AtualizarDiscursoJob(atualizarDiscursoService: atualizarDiscursoService, limparDiscursoService: limparDiscursoService)
		atualizarDespesaJob = new AtualizarDespesaJob(atualizarDespesaService: atualizarDespesaService, limparDespesaService: limparDespesaService)
		atualizarVotacaoJob = new AtualizarVotacaoJob(atualizarVotacaoService: atualizarVotacaoService, limparVotacaoService: limparVotacaoService)
	}
	
	def atualizarIndice() {
		log.debug "Reindexando Deputado."
		Proposicao.reindex()
		Deputado.reindex()		
		log.debug "Reindexação concluida."
	}
}
