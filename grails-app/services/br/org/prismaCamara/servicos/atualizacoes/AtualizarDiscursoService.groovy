package br.org.prismaCamara.servicos.atualizacoes

import java.util.Date;

import javax.smartcardio.ATR;

import groovy.util.slurpersupport.GPathResult
import hackathon2013.Deputado;
import hackathon2013.Discurso;
import hackathon2013.Parametro;

class AtualizarDiscursoService extends AtualizadorEntidade
 {

	@Override
	public String getSiglaDeParametro() {
		// http://www.camara.gov.br/sitcamaraws/SessoesReunioes.asmx/ListarDiscursosPlenario?codigoSessao=&parteNomeParlamentar=&siglaPartido=&siglaUF=&dataIni=${data}&dataFim=${data}
		return "url_discursos";
	}
	
	@Override
	public Object atualizar() {
		Date proximaAtualizacao = (new Date()-15).clearTime() // tirar esse "-15" depois
		
		def urlT = null
		GPathResult xmlr = null
		int limite = 45
		def quant = 0
		while (!quant) {
			
			urlT = getUrlAtualizacao([data:proximaAtualizacao.format("dd/MM/yyyy")])
			try {
				xmlr = getXML(urlT)
				quant = xmlr.childNodes()?.size()
			} catch (Exception e) {
				log.error("A url ${urlT} não retornou XML válido: ${e.message}")
			}
			proximaAtualizacao--
			
			if (--limite<0)  {
				def msg = "Mais de $limite tentativas não conseguiram recuperar um XML válido de Atualização de Discurso"
				log.error(msg)
				throw new Exception(msg)
			}
		}
		proximaAtualizacao++
		
		log.debug("Chegaram ${xmlr.childNodes()?.size()} discursos dos deputados chegaram no XML de ${urlT}")
		
		for (sessao in xmlr.sessao) {
			
			def nosSessao = sessao.childNodes()			
			def attrSessao = [codigo:nosSessao[0].text().trim(), data:Date.parse('dd/MM/yyyy',nosSessao[0].text().trim()), numeroSessao:nosSessao[0].text().trim()]
			
			for (faseSessao in sessao.childNodes()[4].childNodes()) {
				
				// etapa=${etapa}&nuSessao=${nuSessao}&nuQuarto=${nuQuarto}&nuOrador=${nuOrador}&nuInsercao=${nuInsercao}&dtHorarioQuarto=${horario}&sgFaseSessao=${cdFaseSessao}&Data=${data}&txApelido=${nomeParlamentar}
				def cdFaseSessao = faseSessao.childNodes()[0].text().trim()
				
				for (discurso in faseSessao.childNodes()[2].childNodes()) {
					
					def orador = discurso.childNodes()[0]
					def siglaA = orador.childNodes()[2].text().trim()
					
					// verificando se é um Deputado (há casos em que o orador não pe Deputado)
					if (!siglaA) {
						log.debug('Orador que não é Deputado - Este discurso não será salvo')
						continue
					}
					
					Deputado.withNewTransaction { tx ->
	
						def horaInicioA = discurso.childNodes()[1].text().trim()
						horaInicioA = horaInicioA.substring(horaInicioA.indexOf(" ")+1)
						
						def nomeDeputadoA = orador.childNodes()[1].text()
						for (ch in ["(","[","-",","]) { 
							nomeDeputadoA = nomeDeputadoA.indexOf(ch)>0?nomeDeputadoA.substring(0,nomeDeputadoA.indexOf(ch)):nomeDeputadoA
						}
						nomeDeputadoA = nomeDeputadoA.trim().toUpperCase()
						def ufA = orador.childNodes()[3].text().trim()
						
						def deputadoA = Deputado.where {nomeParlamentar==nomeDeputadoA && uf==ufA && partido.sigla==siglaA}.find() 
						if (!deputadoA) {
							deputadoA = new Deputado(nome:nomeDeputadoA,nomeParlamentar:nomeDeputadoA,siglaPartido:siglaA,uf:ufA,ativo:false)
							deputadoA.save()
							log.debug("Deputado ${deputadoA.descricao} não estava na base. Salvo como 'inativo'")
						}
						
						def numeroOrador = orador.childNodes()[0].text().toInteger()
						def numeroQuarto = discurso.childNodes()[2].text().toInteger()
						def numeroInsercao = discurso.childNodes()[3].text().toInteger()
						def sumario = discurso.childNodes()[4].text()
						
						def atributos = [horaInicio:horaInicioA,cdFaseSessao:cdFaseSessao,numeroOrador:numeroOrador, numeroQuarto:numeroQuarto, numeroInsercao:numeroInsercao, deputado:deputadoA, sumario:sumario]
						atributos+=attrSessao
						
						Discurso entidade = Discurso.findByCodigoAndDeputado(atributos.codigo,deputadoA)
						if (entidade) {
							entidade.properties=atributos
							log.debug("Discurso ${atributos.codigo} provavelmente atualizado no banco")
						} else {
							entidade = new Discurso(atributos)
							entidade.save()
							log.debug("Discurso ${atributos.codigo} salvo no banco")
						}
					}
				}
			}
		}
		log.debug("Atualização de Discursos de Deputados concluída com sucesso")
	}

}
