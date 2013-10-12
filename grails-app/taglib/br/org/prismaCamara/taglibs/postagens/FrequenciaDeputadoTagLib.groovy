package br.org.prismaCamara.taglibs.postagens

import br.org.prismaCamara.mensagens.Postagem;
import hackathon2013.Deputado;
import hackathon2013.Parametro;

class FrequenciaDeputadoTagLib extends PostagemTag {

	/**
	 * Mensagem da Última Frequencia registrada de Deputado.
	 * Renderiza o texto a partir de "_frequencia-deputado-xxx.gsp"
     * @attr dep REQUIRED Instância de {@link Deputado}
     * @attr tipo REQUIRED Tipo de postagem (facebook ou twitter). Usar um {@link Postagem}.TIPO_XXX
	 */
	String getConteudo(attrs) {
		attrs.freq=attrs.dep.ultimaFrequencia
		String mensagem = getTexto("frequencia-deputado-${attrs.tipo}", attrs)
		return mensagem
		
	}
	
}
 