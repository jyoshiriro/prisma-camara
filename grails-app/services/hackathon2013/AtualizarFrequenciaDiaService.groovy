package hackathon2013

import groovy.util.logging.Log4j;
import groovy.util.slurpersupport.GPathResult

/**
 * Atualização de frequências de todos os Deputados no (dia atual-2) ou de determinada data
 */
@Log4j
class AtualizarFrequenciaDiaService extends AtualizadorEntidade {

	@Override
	public String getSiglaDeParametro() {
		// "http://www.camara.gov.br/SitCamaraWS/sessoesreunioes.asmx/ListarPresencasDia?numLegislatura=&numMatriculaParlamentar=&siglaPartido=&siglaUF=&data=${data}"
		// data: dd/MM/yyyy
		return 'url_frequecias_dia';
	}
	
	private Date getUltimaData() {
		def ultimoDiaS = Parametro.findBySigla('ultimo_dia_frequencia')?.valor
		try {
			Date proximaData = ultimoDiaS?(Date.parse("dd/MM/yyyy", ultimoDiaS)):(new Date())
			proximaData
		} catch (Exception e) {
			new Date()
		}
	}
	
	
    def atualizar() {
		
		Date dataAtualizacao = getUltimaData()
		
		def urlT = null
		GPathResult xmlr = null
		try {
			
			def quant = 0
			while (!quant) {
				dataAtualizacao--
				urlT = getUrlAtualizacao([data:dataAtualizacao.format("dd/MM/yyyy")])
				xmlr = getXML(urlT)
				quant = xmlr.childNodes()?.size()
			}
			dataAtualizacao.clearTime()
			Parametro pData = Parametro.findBySigla('ultimo_dia_frequencia')
			if (pData)
				pData.valor=dataAtualizacao.format('dd/MM/yyyy')
			else {
				Parametro.withNewTransaction { tx ->
					pData = new Parametro(sigla: 'ultimo_dia_frequencia', valor: dataAtualizacao.format('dd/MM/yyyy'), descricao: 'Última atualização de frequências')
					pData.save()
				}
			}
			
		} catch (Exception e) {
			log.error("A url ${urlT} não retornou XML válido: ${e.message}")
		}
		
		log.debug("Chegaram ${xmlr.parlamentares.childNodes()?.size()} frequências dos deputados chegaram no XML de ${urlT}...")

		for (parlemantar in xmlr.parlamentares.parlamentar) { 
			
			Deputado.withNewTransaction { tx ->
			
			def atributos = [dia:dataAtualizacao, frequenciaDia:parlemantar.descricaoFrequenciaDia.toString(), justificativa:parlemantar.justificativa.toString()]
			
			Deputado deputadoA = Deputado.where {matricula==parlemantar.carteiraParlamentar.toString().toInteger()}.find()
			if (!deputadoA) {
				deputadoA = new Deputado(nome:parlemantar.nomeParlamentar.toString(),nomeParlamentar:parlemantar.nomeParlamentar.toString(),siglaPartido:parlemantar.siglaPartido.toString(),matricula:parlemantar.carteiraParlamentar.toString().toInteger(),uf:parlemantar.siglaUF.toString(),ativo:false)
				deputadoA.save()
			}
			atributos+=[deputado:deputadoA]
			
			FrequenciaDia entidade = FrequenciaDia.where {deputado==deputadoA && dia==dataAtualizacao}.find()
			
			if (entidade) { // já existe o registro, atualize os dados
				entidade.properties=atributos
				log.debug("Frequência de deputado ${deputadoA.nomeParlamentar} em ${entidade.dia} possivelmente atualizada")
			} else { // ainda não existe. Persista agora
				entidade = new FrequenciaDia(atributos)
				entidade.save()
				if (entidade.errors.errorCount>0) {
					log.error("Frequência de deputado ${deputadoA?.nomeParlamentar} em ${entidade?.dia} NÃO foi salva devido a erros: ${entidade?.errors}")
				} else {
					log.debug("Frequência de deputado ${deputadoA?.nomeParlamentar} em ${entidade?.dia} salva no banco")
				}
			}
	
			// Frequencias de sessões do dia
			def frequenciasSessao = parlemantar.childNodes()[7].childNodes()
			for (sd in frequenciasSessao) {
				
				def inicioA=Date.parse('d/M/yyyy HH:mm:ss',sd.childNodes()[0]?.text())
				def descricaoA=sd.childNodes()[1]?.text()
				def frequenciaA=sd.childNodes()[2]?.text()
				
				def atributosS = [inicio:inicioA, descricao:descricaoA, frequencia:frequenciaA] 
				FrequenciaSessao fSessao = FrequenciaSessao.findByFrequenciaDiaAndInicio(entidade,inicioA)
				if (fSessao) {
					fSessao.properties=atributosS
					log.debug("Frequencia da Sessão ${descricaoA} provavelmente atualizada no banco")
				} else {
					atributosS+=[frequenciaDia:entidade]
					fSessao = new FrequenciaSessao(atributosS)
					fSessao.save()
					log.debug("Frequencia da Sessão ${descricaoA} salva no banco")
				}
			}
			
			}
		}
		dataAtualizacao=null
		log.debug("Atualização de Frequencias de Deputados concluída com sucesso")
    }

}
