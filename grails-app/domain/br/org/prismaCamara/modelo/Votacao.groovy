package br.org.prismaCamara.modelo

class Votacao {
	String resumo
	Date dataHoraVotacao
	String objVotacao
	boolean enviado = false
	
	static belongsTo = [proposicao:Proposicao]
	static hasMany = [votos:Voto]
	
	static mapping = {
		votos(sort:'deputado')
	}
	
	static constraints = {
		resumo(maxSize:2048)
		objVotacao(maxSize:256)
	}
}
