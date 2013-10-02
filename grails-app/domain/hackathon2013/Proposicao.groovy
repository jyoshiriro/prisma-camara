package hackathon2013

class Proposicao {
	
	String nome
	String sigla
	String numero
	String ano
	Date dataApresentacao
	String txtEmenta
	String txtExplicacaoEmenta
	String txtApreciacao
	
	Deputado autor

	Date ultimoDespacho	
	String txtDespacho	
	String situacao
	
	List<Votacao> votacoes
	
	static mapping = {
		autor(cascade:'all')
	}
		
}
