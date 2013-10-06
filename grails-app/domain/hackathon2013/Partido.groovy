package hackathon2013

class Partido {

	String sigla
	
	static constraints = {
		sigla(maxSize:30)
	}
	
	static mapping = {
		cache(true)
	}
	
	
}
