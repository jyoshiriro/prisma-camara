package br.org.prismaCamara.mensagem

import br.org.prismaCamara.modelo.Deputado;

abstract class Postagem {

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
