<%--
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
 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bem-vindo!</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<sec:ifNotLoggedIn>
				<div class="col-sm-4 centered">
						<div class="row">
							<div class="col-md-8 col-md-offset-2">
								<span class="glyphicon glyphicon-arrow-left"></span> 
								Conecte-se com
								<span class="glyphicon glyphicon-arrow-right"></span>
								<br/> 
							</div>
						</div>
						<div class="row">		
							<div class="col-md-8 col-md-offset-2">
								<a href="j_spring_security_facebook_redirect" title="Entrar com Facebook"><img class="redes" alt="" src="${resource(dir:'images', file:'botao-face.png')}"></a>
								<a href="j_spring_twitter_security_check" title="Entrar com Twitter"><img class="redes" alt="" src="${resource(dir:'images', file:'botao-twitter.png')}"></a>
							</div>
						</div>
				</div>
				</sec:ifNotLoggedIn>
				<div class="col-sm-6">
					<g:include view="banner-home.gsp"/>
				</div>
			</div> <!-- row -->
		</div> <!-- container -->
	</body>
</html>
