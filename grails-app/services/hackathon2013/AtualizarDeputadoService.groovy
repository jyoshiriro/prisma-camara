package hackathon2013

import org.springframework.transaction.support.DefaultTransactionStatus;

import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

/**
 * Atualizar a tabela de Deputados. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
 */
@Log4j
class AtualizarDeputadoService extends AtualizadorEntidade {
	
	@Override
	public String getSiglaDeParametro() {
		// 'http://www.camara.gov.br/SitCamaraWS/Deputados.asmx/ObterDeputados'
		return 'url_listagem_deputados';
	}
	
	def atualizar() {
		
		GPathResult xmlr = getXML(getUrlAtualizacao(null))
		
		def chavesRecebidos = [] // coleta os Ids recebidos para saber quais deputados não são mais ativos 
		log.debug("${xmlr.childNodes().size()} deputados chegaram no XML")
		
		for (dep in xmlr.deputado) {
			Deputado.withNewTransaction { tx->
			 
			def ideCadastroA = dep.ideCadastro.toInteger()
			chavesRecebidos+=ideCadastroA
			
			def atributos = [ideCadastro: ideCadastroA, condicao: dep.condicao.toString(), matricula: dep.matricula.toInteger(), nome: dep.nome.toString().toUpperCase(), nomeParlamentar: dep.nomeParlamentar.toString().toUpperCase(),  sexo: dep.sexo.toString(), uf:dep.uf.toString(), siglaPartido: dep.partido.toString(), fone: dep.fone.toString(), email:dep.email.toString(), ativo:true]
			
			Deputado entidade = Deputado.where {ideCadastro==ideCadastroA && ativo}.find()
			if (!entidade) {
				// tenta pegar por "apelido", partido e uf, pois pode ter sido cadastrado no momento de registro de votos (isso não vem normalizado no XML de votações)
				entidade = Deputado.where{nomeParlamentar==dep.nomeParlamentar?.toString()?.trim() && partido.sigla==dep.partido?.toString()?.trim() && uf==dep.uf?.toString()?.trim()}.find()
			}
			
			if (entidade) { // já existe o registro, atualize os dados
				entidade.properties=atributos
				log.debug("Deputado ${ideCadastroA} possivelmente atualizado")
			} else { // ainda não existe. Persista agora
				entidade = new Deputado(atributos)
				entidade.save()
				log.debug("Deputado ${ideCadastroA} salvo no banco")
			}

			// comissões como titular
			def comissoesTitular = dep.childNodes()[13].childNodes()[0].childNodes()
			for (ct in comissoesTitular) {
				
				def nomeA=ct.attributes.nome.trim()
				def siglaA=ct.attributes.sigla.trim()
				Comissao comissao = Comissao.findBySigla(siglaA)
				
				if (!comissao) {
					comissao = new Comissao(nome: nomeA, sigla: siglaA)
					comissao.save()
					log.debug("Comissão ${siglaA} salva no banco")
				}
				if (entidade.comissoesTitular.findIndexOf {it.sigla==siglaA}<0) {
					entidade.addToComissoesTitular(comissao)
					log.debug("Deputado ${ideCadastroA} com nova Comissão como Titular: ${siglaA}")
				}
			}
			
			// comissões como suplente
			def comissoesSuplente = dep.childNodes()[13].childNodes()[1].childNodes()
			for (ct in comissoesSuplente) {
				
				def nomeA=ct.attributes.nome.trim()
				def siglaA=ct.attributes.sigla.trim()
				Comissao comissao = Comissao.findBySigla(siglaA)
				if (!comissao) {
					comissao = new Comissao(nome: nomeA, sigla: siglaA)
					comissao.save()
					log.debug("Comissão ${siglaA} salva no banco")
				}
				if (entidade.comissoesSuplente.findIndexOf {it.sigla==siglaA}<0) {
					entidade.addToComissoesSuplente(comissao)
					log.debug("Deputado ${ideCadastroA} com nova Comissão como Suplente: ${siglaA}")
				}
			}
			}
	
		}
		
		def inativos = Deputado.executeUpdate("update Deputado set ativo=false where ideCadastro not in (:ids)",[ids:chavesRecebidos])
		
		if (inativos)	
			log.debug("${chavesRecebidos} deputados marcados como inativos")
			
		log.debug("Atualização de Deputados concluída com sucesso")
	}
}
