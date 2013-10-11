package br.org.prismaCamara.servicos.limpezas

import hackathon2013.Deputado
import hackathon2013.Despesa;
import hackathon2013.Usuario

class LimparDespesaService extends LimpadorEntidade {

	@Override
	public void limpar() {
		// TODO: passar essas consultas para o UsuarioService
		// Deputados marcados para acompanhamento por usuários
		Set<Deputado> deputados = Deputado.findAllById(597) //[]
		for (usuario in Usuario.list()) {
			deputados+=usuario.deputados
			deputados+=Deputado.findAllByPartidoInList(usuario.partidos)
		}
		
		for (deputado in deputados) {
			def ultimoDiaGasto = (deputado.ultimoDiaGasto)?:Despesa.executeQuery("select max(d.dataEmissao) from Despesa d where d.deputado=?",[deputado])[0]-1
			Despesa.executeUpdate("delete from Despesa where deputado=? and dataEmissao<=?",[deputado,ultimoDiaGasto])
		}
	}

}
