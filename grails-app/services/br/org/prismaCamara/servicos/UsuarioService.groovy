package br.org.prismaCamara.servicos

import hackathon2013.Deputado;
import hackathon2013.Usuario;

class UsuarioService {
	
	boolean isDeputadoObservado(Deputado deputado) {
		// TODO: alimentar na base pra poder testar isso
		/*def deputados = Usuario.executeQuery("select u.deputados from Usuario u")
		def partidos = Usuario.executeQuery("select u.partidos from Usuario u")
		for (p in partidos) {
			deputados+=Deputado.findAllByPartido(p)
		}
		return deputados.contains(deputado)*/
		return true
	}
}
