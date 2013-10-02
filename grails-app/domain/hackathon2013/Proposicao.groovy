package hackathon2013

class Proposicao {
	
	Integer idProposicao
	Integer numero
	String ano
	Date dataApresentacao
	String txtEmenta
	String txtExplicacaoEmenta
	String txtApreciacao
	
	Deputado autor

	Date ultimoDespacho	
	String txtUltimoDespacho	
	String situacao
	
	List<Votacao> votacoes
	
	static mapping = {
		autor(cascade:'all')
	}
	
	static constraints = {
		ano(maxSize:4)
		txtEmenta(maxSize:256)
		txtExplicacaoEmenta(maxSize:1024)
		txtApreciacao(maxSize:200)
		txtUltimoDespacho(maxSize:1024)
		situacao(maxSize:128) 
	}
		
}
