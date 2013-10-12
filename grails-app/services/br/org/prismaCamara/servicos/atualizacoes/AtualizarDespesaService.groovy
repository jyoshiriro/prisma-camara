package br.org.prismaCamara.servicos.atualizacoes

import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult
import hackathon2013.Deputado;
import hackathon2013.Despesa;

import java.util.zip.ZipFile

import org.junit.After;


/**
 * Atualizar a tabela de Tipos de Proposicao. Os que estiverem na tabela e não chegarem no XML são marcados com "ativo=false"
 */
@Log4j
class AtualizarDespesaService extends AtualizadorEntidade {

	def usuarioService
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
		
		String contXml = new File('/home/yoshiriro/workspaces/hackathon2013/prisma-camara/testeCota2.xml').text
		
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
			if (++iTemp>100) break;
			// WA
			
			def matriculaA = despesa.nuCarteiraParlamentar.toString().trim().toInteger()
			
			def nomeA = despesa.txNomeParlamentar.toString().trim().toUpperCase()
			def partidoA = despesa.sgPartido.toString().trim()
			def ufA = despesa.sgUF.toString().trim()
			
			Date dataEmissao = Date.parse("yyyy-MM-dd'T00:00:00'",despesa.datEmissao.toString().trim())
			
			Deputado deputadoA = Deputado.findByMatricula(matriculaA)
			if (!deputadoA) {
				// o novo deputado 'nasce' com a data de último gasto como sendo a da Iteração-1dia 
				deputadoA = new Deputado(nome:nomeA,nomeParlamentar: nomeA, siglaPartido:partidoA, uf:ufA, ultimoDiaGasto:(dataEmissao-1), ativo:false)
				deputadoA.save()
				log.debug("Deputado ${deputadoA.descricao}) não existia na base. Salvo como 'inativo', então nenhum gasto dele será salvo por enquanto.")
				// se ele não existia, nenhum usuário o acompanha
				continue
			} else {
				if (!usuarioService.isDeputadoObservado(deputadoA)) {
					log.debug("Deputado ${deputadoA.descricao}) não está sendo observado por nenhum usuário. Despesa ignorada.")
					continue
				}
				if (!deputadoA.ultimoDiaGasto) {
					deputadoA.ultimoDiaGasto=(dataEmissao-1)
				}
			}
			
			def atributos = [txtDescricao:despesa.txtDescricao.toString().trim(), txtBeneficiario:despesa.txtBeneficiario.toString().trim(),txtCNPJCPF:despesa.txtCNPJCPF.toString().trim(), numParcela:despesa.numParcela.toString().toInteger(),valor:despesa.vlrDocumento.toString()?.toDouble(), txtNumero:despesa.txtNumero.toString().trim()]
			atributos+=[dataEmissao:dataEmissao]
			
			// esse 'ultimoDiaGasto' de Deputado é atualizado em PostagemGastoDeputado
			def isMaisRecente = dataEmissao.after(deputadoA.ultimoDiaGasto)
			if (isMaisRecente) { // só persiste a despesa se for mais recente que a última data de atualização
				if (Despesa.countByDeputadoAndDataEmissao(deputadoA,dataEmissao)==0) { 
					atributos+=[deputado:deputadoA, dataEmissao:dataEmissao]
					Despesa entidade = new Despesa(atributos)
					entidade.save()
					log.debug("Despesa ${entidade.id} salva no banco")
				}
			}
		}
		
		log.debug("Atualização de Despesas do Ano Atual concluída com sucesso")
	}
}
