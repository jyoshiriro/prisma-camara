package hackathon2013

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class PainelController {
	
	def springSecurityService

    def index() {
		Usuario usuario = springSecurityService.currentUser
		[usuario : usuario]
	}
}
