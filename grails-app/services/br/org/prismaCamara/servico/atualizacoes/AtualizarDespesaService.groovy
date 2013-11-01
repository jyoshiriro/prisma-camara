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

import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

import java.util.Map;
import java.util.zip.ZipFile

import org.junit.After;

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Despesa;
import br.org.prismaCamara.servico.UsuarioService
import br.org.prismaCamara.util.xml.LerXmlCota;


/**
 * Atualizar a tabela de Tipos de Proposicao. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
 */
@Log4j
class AtualizarDespesaService extends AtualizadorEntidade {

	def usuarioService = new UsuarioService()
	@Override
	public String getSiglaDeParametro() {
		// 'http://www.camara.gov.br/cotas/AnoAtual.zip'
		return 'url_gastos';
	}
	
	def atualizar() {
		def urlZip = getUrlAtualizacao()
	
		byte[] contZip =  urlZip.toURL().bytes //new File("/home/yoshiriro/Downloads/AnoAtual.zip").bytes
		
		LerXmlCota lerXmlCota = new LerXmlCota()
		List<Map> novasDespesas = []
		
		String nomeArquivo="cotaAtuaisTmp"
		try {
			novasDespesas = lerXmlCota.getNovasDespesas(contZip, usuarioService.deputadosMapeados, nomeArquivo);
		} catch (Exception e) {
			log.error("Erro ao tentar ler o XML de Cota Parlamentar! ${e.message}")
			e.printStackTrace()
			return
		} finally {
			new File("${nomeArquivo}.zip").delete()
			new File("${nomeArquivo}.xml").delete()		
		}
		
		for (mapDespesa in novasDespesas) {
			try {
				Despesa despesa = new Despesa(mapDespesa)
				Deputado dep = Deputado.findByMatricula(despesa.deputado.matricula)
				def despesaCount = Despesa.countByTxtNumeroAndDataEmissaoAndDeputado(despesa.txtNumero,despesa.dataEmissao,dep)
				if (!despesaCount) {
					despesa.deputado = dep
					despesa.save(flush:true)
					log.debug("Despesa ${despesa.id} para o Deputado ${despesa.deputado.matricula} salva no banco")
				} else{
					log.debug("Despesa ${despesa.txtNumero} já existia para o Deputado ${despesa.deputado.matricula}")
				}
			} catch (Exception e) {
				log.error("Erro ao tentar salvar novo registro de despesa: ${e.message}")
				e.printStackTrace()
			}
		}
		log.debug("Atualização de Despesas do Ano Atual concluída com sucesso")
	}
}
