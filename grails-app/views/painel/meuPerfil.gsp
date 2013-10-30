<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Meu Perfil</title>
	</head>
	<body>
		<div class="container">
			<h1>Meu Perfil</h1>
			<div class="row">
				<div class="col-lg-4">
					<g:form role="form" controller="painel" action="atualizaNome">
						<div class="form-group">
						    <label for="nome">O nome pelo qual o chamamos aqui é:</label>
							<input type="text" class="form-control" name="nome" placeholder="Seu nome" value="${nome}">
						</div>
						<button type="submit" class="btn btn-default">Gravar</button>
					</g:form>
				</div>
			</div>
			<br/> <br/> <br/>
			<div class="row">
				<div class="col-lg-4 center">
					<button type="button" class="btn btn-danger">Quero cancelar meu cadastro!</button>
				</div>
			</div>
		</div>
	</body>
</html>