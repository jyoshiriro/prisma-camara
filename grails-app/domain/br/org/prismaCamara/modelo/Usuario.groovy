package br.org.prismaCamara.modelo

import br.org.prismaCamara.mensagem.Postagem

class Usuario {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	String nome
	String tipoRede
	boolean receberBiografias = true
	
	static hasMany = [usuarioPartidos:UsuarioPartido,usuarioDeputados:UsuarioDeputado,usuarioProposicoes:UsuarioProposicao]
	
	static constraints = {
		username blank: false, unique: true
		password blank: false
		nome(maxSize:50, nullable:true)
		tipoRede(nullable:true, inList:Postagem.TIPOS)
		
	}

	static mapping = {
		password column: '`password`'
		
	}

	Set<Perfil> getAuthorities() {
		UsuarioPerfil.findAllByUsuario(this).collect { it.perfil } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.encodePassword(password)
	}
	
	
	
}
