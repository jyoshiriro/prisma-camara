package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.servico.atualizacoes.AtualizarFrequenciaDiaService
import br.org.prismaCamara.servico.limpezas.LimparFrequenciaDiaService


/**
 * Job de atualização de {@link FrequenciaDia}. Executado Diariamente, as 04:30:00
 * @author jyoshiriro
 */
@Log4j
class AtualizarFrequenciaDiaJob {
	
	AtualizarFrequenciaDiaService atualizarFrequenciaDiaService
	LimparFrequenciaDiaService limparFrequenciaDiaService
	
	def concurrent = false
	
    static triggers = {
//      cron name: 'atualizacaoFrequenciasTrigger', cronExpression: "1 * * * * ?"
	  cron name: 'atualizacaoFrequenciasTrigger', cronExpression: "0 30 4 * * ?"
    }

    def execute() {
		try {
			limparFrequenciaDiaService.limpar()
			atualizarFrequenciaDiaService.atualizar()
			log.debug("Atualização Geral dos registros de Frequencias de Deputados concluída com sucesso")
		} catch (Exception e) {
			log.error("Erro ao tentar a Atualização Geral dos registros de Frequencias de Deputados: ${e.message}")
			e.printStackTrace()
		}
    }
}
