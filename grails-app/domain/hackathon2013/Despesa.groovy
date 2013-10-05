package hackathon2013

class Despesa {

	String txtDescricao
	String txtBeneficiario
	String txtCNPJCPF
	String txtNumero
	Date dataEmissao
	Double valor
	Integer numParcela 
	boolean enviado = false
	
	Deputado deputado
	
	static constraints = {
		txtDescricao(maxSize:1024)
		txtBeneficiario(maxSize:256)
		txtCNPJCPF(maxSize:16)
		txtNumero(maxSize:64)
		numParcela(nullable:true) 
	}
	
	static mapping = {
		deputado(cascade:'all')
	}
	
}
