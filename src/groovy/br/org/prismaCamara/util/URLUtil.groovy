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
package br.org.prismaCamara.util

import br.org.prismaCamara.modelo.UrlCurta

/**
 * Classe para tratamento de URL (principalmente para encurtá-las)
 * @author jyoshiriro
 */
class URLUtil {

	static Map cache = [:]
	
	/**
	 * Retorna a URL curta a partir de uma longa, usando o "getUrlCurta()" de {@link UrlCurta}.  
	 * @param urlLonga
	 * @return
	 */
	static String getUrlCurta(String urlLonga) {
		UrlCurta.getUrlCurta(urlLonga)	
	}
	
	/**
	 * Recupera a URL detalhes de cota de Deputado. 
	 * Método necessário pois o site da Câmara faz um 302 quando se faz uma chamada à URL original gerando um nova parâmetro "nuDeputadoId" 
	 * que não existe em nenhum lugar nos "dados abertos". 
	 * @param urlTemporaria
	 * @return
	 */
	static String getURLDetalhesCotaDeputado(String urlTemporaria) {
		// teve que ser via comando de SO, pois com código Java ou Groovy os códigos de resposta não eram os mesmos, de jeito algum!
		Process p = Runtime.getRuntime().exec("curl --head ${urlTemporaria}")
		p.waitFor()
		def linhas = p.inputStream.text
		def novaUrl = ""
		linhas.eachLine {
			if (it.toLowerCase().startsWith("location")) {
				novaUrl = it.substring(it.indexOf(":")+2)
				return
			}
		}
		novaUrl
	}
	
}
