class UrlMappings {

	static mappings = {
		
		"/painel" (controller: 'painel', action: 'index')
		"/painel/sobre" view: "sobre"
		"/docs" (controller: 'docs', action:'index')
		
		"/searchable/$action?" (controller: "erros", action: "naoEncontrado")
		"/searchable" (controller: "erros", action: "naoEncontrado")
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/" (view:"/index")
		"403" (controller: "erros", action: "acessoNegado")
		"404" (controller: "erros", action: "naoEncontrado")
		"405" (controller: "erros", action: "naoPermitido")
		"500" (view: '/error')
	}
}
