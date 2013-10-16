package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.servico.atualizacoes.AtualizarDeputadoService

/**
 * Job de atualização de cadastro de {@link Deputado}. Executado Diariamente, as 01:00:00
 * @author jyoshiriro
 */
@Log4j
class AtualizarDeputadoJob {

	AtualizarDeputadoService atualizarDeputadoService
	
	def concurrent = false
	
	static triggers = {
//      cron name: 'atualizacaoDeputadosTrigger', cronExpression: "1 * * * * ?"
	  cron name: 'atualizacaoDeputadosTrigger', cronExpression: "0 0 1 * * ?"
	}

	def execute() {
		try {
			atualizarDeputadoService.atualizar()
			log.debug("Atualização Geral dos registros de Cadastro de Deputados concluída com sucesso")
		} catch (Exception e) {
			log.error("Erro ao tentar a Atualização Geral dos registros de Cadastro de Deputados: ${e.message}")
			e.printStackTrace()
		}
	}
}
