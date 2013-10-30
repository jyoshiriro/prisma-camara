package br.org.prismaCamara.controle

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Partido;
import br.org.prismaCamara.modelo.Proposicao;
import br.org.prismaCamara.modelo.Usuario;
import br.org.prismaCamara.modelo.UsuarioDeputado;
import br.org.prismaCamara.modelo.UsuarioPartido;
import br.org.prismaCamara.modelo.UsuarioProposicao
import br.org.prismaCamara.servico.UsuarioService;
import br.org.prismaCamara.util.PesquisaFoneticaUtil;
import grails.plugins.springsecurity.Secured
import groovy.util.logging.Log4j
import org.compass.core.engine.SearchEngineQueryParseException


@Log4j
@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class PainelController {
	
	def searchableService
	def springSecurityService
	def usuarioService
	
	def pegaUsuarioLogado() {
		Usuario usuarioAtual = springSecurityService.currentUser
		if (!usuarioAtual.nome)
			usuarioService.atualizaNome(usuarioAtual)
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
	
	/***
	 * Action para selecionar deputados para serem adicionados à lista de acompanhamento.
	 */
	def adicionarDeputados() {
		[ usuario : pegaUsuarioLogado() ]
	}

	
	/***
	 * Action para selecionar proposições para serem adicionadas à lista de acompanhamento.
	 */
	def adicionarProposicoes() {
		[ usuario : pegaUsuarioLogado() ]
	}
	
	def gravarProposicoes() {
		Usuario usuario = pegaUsuarioLogado()
		def proposicoesSelecionadas = params.list('proposicoesSelecionadas')
		log.debug "Proposições selecionadas: ${proposicoesSelecionadas}"
		proposicoesSelecionadas?.each {
			Proposicao p = Proposicao.get(it)
			UsuarioProposicao up = new UsuarioProposicao(usuario:usuario, proposicao:p)
			up.save()
			log.debug("O usuário ${up.usuario.username} agora acompanha a Proposição ${up.proposicao.descricao}")
		}
		redirect(action: 'configurarPostagens')
	}
	
	def removerProposicao() {
		def up = UsuarioProposicao.get(params.id)
		up.delete(flush:true)
		log.debug("O usuário ${up.usuario.username} não acompanha mais a Proposição ${up.proposicao.descricao}")
		redirect action: 'configurarPostagens'
	}
	
}
