package br.org.prismaCamara.util

import groovy.util.logging.Log4j;
import br.org.prismaCamara.modelo.Usuario;
import br.org.prismaCamara.modelo.UsuarioFacebook;

@Log4j
class FacebookUtil {

	void postar(Usuario usuario, String conteudo) {
		try {
			UsuarioFacebook uface = UsuarioFacebook.where{user==usuario}.find()
			log.debug("Mensagem '${conteudo[0..99]}' enviada  com sucesso para ${usuario.username}")
		} catch (Exception e) {
			log.error("Erro ao postar mensagem para rede social ${usuario.tipoRede} de ${usuario.username}: ${e.message}")
		} 
	}
}
