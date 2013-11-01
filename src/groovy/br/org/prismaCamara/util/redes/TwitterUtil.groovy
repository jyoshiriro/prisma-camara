/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.util.redes

import grails.plugins.rest.client.RestBuilder
import groovy.util.logging.Log4j

import org.springframework.social.twitter.api.Twitter
import org.springframework.social.twitter.api.impl.TwitterTemplate

import br.org.prismaCamara.modelo.Parametro
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioTwitter

@Log4j
class TwitterUtil {
	
	def grailsApplication

	void postar(Usuario usuario, String conteudo) {
		try {
			UsuarioTwitter utwitter = UsuarioTwitter.where{user==usuario}.find()
	
			Properties prop = new Properties()
			def nomeArquivoConf = grailsApplication.config.grails.config.locations[1].substring(5)
			prop.load(new ByteArrayInputStream(new File(nomeArquivoConf).bytes))
			def consumerKey = prop["grails.plugins.springsecurity.twitter.consumerKey"]
			def consumerSecret = prop["grails.plugins.springsecurity.twitter.consumerSecret"]
			
			Twitter twitter = new TwitterTemplate(consumerKey,consumerSecret,utwitter.token,utwitter.tokenSecret)
			def conteudos = []
			def ctemp = ''
			conteudo.eachLine {
				def linha = it
			    if (linha=='<hr>') {
			        conteudos+=ctemp
			        ctemp=''
			    } else {
			        ctemp+=linha+"\n"
			    }
			}
			for (postc in conteudos) {
				def post = postc[0..postc.size()-2]
				if (post.size()>140) {
					log.error("Uma mensagem para o twitter tinha mais de 140 letras e foi truncada: \n${conteudo}\n\n")
					post = post[0..139]
				}
				twitter.timelineOperations().updateStatus(post)
			}
			
			log.debug("Mensagem '${conteudo[0..79].trim()}' enviada com sucesso para ${usuario.username}")
		} catch (Exception e) {
			log.error("Erro ao postar mensagem para rede social ${usuario?.tipoRede} de ${usuario?.username}: ${e.message}")
			throw e
		} 
	}
	
	static main(args) {
		def resp = new RestBuilder().post('https://api.twitter.com/oauth/request_token'){
			contentType "application/json"
			accept "application/json"
			json longUrl: "${urlLonga}"
		}

	}
}
