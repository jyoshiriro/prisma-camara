package br.org.prismaCamara.mensagens

import hackathon2013.Proposicao
import hackathon2013.Votacao
import br.org.prismaCamara.taglibs.postagens.VotacaoProposicaoTagLib

class PostagemVotacaoProposicao extends Postagem {

	 /**
	 * Gera e retorna o texto da última votação e seus votos de determinada Proposição
	 * @param params (prop: um {@link Proposicao}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		Proposicao prop = params.prop
		Votacao votacao = prop.votacoes.first()
		
		def mvotos = [:] // ex: ['sim':['ze ruela','ze buduia','maria bigodenha'], 'nao':'tiririca']
		for (voto in votacao.votos) {
			def v = voto.voto
			if (mvotos["$v"]) {
				mvotos["$v"]=mvotos["$v"]+voto.deputado
			} else {
				mvotos["$v"]=[voto.deputado]
			}
		}
		mvotos = mvotos.sort{v1,v2 -> v2.value.size()<=>v1.value.size()}
		params.votacao=votacao
		params.mvotos=mvotos
		String mensagem = new VotacaoProposicaoTagLib().getConteudo(params)
		return mensagem
	}

}
