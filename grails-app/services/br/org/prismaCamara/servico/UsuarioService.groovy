package br.org.prismaCamara.servico

import groovy.util.logging.Log4j;
import br.org.prismaCamara.modelo.Deputado
import br.org.prismaCamara.modelo.Partido;
import br.org.prismaCamara.modelo.Proposicao
import br.org.prismaCamara.modelo.Usuario;
import br.org.prismaCamara.modelo.UsuarioDeputado
import br.org.prismaCamara.modelo.UsuarioFacebook
import br.org.prismaCamara.modelo.UsuarioPartido
import br.org.prismaCamara.modelo.UsuarioProposicao;
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.FacebookProfile
import org.springframework.social.facebook.api.impl.FacebookTemplate

@Log4j
class UsuarioService {
	
	def atualizaNome(Usuario usuario) {
		UsuarioFacebook usuarioFacebook = UsuarioFacebook.findByUser(usuario)
		Facebook facebook = new FacebookTemplate(usuarioFacebook.accessToken)
		FacebookProfile fbProfile = facebook.userOperations().userProfile
		usuario.nome = fbProfile.name
		usuario.save()
		log.debug "Usuario ${usuario.username} atualizado. Nome: ${usuario.nome}"
	}
	
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
	
	Integer countDeputadosDeUsuario(Usuario usuario) {
		def deputados1 = Usuario.executeQuery("""
			select count(ud.deputado) from UsuarioDeputado ud where ud.deputado.ativo=true and ud.usuario=? order by ud.deputado.nomeParlamentar
			""",[usuario])
			
		def deputados2 = Partido.executeQuery("""
			select count(d) from Deputado d where d.partido in 
			(select up.partido from UsuarioPartido up where up.usuario=:u)
			and d not in (
				select ud.deputado from UsuarioDeputado ud where ud.deputado.ativo=true and ud.usuario=? order by ud.deputado.nomeParlamentar
			)  
			order by d.nomeParlamentar
			""",[u:usuario])
		
		Integer countt = deputados1+deputados2
		return countt
	}
	
	List<Deputado> getDeputadosDeUsuario(Usuario usuario) {
		def deputados1 = Usuario.executeQuery("""
			select ud.deputado from UsuarioDeputado ud where ud.deputado.ativo=true and ud.usuario=? order by ud.deputado.nomeParlamentar
			""",[usuario])
		
		def deputados2 = Partido.executeQuery("""
			select d from Deputado d where d.partido in 
			(select up.partido from UsuarioPartido up where up.usuario=:u)
			and d not in (:deps)  
			order by d.nomeParlamentar
			""",[u:usuario,deps:deputados1?:[Deputado.get(0)]])
		List deputadost = deputados1+deputados2
		return deputadost
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
