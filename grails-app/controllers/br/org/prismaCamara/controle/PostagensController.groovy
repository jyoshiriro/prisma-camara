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
package br.org.prismaCamara.controle

import grails.gsp.PageRenderer;
import br.org.prismaCamara.mensagem.Postagem
import br.org.prismaCamara.mensagem.PostagemBiografiaDeputado
import br.org.prismaCamara.mensagem.PostagemDiscursoDeputado
import br.org.prismaCamara.mensagem.PostagemFrequenciaDeputado
import br.org.prismaCamara.mensagem.PostagemGastoDeputado
import br.org.prismaCamara.mensagem.PostagemVotacaoProposicao
import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Proposicao;
import br.org.prismaCamara.modelo.Votacao;
import br.org.prismaCamara.servico.UsuarioService;

class PostagensController {

	UsuarioService usuarioService
	PageRenderer groovyPageRenderer
	
    def index() {
		[proposicoes:proposicoes, deputados:deputados]
	}
	
	def getDeputados() {
		usuarioService.deputadosMapeados	
	}
	
	def getProposicoes() {
		def proposicoesV = usuarioService.proposicoesMapeadas //Votacao.executeQuery("select proposicao from Votacao") 
		return proposicoesV
	}
	
	def biografiaDeputado() {
		Postagem post = new PostagemBiografiaDeputado(r:groovyPageRenderer,usuarioService:usuarioService)
		flash.postagem1 = post.getTexto([tipo:Postagem.TIPO_FACE])
		flash.postagem2 = post.getTexto([tipo:Postagem.TIPO_TWITTER])
		render(view:'index')
	}
	
	def frequenciaDeputado(Long idDeputado) {
		Deputado deputado = Deputado.get(idDeputado)
		Postagem post = new PostagemFrequenciaDeputado(r:groovyPageRenderer) 
		flash.postagem1 = post.getTexto([dep:deputado,tipo:Postagem.TIPO_FACE])
		flash.postagem2 = post.getTexto([dep:deputado,tipo:Postagem.TIPO_TWITTER])
		render(view:'index')
	}

	def gastoDeputado(Long idDeputado) {
		Deputado deputado = Deputado.get(idDeputado)
		Postagem post = new PostagemGastoDeputado(r:groovyPageRenderer)
		flash.postagem1 = post.getTexto([dep:deputado,tipo:Postagem.TIPO_FACE])
		flash.postagem2 = post.getTexto([dep:deputado,tipo:Postagem.TIPO_TWITTER])
		render(view:'index')
	}
	
	def discursoDeputado(Long idDeputado) {
		Deputado deputado = Deputado.get(idDeputado)
		Postagem post = new PostagemDiscursoDeputado(r:groovyPageRenderer)
		flash.postagem1 = post.getTexto([dep:deputado,tipo:Postagem.TIPO_FACE])
		flash.postagem2 = post.getTexto([dep:deputado,tipo:Postagem.TIPO_TWITTER])
		render(view:'index')
	}

	def votacaoProposicao(Long idProposicao) {
		Proposicao proposicao = Proposicao.get(idProposicao)
		Postagem post = new PostagemVotacaoProposicao(r:groovyPageRenderer)	
		flash.postagem1 = post.getTexto([prop:proposicao,tipo:Postagem.TIPO_FACE])
		flash.postagem2 = post.getTexto([prop:proposicao,tipo:Postagem.TIPO_TWITTER])
		render(view:'index',model:[proposicoes:proposicoes,deputados:deputados])
	}	
}
