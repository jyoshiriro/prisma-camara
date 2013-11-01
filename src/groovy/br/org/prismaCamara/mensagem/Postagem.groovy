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
