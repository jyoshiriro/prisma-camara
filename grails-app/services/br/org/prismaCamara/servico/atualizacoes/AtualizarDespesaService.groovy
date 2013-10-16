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
	
/*		def urlZip = getUrlAtualizacao()
		
		def nTmp = "${new Date().time}"
		def zipFileT = new File("${nTmp}.zip")
		zipFileT<<urlZip.toURL().bytes

		ZipFile zipFile = new ZipFile("${nTmp}.zip")
		
		String contXml = zipFile.getInputStream(zipFile.entries().nextElement()).text
		zipFile.close()
		zipFileT.delete()*/
		
		byte[] contZip = new File("/home/yoshiriro/Downloads/AnoAtual.zip").bytes
		
		LerXmlCota lerXmlCota = new LerXmlCota()
		List<Map> novasDespesas = []
		
		try {
			novasDespesas = lerXmlCota.getNovasDespesas(contZip, usuarioService.deputadosMapeados);
//			novasDespesas = lerXmlCota.getNovasDespesas(contXml, usuarioService.deputadosMapeados);
		} catch (Exception e) {
			log.error("Erro ao tentar ler o XML de Cota Parlamentar! ${e.message}")
			e.printStackTrace()
			return
		}
		
		for (mapDespesa in novasDespesas) {
			try {
				Despesa despesa = new Despesa(mapDespesa)
				Deputado dep = Deputado.findByMatricula(despesa.deputado.matricula)
				def despesaCount = Despesa.countByTxtNumeroAndDataEmissaoAndDeputado(despesa.txtNumero,despesa.dataEmissao,dep)
				if (!despesaCount) {
					despesa.deputado = dep
					despesa.save(flush:true)
					log.debug("Despesa ${despesa.id} salva no banco")
				}
				log.debug("Despesa ${despesa} já existia")
			} catch (Exception e) {
				log.error("Erro ao tentar salvar novo registro de despesa: ${e.message}")
				e.printStackTrace()
			}
		}
		log.debug("Atualização de Despesas do Ano Atual concluída com sucesso")
	}
}
