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
package br.org.prismaCamara.util.xml;

import java.util.LinkedHashMap;
import java.util.Map;

public class CaracteresUtil {

	static Map<String, String> ms = new LinkedHashMap<String, String>();
	
	static {
		ms.put("Ö","Í");
		ms.put("Ç","Ã");
		ms.put("å","ÇÕ");
//		ms.put("å","Õ");
		ms.put("µ","Á");
		ms.put("¢","ó");
		ms.put("à","Ó");
		ms.put("Æ","ã");
		ms.put("Ò","Ê");
		ms.put("â","Ô");
		ms.put("", "Ç");
		ms.put("", "É");
		ms.put("", "ç");
		ms.put("é", "Ú");
		ms.put("S¶O", "SÃO");
		ms.put("C¶N", "CÂM");
		
	}
	
	static String corrigirEspeciais(String original) {
		StringBuffer sb = new StringBuffer(original);
		for (String chave:ms.keySet()) {
			String novo = sb.toString().replace(chave, ms.get(chave));
			sb = new StringBuffer(novo);
		}
		return sb.toString();
	}

}
