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
class UrlMappings {

	static mappings = {
		
		"/painel" (controller: 'painel', action: 'index')
		"/docs" (controller: 'docs', action:'index')
		
		"/searchable/$action?" (controller: "erros", action: "naoEncontrado")
		"/searchable" (controller: "erros", action: "naoEncontrado")
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/" (view:"/index")
		"403" (controller: "erros", action: "acessoNegado")
		"404" (controller: "erros", action: "naoEncontrado")
		"405" (controller: "erros", action: "naoPermitido")
		"500" (view: '/error')
	}
}
