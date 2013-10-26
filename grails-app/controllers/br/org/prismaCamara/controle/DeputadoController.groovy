package br.org.prismaCamara.controle

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Deputado

@Log4j
class DeputadoController {

    def list() {		
		//Book.findAllByTitleLike("Harry Pot%", [max: 3, offset: 2, sort: "title", order: "desc"])
		def listaDeputados = Deputado.findAllByNomeParlamentarLike("%${params.sSearch}%", [max: params.iDisplayLength, offset: params.iDisplayStart, sort: "nomeParlamentar", order: "asc"]);
		log.debug "Pesquisa por %${params.sSearch}%, inicio: ${params.iDisplayStart}, porPagina: ${params.iDisplayLength}"
		render (contentType: 'text/json') {
			sEcho = params.sEcho
			iTotalRecords = Deputado.count()
			iTotalDisplayRecords =  Deputado.countByNomeParlamentarLike("%${params.sSearch}%")
			aaData = array {
				for(d in listaDeputados){
					deputado id: d.id, nomeParlamentar: d.nomeParlamentar, partido: d.partido?.sigla, uf: d.uf
				}
			}
		}
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
			
}
