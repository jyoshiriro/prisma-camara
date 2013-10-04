package hackathon2013

import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult

import java.util.zip.ZipFile


/**
 * Atualizar a tabela de Tipos de Proposicao. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
 */
@Log4j
class AtualizarDespesaService extends AtualizadorEntidade {

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
		
		String contXml = new File('C:\\Users\\Administrador\\Documents\\yoshi\\partiubrasilia\\workspace\\testesG\\testeCota.xml').text
		
/*		def xmlFile = new File("${nTmp}.zml")
		xmlFile<<zipFile.getInputStream(zipFile.entries().nextElement()).text
		
		StringBuffer xmlCont
		xmlFile.eachLine {
			xmlCont.append(it)
		}
*/		
		
		GPathResult xmlr = getXMLDeTexto(contXml.toString())
		
		log.debug("Despesas chegaram no ZIP de (${urlZip})")
		
		int iTemp = 0
		for (despesa in xmlr.DESPESAS.DESPESA) {
			
			// WA
			if (++iTemp>20) break;
			// WA
			
			def matriculaA = despesa.nuCarteiraParlamentar.toString().trim().toInteger()
			
			def nomeA = despesa.txNomeParlamentar.toString().trim()
			def partidoA = despesa.sgPartido.toString().trim()
			def ufA = despesa.sgUF.toString().trim()
			
			Deputado deputadoA = Deputado.findByMatricula(matriculaA)
			if (!deputadoA) {
				deputadoA = new Deputado(nome:nomeA,nomeParlamentar: nomeA, partido:partidoA, uf:ufA, ativo:false)
				deputadoA.save()
				log.debug("Deputado ${nomeA}(${partidoA}/${ufA}) não existia na base. Salvo como 'inativo'")
			}
			
			def atributos = [txtDescricao:despesa.txtDescricao.toString().trim(), txtBeneficiario:despesa.txtBeneficiario.toString().trim(),txtCNPJCPF:despesa.txtCNPJCPF.toString().trim(), numParcela:despesa.numParcela.toString().toInteger(),valorLiquido:despesa.vlrLiquido.toString()?.toDouble(), txtNumero:despesa.txtNumero.toString().trim()]
			Date dataEmissao = Date.parse("yyyy-MM-dd'T00:00:00'",despesa.datEmissao.toString().trim())
			atributos+=[dataEmissao:dataEmissao]
			
			Despesa entidade = Despesa.findByTxtNumeroAndDeputado(despesa.txtNumero.toString().trim(),deputadoA)
			if (entidade) { // já existe o registro, atualize os dados
				entidade.properties=atributos
				log.debug("Despesa ${entidade.id} possivelmente atualizada")
			} else { // ainda não existe. Persista agora
				atributos+=[deputado:deputadoA, dataEmissao:dataEmissao]
				entidade = new Despesa(atributos)
				entidade.save()
				log.debug("Despesa ${entidade.id} salva no banco")
			}
		}
		
		log.debug("Atualização de Despesas do Ano Atual concluída com sucesso")
	}
}
