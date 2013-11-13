/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
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
	Date dataNascimento
	boolean msgInicialLida = false
	
	static hasMany = [usuarioPartidos:UsuarioPartido,usuarioDeputados:UsuarioDeputado,usuarioProposicoes:UsuarioProposicao]
	
	static constraints = {
		username blank: false, unique: true
		password blank: false
		nome maxSize:50, nullable: true
		tipoRede nullable:true, inList:Postagem.TIPOS
		dataNascimento nullable: true 
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
