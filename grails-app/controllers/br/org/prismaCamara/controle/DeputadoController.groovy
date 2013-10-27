package br.org.prismaCamara.controle

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Deputado
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioDeputado
import br.org.prismaCamara.util.PesquisaFoneticaUtil

@Log4j
class DeputadoController {

	def usuarioService
	def springSecurityService
	
    def list() {		
		
		def pesquisa = PesquisaFoneticaUtil.getTermosFoneticosParaPesquisa(params.q)
		log.debug "Pesquisa fonetizada: ${pesquisa}"
		def listaDeputados = Deputado.searchEvery(pesquisa)
		log.debug "Resultado: ${listaDeputados.id}"
		listaDeputados.each { it.refresh() }
		
		def mapDeputados = [:]
		
		def deputadosDeUsuario = usuarioService.getDeputadosDeUsuario(springSecurityService.currentUser)
		
		for (dep in listaDeputados) {
			def mapeado = deputadosDeUsuario.contains(dep)
			mapDeputados.put(dep, mapeado)
		}
		
		render(template:'resultadoPesquisa',model:[mapa:mapDeputados])
	}
	
	/**
	 * Download da foto miniatura do deputado (".../deputado/foto/$id")
	 * @return
	 */
	def foto() {
		
		def dep = Deputado.get(params.id)
		def bmini = dep.foto
		
		response.contentType='image/jpeg'
		response.contentLength=bmini.size()
		response.outputStream<<bmini
	}
			
	def toogleAssociar() {
		Usuario usuario = springSecurityService.currentUser
		Deputado deputado = Deputado.get(params.id)
		def ud = UsuarioDeputado.findByUsuarioAndDeputado(usuario,deputado)
		if (ud) {
			ud.delete()
		} else {
			ud = new UsuarioDeputado(usuario:usuario, deputado:deputado)
			ud.save()
		}
		render(status:200) 
	}
}
