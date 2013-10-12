package hackathon2013

import groovy.util.logging.Log4j;

@Log4j
class Deputado {
	
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
	Date ultimoDiaGasto
	Date ultimoDiaDiscurso
	Boolean ativo
	
	Partido partido
	
	static hasMany = [comissoesTitular:Comissao, comissoesSuplente:Comissao, frequenciasDia:FrequenciaDia,discursos:Discurso, despesas:Despesa]
	
	static transients = ['siglaPartido','descricao','urlDetalhes','ultimaFrequencia']
	
	static constraints = {
		condicao(maxSize:20, nullable:true)
		nome(maxSize:60)
		nomeParlamentar(maxSize:40)
		sexo(maxSize:10, nullable:true)
		uf(maxSize:2)
		fone(maxSize:20, nullable:true)
		email(maxSize:60, nullable:true)
		ultimoDiaGasto(nullable:true) 
		ultimoDiaDiscurso(nullable:true) 
		
		ideCadastro(nullable:true) 
		matricula(nullable:true)
	}
	
	static mapping = {
		despesas(sort:'dataEmissao')
		discursos(sort:'horaInicio')
	}
	
	def beforeValidate() {
		
		// alguns nomes vêm com "-sigla/uf" ao final. Ex: JOSÉ RUELA-PX/SP
		
		def fimPartido = "${partido.sigla}/${uf}"
		def idFimNome = nome.indexOf(fimPartido)
		def idFimNomeParlamentar = nomeParlamentar.indexOf(fimPartido)

		if (idFimNome>=0)
			nome=nome.substring(0,idFimNome-1)
		if (idFimNomeParlamentar>=0)
			nomeParlamentar=nomeParlamentar.substring(0,idFimNomeParlamentar-1)
			
		def x = 0	
	}
	
	public String getSiglaPartido() {
		siglaPartido
	}
	
	public void setSiglaPartido(String siglaPartido) {
		siglaPartido = siglaPartido?.trim()
		this.siglaPartido=siglaPartido
		Partido nPartido = Partido.findBySigla(siglaPartido)
		if (!nPartido) {
			nPartido = new Partido(sigla:siglaPartido)
			nPartido.save()
			log.debug("Novo partido ${siglaPartido} criado")
		}
		partido=nPartido
	}
	
	public String getDescricao() {
		"${nomeParlamentar} (${partido.sigla}/${uf})"
	}
	
	public String getUrlDetalhes() {
		"${Parametro.findBySigla('url_biografia_deputado').valor}${ideCadastro}"
	}
	
	public FrequenciaDia getUltimaFrequencia() {
		frequenciasDia?.first()
	}
	
	
}
