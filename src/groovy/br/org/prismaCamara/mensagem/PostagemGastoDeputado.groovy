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
package br.org.prismaCamara.mensagem

import br.org.prismaCamara.modelo.Deputado

class PostagemGastoDeputado extends Postagem {

	 /**
	 * Gera e retorna o texto de uma postagem de gastos de deputado
	 * @param params (dep:um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		if (params.dep.despesas.empty) {
			return null
		}
		Deputado dep = params.dep

		if ((!dep.ultimoDiaGasto) || (dep.ultimoDiaGasto.before(dep.despesas.last().dataEmissao)) ) {
			dep.ultimoDiaGasto=dep.despesas.last().dataEmissao	
		}

		params.despesas=dep.despesas
		String p = r.render(template:"/postagens/gasto-deputado-${params.tipo}", model:params).toString()
		return p
	}

}
