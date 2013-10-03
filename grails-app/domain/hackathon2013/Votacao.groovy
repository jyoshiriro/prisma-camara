package hackathon2013

class Votacao {
	String resumo
	Date dataHoraVotacao
	String objVotacao
	
	Proposicao proposicao
	
	static hasMany = [orientacoesBancada:OrientacaoBancada,votos:Voto]
	
	static mapping = {
		proposicao(cascade:'all')
	}
	
	static constraints = {
		resumo(maxSize:512)
		objVotacao(maxSize:256)
	}
}
