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
package br.org.prismaCamara.test.unit

import groovy.sql.Sql
import java.nio.charset.Charset;
import java.text.SimpleDateFormat

import org.apache.commons.lang.CharSet;
import org.apache.commons.lang.CharSetUtils;

import br.org.prismaCamara.modelo.Deputado
import br.org.prismaCamara.util.xml.LerXmlCota;

class TesteCharSetXmlCota {

	static main(args) {
		File fxml = new File("C:/Users/Administrador/Downloads/AnoAtual (1).zip")
		LerXmlCota ler = new LerXmlCota()
		def fv = new File("C:/Users/Administrador/Downloads/deputados.txt").text //18 (jordy)
		def deputados = []
		fv.eachLine {
			deputados+=new Deputado(ultimoDiaGasto:(new Date()-665),matricula:it.toInteger())
		}
		ler.getNovasDespesas(fxml.bytes, deputados as Set, "testeX"+new Date().getTime())
	}

}
