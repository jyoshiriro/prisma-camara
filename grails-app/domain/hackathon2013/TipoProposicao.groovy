package hackathon2013

class TipoProposicao {

	String sigla
	String descricao
	boolean ativo
	String genero
	
	static constraints = {
		sigla(maxSize:8)
		descricao(maxSize:50)
		genero(maxSize:1)
	}
}
