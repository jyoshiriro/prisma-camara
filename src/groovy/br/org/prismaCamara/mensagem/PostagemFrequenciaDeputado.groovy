package br.org.prismaCamara.mensagem


import br.org.prismaCamara.modelo.FrequenciaDia
import br.org.prismaCamara.modelo.Parametro

class PostagemFrequenciaDeputado extends Postagem {

	 /**
	 * Gera e retorna o texto de uma postagem de frequência de deputado
	 * @param params (dep:um {@link Deputado}, tipo: um {@link Postagem}.TIPO_XX)
	 * @return Conteúdo da mensagem
	 */
	public String getTexto(Map params) {
		if (!params.dep.ultimaFrequencia)
			return null
			
		FrequenciaDia freq = params.dep.ultimaFrequencia
		
		Parametro pData = Parametro.findBySigla('ultimo_dia_frequencia')
		if (pData) {
			if (freq.dia.after(Date.parse('dd/MM/yyyy',pData.valor))) {
				pData.valor=freq.dia.format('dd/MM/yyyy')
			}
		} else {
			pData = new Parametro(sigla: 'ultimo_dia_frequencia', valor: freq.dia.format('dd/MM/yyyy'), descricao: 'Última atualização de frequências')
			pData.save()
		}
		int qsessoes = freq.frequenciasSessao.size()
		params.freq=freq
		params.qsessoes=qsessoes
		params.souma=(qsessoes==1)
		String p = r.render(template:"/postagens/frequencia-deputado-${params.tipo}", model:params).toString()
		return p
	}

}
