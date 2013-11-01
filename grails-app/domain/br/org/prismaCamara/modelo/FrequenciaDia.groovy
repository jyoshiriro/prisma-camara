/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
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
