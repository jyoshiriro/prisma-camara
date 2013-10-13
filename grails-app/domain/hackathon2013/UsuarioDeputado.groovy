package hackathon2013

class UsuarioDeputado {

	Usuario usuario
	
	static belongsTo = [deputado:Deputado]
	
    static constraints = {
		usuario(unique: 'deputado')
    }
}
