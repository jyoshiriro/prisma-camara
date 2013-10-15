package br.org.prismaCamara.servico.limpezas

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Votacao

/**
 * Classe que exclui os registros defasados de {@link Votacao}. <br>
 * A regra é excluir todas as votações associadas a deputados cuja "dataHoraVotacao" seja anterior a "ultimaVotacao" da {@link Proposicao}. <br>
 * Varre-se todos as Proposições associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
@Log4j
class LimparVotacaoService extends LimpadorEntidade {

	def usuarioService
	
	@Override
	public void limpar() {

		def proposicoes = usuarioService.proposicoesMapeadas
		
		for (proposicao in proposicoes) {
			def ultimoDiaVotacao = proposicao.ultimaVotacao
			if (!ultimoDiaVotacao) {
				log.debug("A proposicao ${proposicao.descricao} ainda não tinha votações registradas para excluir")
				continue;
			}
			def votacoes = Votacao.findAllByProposicaoAndDataHoraVotacaoLessThanEquals(proposicao,ultimoDiaVotacao)
			for (votacao in votacoes) {
				votacao.delete()
				log.debug("Votacao ${votacao.id} excluida")
			}
		}
		log.debug("Limpezas de Votacões defasadas concluída com sucesso!")
	}

}
