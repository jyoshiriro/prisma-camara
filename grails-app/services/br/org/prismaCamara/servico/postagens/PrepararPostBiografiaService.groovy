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
package br.org.prismaCamara.servico.postagens

import grails.gsp.PageRenderer
import groovy.util.logging.Log4j
import br.org.prismaCamara.mensagem.Postagem;
import br.org.prismaCamara.mensagem.PostagemBiografiaDeputado
import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Usuario

@Log4j
class PrepararPostBiografiaService extends PrepararPost {

	def usuarioService
	
	@Override
	public String getNomeTipoInformacao() {
		return "biografia";
	}
	
	@Override
	public Postagem getPostagem() {
		return new PostagemBiografiaDeputado();
	}
	
	@Override
	public void preparar(Usuario usuario, Long idEntidade) {
		def postagemPreparada = prepararPostagem(usuario, idEntidade, [dep:Deputado.get(idEntidade),tipo:usuario.tipoRede])
		if (postagemPreparada)
			log.debug("Postagem preparada com sucesso para ${usuario.id} em ${nomeTipoInformacao} (idEntidade: ${idEntidade})")
	}

}
