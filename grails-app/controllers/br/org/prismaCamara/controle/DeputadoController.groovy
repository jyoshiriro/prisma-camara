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
		
		Usuario usuario = springSecurityService.currentUser
		
		def mapDeputados = [:]
		def listaDeputados = []
		// TODO: caso nada venha na pesquisa, pegar todos os já associados ao usuario e mandar essa lista para a view
		
		if (!params.q) {
			listaDeputados = usuarioService.getDeputadosDeUsuario(usuario)
		}
		else {
			if (params.q.size()>2) {
				def pesquisa = PesquisaFoneticaUtil.getTermosFoneticosParaPesquisa(params.q)
				
				log.debug "Pesquisa fonetizada: ${pesquisa}"
				listaDeputados = Deputado.searchEvery(pesquisa)
				log.debug "Resultado: ${listaDeputados.id}"
				listaDeputados.each { it.refresh() }
			}
			
		}
		
		def deputadosDeUsuario = usuarioService.getDeputadosDeUsuario(usuario)
		for (dep in listaDeputados) {
			if (!dep.ativo)
				continue
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
		cache(validUntil:new Date()+30)
		
		def dep = Deputado.get(params.id)
		def bmini = dep.foto
		
		if (!bmini) {
			bmini = grailsAttributes.getApplicationContext().getResource("/images/pessoa-sem-foto.png").getFile().bytes
		}
		response.contentType='image/jpeg'
		response.contentLength=bmini.size()
		response.outputStream<<bmini
		response.setStatus(200)
	}
			
	def toogleAssociar() {
		Usuario usuario = springSecurityService.currentUser
		Deputado deputado = Deputado.get(params.id)
		try {
			def ud = UsuarioDeputado.findByUsuarioAndDeputado(usuario,deputado)
			if (ud) {
				ud.delete()
			} else {
				ud = new UsuarioDeputado(usuario:usuario, deputado:deputado)
				ud.save()
			}
			render(status:200)
		} catch (Exception e) {
			log.error("Erro ao tentar (des)associar deputado (${deputado.descricao}) a usuário ${usuario.login}: ${e.message}")
			e.printStackTrace()
			render(status:500, text:message(code:'erro.padrao'))
		} 
	}
}
