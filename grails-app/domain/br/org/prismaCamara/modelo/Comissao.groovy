package br.org.prismaCamara.modelo

class Comissao {
	
	String nome
	String sigla
	
	static hasMany = [deputados:Deputado]
	
	static belongsTo = [Deputado]
	
	static transients = ['descricao']
	
	static constraints = {
		nome(maxSize:512)
		sigla(maxSize:30)
	}
	
	String getDescricao() {
		"$nome - $sigla"
	}
	
}
