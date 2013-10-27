package br.org.prismaCamara.servico

import br.org.prismaCamara.modelo.Deputado
import br.org.prismaCamara.modelo.Partido;
import br.org.prismaCamara.modelo.Proposicao
import br.org.prismaCamara.modelo.Usuario;
import br.org.prismaCamara.modelo.UsuarioDeputado
import br.org.prismaCamara.modelo.UsuarioPartido
import br.org.prismaCamara.modelo.UsuarioProposicao;

class UsuarioService {
	
	boolean isDeputadoObservado(Deputado deputado) {

		def deputados = UsuarioDeputado.countByDeputado(deputado)
		if (deputados)
			return true
			
		def partidos = UsuarioPartido.executeQuery("select count(up) from UsuarioPartido up where up.partido.id=${deputado.partido.id}")[0]
		return (partidos)
		
		return (deputadosMapeados.contains(deputado))
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
		
		/*Set<Deputado> deputados = Deputado.getAll(10,619,173,374,324,458, 437,174,359,479,500,5,320)
		deputados = deputados.sort{d1,d2-> d1.nomeParlamentar<=>d2.nomeParlamentar} 
		deputados*/
	}
	
	List<Deputado> getDeputadosDeUsuario(Usuario usuario) {
		Set<Deputado> deputados1 = Usuario.executeQuery("select ud.deputado from UsuarioDeputado ud where ud.deputado.ativo=true and ud.usuario=?",[usuario]) as Set
		Set<Deputado> deputados2 = Partido.executeQuery("select d from Deputado d where d.partido in (select up.partido from UsuarioPartido up where up.usuario=?)",[usuario]) as Set
		def deputadost = deputados1+deputados2
		return deputadost as List
		
		/*return Deputado.getAll(10,619,173,500,374,324,458,437,174,359,479,500,5,320)*/
	}
	
	List<Proposicao> getProposicoesDeUsuario(usuario) {
		def proposicoes = UsuarioProposicao.executeQuery("select up.proposicao from UsuarioProposicao up where up.usuario=?",[usuario])
		proposicoes 
		/*return getProposicoesMapeadas() as List*/
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

	private Deputado getDeputadoAleatorio() {
		def quantAtivos = Deputado.countByAtivo(true)
		def id = new Random().nextInt(quantAtivos.toInteger())-1
		def deputado = Deputado.list(max:1,offset:id).first()
		deputado
	}
		
}
