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

import br.org.prismaCamara.util.URLUtil

class Despesa {

	String txtDescricao
	String txtBeneficiario
	String txtCNPJCPF
	String txtNumero
	Date dataEmissao
	Double valor
	Double valorGlosa
	Integer numParcela 
	boolean enviado = false
	
	static belongsTo = [deputado:Deputado]
	
	static transients = ['urlDetalhes','urlDetalhesCurta','descDoc','valorLiquido']
	
	static constraints = {
		txtDescricao(maxSize:1024)
		txtBeneficiario(maxSize:256)
		txtCNPJCPF(maxSize:16)
		txtNumero(maxSize:64)
		numParcela(nullable:true) 
		valorGlosa(nullable:true) 
	}
	
	static mapping = {
	}

	public String getUrlDetalhes() {
		def urlOriginal = "${Parametro.findBySigla('url_gastos_site').valor}${deputado.ideCadastro}"
		def urlFinal = URLUtil.getURLDetalhesCotaDeputado(urlOriginal)
		
		urlFinal += "&mesAnoConsulta=${dataEmissao.format('MM-yyyy')}"
		urlFinal
	}
	
	public String getUrlDetalhesCurta() {
		URLUtil.getUrlCurta(urlDetalhes)
	}

	
	public String getDescDoc() {
		if (!txtCNPJCPF)
			return ""
			
		if (txtCNPJCPF.trim().length()==11) {
			// 999.999.999-99
			"CPF ${txtCNPJCPF[0..2]}.${txtCNPJCPF[3..5]}.${txtCNPJCPF[6..8]}-${txtCNPJCPF[9..10]}"
		} else {
			// 99.999.999/9999-99
			"CNPJ ${txtCNPJCPF[0..1]}.${txtCNPJCPF[2..4]}.${txtCNPJCPF[5..7]}/${txtCNPJCPF[8..11]}-${txtCNPJCPF[12..13]}"
		}
	}

	public Double getValorLiquido() {
		valor-valorGlosa?:0.0
	}	
	
}
