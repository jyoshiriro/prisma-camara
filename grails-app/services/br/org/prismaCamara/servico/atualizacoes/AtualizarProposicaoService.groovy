package br.org.prismaCamara.servico.atualizacoes

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Proposicao;
import br.org.prismaCamara.modelo.TipoProposicao;
import groovy.text.SimpleTemplateEngine
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult


/**
 * Atualizar a tabela de Proposicões. Essa classe Inclui e Exclui registros também.
 * A exclusão ocorre quando a proposição chega como "arquivada"  
 */
@Log4j
class AtualizarProposicaoService extends AtualizadorEntidade {

	@Override
	public String getSiglaDeParametro() {
		// "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?numero=&datApresentacaoIni=&datApresentacaoFim=&autor=&parteNomeAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=&ano=${ano}&sigla=${sigla}"
		return 'url_listagem_proposicoes';
	}

	def atualizar() {

		def tipos = ['PEC']// TipoProposicao.list().collect{it.sigla} //['PL','PEC']
		def anos = [2007,2008,2009,2010,2011,2012,2013] // Proposicao.PRIMEIRO_ANO..(new Date().calendarDate.year)
		
		l1:for (tipo in tipos) {
			l2:for (ano in anos) {
				
			def tx = Proposicao.withNewTransaction { tx ->
					
				def urlT = getUrlAtualizacao([ano:ano.toString(),sigla:tipo])
				
				GPathResult xmlr = null
				try {
					xmlr = getXML(urlT)
				} catch (Exception e) {
					log.error("A url ${urlT} não retornou XML válido: ${e.message}")
					return 'l2';
				}
				
				log.debug("${xmlr.childNodes().size()} proposições chegaram no XML")
				
				l3:for (prop in xmlr.proposicao) { 
					
					// WA
//					if (prop.numero?.toString()!='1992')
//						return false
					// WA
					// Se a proposição estiver arquivada, não será cadastrada no banco
					def situacaoA = prop.situacao.descricao?.toString()
					
					def siglaA = prop.tipoProposicao.sigla.toString()
					def numeroA = prop.numero?.toString()?.toInteger()
					def anoA = prop.ano?.toString()
					def desc = "${siglaA} ${numeroA}/${anoA}"
					
					if (situacaoA.toLowerCase().equals("arquivada")) {
						def tipoPropA = TipoProposicao.findBySigla(siglaA)
						Proposicao proposicaoT = Proposicao.findByNumeroAndAnoAndTipoProposicao(numeroA,anoA,tipoPropA)
						if (proposicaoT) {
							proposicaoT.delete()
							log.debug("Proposição ${desc} agora está arquivada e foi excluída do banco.")
						} else {
							log.debug("Proposição ${desc} já está arquivada e não será salva.")
							continue l3;
						}
					}
					 
					def idA = prop.id.toString().trim()
					TipoProposicao tipoP = TipoProposicao.findBySigla(siglaA)
					
					def atributos = [idProposicao:prop.id?.toString()?.toInteger(), tipoProposicao:tipoP, numero: numeroA, ano:anoA, dataApresentacao: Date.parse('d/M/yyyy',prop.datApresentacao.toString()), situacao: situacaoA, txtApreciacao: prop.apreciacao.txtApreciacao.toString(), txtEmenta: prop.txtEmenta.toString(), txtExplicacaoEmenta: prop.txtExplicacaoEmenta.toString(),txtUltimoDespacho: prop.ultimoDespacho.txtDespacho?.toString()]
					
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
						log.debug("Tipo de proposição ${desc} atualizado")
					} else { // ainda não existe. Persista agora
						entidade = new Proposicao(atributos)
						entidade.save()
						
						if (entidade.errors.errorCount>0) {
							log.error("Proposição ${desc} NÃO foi salva devido a erros: ${entidade.errors}")
						} else {
							log.debug("Proposição ${desc} salva no banco")
						}
					}
					
				}
			}
			
			if (tx=='l2') continue l2
			
			} // for de anos
		} // for de tipos
		log.debug("Atualização de Proposições concluída com sucesso")
	}

}
