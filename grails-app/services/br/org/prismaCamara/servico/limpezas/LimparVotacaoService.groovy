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
