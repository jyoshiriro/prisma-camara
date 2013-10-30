package br.org.prismaCamara.controle

import grails.plugins.springsecurity.Secured
import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Partido
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioPartido
import br.org.prismaCamara.util.PesquisaFoneticaUtil

@Log4j
@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
class PartidoController {
	
	def usuarioService
	def springSecurityService
	
	
	def index() {
		
	}
	
	def list() {
		
		Usuario usuario = springSecurityService.currentUser
		
		LinkedHashMap mapPartidos = new LinkedHashMap()
		def listaPartidos = []
		
		def partidosDeUsuario = usuarioService.getPartidosDeUsuario(usuario)
		
		if (!params.q) {
			listaPartidos = partidosDeUsuario
		}
		else {
			if (params.q.size()>=2) {
				def pesquisa = PesquisaFoneticaUtil.getTermosFoneticosParaPesquisa(params.q)
				
				log.debug "Pesquisa fonetizada: ${pesquisa}"
				def listaPartidosTmp = Partido.searchEvery(pesquisa)
				log.debug "Resultado: ${listaPartidos.size()}"
				
				if (!listaPartidosTmp) {
					request.message="Nenhum Partido encontrado com \"${params.q}\""
				} else {
					listaPartidosTmp.each {
						listaPartidos+=Partido.get(it.id)
					}
					listaPartidos.sort{d1,d2-> d2.sigla<=>d1.sigla}
				}
			} else {
				request.message='Informe pelo menos 2 letras'
			}
			
		}
		
		for (prop in listaPartidos) {
			def mapeado = partidosDeUsuario.contains(prop)
			mapPartidos.put(prop, mapeado)
		}
		
		
		render(template:'resultadoPesquisa',model:[mapa:mapPartidos])
		
	}
	
	def toogleAssociar() {
		Usuario usuario = springSecurityService.currentUser
		Partido partido = Partido.get(params.id)
		try {
			def up = UsuarioPartido.findByUsuarioAndPartido(usuario,partido)
			if (up) {
				up.delete()
			} else {
				up = new UsuarioPartido(usuario:usuario, partido:partido)
				up.save()
			}
			Integer contagemPartidos = usuarioService.countPartidosDeUsuario(usuario)
			session.contagemPartidos = contagemPartidos
			render(status:200)
		} catch (Exception e) {
			log.error("Erro ao tentar (des)associar partido (${partido.sigla}) a usuário ${usuario.login}: ${e.message}")
			e.printStackTrace()
			render(status:500, text:message(code:'erro.padrao'))
		}
	}
	
}
