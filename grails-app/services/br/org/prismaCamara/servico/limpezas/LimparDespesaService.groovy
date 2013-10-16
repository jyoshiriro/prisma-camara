package br.org.prismaCamara.servico.limpezas

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Despesa

/**
 * Classe que exclui os registros defasados de {@link Despesa}. <br>
 * A regra é excluir todas as despesas associadas a deputados cuja "dataEmissao" seja anterior ao "ultimoDiaGasto" do {@link Deputado}. <br>
 * Varre-se todos os Deputados associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
@Log4j
class LimparDespesaService extends LimpadorEntidade {

	def usuarioService
	
	@Override
	public void limpar() {
		def deputados = usuarioService.deputadosMapeados

		for (deputado in deputados) {
			if (deputado.despesas.empty) 
				continue
			 
			def ultimoDiaGasto = deputado.ultimoDiaGasto
			if (!ultimoDiaGasto) {
				log.debug("O deputado ${deputado.descricao} ainda não possui registro de despesa para excluir")
				continue;
			}
			Despesa.executeUpdate("delete from Despesa where deputado=? and dataEmissao<=?",[deputado,ultimoDiaGasto])
			log.debug("Despesas do deputado ${deputado.descricao} excluidas")
		}
		log.debug("Limpezas de Despesas defasadas de Deputados concluída com sucesso!")
	}

}
