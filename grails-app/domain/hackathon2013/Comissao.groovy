package hackathon2013

class Comissao {
	
	String nome
	String sigla
	
	static hasMany = [deputados:Deputado]
	
	static belongsTo = [Deputado]
	
}
