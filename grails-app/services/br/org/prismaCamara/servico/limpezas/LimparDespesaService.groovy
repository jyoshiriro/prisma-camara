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
package br.org.prismaCamara.servico.limpezas

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Despesa

/**
 * Classe que exclui os registros defasados de {@link Despesa}. <br>
 * A regra é excluir todas as despesas associadas a deputados cuja "dataEmissao" seja anterior ao "ultimoDiaGasto" do {@link Deputado}. <br>
 * Varre-se todos os Deputados associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
@Log4j
class LimparDespesaService extends LimpadorEntidade {

	def usuarioService
	
	@Override
	public void limpar() {
		def deputados = usuarioService.deputadosMapeados

		for (deputado in deputados) {
			if (deputado.despesas.empty) 
				continue
			 
			def ultimoDiaGasto = deputado.ultimoDiaGasto
			if (!ultimoDiaGasto) {
				log.debug("O deputado ${deputado.descricao} ainda não possui registro de despesa para excluir")
				continue;
			}
			Despesa.executeUpdate("delete from Despesa where deputado=? and dataEmissao<=?",[deputado,ultimoDiaGasto])
			log.debug("Despesas do deputado ${deputado.descricao} excluidas")
		}
		log.debug("Limpezas de Despesas defasadas de Deputados concluída com sucesso!")
	}

}
