package br.org.prismaCamara.util

import grails.plugins.rest.client.ErrorResponse
import grails.plugins.rest.client.RestBuilder
import br.org.prismaCamara.modelo.Parametro

/**
 * Classe para tratamento de URL (principalmente para encurtá-las)
 * @author jyoshiriro
 */
class URLUtil {
	// TODO: verificar se precisa disso depois
	static Map cache = [:]
	
	static String getUrlCurta(String urlLonga) {
		def curta = cache.get(urlLonga)
		if (curta)
			return curta

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
		// cache.put(urlLonga,urlcurta) TODO: lá de cima
		urlcurta
	}
}
