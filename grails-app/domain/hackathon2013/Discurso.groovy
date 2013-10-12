package hackathon2013

import groovy.text.SimpleTemplateEngine

class Discurso {

	String codigo
	Date data
	Integer numeroSessao
	String cdFaseSessao
	Integer numeroOrador
	String horaInicio
	Integer numeroQuarto
	Integer numeroInsercao
	String sumario
	
	Deputado deputado
	
	static transients = ['urlDetalhes']
	
	static constraints = {
		codigo(maxSize:16)
		cdFaseSessao(maxSize:5)
		horaInicio(maxSize:8)
		sumario(maxSize:4096)
	}
	
	static mapping = {
		deputado(cascade:'all')
	}

	public String getUrlDetalhes() {

// http://www.camara.gov.br/internet/sitaqweb/TextoHTML.asp
// ?etapa=${etapa}&nuSessao=${nuSessao}&nuQuarto=${nuQuarto}&nuOrador=${nuOrador}&nuInsercao=${nuInsercao}
// &dtHorarioQuarto=${horario}&sgFaseSessao=${cdFaseSessao}&Data=${data}&txApelido=${nomeParlamentar}

		def texto = Parametro.findBySigla('url_discurso_deputado_dia').valor
		def valores = [etapa:numeroSessao,nuSessao:codigo,nuQuarto:numeroQuarto,nuOrador:numeroOrador,nuInsercao:numeroInsercao,horario:horaInicio,cdFaseSessao:cdFaseSessao,data:data.format('dd/MM/yyyy'),nomeParlamentar:deputado.nomeParlamentar] 
		Writable template = new SimpleTemplateEngine().createTemplate(texto).make(valores)
		template.toString()
	}
}




