package hackathon2013


class UsuarioFacebook {

	long uid
	String accessToken
	Date accessTokenExpires

	static belongsTo = [user: Usuario]

	static constraints = {
		uid unique: true
	}
}
