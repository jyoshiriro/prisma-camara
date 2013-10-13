package br.org.prismaCamara.servicos.limpezas

import hackathon2013.Despesa
import hackathon2013.Discurso;

/**
 * Classe que exclui os registros defasados de {@link Discurso}. <br>
 * A regra é excluir todas as despesas associadas a deputados cuja "data" seja anterior ao "ultimoDiaDiscurso" do {@link Deputado}. <br>
 * Varre-se todos os Deputados associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
class LimparDiscursoService extends LimpadorEntidade {

	def usuarioService
	
	@Override
	public void limpar() {
		def deputados = usuarioService.deputadosMapeados

		for (deputado in deputados) {
			if (deputado.discursos.empty) 
				continue
			 
			def ultimoDiaDiscurso = (deputado.ultimoDiaDiscurso)?:Discurso.executeQuery("select max(d.data) from Discurso d where d.deputado=?",[deputado])[0]-1
			Despesa.executeUpdate("delete from Despesa where deputado=? and dataEmissao<=?",[deputado,ultimoDiaDiscurso])
			log.debug("Discursos do deputado ${deputado.descricao} excluidas")
		}
		log.debug("Limpezas de Discursos defasados de Deputados concluída com sucesso!")
	}

}
