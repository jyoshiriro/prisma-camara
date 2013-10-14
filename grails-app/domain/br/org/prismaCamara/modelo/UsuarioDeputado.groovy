package br.org.prismaCamara.modelo


class UsuarioDeputado {

	Usuario usuario
	
	static belongsTo = [deputado:Deputado]
	
    static constraints = {
		usuario(unique: 'deputado')
    }
}
