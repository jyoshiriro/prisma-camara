package hackathon2013

class TipoProposicao {

	String sigla
	String descricao
	boolean ativo
	String genero
	
	static mapping = {
		cache(true)
	}
	
	static constraints = {
		sigla(maxSize:8)
		descricao(maxSize:70)
		genero(maxSize:1)
	}
}
