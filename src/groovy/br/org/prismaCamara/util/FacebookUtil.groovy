package br.org.prismaCamara.util

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate

import groovy.util.logging.Log4j;
import br.org.prismaCamara.modelo.Usuario;
import br.org.prismaCamara.modelo.UsuarioFacebook;

@Log4j
class FacebookUtil {

	void postar(Usuario usuario, String conteudo) {
		try {
			UsuarioFacebook uface = UsuarioFacebook.where{user==usuario}.find()
			Facebook facebook = new FacebookTemplate(uface.accessToken)
			facebook.feedOperations().updateStatus(conteudo)
			
			log.debug("Mensagem '${conteudo[3..101].trim()}' enviada  com sucesso para ${usuario.username}")
		} catch (Exception e) {
			log.error("Erro ao postar mensagem para rede social ${usuario.tipoRede} de ${usuario.username}: ${e.message}")
			throw e
		} 
	}
}
