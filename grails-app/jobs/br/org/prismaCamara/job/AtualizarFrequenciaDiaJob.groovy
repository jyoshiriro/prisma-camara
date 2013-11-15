/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
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
		// * horário de brasília+3
		cron name: 'atualizacaoFrequenciasTrigger', cronExpression: "0 30 7 * * ?"
//      cron name: 'atualizacaoFrequenciasTrigger', cronExpression: "1 * * * * ?"
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
