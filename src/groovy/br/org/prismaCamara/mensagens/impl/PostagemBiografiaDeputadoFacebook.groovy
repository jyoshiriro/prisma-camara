package br.org.prismaCamara.mensagens.impl

import hackathon2013.Deputado;
import hackathon2013.Parametro;
import br.org.prismaCamara.mensagens.PostagemBiografiaDeputado;

class PostagemBiografiaDeputadoFacebook extends PostagemBiografiaDeputado {

	@Override
	public String getTexto(Long id) {
		Deputado dep = deputadoAleatorio
		// TODO: ver como pegar de um GSP
		String mensagem = """ 
			O Deputado ${dep.nomeParlamentar} (${dep.partido.sigla}-${dep.uf}) 
			participa de ${dep.comissoesTitular.size()} comissões como titular e
			de ${dep.comissoesSuplente.size()} comissões como suplente.
			Deseja conhecer mais? Veja em ${Parametro.findBySigla('url_biografia_deputado').valor}${dep.ideCadastro}.
		""" 
		return mensagem
	}

}
