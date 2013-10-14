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
