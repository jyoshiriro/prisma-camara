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
package br.org.prismaCamara.taglibs

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import br.org.prismaCamara.modelo.Usuario;

class UsuarioTagLib {
	
	def springSecurityService

	/**
	 * Retorna o nome do Usuário atual da sessão
	 */
	def nomeUsuario = {
		def nome = ""
		Usuario usuario = usuarioAtual
		nome = (usuario?.nome)?:usuario.username
		out << nome
	}
	
	/**
	 * Retorna o nome da rede social do Usuário atual da sessão
	 */
	def tipoRedeUsuario = {
		def tipo = usuarioAtual?.tipoRede
		out << tipo
	}
	
	private Usuario getUsuarioAtual() {
		Usuario usuario = null
		if (SpringSecurityUtils.ifAllGranted('ROLE_USER')) {
			usuario = springSecurityService.getCurrentUser()
		}
		return usuario
	}
	
}
