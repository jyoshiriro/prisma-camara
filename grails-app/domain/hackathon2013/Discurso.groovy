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
	
	Deputado deputado
	
	static mapping = {
		deputado(cascade:'all')
	}
	
}
