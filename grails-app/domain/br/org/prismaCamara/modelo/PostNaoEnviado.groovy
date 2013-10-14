package br.org.prismaCamara.modelo


class PostNaoEnviado {
	
	String tipo
	Usuario usuario
	TextoNaoEnviado textoNaoEnviado
	boolean enviado = false
	
	static mapping = {
		usuario(cascade:'all')
		textoNaoEnviado(cascade:'all')
	}
}
