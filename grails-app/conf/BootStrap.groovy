import org.quartz.SchedulerFactory

import br.org.prismaCamara.modelo.Parametro
import br.org.prismaCamara.modelo.Perfil
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioPerfil
import br.org.prismaCamara.modelo.Perfil.*
import br.org.prismaCamara.servico.atualizacoes.AtualizarDespesaService

class BootStrap {

	AtualizarDespesaService atualizarDespesas1 = new AtualizarDespesaService()
	
	def init = { servletContext ->

		inicializarSpringSecurity()

		// atualizacao de parametros
		verificacoesIniciais()

//		iniciarJobs(servletContext)
		// tarefas quartz iniciando

		// atualização dos cadastros dos deputados: toda 6a feira, 23h

		// atualização dos cadastros das proposições: todo dia, 18h

		// acompanhamento das proposiçõees: todo dia, 22h

		// acompanhamento das frequencias dos deputados: todo dia, 22h

		// acompanhamento dos gastos dos deputados: todo sábado, 6h (PERGUNTAR A FREQUENCIA DE ATUALIZAÇÃO DISSO)99
	}

	def destroy = { servletContext->
		// tarefas quartz morrendo
		SchedulerFactory fabricaAgendas = servletContext.getAttribute('agendas');
		fabricaAgendas.allSchedulers.each {
			it.shutdown(true)
		}
	}

	def verificacoesIniciais() {
		if (Parametro.count()==0) {
			throw new Exception("Não há nenhum registro na tabela de Parâmetros! Tente executar o SQL com os registros iniciais (insert-inicial-parametro.sql)")
		}
		//		AtualizarDeputadosService.URL=Parametro.findBySigla('url_listagem_deputados').valor
		//		AtualizarTiposProposicoesService.URL=Parametro.findBySigla('url_listagem_tipos_proposicoes').valor
		//		AtualizarProposicoesService.URL=Parametro.findBySigla('url_listagem_proposicoes').valor
		//		Deputado.URL_BIOGRAFIAS=Parametro.findBySigla('url_biografia_deputado')

	}

	def inicializarSpringSecurity() {
		def perfilAdministrador = Perfil.findOrSaveByAuthority('ROLE_ADMIN')
		def perfilUsuario = Perfil.findOrSaveByAuthority('ROLE_USER')

		String username = 'admininistrador'

		if (!Usuario.findByUsername(username)) {
			def testeUsuario = new Usuario(username: username, enabled: true, password: 'admin')
			testeUsuario.save()
			UsuarioPerfil.create tesetUsuario, perfilAdministrador, true
		}
	}

/*	public def iniciarJobs(servletContext) {
		
		SchedulerFactory fabricaAgendas = new StdSchedulerFactory();
		servletContext.setAttribute('agendas',fabricaAgendas);
		
		Scheduler agendaAtualizacoes = fabricaAgendas.getScheduler();

		agendaAtualizacoes.start();

		// Jobs (tarefas)
		AtualizarDespesaService atualizarDespesas = new AtualizarDespesaService() 
		JobDetail despesasJob = JobBuilder.newJob(AtualizarDespesa2Job.class)
				.withIdentity("despesaJob", "gAtualizacoes")
				.build();

		// Triggers (modalidades de agendamentos)
		Trigger tDiario01 = TriggerBuilder.newTrigger()
				.withIdentity("triggerDiaria", "gAtualizacoes")
				.startNow()
				.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(18, 53))
				.build();
				
		// Tell quartz to schedule the job using our trigger
		agendaAtualizacoes.scheduleJob(despesasJob, tDiario01);
	}*/
	
}
