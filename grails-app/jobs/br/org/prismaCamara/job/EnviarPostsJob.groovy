package br.org.prismaCamara.job

import br.org.prismaCamara.modelo.PostNaoEnviado;
import br.org.prismaCamara.modelo.UsuarioPostNaoEnviado;
import br.org.prismaCamara.util.redes.FacebookUtil;
import br.org.prismaCamara.util.redes.TwitterUtil;
import groovy.util.logging.Log4j;


@Log4j
class EnviarPostsJob {
	
    static triggers = {
      cron name: 'enviarPostsTrigger', cronExpression: "30 0 0 * * ?"
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
				upost.postNaoEnviado.tentativas++
			}
		}
		
		PostNaoEnviado.executeUpdate("""
        delete from PostNaoEnviado p where p.id not in 
		(select up.postNaoEnviado.id from UsuarioPostNaoEnviado up)
		""")
		
		log.debug("Envio de postagens concluído com sucesso")
    }
}
