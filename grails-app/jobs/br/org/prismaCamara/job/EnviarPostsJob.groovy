package br.org.prismaCamara.job

import br.org.prismaCamara.modelo.PostNaoEnviado;
import br.org.prismaCamara.util.FacebookUtil;
import groovy.util.logging.Log4j;


@Log4j
class EnviarPostsJob {
	
    static triggers = {
      cron name: 'enviarPostsTrigger', cronExpression: "30 0 0 * * ?"
      //cron name: 'enviarPostsTrigger', cronExpression: "0 30 8 * * ?"
    }
	
	FacebookUtil facebookUtil = new FacebookUtil()

    def execute() {
		
        for (post in PostNaoEnviado.list()) {
			def usuario = post.usuario
			String conteudo = post.conteudo
			try {
				this."${usuario.tipoRede}Util".postar(usuario, conteudo)
				post.delete(flush:true)
				log.debug("Postagem ${post.id} enviado com sucesso para ${usuario.username}")
			} catch (Exception e) {
				log.error("Erro ao tentar enviar o post ${post.id}: ${e.message}")
				post.tentativas++
				post.pendente=true
			}
		}
    }
}
