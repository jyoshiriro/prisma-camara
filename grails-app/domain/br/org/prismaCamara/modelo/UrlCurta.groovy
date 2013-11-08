package br.org.prismaCamara.modelo

import grails.plugins.rest.client.ErrorResponse
import grails.plugins.rest.client.RestBuilder

class UrlCurta {
	
	String urlLonga
	String chaveCurta
	
	static String getUrlCurta(String urlLonga) {
		def curta = findByUrlLonga(urlLonga.encodeAsMD5())
		if (curta)
			return "http://goo.gl/${curta.chaveCurta}"

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
		UrlCurta novaUrlCurta = new UrlCurta(urlLonga: urlLonga.encodeAsMD5(), chaveCurta:urlcurta.substring(urlcurta.lastIndexOf('/')+1))
		novaUrlCurta.save() 
		urlcurta
	}

}
