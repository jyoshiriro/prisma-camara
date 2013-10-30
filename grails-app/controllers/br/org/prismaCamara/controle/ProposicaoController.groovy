package br.org.prismaCamara.controle

import grails.plugins.springsecurity.Secured
import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Proposicao
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioProposicao
import br.org.prismaCamara.util.PesquisaFoneticaUtil

@Log4j
@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class ProposicaoController {
	
	def usuarioService
	def springSecurityService

	
	def index() {
		
	}
	
	def list() {
		
		Usuario usuario = springSecurityService.currentUser
		
		LinkedHashMap mapProposicoes = new LinkedHashMap()
		def listaProposicoes = []
		
		def proposicoesDeUsuario = usuarioService.getProposicoesDeUsuario(usuario)
		
		if (!params.q) {
			listaProposicoes = proposicoesDeUsuario
		}
		else {
			params.q=params.q.replace('/',' ')
			if (params.q.validoMinimoPalavras(2)) {
				def pesquisa = PesquisaFoneticaUtil.getTermosFoneticosParaPesquisa(params.q)
				
				log.debug "Pesquisa fonetizada: ${pesquisa}"
				def listaProposicoesTmp = Proposicao.searchEvery(pesquisa)
				log.debug "Resultado: ${listaProposicoes.size()}"
				
				if (!listaProposicoesTmp) {
					request.message="Nenhuma Proposição encontrada com \"${params.q}\""
				} else {
					listaProposicoesTmp.each { 
						listaProposicoes+=Proposicao.get(it.id)
					}
					listaProposicoes.sort{d1,d2-> d2.ano<=>d1.ano}
				}
			} else {
				request.message='Informe pelo menos 2 palavras diferentes na pesquisa'
			}
			
		}
		
		for (prop in listaProposicoes) {
			def mapeado = proposicoesDeUsuario.contains(prop)
			mapProposicoes.put(prop, mapeado)
		}
		
		
		render(template:'resultadoPesquisa',model:[mapa:mapProposicoes])
		
	}
	
	def toogleAssociar() {
		Usuario usuario = springSecurityService.currentUser
		Proposicao proposicao = Proposicao.get(params.id)
		try {
			def up = UsuarioProposicao.findByUsuarioAndProposicao(usuario,proposicao)
			if (up) {
				up.delete()
			} else {
				up = new UsuarioProposicao(usuario:usuario, proposicao:proposicao)
				up.save()
			}
			Integer contagemProposicoes = usuarioService.countProposicoesDeUsuario(usuario)
			session.contagemProposicoes = contagemProposicoes
			render(status:200)
		} catch (Exception e) {
			log.error("Erro ao tentar (des)associar proposição (${proposicao.descricao}) a usuário ${usuario.login}: ${e.message}")
			e.printStackTrace()
			render(status:500, text:message(code:'erro.padrao'))
		}
	}
	
	def detalhes(Long id) {
		render(template:'detalhes', model:[prop:Proposicao.get(id)])
	}
}
