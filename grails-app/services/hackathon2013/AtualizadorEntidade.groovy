package hackathon2013

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.util.slurpersupport.GPathResult

abstract class AtualizadorEntidade {
	
	RestBuilder rest = new RestBuilder()

	protected GPathResult getXML(String url) {
		getXML(url, null)
	}
	
	protected GPathResult getXML(String url, Map parametros) {
		def respostaTmp = rest.get(url)
		if (respostaTmp instanceof RestResponse) {
			RestResponse resposta = respostaTmp as RestResponse
			return resposta.xml
		} else {
			def msg = "Não foi possível recuperar o XML da resposta para ${url}: ${respostaTmp.text}"
			log.error(msg)
			throw new Exception(msg)
		}
	}
}
