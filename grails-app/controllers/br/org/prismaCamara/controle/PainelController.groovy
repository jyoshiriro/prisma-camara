package br.org.prismaCamara.controle

import grails.plugins.springsecurity.Secured
import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Usuario


@Log4j
class PainelController {
	
	def searchableService
	def springSecurityService
	def usuarioService
	
	def aliasEntidades = [Deputados:['Deputado(a)','Deputados(as)'],Proposicoes:['Proposição','Proposições'],Partidos:['Partido','Partidos']]
	
	def sobre() {}
	
	def getUsuarioAutenticado() {
		Usuario usuarioAtual = springSecurityService.currentUser
		log.debug "Usuário logado: $usuarioAtual"
		return usuarioAtual
	}

	@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
    def index() {
		def usuario = getUsuarioAutenticado()
		
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
	
	@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
	def meuPerfil() {
		[ nome : getUsuarioAutenticado().nome]
	}
	
	@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
	def atualizaNome() {
		Usuario usuario = getUsuarioAutenticado()
		usuario.nome = params.nome
		usuario.save()
		redirect action: 'meuPerfil'
	}
	
	@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
	def cancelaInscricao() {
		Usuario usuario = getUsuarioAutenticado()
		usuario.delete()
		redirect controller: '/'
	}
	
}
