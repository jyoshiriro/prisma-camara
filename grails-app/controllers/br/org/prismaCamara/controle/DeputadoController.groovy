package br.org.prismaCamara.controle

import br.org.prismaCamara.modelo.Deputado;
import groovy.util.logging.Log4j;

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
			
}
