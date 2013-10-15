package br.org.prismaCamara.controle

/**
 * Download de documentos para o usuário. Ex: 'processo-legislativo', sempre na pasta 'web-app/docs'
 * @author yoshiriro
 *
 */
class DocsController {
	
    def index() {
		redirect(uri:"/docs/${params.id}")
	}
}
