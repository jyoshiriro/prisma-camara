/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.servico.atualizacoes

import br.org.prismaCamara.modelo.TipoProposicao;
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult


/**
 * Atualizar a tabela de Partidos. Os que chegarem com dataExtincao anterior a "hoje" serão excluídos do banco
 */
@Log4j
class AtualizarTipoProposicaoService extends AtualizadorEntidade {

	@Override
	public String getSiglaDeParametro() {
		// 'http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarSiglasTipoProposicao'
		return 'url_listagem_tipos_proposicoes';
	}
	
	def atualizar() {
		
		GPathResult xmlr = getXML(getUrlAtualizacao(null))
		
		def chavesRecebidas = [] // coleta os Ids recebidos para saber quais deputados não são mais ativos 
		log.debug("${xmlr.childNodes().size()} tipos de proposições chegaram no XML")
		
		xmlr.sigla.each{ tipo->
			
			def tipoSiglaA = tipo.@tipoSigla.toString().trim()
			
			chavesRecebidas+=tipoSiglaA
			
			def atributos = [sigla:tipoSiglaA,descricao:tipo.@descricao.toString(), ativo:tipo.@ativa.toString().toLowerCase().toBoolean(), genero:tipo.@genero.toString()]
			
			TipoProposicao entidade = TipoProposicao.where {sigla==tipoSiglaA && ativo}.find()
			
			if (entidade) { // já existe o registro, atualize os dados
				entidade.properties=atributos
				log.debug("Tipo de proposição ${tipoSiglaA} possivelmente atualizado")
			} else { // ainda não existe. Persista agora
				entidade = new TipoProposicao(atributos)
				entidade.save()
				log.debug("Tipo de proposição ${tipoSiglaA} salvo no banco")
			}
			
		}
		
		int inativos = TipoProposicao.executeUpdate("update TipoProposicao set ativo=false where sigla not in (:ids)",[ids:chavesRecebidas])
		
		if (inativos)	
			log.debug("${chavesRecebidas} Tipos de proposição marcados como inativos")
		
		log.debug("Atualização de Tipos de Proposições concluída com sucesso")
	}
}
