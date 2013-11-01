/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.modelo


import org.apache.commons.lang.builder.HashCodeBuilder

class UsuarioPerfil implements Serializable {

	Usuario usuario
	Perfil perfil

	boolean equals(other) {
		if (!(other instanceof UsuarioPerfil)) {
			return false
		}

		other.usuario?.id == usuario?.id &&
			other.perfil?.id == perfil?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (usuario) builder.append(usuario.id)
		if (perfil) builder.append(perfil.id)
		builder.toHashCode()
	}

	static UsuarioPerfil get(long usuarioId, long perfilId) {
		find 'from UsuarioPerfil where usuario.id=:usuarioId and perfil.id=:perfilId',
			[usuarioId: usuarioId, perfilId: perfilId]
	}

	static UsuarioPerfil create(Usuario usuario, Perfil perfil, boolean flush = false) {
		new UsuarioPerfil(usuario: usuario, perfil: perfil).save(flush: flush, insert: true)
	}

	static boolean remove(Usuario usuario, Perfil perfil, boolean flush = false) {
		UsuarioPerfil instance = UsuarioPerfil.findByUsuarioAndPerfil(usuario, perfil)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(Usuario usuario) {
		executeUpdate 'DELETE FROM UsuarioPerfil WHERE usuario=:usuario', [usuario: usuario]
	}

	static void removeAll(Perfil perfil) {
		executeUpdate 'DELETE FROM UsuarioPerfil WHERE perfil=:perfil', [perfil: perfil]
	}

	static mapping = {
		id composite: ['perfil', 'usuario']
		version false
	}
}
