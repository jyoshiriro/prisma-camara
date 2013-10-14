package br.org.prismaCamara.modelo


class UsuarioPartido {

	Usuario usuario
	Partido partido
	
	static constraints = {
		usuario(unique: 'partido')
	}

}
