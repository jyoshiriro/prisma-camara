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
		"${Parametro.findBySigla('url_gastos_site').valor}${deputado.ideCadastro}"
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
