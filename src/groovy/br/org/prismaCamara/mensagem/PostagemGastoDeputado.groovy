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
