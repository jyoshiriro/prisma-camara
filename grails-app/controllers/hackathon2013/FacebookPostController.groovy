package hackathon2013

import grails.plugins.springsecurity.Secured
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.FacebookProfile
import org.springframework.social.facebook.api.Post
import org.springframework.social.facebook.api.impl.FacebookTemplate

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
		redirect(action: 'timeline')
	}
	
}
