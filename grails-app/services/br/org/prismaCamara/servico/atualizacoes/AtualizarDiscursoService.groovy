package br.org.prismaCamara.servico.atualizacoes

import java.util.Date;

import javax.smartcardio.ATR;

import br.org.prismaCamara.modelo.Deputado;
import br.org.prismaCamara.modelo.Discurso;
import br.org.prismaCamara.modelo.Parametro;

import groovy.util.logging.Log4j;
import groovy.util.slurpersupport.GPathResult

@Log4j
class AtualizarDiscursoService extends AtualizadorEntidade {

	def usuarioService
	
	@Override
	public String getSiglaDeParametro() {
		// http://www.camara.gov.br/sitcamaraws/SessoesReunioes.asmx/ListarDiscursosPlenario?codigoSessao=&parteNomeParlamentar=&siglaPartido=&siglaUF=&dataIni=${data}&dataFim=${data}
		return "url_discursos";
	}
	
	@Override
	public Object atualizar() {
		Date proximaAtualizacao = (new Date()-21).clearTime() // tirar esse "-16" depois
		
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
			String codSessao = attrSessao.codigo 
			Date dataSessao = attrSessao.data
			
			for (faseSessao in sessao.childNodes()[4].childNodes()) {
				
				// etapa=${etapa}&nuSessao=${nuSessao}&nuQuarto=${nuQuarto}&nuOrador=${nuOrador}&nuInsercao=${nuInsercao}&dtHorarioQuarto=${horario}&sgFaseSessao=${cdFaseSessao}&Data=${data}&txApelido=${nomeParlamentar}
				def cdFaseSessao = faseSessao.childNodes()[0].text().trim()
				
				for (discurso in faseSessao.childNodes()[2].childNodes()) {
					
					def orador = discurso.childNodes()[0]
					def siglaA = orador.childNodes()[2].text().trim()
					
					// verificando se é um Deputado (há casos em que o orador não é Deputado)
					if (!siglaA) {
						log.debug('Orador que não é Deputado - Este discurso não será salvo')
						continue
					}
					

					def horaInicioA = discurso.childNodes()[1].text().trim()
					horaInicioA = horaInicioA.substring(horaInicioA.indexOf(" ")+1)
					
					def nomeDeputadoA = orador.childNodes()[1].text()
					for (ch in ["(","[","-",","]) { 
						nomeDeputadoA = nomeDeputadoA.indexOf(ch)>0?nomeDeputadoA.substring(0,nomeDeputadoA.indexOf(ch)):nomeDeputadoA
					}
					nomeDeputadoA = nomeDeputadoA.trim().toUpperCase()
					def ufA = orador.childNodes()[3].text().trim()
					
					def deputadoA = Deputado.findByNomeParlamentarAndUf(nomeDeputadoA,ufA) 
					if (!deputadoA) {
						log.debug("Deputado ${nomeDeputadoA}/${ufA} não estava na base. Discurso Ignorado.")
						// se ele não existia, nenhum usuário o acompanha
						continue
					}else {
						if (!usuarioService.isDeputadoObservado(deputadoA)) {
							log.debug("Deputado ${deputadoA.descricao}) não está sendo observado por nenhum usuário. Discurso ignorado.")
							continue
						}
					}
					
					def numeroOrador = orador.childNodes()[0].text().toInteger()
					def numeroQuarto = discurso.childNodes()[2].text().toInteger()
					def numeroInsercao = discurso.childNodes()[3].text().toInteger()
					def sumario = discurso.childNodes()[4].text()
					
					def atributos = [horaInicio:horaInicioA,cdFaseSessao:cdFaseSessao,numeroOrador:numeroOrador, numeroQuarto:numeroQuarto, numeroInsercao:numeroInsercao, sumario:sumario]
					atributos+=attrSessao
					
					// esse 'ultimoDiaDiscurso' de Deputado é atualizado em PostagemDiscursoDeputado
					def isMaisRecente = deputadoA.ultimoDiaDiscurso?dataSessao.after(deputadoA.ultimoDiaDiscurso):true
					if (isMaisRecente) { // só persiste a despesa se for mais recente que a última data de atualização
						if (Discurso.where{deputado==deputadoA && data==dataSessao && codigo==codSessao && horaInicio==horaInicioA}.count()==0) {
							atributos+=[deputado:deputadoA]
							Discurso entidade = new Discurso(atributos)
							entidade.save()
							log.debug("Discurso ${entidade.id} salvo no banco")
						}
					}
					
				} // loop de discursos
			} // loop de fasesSessao
		}
		log.debug("Atualização de Discursos de Deputados concluída com sucesso")
	}

}
