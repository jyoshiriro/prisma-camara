package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioDeputado
import br.org.prismaCamara.servico.UsuarioService
import br.org.prismaCamara.servico.postagens.PrepararPostFrequenciaDiaService


@Log4j
class PrepararEnvioFrequenciaDiaJob {
	
	PrepararPostFrequenciaDiaService prepararPostFrequenciaDiaService
	UsuarioService usuarioService
		
    static triggers = {
	  cron name: 'prepararPostsFrequenciaTrigger', cronExpression: "1 0 0 * * ?"
      //cron name: 'prepararPostsFrequenciaTrigger', cronExpression: "0 0 3 * * ?"
    }

    def execute() {
        def usuarios = Usuario.list()
		
		for (usuario in usuarios) {
			def deputados = usuarioService.getDeputadosDeUsuario(usuario,true)
			
			for (deputado in deputados) {
				prepararPostFrequenciaDiaService.preparar(usuario, deputado.id)
			}
			log.debug("Todas as postagens sobre frequencia dos deputados de ${usuario.username} já preparadas!")
		}
		log.debug("Todas as postagens sobre frequencia já preparadas!")
    }
}
