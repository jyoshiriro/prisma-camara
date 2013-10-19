package br.org.prismaCamara.servico.postagens

import grails.gsp.PageRenderer
import groovy.util.logging.Log4j
import br.org.prismaCamara.mensagem.PostagemFrequenciaDeputado
import br.org.prismaCamara.mensagem.PostagemGastoDeputado
import br.org.prismaCamara.modelo.Deputado
import br.org.prismaCamara.modelo.Usuario

@Log4j
class PrepararPostDespesaService extends PrepararPost {

	def usuarioService
	
	@Override
	public String getNomeTipoInformacao() {
		return "despesa";
	}
	
	@Override
	public void preparar(Usuario usuario, Long idEntidade) {
		def postagemPreparada = prepararPostagem(usuario, idEntidade, new PostagemGastoDeputado(r:groovyPageRenderer).getTexto([dep:Deputado.get(idEntidade),tipo:usuario.tipoRede]))
		if (postagemPreparada)
			log.debug("Postagem de Despesa preparada com sucesso para ${usuario.id} em ${nomeTipoInformacao} (idEntidade: ${idEntidade})")
	}

}
