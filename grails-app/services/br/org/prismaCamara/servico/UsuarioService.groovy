package br.org.prismaCamara.servico

import br.org.prismaCamara.modelo.Deputado
import br.org.prismaCamara.modelo.Partido
import br.org.prismaCamara.modelo.Proposicao
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioDeputado
import br.org.prismaCamara.modelo.UsuarioPartido

class UsuarioService {
	
	boolean isDeputadoObservado(Deputado deputado) {

		def deputados = UsuarioDeputado.countByDeputado(deputado)
		if (deputados)
			return true
			
		def partidos = UsuarioPartido.executeQuery("select count(up) from UsuarioPartido up where up.partido.id=${deputado.partido.id}")[0]
		return (partidos)
	}
	
	/**
	 * Recupera todos os deputados associados a {@link Usuario}, seja por associação com {@link Deputado}, seja com {@link Partido} <br>
	 * Somente os deputados "ativos" são selecionados
	 * @return
	 */
	Set<Deputado> getDeputadosMapeados() {
		Set<Deputado> deputados = Usuario.executeQuery("select ud.deputado from UsuarioDeputado ud where ud.deputado.ativo=true")
		Set<Partido> partidos = Partido.executeQuery("select partido from UsuarioPartido")
		deputados+=Deputado.findAllByPartidoInListAndAtivo(partidos,true)
		return deputados
		/*Set<Deputado> deputados = Deputado.getAll([10,76,324,472]) //[]
		deputados*/
	}
	
	Set<Deputado> getDeputadosDeUsuario(Usuario) {
		return []
	}
	
	/**
	 * Recupera todos as Proposições associados a {@link Usuario}, pela associação com {@link Proposicao}
	 * @return
	 */
	Set<Proposicao> getProposicoesMapeadas() {
		Set<Proposicao> proposicoes = Usuario.executeQuery("select proposicao from UsuarioProposicao")
		return proposicoes
		/*Set<Proposicao> proposicoes = Proposicao.findAllByNumeroInList([190,300])
		proposicoes*/
	}
	
}
