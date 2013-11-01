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
package br.org.prismaCamara.taglibs.postagens

import org.codehaus.groovy.grails.plugins.web.taglib.RenderTagLib

abstract class PostagemTag {

	abstract String getConteudo(attrs)
	
	
	// TODO: VER COMO APLICAR O LANCE DA REFATORAÇÃO DO NÃO USO DE TAGLIBS NESSA SUPERCLASSE
	
	/**
	 * @param template Nome do arquivo de template sempre em /views/postagens/ 
	 * @param attrs
	 * @return
	 */
	protected def getTexto(String template, Map attrs) {
		RenderTagLib r = new RenderTagLib()
		
		String p = r.render(template:template, controller:'postagens', model:attrs).toString()
		
		//String p = render(template:template,model:attrs)
		p
	}
}
