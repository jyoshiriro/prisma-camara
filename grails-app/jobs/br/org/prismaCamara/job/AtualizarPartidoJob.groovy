package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.servico.atualizacoes.AtualizarPartidoService

/**
 * Job de atualização de cadastro de {@link Partido}. Executado Diariamente, as 01:00:00
 * @author jyoshiriro
 */
@Log4j
class AtualizarPartidoJob {

	AtualizarPartidoService atualizaPartidoService
	
	def concurrent = false
	
	static triggers = {
//      cron name: 'atualizacaoDeputadosTrigger', cronExpression: "1 * * * * ?"
	  cron name: 'atualizacaoPartidosTrigger', cronExpression: "0 0 1 * * ?"
	}

	def execute() {
		try {
			atualizaPartidoService.atualizar()
			log.debug("Atualização Geral dos registros de Partidos concluída com sucesso")
		} catch (Exception e) {
			log.error("Erro ao tentar a Atualização Geral dos registros de Cadastro de Partidos: ${e.message}")
			e.printStackTrace()
		}
	}
}
