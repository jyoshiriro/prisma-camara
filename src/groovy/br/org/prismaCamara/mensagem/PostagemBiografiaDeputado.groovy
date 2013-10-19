package br.org.prismaCamara.mensagem

import java.util.Map;

import br.org.prismaCamara.modelo.Deputado;

class PostagemBiografiaDeputado extends Postagem {
	
	private Deputado getDeputadoAleatorio() {
		def quantAtivos = Deputado.countByAtivo(true)
		def id = new Random().nextInt(quantAtivos.toInteger())-1
		def deputado = Deputado.list(max:1,offset:id).first()
		deputado
	}

	 /**
	 * Gera e retorna o texto de uma postagem de biografia de deputado
	 * @param params (tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		params.dep = deputadoAleatorio
		
		String p = r.render(template:"/postagens/biografia-deputado-${params.tipo}", model:params).toString()
		p
	}

}
