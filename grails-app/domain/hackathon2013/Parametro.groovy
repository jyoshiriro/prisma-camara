package hackathon2013

class Parametro {
	
	String sigla
	String valor
	String descricao
	
	static mapping = {
		cache(true)
	}	
	
	static constraints = {
		sigla(maxSize:30)
		valor(maxSize:512)
		descricao(maxSize:1024,nullable:true)
	}
	
}
