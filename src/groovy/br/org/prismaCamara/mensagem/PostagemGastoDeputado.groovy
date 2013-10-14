package br.org.prismaCamara.mensagem

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.taglibs.postagens.GastoDeputadoTagLib

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

		if (dep.ultimoDiaGasto.before(dep.despesas.last().dataEmissao)) {
			dep.ultimoDiaGasto=dep.despesas.last().dataEmissao	
		}

		params.despesas=dep.despesas
		String mensagem = new GastoDeputadoTagLib().getConteudo(params)
		return mensagem
	}

}
