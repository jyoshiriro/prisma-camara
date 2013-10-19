package br.org.prismaCamara.modelo

import br.org.prismaCamara.mensagem.Postagem;



class UsuarioFacebook {

	long uid
	String accessToken
	Date accessTokenExpires

	static belongsTo = [user: Usuario]

	static constraints = {
		uid unique: true
	}
	
	def beforeInsert() {
		user?.tipoRede=Postagem.TIPO_FACE
	}
}
