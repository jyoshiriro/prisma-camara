package br.org.prismaCamara.servico

import groovy.util.logging.Log4j

import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.FacebookProfile
import org.springframework.social.facebook.api.impl.FacebookTemplate

import br.org.prismaCamara.modelo.Deputado
import br.org.prismaCamara.modelo.Partido
import br.org.prismaCamara.modelo.Proposicao
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioDeputado
import br.org.prismaCamara.modelo.UsuarioFacebook
import br.org.prismaCamara.modelo.UsuarioPartido
import br.org.prismaCamara.modelo.UsuarioProposicao

@Log4j
class UsuarioService {
	

	/**
	 * Atualiza o nome do usuário com seu nome em sua Rede Social no momento do cadastro 
	 * @param usuario
	 * @return
	 */
	def atualizaNome(Usuario usuario) {
		UsuarioFacebook usuarioFacebook = UsuarioFacebook.findByUser(usuario)
		Facebook facebook = new FacebookTemplate(usuarioFacebook.accessToken)
		FacebookProfile fbProfile = facebook.userOperations().userProfile
		usuario.nome = fbProfile.name
		usuario.save()
		log.debug "Usuario ${usuario.username} atualizado. Nome: ${usuario.nome}"
	}
	
	/**
	 * Verifica de um deputado é observado por pelo menos 1 usuário, seja via acompanhamento direto ({@link UsuarioDeputado}), seja via partido ({@link UsuarioPartido})
	 * @param deputado
	 * @return
	 */
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
		
	}
	
	/**
	 * Contagem de deputados um determinado usuário acompanha
	 * @param usuario Intancia de {@link Usuario}
	 * @param considerarPartidos Se <b>true</b>, considera os acompanhamentos via {@link UsuarioPartido}, caso contrário, só considera os acompanhamentos diretos de {@link UsuarioDeputado}
	 * @return
	 */
	Integer countDeputadosDeUsuario(Usuario usuario, boolean considerarPartidos) {
		def deputados1 = Usuario.executeQuery("""
			select count(ud.deputado) from UsuarioDeputado ud where ud.deputado.ativo=true and ud.usuario=?
			""",[usuario])
			
		def deputados2 = considerarPartidos?Partido.executeQuery("""
			select count(d) from Deputado d where d.partido in 
			(select up.partido from UsuarioPartido up where up.usuario=:u)
			and d not in (
				select ud.deputado from UsuarioDeputado ud where ud.deputado.ativo=true and ud.usuario=:u
			)  
			order by d.nomeParlamentar
			""",[u:usuario]):[0]
		
		Integer countt = deputados1[0]+deputados2[0]
		return countt
	}
	
	/**
	 * Listagem de deputados um determinado usuário acompanha
	 * @param usuario Intancia de {@link Usuario}
	 * @param considerarPartidos Se <b>true</b>, considera os acompanhamentos via {@link UsuarioPartido}, caso contrário, só considera os acompanhamentos diretos de {@link UsuarioDeputado}
	 * @return a {@link List} preenchida ou vazia. Nunca <code>null</code>.
	 */
	List<Deputado> getDeputadosDeUsuario(Usuario usuario, boolean considerarPartidos) {
		def deputados1 = Usuario.executeQuery("""
			select ud.deputado from UsuarioDeputado ud where ud.deputado.ativo=true and ud.usuario=? order by ud.deputado.nomeParlamentar
			""",[usuario])
		
		def deputados2 = considerarPartidos?Partido.executeQuery("""
			select d from Deputado d where d.partido in 
			(select up.partido from UsuarioPartido up where up.usuario=:u)
			and d not in (:deps)  
			order by d.nomeParlamentar
			""",[u:usuario,deps:deputados1?:[Deputado.get(0)]]):[]
		
		List deputadost = deputados1+deputados2
		return deputadost
		/*return Deputado.getAll(10,619,173,500,374,324,458,437,174,359,479,500,5,320)*/
	}
	
	/**
	 * Listagem de partidos um determinado usuário acompanha
	 * @param usuario Intancia de {@link Usuario}
	 * @return a {@link List} preenchida ou vazia. Nunca <code>null</code>.
	 */
	List<Partido> getPartidosDeUsuario(Usuario usuario) {
		def partidos = Partido.executeQuery("""
			select up.partido from UsuarioPartido up where up.usuario=?
			order by up.partido.sigla
			""",[usuario])

		return partidos
	}
	
	/**
	 * Contagem de partidos um determinado usuário acompanha
	 * @param usuario Intancia de {@link Usuario}
	 * @return 
	 */
	Integer countPartidosDeUsuario(Usuario usuario) {
		def cpartidos = Partido.executeQuery("""
				select count(up.partido) from UsuarioPartido up where up.usuario=?
				""",[usuario])
				
		return cpartidos[0]
	}
	
	/**
	 * Listagem de proposições um determinado usuário acompanha
	 * @param usuario Intancia de {@link Usuario}
	 * @return a {@link List} preenchida ou vazia. Nunca <code>null</code>.
	 */
	List<Proposicao> getProposicoesDeUsuario(usuario) {
		def proposicoes = UsuarioProposicao.executeQuery("select up.proposicao from UsuarioProposicao up where up.usuario=?",[usuario])
		proposicoes 
	}
	
	/**
	 * Contagem de proposições que um determinado usuário acompanha
	 * @param usuario Intancia de {@link Usuario}
	 * @return 
	 */
	Integer countProposicoesDeUsuario(usuario) {
		def cproposicoes = UsuarioProposicao.executeQuery("select count(up) from UsuarioProposicao up where up.usuario=?",[usuario])
		cproposicoes[0] 
	}
		
	/**
	 * Recupera todos as Proposições que pelo menos 1 usuário acompanha {@link UsuarioProposicao}
	 * @return
	 */
	Set<Proposicao> getProposicoesMapeadas() {
		Set<Proposicao> proposicoes = Usuario.executeQuery("select proposicao from UsuarioProposicao") as Set
		return proposicoes
	}

	/**
	 * Recuperar um Deputado aleatório (para o e envio de biografia automática
	 * @return
	 */
	private Deputado getDeputadoAleatorio() {
		def quantAtivos = Deputado.countByAtivo(true)
		def id = new Random().nextInt(quantAtivos.toInteger())-1
		def deputado = Deputado.list(max:1,offset:id).first()
		deputado
	}
		
}
