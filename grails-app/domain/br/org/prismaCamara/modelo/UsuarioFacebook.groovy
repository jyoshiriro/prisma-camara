package br.org.prismaCamara.modelo



class UsuarioFacebook {

	long uid
	String accessToken
	Date accessTokenExpires

	static belongsTo = [user: Usuario]

	static constraints = {
		uid unique: true
	}
}
