package hackathon2013

import grails.plugins.springsecurity.Secured
import groovy.util.logging.Log4j

@Log4j
@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class PainelController {
	
	def springSecurityService
	
	def pegaUsuarioLogado() {
		Usuario usuarioAtual = springSecurityService.currentUser
		log.debug "Usuário logado: $usuarioAtual"
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
		  deputados : Deputado.findAllByAtivo(true,[sort:"nome", order:"asc"]) ]
	}
	
	def gravarConfiguracoes() {
		Usuario usuario = pegaUsuarioLogado()
		
		// Usuario x Partidos
		UsuarioPartido.executeUpdate("delete from UsuarioPartido where usuario=?",[usuario])
		def partidosSelecionados = params.list('partidosSelecionados')
		partidosSelecionados?.each {
			Partido p = Partido.get(it)
			UsuarioPartido up = new UsuarioPartido(usuario:usuario,partido:p)
			up.save()
			log.debug("O usuário ${usuario.username} agora acompanha o Partido ${p.sigla}") 
		}
		
		// Usuario x Deputados
		UsuarioDeputado.executeUpdate("delete from UsuarioDeputado where usuario=?",[usuario])
		def deputadosSelecionados = params.list('deputadosSelecionados')
		deputadosSelecionados?.each {
			Deputado d = Deputado.get(it)
			UsuarioDeputado ud = new UsuarioDeputado(usuario:usuario,deputado:d)
			ud.save()
			log.debug("O usuário ${usuario.username} agora acompanha o Deputado ${d.descricao}")
		}
		usuario.save()
		redirect(action: 'configurarPostagens')
	}
}
