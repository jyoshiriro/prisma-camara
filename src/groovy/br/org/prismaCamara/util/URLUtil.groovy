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
package br.org.prismaCamara.util

import grails.plugins.rest.client.ErrorResponse
import grails.plugins.rest.client.RestBuilder
import br.org.prismaCamara.modelo.Parametro

/**
 * Classe para tratamento de URL (principalmente para encurtá-las)
 * @author jyoshiriro
 */
class URLUtil {

	static Map cache = [:]
	
	static String getUrlCurta(String urlLonga) {
		def curta = cache.get(urlLonga.encodeAsMD5())
		if (curta)
			return "http://goo.gl/${curta}"

		def resp = new RestBuilder().post(Parametro.findBySigla('url_shorturl').valor){
			contentType "application/json"
			accept "application/json"
			json longUrl: "${urlLonga}"
		}
		if (resp instanceof ErrorResponse) {
			System.err.println("Erro ao tentar obter a URL curta de ${urlLonga}: ${resp.text}")
			return urlLonga
		}
		def urlcurta = resp.json.id
		cache.put(urlLonga.encodeAsMD5(),urlcurta.substring(urlcurta.lastIndexOf('/')+1)) 
		urlcurta
	}
	
}
