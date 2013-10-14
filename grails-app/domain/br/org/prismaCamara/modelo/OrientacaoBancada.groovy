package br.org.prismaCamara.modelo

class OrientacaoBancada {
	
	String sigla
	String orientacao
	
	Votacao votacao
	
	static mapping = {
		votacao(cascade:'all')
	}
	
	static constraints = {
		sigla(maxSize:30)
		orientacao(maxSize:20)
	}
}
