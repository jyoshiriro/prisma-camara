package hackathon2013

class FrequenciaSessao {
	
	Date inicio
	String descricao
	String frequencia
	
	FrequenciaDia frequenciaDia
	
	static mapping = {
		frequenciaDia(cascade:'all')
	}
	
	static constraints = {
		descricao(maxSize:50)
		frequencia(maxSize:12)
	}
}
