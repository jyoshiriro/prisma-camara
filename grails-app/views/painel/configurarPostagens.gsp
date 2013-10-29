<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/> 
		<title>Painel</title>
	</head>
	<body>
		<div class="container">
			<h1>Meus Acompanhamentos</h1>
			<br/>
			<div class="panel panel-default">
				<div class="panel-heading">
			    	<h3 class="panel-title">Deputados de Interesse</h3>
				</div>
				<div class="panel-body">
			    	<table id="tbDeputados">
					    <thead>
					        <tr>
					            <th width="60%">Deputado</th>
					            <th width="10%">Partido</th>
					            <th width="10%">Estado</th>
					            <th width="10%">Opções</th>
					        </tr>
					    </thead>
					    <tbody>
					    	<g:if test="${usuario.usuarioDeputados.isEmpty()}">
					    		<tr>
							    	<td colspan="5">Nenhum deputado selecionado.</td>
								</tr>
					    	</g:if>
					    	<g:else>
						    	<g:each in="${usuario.usuarioDeputados}" var="usuarioDeputado">
							    	<tr>
							        	<td>${usuarioDeputado.deputado.nomeParlamentar}</td>
							        	<td>${usuarioDeputado.deputado.partido.sigla}</td>
							        	<td>${usuarioDeputado.deputado.uf}</td>
							        	<td><g:link action="removerDeputado" id="${usuarioDeputado.id}">Remover</g:link></td>
							        </tr>
								</g:each>
							</g:else>
					    </tbody>
					</table>
					<g:link action="adicionarDeputados">Adicionar Deputado</g:link>
				</div>
			</div>
			
			
			<div class="panel panel-default">
				<div class="panel-heading">
			    	<h3 class="panel-title">Proposições de Interesse</h3>
				</div>
				<div class="panel-body">
					<table id="tbProposicoes">
					    <thead>
					        <tr>
					        	<th width="10%">Número</th>
					            <th width="10%">Ano</th>
					            <th width="70%">Emenda</th>
					            <th width="10%">Opções</th>
					        </tr>
					    </thead>
					    <tbody>
					        <g:if test="${usuario.usuarioProposicoes.isEmpty()}">
					    		<tr>
							    	<td colspan="5">Nenhuma proposição selecionada.</td>
								</tr>
					    	</g:if>
					    	<g:else>
						    	<g:each var="usuarioProposicao" in="${usuario.usuarioProposicoes}">
							    	<tr>
							        	<td>${usuarioProposicao.proposicao.numero}</td>
							        	<td>${usuarioProposicao.proposicao.ano}</td>
							        	<td>${usuarioProposicao.proposicao.txtEmenta}</td>
							        	<td><g:link action="removerProposicao" id="${usuarioProposicao.id}">Remover</g:link></td>
							        </tr>
								</g:each>
							</g:else>
					    </tbody>
					</table>
					<g:link action="adicionarProposicoes">Adicionar Proposição</g:link>	
				</div>
			</div>
			
			<br/>
			<g:link class="btn btn-primary" controller="painel" action="index">Voltar</g:link>			
		</div>

	</body>
</html>