/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.util.redes

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
			Thread.sleep(1000) // evitar spam
			log.debug("Mensagem '${conteudo[3..101].trim()}' enviada  com sucesso para ${usuario.username}")
		} catch (Exception e) {
			log.error("Erro ao postar mensagem para rede social ${usuario.tipoRede} de ${usuario.username}: ${e.message}")
			throw e
		} 
	}
}
