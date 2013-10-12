package br.org.prismaCamara.mensagens

import hackathon2013.Deputado
import br.org.prismaCamara.taglibs.postagens.BiografiaDeputadoTagLib

class PostagemBiografiaDeputado extends Postagem {
	
	private Deputado getDeputadoAleatorio() {
		def quantAtivos = Deputado.countByAtivo(true)
		def id = new Random().nextInt(quantAtivos.toInteger())-1
		def deputado = Deputado.list(max:1,offset:id).first()
		deputado
	}

	 /**
	 * Gera e retorna o texto de uma postagem de biografia de deputado
	 * @param params (tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		params.dep = deputadoAleatorio
		String mensagem = new BiografiaDeputadoTagLib().getConteudo(params)
		return mensagem
	}

	/**
	 * Gera e retorna o texto de uma postagem de biografia de deputado
	 * @param params (dep:um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
}
