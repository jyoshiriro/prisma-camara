package br.org.prismaCamara.controle

import grails.plugins.springsecurity.Secured
import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Usuario


@Log4j
@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class PainelController {
	
	def searchableService
	def springSecurityService
	def usuarioService
	
	def aliasEntidades = [Deputados:['Deputado(a)','Deputados(as)'],Proposicoes:['Proposição','Proposições'],Partidos:['Partido','Partidos']]
	
	def getUsuarioautenticado() {
		Usuario usuarioAtual = springSecurityService.currentUser
		log.debug "Usuário logado: $usuarioAtual"
		return usuarioAtual
	}

    def index() {
		def usuario = getUsuarioautenticado()
		
		if (!session.contagemDeputados) {
			def contagemDeputados = usuarioService.countDeputadosDeUsuario(usuario,false)
			session.contagemDeputados = contagemDeputados
		}
			
		if (!session.contagemPartidos) {
			def contagemPartidos = usuarioService.countPartidosDeUsuario(usuario)
			session.contagemPartidos = contagemPartidos
		}
			
		if (!session.contagemProposicoes) {
			def contagemProposicoes = usuarioService.countProposicoesDeUsuario(usuario)
			session.contagemProposicoes = contagemProposicoes 
		}
		
	}
	
	def contagem(String id) {
		def cont = session["contagem${id}"]
		if (cont) {
			def idplural = cont?1:0
			def desc = aliasEntidades.get(id)[idplural]
			render("Você já acompanha <b>${cont}</b> ${desc}")
		}
		else {
			render("Ainda não acompanha nenhum${id=='Deputado'?'(a)':''}")
		}
	}

	
}
