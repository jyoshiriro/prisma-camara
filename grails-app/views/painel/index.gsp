<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<h1>Painel</h1>
			<p>Bem-vindo <g:if test="usuario?.nome?.trim()?.isEmpty()">${usuario.username}</g:if><g:else>${usuario.nome}</g:else>!</p>
			<div id="controller-list" role="navigation">
				<h2>Menu de Opções:</h2>
				<ul>
					<li class="controller"><g:link action="editarUsuario">Editar Meus Dados</g:link></li>
					<li class="controller"><g:link action="configurarPostagens">Configurações de Postagem</g:link></li>
				</ul>
			</div>			
		</div>
	</body>
</html>