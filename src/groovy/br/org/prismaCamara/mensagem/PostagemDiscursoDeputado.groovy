package br.org.prismaCamara.mensagem

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.taglibs.postagens.DiscursoDeputadoTagLib


class PostagemDiscursoDeputado extends Postagem {

	 /**
	 * Gera e retorna o texto dos últimos discursos de deputado
	 * @param params (dep:um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		if (params.dep.discursos.empty) {
			return null
		}
		Deputado dep = params.dep

		if (dep.ultimoDiaDiscurso.before(dep.discursos.last().data)) {
			dep.ultimoDiaDiscurso=dep.discursos.last().data
		}
		
		params.discursos=dep.discursos
		String mensagem = new DiscursoDeputadoTagLib().getConteudo(params)
		return mensagem
	}

}
