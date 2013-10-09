package br.org.prismaCamara.mensagens

import hackathon2013.Deputado;

abstract class PostagemBiografiaDeputado extends Postagem {

	protected Deputado getDeputadoAleatorio() {
		def quantAtivos = Deputado.countByAtivo(true)
		Deputado dep = Deputado.get(new Random().nextInt(quantAtivos).toLong())
		dep
	}
}
