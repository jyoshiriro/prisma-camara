package br.org.prismaCamara.mensagens

import br.org.prismaCamara.taglibs.postagens.GastoDeputadoTagLib
import hackathon2013.Deputado;
import hackathon2013.Despesa;

import java.util.Map;

class PostagemGastoDeputado extends Postagem {

	 /**
	 * Gera e retorna o texto de uma postagem de biografia de deputado
	 * @param params (dep:um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem ou <b>null</b> caso não exista nenhum gasto cadastrado para este deputado
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
