import org.quartz.SchedulerFactory

import br.org.prismaCamara.modelo.Parametro
import br.org.prismaCamara.modelo.Perfil
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioPerfil
import br.org.prismaCamara.modelo.Perfil.*
import br.org.prismaCamara.servico.atualizacoes.AtualizarDespesaService
import groovy.sql.Sql
import groovy.util.logging.Log4j;

@Log4j
class BootStrap {

	def dataSource
	
	def init = { servletContext ->

		verificacoesIniciais()
		
		inicializarSpringSecurity()

	}

	def destroy = { servletContext->
		// tarefas quartz morrendo
		SchedulerFactory fabricaAgendas = servletContext.getAttribute('agendas');
		fabricaAgendas.allSchedulers.each {
			it.shutdown(true)
		}
	}

	def verificacoesIniciais() {
		if (Parametro.count()>=12) {
			println "Os Parâmetros parecem estar corretamente iniciados"
			return 
		}
		try {
			println "Os Parâmetros não parecem estar corretamente iniciados... iniciando realimentação"
			Sql sql = new Sql(dataSource)
			sql.executeUpdate("truncate table parametro")
			def sqlInicial = new File('scripts-iniciais.sql').text
			
			sqlInicial.eachLine {
				if (it.trim()) {
					println "Executando... ${it}"
					sql.executeInsert(it)
				}
			}
			println "Parâmetros configurados com sucesso"
		}
		catch (e) {
			log.error("Falha ao tentar executar o SQL com os registros iniciais (scripts-iniciais.sql): ${e.message}")
			e.printStackTrace()
		}

	}

	def inicializarSpringSecurity() {
		def perfilAdministrador = Perfil.findOrSaveByAuthority('ROLE_ADMIN')
		def perfilUsuario = Perfil.findOrSaveByAuthority('ROLE_USER')

		String username = 'admininistrador'

		if (!Usuario.findByUsername(username)) {
			def testeUsuario = new Usuario(username: username, enabled: true, password: 'admin')
			testeUsuario.save()
			UsuarioPerfil.create testeUsuario, perfilAdministrador, true
		}
	}
	
}
