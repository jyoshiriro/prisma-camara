package hackathon2013

class UsuarioPartido {

	Usuario usuario
	Partido partido
	
	static constraints = {
		usuario(unique: 'partido')
	}

}
