<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bem-vindo!</title>
	</head>
	<body>
		<div class="container">
		
			<div style="width: 476px; margin-bottom: 1em">
			<g:include view="banner-home.gsp"/>
			</div>
			
			<sec:ifNotLoggedIn>
				<div style="margin-bottom: 0.7em; margin-left: 3em">
					<span class="glyphicon glyphicon-arrow-left"></span> 
					Conecte-se com
					<span class="glyphicon glyphicon-arrow-right"></span>
				</div>			
				<div class="redes">
					<a href="/prisma-camara/j_spring_security_facebook_redirect" class="botao-face" title="Entrar com Facebook"></a>
				</div>
				<div class="redes peq"> ou </div>
				<div class="redes">
					<a href="/prisma-camara/j_spring_twitter_security_check" class="botao-twitter" title="Entrar com Twitter"></a>
				</div>
			</sec:ifNotLoggedIn>
			<sec:ifAllGranted roles="ROLE_USER">
				<p>Use o sistema acessando as opções do menu.</p>
			</sec:ifAllGranted>

			<%--
			<div id="controller-list" role="navigation">
				<h2>Ações Disponíveis:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
			--%>
		</div>
	</body>
</html>
