package br.org.prismaCamara.modelo

import org.apache.commons.lang.WordUtils;

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
	String fone
	String email
	Date ultimoDiaGasto // essa data pode ser diferente por deputado, por isso não é como o "ultimo_dia_frequencia" na tabela de parâmetros
	Date ultimoDiaDiscurso // essa data pode ser diferente por deputado, por isso não é como o "ultimo_dia_frequencia" na tabela de parâmetros
	Boolean ativo
	
	Partido partido
	
	static hasMany = [comissoesTitular:Comissao, comissoesSuplente:Comissao, frequenciasDia:FrequenciaDia, discursos:Discurso, despesas:Despesa]
	
	static transients = ['siglaPartido', 'descricao', 'descricaoSemCaixaAlta', 'urlDetalhes', 'ultimaFrequencia', 'urlFoto','contatos']
	
	static searchable = [only: ['nome', 'nomeParlamentar']]
	
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
	
	def afterUpdate() {
		if (!(this.ativo)) {
			UsuarioDeputado.executeUpdate("delete from UsuarioDeputado ud where ud.deputado.id=?",[this.id])
		}	
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
	
	public String getDescricaoSemCaixaAlta() {		
		"${WordUtils.capitalizeFully(nomeParlamentar)} (${partido.sigla}/${uf})"
	}
	
	public String getUrlDetalhes() {
		"${Parametro.findBySigla('url_biografia_deputado').valor}${ideCadastro}"
	}
	
	public FrequenciaDia getUltimaFrequencia() {
		frequenciasDia?frequenciasDia.first():null
	}
	
	public String getUrlFoto() {
		"${Parametro.findBySigla('url_foto_deputado').valor}${ideCadastro}.jpg"
	}
	
	public String getContatos() {
		"${email} / (61) ${fone}"
	}
		
}
