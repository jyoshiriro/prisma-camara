package br.org.prismaCamara.servico.postagens

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.PostNaoEnviado
import br.org.prismaCamara.modelo.Usuario

@Log4j
abstract class PrepararPost {

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
	protected PostNaoEnviado prepararPostagem(Usuario usuario, Long idEntidade, String conteudoPostagem) {
		def post = PostNaoEnviado.findByHashAndPendente(PostNaoEnviado.getHashGerado(idEntidade,nomeTipoInformacao,usuario),false)
		try {
			if (!post) {
				post = new PostNaoEnviado(idEntidade:idEntidade,tipoInformacao:nomeTipoInformacao,usuario:usuario)
				post.conteudo=conteudoPostagem
				post.save(flush:true)
				log.debug("Postagem de ${usuario.id} em ${nomeTipoInformacao} ainda não existia... salva agora")
			} else {
				log.debug("Postagem de ${usuario.id} em ${nomeTipoInformacao} JÁ existia... recuperada")
			}
			post
		} catch (Exception e) {
			log.error("Erro ao preparara a Postagem de ${usuario.id} em ${nomeTipoInformacao}: ${e.message}")
			e.printStackTrace()
		}
	}
	
}
