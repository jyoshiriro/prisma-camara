package hackathon2013

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

@Log4j
class AtualizarController {
	
	RestBuilder rest = new RestBuilder()
	
	def atualizarDeputadosService

    def index() {
		
	}
	
	def deputados() {
		try {
			def urlx = atualizarDeputadosService.URL_ATUALIZACAO
			atualizarDeputadosService.atualizar(getXML(urlx))
			flash.message="Cadastro de Deputados atualizado com Sucesso"
		} catch (Exception e) {
			e.printStackTrace()
			flash.error="Erro: ${e.message}"
		}
		redirect(action:'index')
	}
	
	private GPathResult getXML(String url) {
		def respostaTmp = rest.get(url)
		if (respostaTmp instanceof RestResponse) {
			RestResponse resposta = respostaTmp as RestResponse
			return resposta.xml
		} else {
			def msg = "Não foi possível recuperar o XML da resposta para ${url}: ${respostaTmp.text}"
			log.error(msg)
			throw new Exception(msg)
		}
	}
	
	Map getListas() {
		[Deputados:'http://www.camara.gov.br/SitCamaraWS/Deputados.asmx/ObterDeputados',Proposicoes:'http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?sigla=PEC&numero=&ano=&datApresentacaoIni=&datApresentacaoFim=&autor=&parteNomeAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=']
	}
	
}
