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
		File fxml = new File("/home/yoshiriro/Downloads/AnoAtual.zip")
		LerXmlCota ler = new LerXmlCota()
		def fv = '18' //new File("/home/yoshiriro/teste.csv").text //18 (jordy)
		def deputados = []
		fv.eachLine {
			deputados+=new Deputado(ultimoDiaGasto:(new Date()-665),matricula:it.toInteger())
		}
		ler.getNovasDespesas(fxml.bytes, deputados as Set, "testeX")
	}

}
