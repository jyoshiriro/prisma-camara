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
package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.PostNaoEnviado
import br.org.prismaCamara.modelo.UsuarioPostNaoEnviado
import br.org.prismaCamara.util.redes.FacebookUtil
import br.org.prismaCamara.util.redes.TwitterUtil

/**
 * Envia todas as postagens pendentes em {@link PostNaoEnviado} às redes sociais dos usuários.
 * Executado toda 3ª e 6ª, as 11:30:00
 * @author jyoshiriro
 */
@Log4j
class EnviarPostsJob {
	
    static triggers = {
	  // * horário de brasília+3
      cron name: 'enviarPostsTrigger', cronExpression: "0 30 14 ? * TUE,FRI"
      //cron name: 'enviarPostsTrigger', cronExpression: "0 30 8 * * ?"
    }
	
	def grailsApplication
	
	FacebookUtil facebookUtil
    TwitterUtil twitterUtil

    def execute() {
    	facebookUtil = new FacebookUtil()
    	twitterUtil = new TwitterUtil(grailsApplication:grailsApplication)
		
        for (upost in UsuarioPostNaoEnviado.list()) {
			def usuario = upost.usuario
			String conteudo = upost.postNaoEnviado.conteudo
			try {
				this."${usuario.tipoRede}Util".postar(usuario, conteudo)
				upost.delete(flush:true)
				log.debug("Postagem enviada com sucesso para ${usuario.username} - ${usuario.tipoRede}")
			} catch (Exception e) {
				log.error("Erro ao tentar enviar o post ${upost.id}: ${e.message}")
				upost.tentativas++
			}
		}
		
		PostNaoEnviado.executeUpdate("""
        delete from PostNaoEnviado p where p.id not in 
		(select up.postNaoEnviado.id from UsuarioPostNaoEnviado up)
		""")
		
		log.debug("Envio de postagens concluído com sucesso")
    }
}
