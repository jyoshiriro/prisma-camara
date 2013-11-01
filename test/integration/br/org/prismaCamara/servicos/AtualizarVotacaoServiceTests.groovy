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
package br.org.prismaCamara.servicos

import static org.junit.Assert.*
import org.junit.*

class AtualizarVotacaoServiceTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testExcluirProposicao() {
		def siglaA = 'PL'
		def numeroA = 216
		def anoA = '2007'
		def desc = "${siglaA} ${numeroA}/${anoA}"

		def tipoPropA = TipoProposicao.findBySigla(siglaA)
		def proposicaoT = Proposicao.findByNumeroAndAnoAndTipoProposicao(numeroA,anoA,tipoPropA)
		try {
			proposicaoT.delete()
			assert true
		} catch (Exception e) {
			e.printStackTrace()
			fail "Num rolou a exclusão!"
		}
    }
}
