/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
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
