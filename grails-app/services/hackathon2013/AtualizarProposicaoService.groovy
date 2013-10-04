package hackathon2013

import groovy.text.SimpleTemplateEngine
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult


/**
 * Atualizar a tabela de Proposicões. 
 */
@Log4j
class AtualizarProposicaoService extends AtualizadorEntidade {

	@Override
	public String getSiglaDeParametro() {
		// "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?numero= &datApresentacaoIni= &datApresentacaoFim= &autor= &parteNomeAutor= &siglaPartidoAutor= &siglaUFAutor= &generoAutor= &codEstado= &codOrgaoEstado= &emTramitacao= &ano=${ano}&sigla=${sigla}"
		return 'url_listagem_proposicoes';
	}

	def atualizar() {

		
		def tipos = ['PL']// TipoProposicao.list().collect{it.sigla} //['PL','PEC']
		def anos = [2007] // Proposicao.PRIMEIRO_ANO..(new Date().calendarDate.year)
		
		l1:for (tipo in tipos) {
			l2:for (ano in anos) {
				
				def urlT = getUrlAtualizacao([ano:ano.toString(),sigla:tipo])
				
				GPathResult xmlr = null
				try {
					xmlr = getXML(urlT)
				} catch (Exception e) {
					log.error("A url ${urlT} não retornou XML válido: ${e.message}")
					continue l2;
				}
				
				log.debug("${xmlr.childNodes().size()} proposições chegaram no XML")
				
				xmlr.proposicao.each{ prop->
					
					// WA
//					if (prop.numero?.toString()!='1992')
//						return false
					// WA
					
					def idA = prop.id.toString().trim()
					TipoProposicao tipoP = TipoProposicao.findBySigla(prop.tipoProposicao.sigla.toString())
					
					def atributos = [idProposicao:prop.id?.toString()?.toInteger(), tipoProposicao:tipoP, numero: prop.numero?.toString()?.toInteger(), ano:prop.ano?.toString()?.toInteger(), dataApresentacao: Date.parse('d/M/yyyy',prop.datApresentacao.toString()), situacao: prop.situacao.descricao?.toString(), txtApreciacao: prop.apreciacao.txtApreciacao.toString(), txtEmenta: prop.txtEmenta.toString(), txtExplicacaoEmenta: prop.txtExplicacaoEmenta.toString(),txtUltimoDespacho: prop.ultimoDespacho.txtDespacho?.toString()]
					
					if (prop.ultimoDespacho.datDespacho.toString()) 
						atributos+=[ultimoDespacho: Date.parse('d/M/yyyy',prop.ultimoDespacho.datDespacho?.toString())]
					
					Deputado autor = Deputado.findByIdeCadastro(prop.autor1.idecadastro?.toString())
					if (autor) {
						atributos+=[autor:autor]
					} else {
						atributos+=[nomeAutor:prop.autor1.txtNomeAutor?.toString()]
					}
					
					Proposicao entidade = Proposicao.where {idProposicao==idA}.find()
					
					if (entidade) { // já existe o registro, atualize os dados
						entidade.properties=atributos
						log.debug("Tipo de proposição ${idA} atualizado")
					} else { // ainda não existe. Persista agora
						entidade = new Proposicao(atributos)
						entidade.save()
						
						if (entidade.errors.errorCount>0) {
							log.error("Proposição ${idA} NÃO foi salva devido a erros: ${entidade.errors}")
						} else {
							log.debug("Proposição ${idA} salvo no banco")
						}
					}
					
				}
			} // for de anos
		} // for de tipos
		
	}

}
