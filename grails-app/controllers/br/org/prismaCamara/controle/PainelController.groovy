package br.org.prismaCamara.controle

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Partido;
import br.org.prismaCamara.modelo.Proposicao;
import br.org.prismaCamara.modelo.Usuario;
import br.org.prismaCamara.modelo.UsuarioDeputado;
import br.org.prismaCamara.modelo.UsuarioPartido;
import br.org.prismaCamara.modelo.UsuarioProposicao
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
		[ usuario : pegaUsuarioLogado() ]
	}
	
	def adicionarDeputados() {
		def usuario = pegaUsuarioLogado()
		def deputados = Deputado.executeQuery("from Deputado d where d.ativo = true and d.id not in (select up.partido.id from UsuarioPartido up where up.usuario.id = :usuarioid)", [usuarioid: usuario.id])
		[ deputados : deputados ]
	}
	
	def gravarDeputados() {
		Usuario usuario = pegaUsuarioLogado()
		def deputadosSelecionados = params.list('deputadosSelecionados')
		log.debug "Deputados selecionados: ${deputadosSelecionados}"
		deputadosSelecionados?.each {
			Deputado d = Deputado.get(it)
			UsuarioDeputado ud = new UsuarioDeputado(usuario:usuario, deputado:d)
			ud.save()
			log.debug("O usuário ${usuario.username} agora acompanha o Deputado ${d.descricao}")
		}
		redirect(action: 'configurarPostagens')
	}
	
	def removerDeputado() {
		def ud = UsuarioDeputado.get(params.id)
		ud.delete(flush:true)
		log.debug("O usuário ${usuario.username} não acompanha mais o Deputado ${d.descricao}")
		redirect action: 'configurarPostagens'
	}
	
	def adicionarProposicoes() {
		def usuario = pegaUsuarioLogado()
		def proposicoes = Proposicao.executeQuery("from Proposicao p where p.id not in (select up.proposicao.id from UsuarioProposicao up where up.usuario.id = :usuarioid)", [usuarioid: usuario.id])
		[ proposicoes : proposicoes ]
	}
	
	def gravarProposicoes() {
		Usuario usuario = pegaUsuarioLogado()
		def proposicoesSelecionadas = params.list('proposicoesSelecionadas')
		log.debug "Proposições selecionadas: ${proposicoesSelecionadas}"
		proposicoesSelecionadas?.each {
			Proposicao p = Proposicao.get(it)
			UsuarioProposicao up = new UsuarioProposicao(usuario:usuario, proposicao:p)
			up.save()
			log.debug("O usuário ${usuario.username} agora acompanha a Proposição ${p.numero}")
		}
		redirect(action: 'configurarPostagens')
	}
	
	def removerProposicao() {
		def up = UsuarioProposicao.get(params.id)
		up.delete(flush:true)
		log.debug("O usuário ${usuario.username} não acompanha mais a Proposição ${p.numero}")
		redirect action: 'configurarPostagens'
	}
	
}
