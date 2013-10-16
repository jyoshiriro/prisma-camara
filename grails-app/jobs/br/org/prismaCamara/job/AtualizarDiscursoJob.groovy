package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.servico.atualizacoes.AtualizarDiscursoService
import br.org.prismaCamara.servico.limpezas.LimparDiscursoService


/**
 * Job de atualização de {@link Discurso}. Executado diariamente, as 05:00:00
 * @author jyoshiriro
 *
 */
@Log4j
class AtualizarDiscursoJob {
	
	AtualizarDiscursoService atualizarDiscursoService
	LimparDiscursoService limparDiscursoService
	
	def concurrent = false
	
    static triggers = {
	  cron name: 'atualizacaoDiscursosTrigger', cronExpression: "0 0 5 * * ?"
//      cron name: 'atualizacaoDiscursosTrigger', cronExpression: "1 * * * * ?"
    }

    def execute() {
		try {
			limparDiscursoService.limpar()
			atualizarDiscursoService.atualizar()
			log.debug("Atualização Geral dos registros de Discursos de Deputados concluída com sucesso")
		} catch (Exception e) {
			log.error("Erro ao tentar a Atualização Geral dos registros de Discursos de Deputados: ${e.message}")
			e.printStackTrace()
		}
    }
}
