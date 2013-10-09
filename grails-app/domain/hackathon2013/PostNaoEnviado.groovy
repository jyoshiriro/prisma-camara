package hackathon2013

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
