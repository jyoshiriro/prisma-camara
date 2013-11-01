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
package br.org.prismaCamara.servico.limpezas

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.FrequenciaDia
import br.org.prismaCamara.modelo.Parametro

/**
 * Classe que exclui os registros defasados de {@link FrequenciaDia} e {@link FrequenciaSessao}. <br>
 * A regra é excluir todas as frequências associadas a deputados cujo "dia" seja anterior ao "ultimo_dia_frequencia" na tabela de parâmetros ({@link Parametro}). <br>
 * Varre-se todos os Deputados associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
@Log4j
class LimparFrequenciaDiaService extends LimpadorEntidade {

	@Override
	public void limpar() {
		
		def ultimoDiaFrequenciaS = Parametro.findBySigla('ultimo_dia_frequencia')?.valor
		
		if (ultimoDiaFrequenciaS) {
			def ultimoDiaFrequencia = Date.parse('dd/MM/yyyy', ultimoDiaFrequenciaS)
			
			for (freq in FrequenciaDia.executeQuery("from FrequenciaDia where dia<=?",[ultimoDiaFrequencia])) {
				freq.delete()
				log.debug("Frequencia ${freq.id} excluida")
			}
		}
		
		log.debug("Limpezas de Frequencias de Deputados defasadas concluída com sucesso!")
	}

}
