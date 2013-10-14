package br.org.prismaCamara.mensagem


import br.org.prismaCamara.taglibs.postagens.FrequenciaDeputadoTagLib

class PostagemFrequenciaDeputado extends Postagem {

	 /**
	 * Gera e retorna o texto de uma postagem de frequência de deputado
	 * @param params (dep:um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		if (!params.dep.ultimaFrequencia)
			return null
		params.freq=params.dep.ultimaFrequencia
		String mensagem = new FrequenciaDeputadoTagLib().getConteudo(params)
		return mensagem
	}

}
