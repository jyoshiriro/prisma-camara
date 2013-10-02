package hackathon2013

class FrequenciaDia {
	
	Date dia
	String frequenciaDia
	String justificativa
		
	Deputado deputado
	
	List<FrequenciaSessao> frequenciasSessao
	
	static mapping = {
		deputado(cascade:'all')
	}
	
	static constraints = {
		frequenciaDia(maxSize:12)
		justificativa(maxSize:1024)
	}
}
