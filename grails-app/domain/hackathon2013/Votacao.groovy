package hackathon2013

class Votacao {
	String resumo
	Date dataHoraVotacao
	String objVotacao
	boolean enviado = false
	
	Proposicao proposicao
	
	static hasMany = [votos:Voto]
	
	static mapping = {
		proposicao(cascade:'all')
		votos(cascade:'all',sort:'deputado')
	}
	
	static constraints = {
		resumo(maxSize:512)
		objVotacao(maxSize:256)
	}
}
