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
modules = {
	
    application {
		dependsOn 'bootstrap'
		resource url:'css/prisma.css'
		resource url:'js/prisma.js'
        //resource url:'js/application.js'
		//resource url:'css/main.css'
    }
	
	bootstrap {
		dependsOn 'jquery'
		resource url:'vendor/bootstrap-3.0.0/css/bootstrap.min.css', exclude:'minify'
		resource url:'vendor/bootstrap-3.0.0/css/bootstrap-theme.min.css', exclude:'minify'
		resource url:'vendor/bootstrap-3.0.0/js/bootstrap.min.js', exclude:'minify'
	}
			
}