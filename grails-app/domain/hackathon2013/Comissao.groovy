package hackathon2013

class Comissao {
	
	String nome
	String sigla
	
	static hasMany = [deputados:Deputado]
	
	static belongsTo = [Deputado]
	
	static constraints = {
		nome(maxSize:512)
		sigla(maxSize:30)
	}
	
}
