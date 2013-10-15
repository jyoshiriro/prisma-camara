package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.servico.atualizacoes.AtualizarVotacaoService
import br.org.prismaCamara.servico.limpezas.LimparVotacaoService

/**
 * Job de atualização de {@link Votacao} de {@link Proposicao}. Executado diariamente, as 03:00:00
 * @author jyoshiriro
 *
 */
@Log4j
class AtualizarVotacaoJob {
	
	AtualizarVotacaoService atualizarVotacaoService
	LimparVotacaoService limparVotacaoService
	
	def concurrent = false
	
    static triggers = {
	  cron name: 'atualizacaoFrequenciasTrigger', cronExpression: "0 0 3 * * ?"
//      cron name: 'atualizacaoDiscursosTrigger', cronExpression: "1 * * * * ?"
    }

    def execute() {
		try {
			limparVotacaoService.limpar()
			atualizarVotacaoService.atualizar()
			log.debug("Atualização Geral dos registros de Votações de Proposições concluída com sucesso")
		} catch (Exception e) {
			log.error("Erro ao tentar a Atualização Geral dos registros de Votações de Proposições: ${e.message}")
			e.printStackTrace()
		}
    }
}
