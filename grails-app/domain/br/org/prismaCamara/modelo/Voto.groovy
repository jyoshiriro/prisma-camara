package br.org.prismaCamara.modelo

class Voto {
	Deputado deputado
	String voto 
	
	static belongsTo = [votacao:Votacao]
	
	static mapping = {
	}
	
	static constraints = {
		voto(maxSize:16)
	}
}
