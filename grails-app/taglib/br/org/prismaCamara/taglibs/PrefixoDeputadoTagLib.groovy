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

class PrefixoDeputadoTagLib {
	
	/**
	 * Renderiza "O Deputado" ou "A Deputada" conforme o sexo do deputado informado, segui do seu "nomeParlamentar".
	 * Caso o sexo esteja vazio, o retorno será "O(A) Deputado(a)"
	 * Renderiza o texto a partir de "_frequencia-deputado-xxx.gsp"
	 * @attr dep REQUIRED Instância de {@link Deputado}
	 * @attr minusculo Se vazio ou <b>false</b>, o "O" ou "A" virão em maiúsculo, caso contrário, virão minúsculo
	 */
	String deputadoPrefix = { attrs ->
		def pref = (attrs.dep.sexo)?(attrs.dep.sexo?.startsWith('m')?'o Deputado':'a Deputada'):'o(a) Deputado(a)'
		pref = "${attrs.minusculo?pref:pref.capitalize()} ${attrs.dep.descricao}"
		out << pref
	}
}
