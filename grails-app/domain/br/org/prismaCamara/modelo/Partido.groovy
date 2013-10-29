package br.org.prismaCamara.modelo

import br.org.prismaCamara.util.PesquisaFoneticaUtil;

class Partido {

	String sigla
	String nome
	String campoPesquisa
	
	static constraints = {
		sigla(maxSize:30)
		nome(nullable:true)
	}
	
	static mapping = {
		cache(true)
	}
	
	static searchable = [only: ['campoPesquisa']]
	
	void setSigla(String sigla) {
		this.sigla=sigla.trim()
	}
	
	String getCampoPesquisa() {
		return PesquisaFoneticaUtil.getFonemasParaIndexar("${sigla} ${nome}")
	}
	
	
}
