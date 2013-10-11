package br.org.prismaCamara.taglibs.postagens

abstract class PostagemTag {

	abstract String getConteudo(attrs)
	
	/**
	 * @param template Nome do arquivo de template sempre em /views/postagens/ 
	 * @param attrs
	 * @return
	 */
	protected def getTexto(String template, Map attrs) {
		String p = render(template:template,model:attrs)
		p
	}
}
