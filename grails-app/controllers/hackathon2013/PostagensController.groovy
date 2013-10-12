package hackathon2013

import br.org.prismaCamara.mensagens.Postagem
import br.org.prismaCamara.mensagens.PostagemBiografiaDeputado
import br.org.prismaCamara.mensagens.PostagemDiscursoDeputado
import br.org.prismaCamara.mensagens.PostagemFrequenciaDeputado
import br.org.prismaCamara.mensagens.PostagemGastoDeputado
import br.org.prismaCamara.mensagens.PostagemVotacaoProposicao

class PostagensController {

    def index() {
		[proposicoes:proposicoes]
	}
	
	def getProposicoes() {
		def proposicoesV = Votacao.executeQuery("select proposicao from Votacao") 
				//Proposicao.findAllByNumeroInList([190,300])
		return proposicoesV
	}
	
	def biografiaDeputado() {
		Postagem post = new PostagemBiografiaDeputado()
		flash.postagem = post.getTexto([tipo:Postagem.TIPO_FACE])
		render(view:'index')
	}
	
	def frequenciaDeputado(Long idDeputado) {
		Deputado deputado = Deputado.get(idDeputado)
		Postagem post = new PostagemFrequenciaDeputado() 
		flash.postagem = post.getTexto([dep:deputado,tipo:Postagem.TIPO_FACE])
		render(view:'index')
	}

	def gastoDeputado(Long idDeputado) {
		Deputado deputado = Deputado.get(idDeputado)
		Postagem post = new PostagemGastoDeputado()
		flash.postagem = post.getTexto([dep:deputado,tipo:Postagem.TIPO_FACE])
		render(view:'index')
	}
	
	def discursoDeputado(Long idDeputado) {
		Deputado deputado = Deputado.get(idDeputado)
		Postagem post = new PostagemDiscursoDeputado()
		flash.postagem = post.getTexto([dep:deputado,tipo:Postagem.TIPO_FACE])
		render(view:'index')
	}

	def votacaoProposicao(Long idProposicao) {
		Proposicao proposicao = Proposicao.get(idProposicao)
		Postagem post = new PostagemVotacaoProposicao()	
		flash.postagem = post.getTexto([prop:proposicao,tipo:Postagem.TIPO_FACE])
		render(view:'index',model:[proposicoes:proposicoes])
	}	
}
