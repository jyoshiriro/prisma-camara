package br.org.prismaCamara.modelo

class Perfil {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
