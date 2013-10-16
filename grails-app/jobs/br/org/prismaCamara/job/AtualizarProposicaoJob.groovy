package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.servico.atualizacoes.AtualizarProposicaoService

/**
 * Job de atualização de cadastro de {@link Proposicao}. Executado todo Domingo, as 04:00:00
 * Essa atualização ocorre somente aos domingos de madrugada pois tende a ser a mais demorada, 
 * podendo fazer centenas de milhares de requisições, de acordo com a quantidade de proposições associadas a usuários
 * @author jyoshiriro
 */
@Log4j
class AtualizarProposicaoJob {

	AtualizarProposicaoService atualizarProposicaoService
	
	def concurrent = false
	
	static triggers = {
      cron name: 'atualizacaoDespesasTrigger', cronExpression: "0 0 4 ? * Sun"
//	  cron name: 'atualizacaoDeputadosTrigger', cronExpression: "59 0 0 * * ?"
	}

	def execute() {
		try {
			atualizarProposicaoService.atualizar()
			log.debug("Atualização Geral dos registros de Cadastro de Proposições concluída com sucesso")
		} catch (Exception e) {
			log.error("Erro ao tentar a Atualização Geral dos registros de Cadastro de Proposições: ${e.message}")
			e.printStackTrace()
		}
	}
}
