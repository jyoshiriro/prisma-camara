package br.org.prismaCamara.servicos.limpezas

import hackathon2013.Despesa

/**
 * Classe que exclui os registros defasados de {@link Despesa}. <br>
 * A regra é excluir todas as despesas associadas a deputados cuja "dataEmissao" seja anterior ao "ultimoDiaGasto" do {@link Deputado}. <br>
 * Varre-se todos os Deputados associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
class LimparDespesaService extends LimpadorEntidade {

	def usuarioService
	
	@Override
	public void limpar() {
		def deputados = usuarioService.deputadosMapeados

		for (deputado in deputados) {
			if (deputado.despesas.empty) 
				continue
			 
			def ultimoDiaGasto = (deputado.ultimoDiaGasto)?:Despesa.executeQuery("select max(d.dataEmissao) from Despesa d where d.deputado=?",[deputado])[0]-1
			Despesa.executeUpdate("delete from Despesa where deputado=? and dataEmissao<=?",[deputado,ultimoDiaGasto])
			log.debug("Despesas do deputado ${deputado.descricao} excluidas")
		}
		log.debug("Limpezas de Despesas defasadas de Deputados concluída com sucesso!")
	}

}
