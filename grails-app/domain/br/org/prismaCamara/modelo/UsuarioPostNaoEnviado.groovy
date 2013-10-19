package br.org.prismaCamara.modelo

class UsuarioPostNaoEnviado {

	static belongsTo = [postNaoEnviado:PostNaoEnviado, usuario:Usuario]
	
}
