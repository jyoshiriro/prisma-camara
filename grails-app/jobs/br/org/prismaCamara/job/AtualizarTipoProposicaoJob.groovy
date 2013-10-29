package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.TipoProposicao;
import br.org.prismaCamara.servico.atualizacoes.AtualizarTipoProposicaoService

/**
 * Job de atualização de cadastro de {@link TipoProposicao}. Executado Diariamente, as 01:00:00
 * @author jyoshiriro
 */
@Log4j
class AtualizarTipoProposicaoJob {

	AtualizarTipoProposicaoService atualizarTipoProposicaoService
	
	def concurrent = false
	
	static triggers = {
//      cron name: 'atualizacaoDeputadosTrigger', cronExpression: "1 * * * * ?"
	  cron name: 'atualizacaoDeputadosTrigger', cronExpression: "0 0 1 * * ?"
	}

	def execute() {
		try {
			atualizarTipoProposicaoService.atualizar()
			log.debug("Atualização Geral dos registros de Tipos de Proposição concluída com sucesso")
		} catch (Exception e) {
			log.error("Erro ao tentar a Atualização Geral dos registros de Cadastro de Tipos de Proposição: ${e.message}")
			e.printStackTrace()
		}
	}
}
