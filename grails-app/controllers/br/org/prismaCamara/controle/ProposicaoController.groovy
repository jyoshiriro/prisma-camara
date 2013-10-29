package br.org.prismaCamara.controle

import br.org.prismaCamara.modelo.Proposicao
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioDeputado
import br.org.prismaCamara.modelo.UsuarioProposicao
import br.org.prismaCamara.util.PesquisaFoneticaUtil

class ProposicaoController {
	
	def usuarioService
	def springSecurityService

    def index() { }
	
	def list() {
		cache(validUntil:new Date()+3)
		
		Usuario usuario = springSecurityService.currentUser
		
		def mapProposicoes = [:]
		def listaProposicoes = []
		
		if (!params.q) {
			listaProposicoes = usuarioService.getProposicoesDeUsuario(usuario)
		}
		else {
			params.q=params.q.replace('/',' ')
			if (params.q.validoMinimoPalavras(2)) {
				def pesquisa = PesquisaFoneticaUtil.getTermosFoneticosParaPesquisa(params.q)
				
				log.debug "Pesquisa fonetizada: ${pesquisa}"
				listaProposicoes = Proposicao.searchEvery(pesquisa)
				log.debug "Resultado: ${listaProposicoes.size()}"
				
				if (!listaProposicoes) {
					request.message="Nenhuma Proposição encontrada com \"${params.q}\""
				} else {
					listaProposicoes.each { it.refresh() }
					listaProposicoes.sort{d1,d2-> d2.ano<=>d1.ano}
				}
			} else {
				request.message='Informe pelo menos 2 palavras diferentes na pesquisa'
			}
			
		}
		
		def proposicoesDeUsuario = listaProposicoes?:usuarioService.getProposicoesDeUsuario(usuario)
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
			render(status:200)
		} catch (Exception e) {
			log.error("Erro ao tentar (des)associar proposição (${proposicao.descricao}) a usuário ${usuario.login}: ${e.message}")
			e.printStackTrace()
			render(status:500, text:message(code:'erro.padrao'))
		}
	}
}
