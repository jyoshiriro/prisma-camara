/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.controle

import grails.plugins.springsecurity.Secured
import groovy.util.logging.Log4j

import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.impl.FacebookTemplate
import org.springframework.social.twitter.api.Twitter
import org.springframework.social.twitter.api.impl.TwitterTemplate

import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioFacebook
import br.org.prismaCamara.modelo.UsuarioTwitter
import br.org.prismaCamara.util.redes.TwitterUtil


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
		
		if (!usuario.nome) {
			session.primeiroAcesso=true
			redirect(action:"meuPerfil")
			return
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
		Usuario usuario = getUsuarioAutenticado()
		def nomeCompleto = ''
		
		switch (usuario.tipoRede) {
			case 'facebook':
				UsuarioFacebook uface = UsuarioFacebook.where{user==usuario}.find()
				Facebook redeobj = new FacebookTemplate(uface.accessToken)
				nomeCompleto = "${redeobj.userOperations().userProfile.firstName} ${redeobj.userOperations().userProfile.lastName}"
				break
			case 'twitter':
				UsuarioTwitter utw = UsuarioTwitter.where{user==usuario}.find()
				def chaves = new TwitterUtil(grailsApplication:grailsApplication).chavesTwitter()
				
				def consumerKey =  chaves.consumerKey
				def consumerSecret = chaves.consumerSecret
				Twitter redeobj = new TwitterTemplate(consumerKey, consumerSecret,utw.token,utw.tokenSecret)
				nomeCompleto = "${redeobj.userOperations().userProfile.name}"
				break
		}
		[ nome : nomeCompleto]
	}
	
	@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
	def atualizaNome() {
		Usuario usuario = getUsuarioAutenticado()
		usuario.nome = params.nome
		usuario.save()
		session.primeiroAcesso=false
		redirect action: ''
	}
	
	@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
	def cancelaInscricao() {
		Usuario usuario = getUsuarioAutenticado()
		usuario.delete()
		redirect controller: 'logout'
	}
	
	def ajuda() { 
		
	}
	
	def confirmarLeituraMsgInicial() {
		Usuario usuario = getUsuarioAutenticado()
		usuario.msgInicialLida=true
		redirect(action:'')
	}
	
}
