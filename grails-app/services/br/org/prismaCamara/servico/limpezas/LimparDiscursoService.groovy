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
import br.org.prismaCamara.modelo.Discurso

/**
 * Classe que exclui os registros defasados de {@link Discurso}. <br>
 * A regra é excluir todas as despesas associadas a deputados cuja "data" seja anterior ao "ultimoDiaDiscurso" do {@link Deputado}. <br>
 * Varre-se todos os Deputados associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
@Log4j
class LimparDiscursoService extends LimpadorEntidade {

	def usuarioService
	
	@Override
	public void limpar() {
		def deputados = usuarioService.deputadosMapeados

		for (deputado in deputados) {
			if (deputado.discursos.empty) 
				continue
			 
			def ultimoDiaDiscurso = deputado.ultimoDiaDiscurso
			if (!ultimoDiaDiscurso) {
				log.debug("O deputado ${deputado.descricao} ainda sem registro de discurso para excluir")
				continue;
			}
			Discurso.executeUpdate("delete from Discurso where deputado=? and data<=?",[deputado,ultimoDiaDiscurso])
			log.debug("Discursos do deputado ${deputado.descricao} excluidas")
		}
		log.debug("Limpezas de Discursos defasados de Deputados concluída com sucesso!")
	}

}
