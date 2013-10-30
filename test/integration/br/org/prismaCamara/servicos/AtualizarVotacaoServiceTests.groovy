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
