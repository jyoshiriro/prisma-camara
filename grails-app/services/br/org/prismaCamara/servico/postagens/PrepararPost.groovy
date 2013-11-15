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
package br.org.prismaCamara.servico.postagens

import java.util.Map;

import grails.gsp.PageRenderer;
import groovy.util.logging.Log4j
import br.org.prismaCamara.mensagem.Postagem
import br.org.prismaCamara.modelo.PostNaoEnviado
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioPostNaoEnviado;

@Log4j
abstract class PrepararPost {
	
	PageRenderer groovyPageRenderer

	abstract String getNomeTipoInformacao()
	
	/**
	 * Define a subclasse de {@link Postagem} a ser usada
	 * @return
	 */
	abstract Postagem getPostagem()
	
	private String getConteudo(Map params) {
		def postagemp = postagem
		postagemp.r=groovyPageRenderer
		postagemp.getTexto(params)
	}
	
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
	 * @param params {@link Map} de parâmetros passados para o respectiva Subclasse de {@link Postagem}
	 * @return
	 */
	protected boolean prepararPostagem(Usuario usuario, Long idEntidade, Map params) {
		def conteudoPostagem = getConteudo(params)
		if (!conteudoPostagem) {
			log.debug("Nenhuma Postagem nova de ${usuario.username} em ${nomeTipoInformacao}")
			return false
		}
		
		def hashTeste = PostNaoEnviado.getHashGerado(idEntidade,nomeTipoInformacao,usuario.tipoRede)
		def post = PostNaoEnviado.findByHash(hashTeste)
		try {
			if (!post) {
				post = new PostNaoEnviado(idEntidade:idEntidade,tipoInformacao:nomeTipoInformacao)
				post.tipoRede=usuario.tipoRede
				post.conteudo=conteudoPostagem
				post.save(failOnError:true, flush:true)
				
				UsuarioPostNaoEnviado upost = new UsuarioPostNaoEnviado(usuario:usuario, postNaoEnviado:post)
				upost.save(failOnError:true, flush:true)
				
				log.debug("Postagem de ${usuario.id} em ${nomeTipoInformacao} ainda não existia... salva agora")
				return true
			} 
			UsuarioPostNaoEnviado upost = UsuarioPostNaoEnviado.findByUsuarioAndPostNaoEnviado(usuario,post)
			if (!upost) {
				upost = new UsuarioPostNaoEnviado(usuario:usuario, postNaoEnviado:post)
				upost.save(failOnError:true, flush:true)
			}
			log.debug("Postagem de ${usuario.id} em ${nomeTipoInformacao} JÁ existia... recuperada")
			return true
		} catch (Exception e) {
			log.error("Erro ao preparara a Postagem de ${usuario.id} em ${nomeTipoInformacao}: ${e.message}")
			return false
		}
	}
	
}
