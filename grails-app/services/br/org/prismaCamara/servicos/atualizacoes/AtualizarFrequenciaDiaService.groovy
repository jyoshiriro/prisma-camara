package br.org.prismaCamara.servicos.atualizacoes

import groovy.util.logging.Log4j;
import groovy.util.slurpersupport.GPathResult
import hackathon2013.Deputado;
import hackathon2013.FrequenciaDia;
import hackathon2013.FrequenciaSessao;
import hackathon2013.Parametro;

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
	
	private Date getUltimaAtualizacao() {
		def ultimoDiaS = Parametro.findBySigla('ultimo_dia_frequencia')?.valor
		Date proximaData = null
		try {
			proximaData = Date.parse("dd/MM/yyyy", ultimoDiaS)
		} catch (Exception e) {
			proximaData = (new Date()-30) // caso ocorra algum problema ou seja a primeira vez, a data máxima a ser buscada é de 30 dias atrás
		}
		return proximaData.clearTime()
	}
	
	
    def atualizar() {
		
		Date ultimaAtualizacao = getUltimaAtualizacao()
		Date proximaAtualizacao = new Date().clearTime()
		
		def urlT = null
		GPathResult xmlr = null
		try {
			def quant = 0
			while (!quant && proximaAtualizacao>ultimaAtualizacao) {
				urlT = getUrlAtualizacao([data:proximaAtualizacao.format("dd/MM/yyyy")])
				xmlr = getXML(urlT)
				quant = xmlr.childNodes()?.size()
				proximaAtualizacao--
			}
			proximaAtualizacao++
			
		} catch (Exception e) {
			log.error("A url ${urlT} não retornou XML válido: ${e.message}")
		}
		
		log.debug("Chegaram ${xmlr.parlamentares.childNodes()?.size()} frequências dos deputados chegaram no XML de ${urlT}...")

		for (parlemantar in xmlr.parlamentares.parlamentar) { 
			
			Deputado.withNewTransaction { tx ->
			
			def atributos = [dia:proximaAtualizacao, frequenciaDia:parlemantar.descricaoFrequenciaDia.toString(), justificativa:parlemantar.justificativa.toString()]
			
			Deputado deputadoA = Deputado.where {matricula==parlemantar.carteiraParlamentar.toString().toInteger()}.find()
			if (!deputadoA) {
				deputadoA = new Deputado(nome:parlemantar.nomeParlamentar.toString().toUpperCase(),nomeParlamentar:parlemantar.nomeParlamentar.toString().toUpperCase(),siglaPartido:parlemantar.siglaPartido.toString(),matricula:parlemantar.carteiraParlamentar.toString().toInteger(),uf:parlemantar.siglaUF.toString(),ativo:false)
				deputadoA.save()
			}
			atributos+=[deputado:deputadoA]
			
			FrequenciaDia entidade = FrequenciaDia.where {deputado==deputadoA && dia==proximaAtualizacao}.find()
			
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
		Parametro pData = Parametro.findBySigla('ultimo_dia_frequencia')
		if (pData) {
			pData.valor=proximaAtualizacao.format('dd/MM/yyyy')
		}
		else {
			pData = new Parametro(sigla: 'ultimo_dia_frequencia', valor: proximaAtualizacao.format('dd/MM/yyyy'), descricao: 'Última atualização de frequências')
			pData.save()
		}

		log.debug("Atualização de Frequencias de Deputados concluída com sucesso")
    }

}
