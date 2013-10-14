package br.org.prismaCamara.servico

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Partido;
import br.org.prismaCamara.modelo.Proposicao;
import br.org.prismaCamara.modelo.Usuario;

class UsuarioService {
	
	boolean isDeputadoObservado(Deputado deputado) {
		// TODO: alimentar na base pra poder testar isso
		/*def deputados = Usuario.executeQuery("select u.deputados from Usuario u")
		def partidos = Usuario.executeQuery("select u.partidos from Usuario u")
		for (p in partidos) {
			deputados+=Deputado.findAllByPartido(p)
		}
		return deputados.contains(deputado)*/
		return true
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
