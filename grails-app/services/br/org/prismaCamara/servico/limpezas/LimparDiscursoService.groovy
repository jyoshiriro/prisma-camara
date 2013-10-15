package br.org.prismaCamara.servico.limpezas

import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Discurso

/**
 * Classe que exclui os registros defasados de {@link Discurso}. <br>
 * A regra é excluir todas as despesas associadas a deputados cuja "data" seja anterior ao "ultimoDiaDiscurso" do {@link Deputado}. <br>
 * Varre-se todos os Deputados associados a {@link Usuario} 
 * @author jyoshiriro
 *
 */
@Log4j
class LimparDiscursoService extends LimpadorEntidade {

	def usuarioService
	
	@Override
	public void limpar() {
		def deputados = usuarioService.deputadosMapeados

		for (deputado in deputados) {
			if (deputado.discursos.empty) 
				continue
			 
			def ultimoDiaDiscurso = deputado.ultimoDiaDiscurso
			if (!ultimoDiaDiscurso) {
				log.debug("O deputado ${deputado.descricao} ainda sem registro de discurso para excluir")
				continue;
			}
			Discurso.executeUpdate("delete from Discurso where deputado=? and data<=?",[deputado,ultimoDiaDiscurso])
			log.debug("Discursos do deputado ${deputado.descricao} excluidas")
		}
		log.debug("Limpezas de Discursos defasados de Deputados concluída com sucesso!")
	}

}
