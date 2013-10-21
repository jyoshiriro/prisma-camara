package br.org.prismaCamara.mensagem

import br.org.prismaCamara.modelo.Proposicao
import br.org.prismaCamara.modelo.Votacao

class PostagemVotacaoProposicao extends Postagem {

	 /**
	 * Gera e retorna o texto da última votação e seus votos de determinada Proposição
	 * @param params (prop: um {@link Proposicao}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		Proposicao prop = params.prop
		
		Votacao votacao = (prop.votacoes)?prop.votacoes.first():null
		if (!votacao)
			return null
		
		if ( (!prop.ultimaVotacao) || (prop.ultimaVotacao.before(votacao.dataHoraVotacao)))
			prop.ultimaVotacao=votacao.dataHoraVotacao
		
		// montando Mapa de tipos de votos e seus deputados
		def mvotos = [:] // ex: ['sim':['ze ruela','ze buduia','maria bigodenha'], 'nao':'tiririca']
		for (voto in votacao.votos) {
			def v = voto.voto
			if (mvotos["$v"]) {
				mvotos["$v"]=mvotos["$v"]+voto.deputado
			} else {
				mvotos["$v"]=[voto.deputado]
			}
		}
		
		// ordenando por nomes de deputados
		mvotos.each {
			it.value=it.value.sort{d1,d2 -> d1.nomeParlamentar<=>d2.nomeParlamentar}
		}
		
		// ordenando por quantidade de cada tipo de voto (descrescente)
		mvotos = mvotos.sort{v1,v2 -> v2.value.size()<=>v1.value.size()}
		
		params.votacao=votacao
		params.mvotos=mvotos
		String p = r.render(template:"/postagens/votacao-proposicao-${params.tipo}", model:params).toString()
		return p
	}
	

}
