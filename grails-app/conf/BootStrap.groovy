import groovy.util.logging.Log4j
import hackathon2013.AtualizarDeputadosService
import hackathon2013.AtualizarTiposProposicoesService
import hackathon2013.Deputado
import hackathon2013.Parametro

@Log4j
class BootStrap {

    def init = { servletContext ->
		// atualizacao de parametros
		atualizarTodos()
		
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
		if (Parametro.count()==0) {
			throw new Exception("Não há nenhum registro na tabela de Parâmetros! Tente executar o SQL com os registros iniciais (insert-inicial-parametro.sql)")
		}
		AtualizarDeputadosService.URL=Parametro.findBySigla('url_listagem_deputados').valor
		AtualizarTiposProposicoesService.URL=Parametro.findBySigla('url_listagem_tipos_proposicoes').valor
		Deputado.URL_BIOGRAFIAS=Parametro.findBySigla('url_biografia_deputado')
	}
}
