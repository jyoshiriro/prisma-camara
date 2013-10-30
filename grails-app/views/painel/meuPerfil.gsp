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
					<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">
						Quero cancelar meu cadastro!
					</button>
				</div>
			</div>
			<!-- Modal -->
			<div class="modal fade" style="overflow-y: auto;" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Confirmação</h4>
						</div>
						<div class="modal-body">
							Você tem certeza que deseja cancelar todos os seus acompanhamentos?
						</div>
						<div class="modal-footer">
							<g:link class="btn btn-primary" controller="painel" action="cancelaInscricao">Sim</g:link>
							<button type="button" class="btn btn-default" data-dismiss="modal">Não</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
	</body>
</html>