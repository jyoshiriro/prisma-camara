package br.org.prismaCamara.servicos.limpezas

import hackathon2013.Despesa
import hackathon2013.Proposicao;
import hackathon2013.Votacao;

/**
 * Classe que exclui os registros defasados de {@link Votacao}. <br>
 * A regra é excluir todas as votações associadas a deputados cuja "dataHoraVotacao" seja anterior a "ultimaVotacao" da {@link Proposicao}. <br>
 * Varre-se todos as Proposições associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
class LimparVotacaoService extends LimpadorEntidade {

	def usuarioService
	
	@Override
	public void limpar() {

		def proposicoes = usuarioService.proposicoesMapeadas
		
		for (proposicao in proposicoes) {
			def ultimoDiaVotacao = (proposicao.ultimaVotacao)?:Votacao.executeQuery("select max(v.dataHoraVotacao) from Votacao v where v.proposicao=?",[proposicao])[0]-1
			for (votacao in Votacao.findAllByProposicaoAndDataHoraVotacaoLessThanEquals(proposicao,ultimoDiaVotacao)) {
				votacao.delete()
				log.debug("Votacao ${votacao.id} excluida")
			}
		}
		log.debug("Limpezas de Votacões defasadas concluída com sucesso!")
	}

}
