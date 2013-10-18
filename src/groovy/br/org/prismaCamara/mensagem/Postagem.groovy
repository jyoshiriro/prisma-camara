package br.org.prismaCamara.mensagem

import grails.gsp.PageRenderer
import groovy.util.logging.Log4j

@Log4j
abstract class Postagem {
	
	PageRenderer r

	static final String TIPO_FACE = "facebook";
	static final String TIPO_TWITTER = "twitter";
	static final String TIPO_GPLUS = "gplus";
	static final String TIPO_LINKEDIN = "linkedin";
	
	static final List TIPOS=[TIPO_FACE,TIPO_TWITTER]
	
	/**
	 * Gera e retorna o texto da postagem conforme os parâmetros informados
	 * @param params
	 * @return
	 */
	abstract String getTexto(Map params)

}
