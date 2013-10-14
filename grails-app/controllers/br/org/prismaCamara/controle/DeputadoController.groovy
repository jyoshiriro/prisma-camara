package br.org.prismaCamara.controle

import br.org.prismaCamara.modelo.Deputado;
import groovy.util.logging.Log4j;

@Log4j
class DeputadoController {

    def list() {		
		//Book.findAllByTitleLike("Harry Pot%", [max: 3, offset: 2, sort: "title", order: "desc"])
		def listaDeputados = Deputado.findAllByNomeLike("%${params.sSeach}%", [max: params.iDisplayStart, offset: params.iDisplayLength, sort: "nome", order: "asc"]);
		log.debug listaDeputados
		render (contentType: 'text/json') {
			sEcho = params.sEcho
			iTotalRecords = Deputado.count()
			iTotalDisplayRecords =  listaDeputados.size()
			aaData = array {
				for(d in listaDeputados){
					deputado id: d.id, nome: d.nome, partido: d.partido?.sigla, uf: d.uf
				}
			}
		}
	}
	
}
