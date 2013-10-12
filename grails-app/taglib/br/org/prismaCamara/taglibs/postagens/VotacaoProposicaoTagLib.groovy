package br.org.prismaCamara.taglibs.postagens



class VotacaoProposicaoTagLib extends PostagemTag {

	/**
	 * Mensagem de Votação e Votos de Proposição.
	 * Renderiza o texto a partir de "_votacao-proposicao-xxx.gsp"
     * @attr prop REQUIRED Instância de {@link Proposicao}
     * @attr tipo REQUIRED Tipo de postagem (facebook ou twitter). Usar um {@link Postagem}.TIPO_XXX
	 */
	String getConteudo(attrs) {
		String mensagem = getTexto("votacao-proposicao-${attrs.tipo}", attrs)
		return mensagem
	}
}
 