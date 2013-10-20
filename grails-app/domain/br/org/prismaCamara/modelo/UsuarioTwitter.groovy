package br.org.prismaCamara.modelo

import br.org.prismaCamara.mensagem.Postagem;

class UsuarioTwitter {

	String username

	Long twitterId

	String token

	String tokenSecret

	static belongsTo = [user: Usuario]

	static constraints = {
		twitterId(unique: true, nullable: false)
		username(nullable: false, blank: false)
	}
	
	
	def beforeInsert() {
		user?.tipoRede=Postagem.TIPO_TWITTER
	}
}
