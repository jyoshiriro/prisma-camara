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
package br.org.prismaCamara.util.xml

import groovy.util.logging.Log4j;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Despesa;

@Log4j
class TesteCota {

	static main(args) {
		LerXmlCota l = new LerXmlCota();
		List deputados = new  ArrayList();
		Deputado d1 = new Deputado();
		d1.setMatricula(256);
		d1.setUltimoDiaGasto(new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2013"));
		Deputado d2 = new Deputado();
		d1.setMatricula(522);
		d1.setUltimoDiaGasto(new SimpleDateFormat("dd/MM/yyyy").parse("01/09/2013"));
		deputados.add(d1);
		deputados.add(d2);
		
		List<Map> novasDespesas = l.getNovasDespesas(FileUtils.readFileToByteArray(new File("C:/Users/Administrador/Documents/yoshi/partiubrasilia/workspace/prisma-camara/testeCotaTudo.xml")), new HashSet<Deputado>(deputados));
		
		for (mapDespesa in novasDespesas) {
			try {
				Despesa despesa = new Despesa(mapDespesa)
				println("Despesa ${despesa} salva no banco")
			} catch (Exception e) {
				println("Erro ao tentar salvar novo registro de despesa: ${e.message}")
				e.printStackTrace()
			}
		}
	}

}
