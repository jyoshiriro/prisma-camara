/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
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
				} else {
					entidade.properties=[sigla:sigla, nome:nome]
					log.debug("Partido ${entidade.sigla} atualizado")
				}
			}
		}
				
		log.debug("Atualização de Partidos concluída com sucesso")
	}
}

