package br.org.prismaCamara.test.unit

import groovy.sql.Sql
import java.nio.charset.Charset;
import java.text.SimpleDateFormat

import org.apache.commons.lang.CharSet;
import org.apache.commons.lang.CharSetUtils;

import br.org.prismaCamara.modelo.Deputado
import br.org.prismaCamara.util.xml.LerXmlCota;

class TesteCharSetXmlCota {

	static main(args) {
		File fxml = new File("C:/Users/Administrador/Downloads/AnoAtual (1).zip")
		LerXmlCota ler = new LerXmlCota()
		def fv = new File("C:/Users/Administrador/Downloads/deputados.txt").text //18 (jordy)
		def deputados = []
		fv.eachLine {
			deputados+=new Deputado(ultimoDiaGasto:(new Date()-665),matricula:it.toInteger())
		}
		ler.getNovasDespesas(fxml.bytes, deputados as Set, "testeX"+new Date().getTime())
	}

}
