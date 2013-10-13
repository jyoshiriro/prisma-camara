package hackathon2013

import grails.plugins.springsecurity.Secured
import groovy.util.logging.Log4j;

@Log4j
@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class PainelController {
	
	def springSecurityService
	
	def pegaUsuarioLogado() {
		Usuario usuarioAtual = springSecurityService.currentUser
		log.debug "Usuário logado: " + usuarioAtual
		return usuarioAtual
	}

    def index() {
		[ usuario : pegaUsuarioLogado() ]
	}
	
	def editarUsuario() {
		
	}
	
	def configurarPostagens() {
		[ usuario : pegaUsuarioLogado(),
		  partidos : Partido.list(sort:"sigla", order:"asc"),
		  deputados : Deputado.list(sort:"nome", order:"asc") ]
	}
	
	def gravarConfiguracoes() {
		Usuario usuario = pegaUsuarioLogado()
		usuario.partidos.clear()
		def partidosSelecionados = params.list('partidosSelecionados')
		partidosSelecionados?.each {
			usuario.addToPartidos(Partido.load(it))
			log.debug "Partido selecionado: " + Partido.load(it).sigla 
		}
		usuario.deputados.clear()
		def deputadosSelecionados = params.list('deputadosSelecionados')
		deputadosSelecionados?.each {
			usuario.addToDeputados(Deputado.load(it))
			log.debug "Deputado selecionado: " + Deputado.load(it).nome
		}
		usuario.save(flush: true)
		redirect (action: 'configurarPostagens')
	}
}
