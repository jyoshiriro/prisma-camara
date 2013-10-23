package br.org.prismaCamara.controle

class ErrosController {

	def acessoNegado = {}
	
	def naoEncontrado = {
		log.debug "Não foi encontrado $request.forwardURI"
	}

	def naoPermitido = {}
   
}
