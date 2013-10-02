import hackathon2013.Deputado
import hackathon2013.Parametro

class BootStrap {

    def init = { servletContext ->
		// atualizacao de parametros
		atualizarTodos();
		
		// tarefas quartz iniciando
		
		// atualização dos cadastros dos deputados: toda 6a feira, 23h
		
		// atualização dos cadastros das proposições: todo dia, 18h
		
		// acompanhamento das proposiçõees: todo dia, 22h
		
		// acompanhamento das frequencias dos deputados: todo dia, 22h
		
		// acompanhamento dos gastos dos deputados: todo sábado, 6h (PERGUNTAR A FREQUENCIA DE ATUALIZAÇÃO DISSO)
    }
    def destroy = {
   		// tarefas quartz morrendo
    }
	
	def atualizarTodos() {
		Deputado.URL_BIOGRAFIAS=Parametro.findBySigla("url_biografia_deputado")
	}
}
