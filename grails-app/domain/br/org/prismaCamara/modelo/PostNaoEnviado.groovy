package br.org.prismaCamara.modelo

import br.org.prismaCamara.util.ZipUtil


class PostNaoEnviado {
	
	String hash
	byte[] conteudoZip
	
	Long idEntidade 
	String tipoInformacao 
	String tipoRede // manter por causa dos get e set 
	
	boolean pendente = false // um post "pendente=true" é aquele que foi tentado o envio à rede social mas ocorreu algum problema
	Integer tentativas = 0 // a cada 3 tentativas de reenvio o post será excluído 
	
	static transients = ['hashGerado','tipoRede','conteudo']
	
	static constraints = {
		hash(maxSize:256) 
		conteudoZip(maxSize:5120) //5MB (sempre compactado) 
		tipoInformacao(maxSize:30) 
	}

	def beforeValidate() {
		if (!hash)
			hash = hashGerado
	}
	
	String getHashGerado() {
		def h = getHashGerado(idEntidade, tipoInformacao, tipoRede)
		h
	}
	
	public static String getHashGerado(idEntidade,tipoInformacao, tipoRede) {
		"${idEntidade}-${tipoInformacao}-${tipoRede}".encodeAsSHA1()
	}
	
	public String getConteudo() {
		def cdesc = ZipUtil.descompactar(conteudoZip)
		cdesc
	}
	
	public String setConteudo(String conteudo) {
		this.conteudoZip = ZipUtil.compactar(conteudo)
	}
	
	def afterUpdate() {
		pendente = (tentativas) 
		if (tentativas>=20) {
			this.delete();
		}
	}
	
}
