<%--
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 --%>
 <%
 if (request?.getRequestURL()?.indexOf("localhost")>=0) {
	 response.sendRedirect('http://olhonacamara.com.br/')
 }
 %>
<sec:ifNotLoggedIn>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>:: Bem-vindo!</title>
	</head>
	<body>
		<div class="container">
		
			<%--Workaround enquanto não está autorizado pelo Face--%>
			<div class="alert alert-info" id="divAvisoFace" style="display: none">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				<b>Atenção:</b> O Facebook ainda não autorizou o uso público da nossa aplicação, porém se você aceitar ser "testador" dela, poderá conectar-se aqui.<br>
				Se quiser usar a aplicação com sua conta do Facebook, entre em contato com 
				<a href="https://www.facebook.com/jyoshiriro">José Yoshiriro</a> ou 
				<a href="https://www.facebook.com/raimundo.norbertolameirajunior">Raimundo Lameira</a>.
			</div>
			<%--Workaround enquanto não está autorizado pelo Face--%>
			
			<div class="row">
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
				<div class="col-sm-6">
					<g:include view="banner-home.gsp"/>
				</div>
			</div> <!-- row -->
		</div> <!-- container -->
	</body>
</html>
</sec:ifNotLoggedIn>
<sec:ifLoggedIn>
<g:include controller="painel" action="/"/>
</sec:ifLoggedIn>