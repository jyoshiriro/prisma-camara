package br.org.prismaCamara.mensagens

import hackathon2013.Deputado;

abstract class AbstractMensagem {

	abstract String getMsgFrequencia(Long idDeputado)
	
	abstract String getMsgGasto(Long idDeputado)
	
	abstract String getMsgVotacao(Long idProposicao)
	
	abstract String getMsgMiniBiografia(Long idDeputado)
	
	protected Long getIdDeputadoAleatorio() {
		def quantAtivos = Deputado.countByAtivo(true)
		Deputado dep = Deputado.get(new Random().nextInt(quantAtivos).toLong())
		dep.id
	}
	
}
