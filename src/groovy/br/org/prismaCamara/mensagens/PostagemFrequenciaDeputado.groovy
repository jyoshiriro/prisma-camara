package br.org.prismaCamara.mensagens

import br.org.prismaCamara.taglibs.FrequenciaDeputadoTagLib
import hackathon2013.FrequenciaDia;

import java.util.Map;

class PostagemFrequenciaDeputado extends Postagem {

	 /**
	 * Gera e retorna o texto de uma postagem de frequência de deputado
	 * @param params (dep:um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		String mensagem = new FrequenciaDeputadoTagLib().getConteudo(params)
		return mensagem
	}

}
