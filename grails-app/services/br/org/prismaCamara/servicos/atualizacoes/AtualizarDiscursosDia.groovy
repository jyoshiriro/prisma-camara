package br.org.prismaCamara.servicos.atualizacoes

class AtualizarDiscursosDia extends AtualizadorEntidade
 {

	@Override
	public String getSiglaDeParametro() {
		// http://www.camara.gov.br/sitcamaraws/SessoesReunioes.asmx/ListarDiscursosPlenario?codigoSessao=&parteNomeParlamentar=&siglaPartido=&siglaUF=&dataIni=${data}&dataFim=${data}
		return "url_discursos";
	}

	@Override
	public Object atualizar() {
		// TODO Auto-generated method stub
		return null;
	}

}
