package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.servico.atualizacoes.AtualizarDespesaService
import br.org.prismaCamara.servico.limpezas.LimparDespesaService

/**
 * Job de atualização de {@link Despesa}. Executado diariamente, as 01:30:00
 * @author jyoshiriro
 *
 */
@Log4j
class AtualizarDespesaJob {
	
	AtualizarDespesaService atualizarDespesaService
	LimparDespesaService limparDespesaService
	
	def concurrent = false
	
    static triggers = {
	  cron name: 'atualizacaoDespesasTrigger', cronExpression: "0 30 1 * * ?"
//      cron name: 'atualizacaoDespesasTrigger', cronExpression: "1 * * * * ?"
    }

    def execute() {
		try {
			limparDespesaService.limpar()
			atualizarDespesaService.atualizar()
			log.debug("Atualização Geral dos registros de Despesas de Deputados concluída com sucesso")
		} catch (Exception e) {
			log.error("Erro ao tentar a Atualização Geral dos registros de Despesas de Deputados: ${e.message}")
			e.printStackTrace()
		}
    }
}
