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

import groovy.util.logging.Log4j

import org.apache.commons.lang.WordUtils

import br.org.prismaCamara.util.ImagensUtil
import br.org.prismaCamara.util.PesquisaFoneticaUtil;
import br.org.prismaCamara.util.StringUtil;
import br.org.prismaCamara.util.URLUtil

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
	String siglaPartido // manter por causa dos get e set
	
	Partido partido
	
	String campoPesquisa
	
	static hasMany = [comissoesTitular:Comissao, comissoesSuplente:Comissao, frequenciasDia:FrequenciaDia, discursos:Discurso, despesas:Despesa]
	
	static transients = ['siglaPartido', 'descricao', 'descricaoSemCaixaAlta', 'urlDetalhes', 'urlDetalhesCurta','ultimaFrequencia', 'urlFoto','contatos','foto']
	
	static searchable = [only: ['campoPesquisa']]
	
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
		
		partido(nullable:true)
		
		ideCadastro(nullable:true) 
		matricula(nullable:true)
		
		campoPesquisa maxSize: 256
	}
	
	static mapping = {
		despesas(sort:'dataEmissao')
		discursos(sort:'horaInicio')
		frequenciasDia(sort:'dia',order:'desc')
		sort('nomeParlamentar')
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
			this.unindex()
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
	
	public String getUrlDetalhesCurta() {
		URLUtil.getUrlCurta(urlDetalhes)
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
	
	/**
	 * Array de bytes da miniatura da foto
	 * @return
	 */
	public byte[] getFoto() {
		//def dirImagens = Parametro.findBySigla('dir_miniaturas').valor
		//dirImagens += StringUtil.terminaCom(dirImagens, "/", "\\") ? "" : "/"
		def nomeArquivo = "${this.ideCadastro}.jpg"
		log.debug "Arquivo: ${nomeArquivo}"
		def bmini = ImagensUtil.getImagemLocal(nomeArquivo)
		
		// a miniatura local provavelmente existirá por causa da Atualização dos Deputados, mas, em todo caso...
		if (!bmini) {
			bmini = ImagensUtil.getMiniatura(this.getUrlFoto(), nomeArquivo)
		}
		bmini
	}
	
	String getCampoPesquisa() {
		return PesquisaFoneticaUtil.getFonemasParaIndexar(nome + " " + nomeParlamentar + " " + uf + " " + partido.sigla)
	}
		
}
