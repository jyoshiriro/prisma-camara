<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bem-vindo ao Prisma-Câmara</title>
	</head>
	<body>
		<div class="container">
			<div>
				<h1>Bem-vindo ao Prisma-Câmara</h1>
				<p>
					Este software foi desenvolvido durante o evento Hackathon 2013 da Câmara dos Deputados.<br/>
					Equipe formada por José Yoshirio e Raimundo Lameira.<br/>
					Este é um software livre e seu código pode ser acessado <a href="https://github.com/jyoshiriro/prisma-camara">aqui</a>.
				</p>
			</div>
			
			<div>
				<br/>
				<sec:ifNotGranted roles="ROLE_USER">
					Acesse o sistema:<br/>
					<facebookAuth:connect /> ou <twitterAuth:button />
				</sec:ifNotGranted>
				<br/>
				<br/>
				<sec:ifAllGranted roles="ROLE_USER">
					Bem-vindo <sec:username/>! (<g:link uri="/j_spring_security_logout">Logout</g:link>)
					<br/>
					<g:link class="create" controller="facebookPost" action="timeline">Minha Timeline</g:link>
				</sec:ifAllGranted>
			</div>

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
