package hackathon2013

import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

@Log4j
class AtualizarProposicoesService extends AtualizadorEntidade {

    static String URL="http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?numero=&datApresentacaoIni=&datApresentacaoFim=&autor=&parteNomeAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=&ano=:ano&sigla=:sigla"

	/**
	 * Atualizar a tabela de Tipos de Proposicao. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
	 */
	private void atualizar() {

		def tipos = ['PL','PEC'] //TipoProposicao.list().collect{it.sigla}
		def anos = 2012..2013 // Proposicao.PRIMEIRO_ANO..(new Date().calendarDate.year)
		
		for (tipo in tipos) {
			for (ano in anos) {
				def urlT = URL.replace(":ano", ano.toString()).replace(":sigla",tipo)
				GPathResult xmlr = getXML(urlT)
				
				log.debug("${xmlr.childNodes().size()} proposições chegaram no XML")
				
				xmlr.proposicao.each{ prop->
					
					def idA = prop.id.toString().trim()
					
					chavesRecebidas+=idA
		
					TipoProposicao tipoP = TipoProposicao.findBySigla(prop.tipoProposicao.sigla.toString())
		
					def atributos = [idProposicao:prop.id.toString().toInteger(), tipoProposicao:tipoP, numero: prop.tipo.toString().toInteger(), ano:prop.ano.toString().toInteger, dataApresentacao: Date.parse('d/M/yyyy',prop.dataApresentacao.toString()), situacao: prop.situacao.descricao?.toString(), txtApreciacao: prop.apreciacao.txtApreciacao.toString(), txtEmenta: prop.txtEmenta.toString(), txtExplicacaoEmenta: prop.txtExplicacaoEmenta.toString(),txtUltimoDespacho: prop.ultimoDespacho.txtDespacho?.toString(), ultimoDespacho: Date.parse('d/M/yyyy',prop.ultimoDespacho,datDespacho?.toString())]
					
					Deputado autor = Deputado.findByIdeCadastro(prop.autor1.idecadastro?.toString())
					if (autor) {
						atributos+=[autor:autor]
					} else {
						atributos+=[nomeAutor:prop.autor1.txtNomeAutor?.toString()]
					}
					
					// TODO: Votações da Proposição!
					
					Proposicao entidade = Proposicao.where {idProposicao==idA}.find()
					
					if (entidade) { // já existe o registro, atualize os dados
						entidade.properties=atributos
						log.debug("Tipo de proposição ${idA} atualizado")
					} else { // ainda não existe. Persista agora
						entidade = new Proposicao(atributos)
						entidade.save()
						log.debug("Tipo de proposição ${idA} salvo no banco")
					}
					
				}
			} // for de anos
		} // for de tipos
		
		log.debug("${chavesRecebidas} Tipos de proposição marcados como inativos")
	}
}
