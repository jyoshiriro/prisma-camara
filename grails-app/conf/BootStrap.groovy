/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
import groovy.sql.Sql
import groovy.util.logging.Log4j
import br.org.prismaCamara.modelo.Parametro
import br.org.prismaCamara.modelo.Perfil
import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioPerfil
import br.org.prismaCamara.modelo.Perfil.*

@Log4j
class BootStrap {

	def dataSource
	
	def init = { servletContext ->

		verificacoesIniciais()
		
		inicializarSpringSecurity()
		
		injetarMetaMetodos()

	}

	def destroy = { servletContext->
		
	}

	def verificacoesIniciais() {
		if (Parametro.count()>=12) {
			println "Os Parâmetros parecem estar corretamente iniciados"
			return 
		}
		try {
			println "Os Parâmetros não parecem estar corretamente iniciados... iniciando realimentação"
			Sql sql = new Sql(dataSource)
			sql.executeUpdate("truncate table parametro")
			def sqlInicial = new File('scripts-iniciais.sql').text
			
			sqlInicial.eachLine {
				if (it.trim()) {
					println "Executando... ${it}"
					sql.executeInsert(it)
				}
			}
			println "Parâmetros configurados com sucesso"
		}
		catch (e) {
			log.error("Falha ao tentar executar o SQL com os registros iniciais (scripts-iniciais.sql): ${e.message}")
			e.printStackTrace()
		}

	}

	def inicializarSpringSecurity() {
		def perfilAdministrador = Perfil.findOrSaveByAuthority('ROLE_ADMIN')
		def perfilUsuario = Perfil.findOrSaveByAuthority('ROLE_USER')
		def perfilTwitter = Perfil.findOrSaveByAuthority('ROLE_TWITTER')

		String username = 'admininistrador'

		if (!Usuario.findByUsername(username)) {
			def testeUsuario = new Usuario(username: username, enabled: true, password: 'admin')
			testeUsuario.save()
			UsuarioPerfil.create(testeUsuario, perfilAdministrador, true)
		}
	}
	
	def injetarMetaMetodos() {
		// Todo String terá o "validoMinimoPalavras(minimo)", onde "minimo" é o número mínimo de palavras a considerar
		String.metaClass.validoMinimoPalavras{minimo ->
			def palavras = delegate.split(" ")
			Set palavrasnr = [] as Set
			def valida = false
			for (palavra in palavras) {
				if (['$','#','%','*','?','!','=','+','-','/','@'].contains(palavra)) {
					break
				}
				if (palavra.size()>=2)
					palavrasnr+=palavra
			}
			valida = palavrasnr.size()>=minimo
			return valida
		}
	}
	
}
