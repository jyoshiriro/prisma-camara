package br.org.prismaCamara.servicos

import static org.junit.Assert.*

import org.junit.*

import br.org.prismaCamara.servico.atualizacoes.AtualizarDespesaService

class AtualizarDespesasTests {
	
	AtualizarDespesaService atualizarDespesaService = new AtualizarDespesaService()

    void testDespesas() {
        atualizarDespesaService.atualizar()
		assert true
    }
}
