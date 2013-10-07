package hackathon2013

import groovy.util.logging.Log4j;

@Log4j
class Deputado {
	
	static String URL_BIOGRAFIAS = "http://www.camara.leg.br/internet/Deputado/dep_Detalhe.asp?id="
	
	Integer ideCadastro
	String condicao
	Integer matricula
	String nome
	String nomeParlamentar
	String sexo
	String uf
	String siglaPartido
	String fone
	String email
	Boolean ativo
	
	Partido partido
	
	static hasMany = [comissoesTitular:Comissao, comissoesSuplente:Comissao, frequenciasDia:FrequenciaDia]
	
	static transients = ['urlBiografia','siglaPartido']
	
	static constraints = {
		condicao(maxSize:20, nullable:true)
		nome(maxSize:60)
		nomeParlamentar(maxSize:40)
		sexo(maxSize:10, nullable:true)
		uf(maxSize:2)
		fone(maxSize:20, nullable:true)
		email(maxSize:60, nullable:true)
		
		ideCadastro(nullable:true) 
		matricula(nullable:true)
	}
	
	String getUrlBiografia() {
		"${URL_BIOGRAFIAS}${ideCadastro}"
	}
	
	public String getSiglaPartido() {
		siglaPartido
	}
	
	public void setSiglaPartido(String siglaPartido) {
		this.siglaPartido=siglaPartido
		Partido nPartido = Partido.findBySigla(siglaPartido)
		if (!nPartido) {
			nPartido = new Partido(sigla:siglaPartido)
			nPartido.save()
			log.debug("Novo partido ${siglaPartido} criado")
		}
		partido=nPartido
	}
}
