package hackathon2013

class FrequenciaDia {
	
	Date dia
	String frequenciaDia
	String justificativa
	boolean enviado = false
		
	Deputado deputado
	
	static hasMany = [frequenciasSessao:FrequenciaSessao]
	
	static mapping = {
		deputado(cascade:'all')
	}
	
	static constraints = {
		frequenciaDia(maxSize:30)
		justificativa(maxSize:1024, nullable:true)
	}
}
