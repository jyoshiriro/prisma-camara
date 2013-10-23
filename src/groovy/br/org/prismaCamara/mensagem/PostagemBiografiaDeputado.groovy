package br.org.prismaCamara.mensagem

import br.org.prismaCamara.servico.UsuarioService

class PostagemBiografiaDeputado extends Postagem {
	
	UsuarioService usuarioService

	 /**
	 * Gera e retorna o texto de uma postagem de biografia de deputado
	 * @param params (dep: um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		if (!params.dep) 
			params.dep = usuarioService.deputadoAleatorio.id
		
		String p = r.render(template:"/postagens/biografia-deputado-${params.tipo}", model:params).toString()
		p
	}

}
