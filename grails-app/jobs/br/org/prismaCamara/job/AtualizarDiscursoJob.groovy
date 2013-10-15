package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Discurso;
import br.org.prismaCamara.servico.atualizacoes.AtualizarDiscursoService
import br.org.prismaCamara.servico.limpezas.LimparDiscursoService


/**
 * Job de atualização de {@link Discurso}. Executado diariamente, as 06:30:00
 * @author jyoshiriro
 *
 */
@Log4j
class AtualizarDiscursoJob {
	
	AtualizarDiscursoService atualizarDiscursoService
	LimparDiscursoService limparDiscursoService
	
	def concurrent = false
	
    static triggers = {
	  cron name: 'atualizacaoFrequenciasTrigger', cronExpression: "0 30 6 * * ?"
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
