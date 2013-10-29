<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bem-vindo!</title>
	</head>
	<body>
		<div class="container">
			<div>
				<h1>Bem-vindo!</h1>
				<p>
					Este software foi desenvolvido durante o evento Hackathon 2013 da Câmara dos Deputados.<br/>
					Equipe formada por José Yoshirio e Raimundo Lameira.<br/>
					Este é um software livre e seu código pode ser acessado <a href="https://github.com/jyoshiriro/prisma-camara">aqui</a>.
				</p>
			</div>
			<sec:ifNotLoggedIn>
				<div class="row">
					<div class="col-md-4 col-md-offset-1" style="text-align: center;">Acesse o sistema:</div>
				</div>			
				<div class="row">
					<div class="col-md-1 col-md-offset-1"><facebookAuth:connect /></div>
					<div class="col-md-1 col-md-offset-1"><twitterAuth:button /></div>
				</div>
			</sec:ifNotLoggedIn>
			<sec:ifAllGranted roles="ROLE_USER">
				<div class="row">
					<div class="col-md-4 col-md-offset-1" style="text-align: center;">Use o sistema acessando as opções do menu.</div>
				</div>
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
