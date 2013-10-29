<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Painel</title>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="container">
			<h1>Meus Acompanhamentos</h1>
			<p><sec:username/>, você pode verificar seus acompanhamentos aqui:</p>
			<div id="controller-list" role="navigation">
				<ul>
					<li class="controller"><g:link action="editarUsuario">Editar Meus Dados</g:link></li>
					<li class="controller"><g:link action="configurarPostagens">Configurações de Postagem</g:link></li>
				</ul>
			</div>			
		</div>
	</body>
</html>