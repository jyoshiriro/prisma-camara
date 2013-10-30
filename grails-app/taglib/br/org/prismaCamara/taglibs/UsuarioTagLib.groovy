package br.org.prismaCamara.taglibs

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import br.org.prismaCamara.modelo.Usuario;

class UsuarioTagLib {
	
	def springSecurityService

	/**
	 * Retorna o nome do Usuário atual da sessão
	 */
	def nomeUsuario = {
		def nome = ""
		Usuario usuario = usuarioAtual
		nome = (usuario?.nome)?:usuario.username
		out << nome
	}
	
	/**
	 * Retorna o nome da rede social do Usuário atual da sessão
	 */
	def tipoRedeUsuario = {
		def tipo = usuarioAtual?.tipoRede
		out << tipo
	}
	
	private Usuario getUsuarioAtual() {
		Usuario usuario = null
		if (SpringSecurityUtils.ifAllGranted('ROLE_USER')) {
			usuario = springSecurityService.getCurrentUser()
		}
		return usuario
	}
	
}
