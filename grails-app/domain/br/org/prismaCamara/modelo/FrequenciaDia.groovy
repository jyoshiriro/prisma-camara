package br.org.prismaCamara.modelo

import groovy.text.SimpleTemplateEngine
import br.org.prismaCamara.util.URLUtil

class FrequenciaDia {
	
	Date dia
	String frequenciaDia
	String justificativa
	boolean enviado = false
		
	Deputado deputado
	
	static hasMany = [frequenciasSessao:FrequenciaSessao]
	
	static transients = ['urlDetalhes','urlDetalhesCurta']
	
	static mapping = {
		frequenciasSessao(sort:'inicio')
	}
	
	static constraints = {
		frequenciaDia(maxSize:30)
		justificativa(maxSize:1024, nullable:true)
	}
	
	public String getUrlDetalhesCurta() {
		URLUtil.getUrlCurta(urlDetalhes)
	}
	
	public String getUrlDetalhes() {
		// http://www.camara.leg.br/internet/deputado/RelPresencaPlenario.asp?nuLegislatura=54
		// &nuMatricula=${matricula}&dtInicio=${dtinicio}&dtFim=${dtfim}
		
		def texto = Parametro.findBySigla('url_frequencia_site').valor
		def dtinicio = "01/01/${new Date(dia.time).calendarDate.year}"
		def valores = [matricula:deputado.matricula,dtinicio:dtinicio,dtfim:dia.format('dd/MM/yyyy')]
		valores.each{
			it.value=it.value.toString().encodeAsURL()
		}
		Writable template = new SimpleTemplateEngine().createTemplate(texto).make(valores)
		def url = template.toString()
		url
	}
		
}
