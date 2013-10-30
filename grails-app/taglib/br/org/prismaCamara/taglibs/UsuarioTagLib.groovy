package br.org.prismaCamara.taglibs

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import br.org.prismaCamara.modelo.Usuario;

class UsuarioTagLib {
	
	def springSecurityService

	def nomeUsuario = {
		def nome = ""
		if (SpringSecurityUtils.ifAllGranted('ROLE_USER')) {
			Usuario usuario = springSecurityService.getCurrentUser()
			nome = (usuario.nome)?:usuario.username
		}
		out << nome
	}
	
}
