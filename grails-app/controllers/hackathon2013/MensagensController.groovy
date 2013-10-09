package hackathon2013

import br.org.prismaCamara.mensagens.Postagem;
import br.org.prismaCamara.mensagens.impl.PostagemBiografiaDeputadoFacebook

class MensagensController {

    def index() { }
	
	def biografiaDeputado() {
		Postagem post = new PostagemBiografiaDeputadoFacebook()
		flash.message = post.getTexto(0)
		redirect(action:'index')
	}
}
