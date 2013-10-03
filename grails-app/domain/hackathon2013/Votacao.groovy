package hackathon2013

class Votacao {
	String resumo
	Date dataHoraVotacao
	String objVotacao
	
	Proposicao proposicao
	
	List<OrientacaoBancada> orientacoesBancada	
	List<Voto> votos	
	
	static mapping = {
		proposicao(cascade:'all')
	}
	
	static constraints = {
		resumo(maxSize:512)
		objVotacao(maxSize:256)
	}
}