package hackathon2013

class UsuarioProposicao {
	
	Usuario usuario
	Proposicao proposicao
	
	static constraints = {
		usuario(unique: 'proposicao')
	}
	
	static mapping = {
		proposicao(cascade:'all')
	}
    
}
