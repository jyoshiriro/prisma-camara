package br.org.prismaCamara.servico.postagens

import grails.gsp.PageRenderer;
import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.PostNaoEnviado
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioPostNaoEnviado;

@Log4j
abstract class PrepararPost {
	
	PageRenderer groovyPageRenderer

	abstract String getNomeTipoInformacao()
	
	/**
	 * Preparar a mensagem (possivelmente salvar uma instância de {@link PostNaoEnviado} caso ainda não existir com os parâmetros indicados)
	 * @param usuario Instância de {@link Usuario}
	 * @param idEntidade Id do {@link Deputado} ou {@link Proposicao}
	 */
	abstract void preparar(Usuario usuario, Long idEntidade)
	
	/**
	 * Se não existir {@link PostNaoEnviado} semelhante cria-se uma nova instância para uma possível nova busca com o parâmetros informados
	 * @param usuario
	 * @param idEntidade
	 * @param postagem Conteúdo da postagem que deve ser criado caso o respectivo {@link PostNaoEnviado} ainda não existir
	 * @return
	 */
	protected boolean prepararPostagem(Usuario usuario, Long idEntidade, String conteudoPostagem) {
		if (!conteudoPostagem) {
			log.debug("Nenhuma Postagem nova de ${usuario.username} em ${nomeTipoInformacao}")
			return false
		}
		def post = PostNaoEnviado.findByHashAndPendente(PostNaoEnviado.getHashGerado(idEntidade,nomeTipoInformacao),false)
		try {
			if (!post) {
				post = new PostNaoEnviado(idEntidade:idEntidade,tipoInformacao:nomeTipoInformacao)
				post.conteudo=conteudoPostagem
				post.save(failOnError:true)
				
				UsuarioPostNaoEnviado upost = new UsuarioPostNaoEnviado(usuario:usuario, postNaoEnviado:post)
				upost.save(failOnError:true)
				
				log.debug("Postagem de ${usuario.id} em ${nomeTipoInformacao} ainda não existia... salva agora")
				return true
			} 
			UsuarioPostNaoEnviado upost = UsuarioPostNaoEnviado.findByUsuarioAndPostNaoEnviado(usuario,post)
			if (!upost) {
				upost = new UsuarioPostNaoEnviado(usuario:usuario, postNaoEnviado:post)
				upost.save(failOnError:true)
			}
			log.debug("Postagem de ${usuario.id} em ${nomeTipoInformacao} JÁ existia... recuperada")
			return true
		} catch (Exception e) {
			log.error("Erro ao preparara a Postagem de ${usuario.id} em ${nomeTipoInformacao}: ${e.message}")
			return false
		}
	}
	
}
