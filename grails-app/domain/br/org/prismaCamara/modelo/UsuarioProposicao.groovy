package br.org.prismaCamara.modelo


class UsuarioProposicao {
	
	Usuario usuario
	Proposicao proposicao
	
	static constraints = {
		usuario(unique: 'proposicao')
	}
	
	static mapping = {
	}
    
}
