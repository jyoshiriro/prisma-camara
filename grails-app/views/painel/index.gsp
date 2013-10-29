<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Painel</title>
	</head>
	<body>
		<div class="container">
			<h1>Painel</h1>
			<p>Olá <sec:username/>, você pode verificar seus acompanhamentos aqui:</p>
			<p>
				<g:link class="btn btn-primary" action="editarUsuario">Meu Perfil</g:link> &nbsp; &nbsp; 
				<g:link class="btn btn-primary" action="configurarPostagens">Meus Acompanhamentos</g:link>
			</p>		
		</div>
	</body>
</html>