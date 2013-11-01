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
package br.org.prismaCamara.controle

import grails.plugins.springsecurity.Secured
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.FacebookProfile
import org.springframework.social.facebook.api.Post
import org.springframework.social.facebook.api.impl.FacebookTemplate

import br.org.prismaCamara.modelo.Usuario;
import br.org.prismaCamara.modelo.UsuarioFacebook;

class FacebookPostController {

	def springSecurityService
	
	@Secured(['ROLE_USER'])
	def timeline = {
		Usuario usuario = springSecurityService.currentUser
		UsuarioFacebook usuarioFacebook = UsuarioFacebook.findByUser(usuario)
		Facebook facebook = new FacebookTemplate(usuarioFacebook.accessToken)
		List<Post> feed = facebook.feedOperations().getFeed()
		[ posts : feed ]
	}
	
	@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
	def postarNoMural = {
		Usuario usuario = springSecurityService.currentUser
		UsuarioFacebook usuarioFacebook = UsuarioFacebook.where{user==usuario}.find()
		Facebook facebook = new FacebookTemplate(usuarioFacebook.accessToken)
		facebook.feedOperations().updateStatus(params.mp)
		redirect(controller:'postagens')
	}
	
	def postarNoMuralUsuarioId2 = {
		Usuario usuario = Usuario.get(2)
		UsuarioFacebook usuarioFacebook = UsuarioFacebook.where{user==usuario}.find()
		Facebook facebook = new FacebookTemplate(usuarioFacebook.accessToken)
		facebook.feedOperations().updateStatus("Post Automático.")
		log.debug "Mensagem postada automáticamente no mural do usuário ${usuario.username}"
		redirect(view: '/')
	}
	
	
}
