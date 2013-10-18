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
