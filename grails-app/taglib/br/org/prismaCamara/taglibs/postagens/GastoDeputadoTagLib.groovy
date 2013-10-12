package br.org.prismaCamara.taglibs.postagens

import br.org.prismaCamara.mensagens.Postagem;
import hackathon2013.Deputado;
import hackathon2013.Parametro;

class GastoDeputadoTagLib extends PostagemTag {

	/**
	 * Mensagem dos Gastos de Deputado.
	 * Renderiza o texto a partir de "_gasto-deputado-xxx.gsp"
     * @attr dep REQUIRED Instância de {@link Deputado}
     * @attr tipo REQUIRED Tipo de postagem (facebook ou twitter). Usar um {@link Postagem}.TIPO_XXX
	 */
	String getConteudo(attrs) {
		String mensagem = getTexto("gasto-deputado-${attrs.tipo}", attrs)
		return mensagem
	}
}
 