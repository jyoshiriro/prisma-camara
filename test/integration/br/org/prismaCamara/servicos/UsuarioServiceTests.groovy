package br.org.prismaCamara.servicos

import static org.junit.Assert.*

import br.org.prismaCamara.servico.UsuarioService
import org.junit.*

class UsuarioServiceTests {

	UsuarioService usuarioService
	
    void setUp() {
        // Setup logic here
    }

    void tearDown() {
        // Tear down logic here
    }

    void testTodosDeputadosDeUsuarios() {
        try {
			def quant = usuarioService.deputadosMapeados.size()
			println quant
			assert quant==4
		} catch (Exception e) {
			e.printStackTrace()
			fail e.getMessage()
		}
    }
    
    void testTodasProposicoesDeUsuarios() {
    	try {
    		assert usuarioService.proposicoesMapeadas.size()>0
    	} catch (Exception e) {
    		e.printStackTrace()
    		fail e.getMessage()
    	}
    }
}
