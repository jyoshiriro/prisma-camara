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
package br.org.prismaCamara.mensagem


import br.org.prismaCamara.modelo.FrequenciaDia
import br.org.prismaCamara.modelo.Parametro

class PostagemFrequenciaDeputado extends Postagem {

	 /**
	 * Gera e retorna o texto de uma postagem de frequência de deputado
	 * @param params (dep:um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		if (!params.dep.ultimaFrequencia)
			return null
			
		FrequenciaDia freq = params.dep.ultimaFrequencia
		
		Parametro pData = Parametro.findBySigla('ultimo_dia_frequencia')
		if (pData) {
			if (freq.dia.after(Date.parse('dd/MM/yyyy',pData.valor))) {
				pData.valor=freq.dia.format('dd/MM/yyyy')
			}
		} else {
			pData = new Parametro(sigla: 'ultimo_dia_frequencia', valor: freq.dia.format('dd/MM/yyyy'), descricao: 'Última atualização de frequências')
			pData.save()
		}
		int qsessoes = freq.frequenciasSessao.size()
		params.freq=freq
		params.qsessoes=qsessoes
		params.souma=(qsessoes==1)
		String p = r.render(template:"/postagens/frequencia-deputado-${params.tipo}", model:params).toString()
		return p
	}

}
