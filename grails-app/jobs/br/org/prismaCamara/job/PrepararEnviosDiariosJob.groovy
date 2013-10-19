package br.org.prismaCamara.job

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioDeputado
import br.org.prismaCamara.servico.UsuarioService
import br.org.prismaCamara.servico.postagens.PrepararPostDespesaService;
import br.org.prismaCamara.servico.postagens.PrepararPostFrequenciaDiaService


@Log4j
class PrepararEnviosDiariosJob {
	
	UsuarioService usuarioService
	
	PrepararPostFrequenciaDiaService prepararPostFrequenciaDiaService
	PrepararPostDespesaService prepararPostDespesaService
		
    static triggers = {
	  cron name: 'prepararPostsDiariosTrigger', cronExpression: "1 0 0 * * ?"
      //cron name: 'prepararPostsFrequenciaTrigger', cronExpression: "0 0 8 * * ?"
    }

    def execute() {
        def usuarios = Usuario.list()
		
		for (usuario in usuarios) {
			def deputados = usuarioService.getDeputadosDeUsuario(usuario)

			// posts relativos a deputados
			for (deputado in deputados) {
				prepararPostFrequenciaDiaService.preparar(usuario, deputado.id)
				prepararPostDespesaService.preparar(usuario, deputado.id)
				log.debug("Postagens a sobre frequencia do deputado ${deputado.descricao} de ${usuario.username} preparada!")
			}
			log.debug("Todas as postagens sobre deputados de ${usuario.username} já preparadas!")
			
			// TODO: posts relativos a prepisições
		}
		log.debug("Todas as postagens de todos os usuários já preparadas!")
    }
}
