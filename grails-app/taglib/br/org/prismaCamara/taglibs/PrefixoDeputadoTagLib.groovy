package br.org.prismaCamara.taglibs

class PrefixoDeputadoTagLib {
	
	/**
	 * Renderiza "O Deputado" ou "A Deputada" conforme o sexo do deputado informado, segui do seu "nomeParlamentar".
	 * Caso o sexo esteja vazio, o retorno será "O(A) Deputado(a)"
	 * Renderiza o texto a partir de "_frequencia-deputado-xxx.gsp"
	 * @attr dep REQUIRED Instância de {@link Deputado}
	 * @attr minusculo Se vazio ou <b>false</b>, o "O" ou "A" virão em maiúsculo, caso contrário, virão minúsculo
	 */
	String deputadoPrefix = { attrs ->
		def pref = (attrs.dep.sexo)?(attrs.dep.sexo?.startsWith('m')?'o Deputado':'a Deputada'):'o(a) Deputado(a)'
		pref = "${attrs.minusculo?pref:pref.capitalize()} ${attrs.dep.descricao}"
		out << pref
	}
}
