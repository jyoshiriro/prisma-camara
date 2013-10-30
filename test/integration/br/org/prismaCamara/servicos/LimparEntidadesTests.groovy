package br.org.prismaCamara.servicos

import static org.junit.Assert.*

import org.junit.*

import br.org.prismaCamara.servico.limpezas.LimparDespesaService
import br.org.prismaCamara.servico.limpezas.LimparFrequenciaDiaService;

class LimparEntidadesTests {

	LimparDespesaService limparDespesaService
	LimparFrequenciaDiaService limparFrequenciaDiaService

    void ntestLimparDespesas() {
        limparDespesaService.limpar()
    }
	
	void testLimparFrequenciasDia() {
		try {
			def count1 = FrequenciaDia.count()
			limparFrequenciaDiaService.limpar()
			assert FrequenciaDia.count()<count1
		} catch (Exception e) {
			e.printStackTrace()
			assert false
		}
	}
}
