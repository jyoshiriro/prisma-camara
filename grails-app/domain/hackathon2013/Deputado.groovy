package hackathon2013

class Deputado {
	
	static String URL_BIOGRAFIAS = "http://www.camara.leg.br/internet/Deputado/dep_Detalhe.asp?id="
	
	Integer ideCadastro
	String condicao
	Integer matricula
	String nome
	String nomeParlamentar
	String sexo
	String uf
	String partido
	String fone
	String email
	Boolean ativo
	
	List<FrequenciaDia> frequenciasDia
	
	static hasMany = [comissoesTitular:Comissao, comissoesSuplente:Comissao]
	
	static transients = ['urlBiografia']
	
	String getUrlBiografia() {
		"${URL_BIOGRAFIAS}${ideCadastro}"
	}
}
