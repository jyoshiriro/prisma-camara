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
import org.compass.core.engine.SearchEngineQueryParseException

@Log4j
@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class PainelController {
	
	def searchableService
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
	
	/***
	 * Action para selecionar deputados para serem adicionados à lista de acompanhamento.
	 */
	def adicionarDeputados() {
		def usuarioAtual = pegaUsuarioLogado()
		if (!params.q?.trim()) {
			return [ listaDeputados : new ArrayList<Deputado>() ]
		}
		try {
			def listaDeputados = Deputado.searchEvery(params.q)
			log.debug "Resultado: ${listaDeputados.id}"
			def jaSelecionados = UsuarioDeputado.where { usuario.id == usuarioAtual.id }.findAll()
			log.debug "Selecionados ${jaSelecionados.deputado.id}"
			listaDeputados.removeAll { it.id in jaSelecionados.deputado.id }
			listaDeputados.each { it.refresh() }
			log.debug "Resultado Final: ${listaDeputados.id}"
			return [listaDeputados : listaDeputados]
			//def deputados = Deputado.executeQuery("from Deputado d where d.ativo = true d.id not in (select up.partido.id from UsuarioPartido up where up.usuario.id = :usuarioid) and d in (:resultado)", [usuarioid: usuario.id, resultado: resultado.results])
			//[ deputados : deputados ]
		} catch (SearchEngineQueryParseException ex) {
			return [parseException : true]
		}
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
	
	/***
	 * Action para selecionar proposições para serem adicionadas à lista de acompanhamento.
	 */
	def adicionarProposicoes() {
		def usuarioAtual = pegaUsuarioLogado()
		if (!params.q?.trim()) {
			return [ listaProposicoes : new ArrayList<Proposicao>() ]
		}
		try {
			def listaProposicoes = Proposicao.searchEvery(params.q)
			log.debug "Resultado: ${listaProposicoes.id}"
			def jaSelecionados = UsuarioProposicao.where { usuario.id == usuarioAtual.id }.findAll()
			log.debug "Selecionados ${jaSelecionados.proposicao.id}"
			listaProposicoes.removeAll { it.id in jaSelecionados.proposicao.id }
			listaProposicoes.each { it.refresh() }
			log.debug "Resultado Final: ${listaProposicoes.id}"
			return [listaProposicoes : listaProposicoes]
			//def deputados = Deputado.executeQuery("from Deputado d where d.ativo = true d.id not in (select up.partido.id from UsuarioPartido up where up.usuario.id = :usuarioid) and d in (:resultado)", [usuarioid: usuario.id, resultado: resultado.results])
			//[ deputados : deputados ]
		} catch (SearchEngineQueryParseException ex) {
			return [parseException : true]
		}
		//def proposicoes = Proposicao.executeQuery("from Proposicao p where p.id not in (select up.proposicao.id from UsuarioProposicao up where up.usuario.id = :usuarioid)", [usuarioid: usuario.id])
		//[ proposicoes : proposicoes ]
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
