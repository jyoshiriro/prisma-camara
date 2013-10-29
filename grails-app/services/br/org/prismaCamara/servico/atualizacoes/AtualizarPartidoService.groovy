package br.org.prismaCamara.servico.atualizacoes

import br.org.prismaCamara.modelo.Partido;
import br.org.prismaCamara.modelo.TipoProposicao;
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult


/**
 * Atualizar a tabela de Tipos de Proposicao. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
 */
@Log4j
class AtualizarPartidoService extends AtualizadorEntidade {

	@Override
	public String getSiglaDeParametro() {
		// 'http://www.camara.gov.br/SitCamaraWS/Deputados.asmx/ObterPartidosCD'
		return 'url_listagem_partidos';
	}
	
	def atualizar() {
		
		GPathResult xmlr = getXML(getUrlAtualizacao(null))
		
		log.debug("${xmlr.childNodes().size()} de partidos chegaram no XML")
		
		xmlr.partido.each{ partido->
			
			def nosPartido = partido.childNodes()
			def sigla = nosPartido[0].text().trim() 
			def nome = nosPartido[1].text().trim()
			if (nome!='--') {
				Partido entidade = Partido.findBySigla(sigla)
				if (!entidade) {
					entidade = new Partido(sigla:sigla, nome:nome)
					entidade.save()
					log.debug("Novo partido ${entidade.sigla} criado")
				}
			}
		}
				
		log.debug("Atualização de Partidos concluída com sucesso")
	}
}
