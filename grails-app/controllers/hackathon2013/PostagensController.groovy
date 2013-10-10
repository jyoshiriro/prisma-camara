package hackathon2013

import br.org.prismaCamara.mensagens.Postagem
import br.org.prismaCamara.mensagens.PostagemBiografiaDeputado
import br.org.prismaCamara.mensagens.PostagemFrequenciaDeputado

class PostagensController {

    def index() { }
	
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
}
