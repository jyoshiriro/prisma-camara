package br.org.prismaCamara.modelo

class FrequenciaSessao {
	
	Date inicio
	String descricao
	String frequencia
	
	static belongsTo = [frequenciaDia:FrequenciaDia]
	
	static mapping = {
	}
	
	static constraints = {
		descricao(maxSize:50)
		frequencia(maxSize:12)
	}
}
