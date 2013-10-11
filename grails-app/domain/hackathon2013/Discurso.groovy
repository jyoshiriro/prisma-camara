package hackathon2013

class Discurso {

	String codigo
	Date data
	Integer numeroSessao
	String cdFaseSessao
	Integer numeroOrador
	String horaInicio
	Integer numeroQuarto
	Integer numeroInsercao
	String sumario
	
	Deputado deputado
	
	static constraints = {
		codigo(maxSize:16)
		cdFaseSessao(maxSize:5)
		horaInicio(maxSize:8)
		sumario(maxSize:4096)
	}
	
	static mapping = {
		deputado(cascade:'all')
	}
	
}
