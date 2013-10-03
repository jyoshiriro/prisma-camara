package hackathon2013

class Proposicao {
	
	// segundo pesquisado no site do congresso e após teste nos web services
	static final Integer PRIMEIRO_ANO = 1934
	
	Integer idProposicao
	Integer numero
	String ano
	Date dataApresentacao
	String txtEmenta
	String txtExplicacaoEmenta
	String txtApreciacao
	
	TipoProposicao tipoProposicao
	Deputado autor
	String nomeAutor // em registros antigos não há relação, apenas o nome do autor

	Date ultimoDespacho	
	String txtUltimoDespacho	
	String situacao
	
	List<Votacao> votacoes
	
	static transients = ['PRIMEIRO_ANO']
	
	static mapping = {
		autor(cascade:'all')
		tipoProposicao(cascade:'all')
	}
	
	static constraints = {
		ano(maxSize:4)
		txtEmenta(maxSize:256)
		txtExplicacaoEmenta(maxSize:1024)
		txtApreciacao(maxSize:200)
		txtUltimoDespacho(maxSize:1024)
		situacao(maxSize:128) 
		
		autor(nullable:true)
		nomeAutor(nullable:true , maxSize:150)
	}
		
}
