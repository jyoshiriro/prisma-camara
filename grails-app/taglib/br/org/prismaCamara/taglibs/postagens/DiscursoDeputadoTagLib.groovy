package br.org.prismaCamara.taglibs.postagens


class DiscursoDeputadoTagLib extends PostagemTag {

	/**
	 * Mensagem de Discursos de Deputado.
	 * Renderiza o texto a partir de "_discurso-deputado-xxx.gsp"
     * @attr dep REQUIRED Instância de {@link Deputado}
     * @attr tipo REQUIRED Tipo de postagem (facebook ou twitter). Usar um {@link Postagem}.TIPO_XXX
	 */
	String getConteudo(attrs) {
		String mensagem = getTexto("discurso-deputado-${attrs.tipo}", attrs)
		return mensagem
	}
}
 