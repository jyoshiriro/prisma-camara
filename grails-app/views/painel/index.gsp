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
			<nav class="navbar navbar-default">
				<div class="nav navbar-nav">
					<p class="navbar-text">
					<span class="glyphicon glyphicon-user"></span>
					<g:link controller="deputado">Deputados</g:link> 
					</p>
				</div>
				<div class="nav navbar-nav navbar-right">
					<g:if test="${session.contagemDeputados}">
					<p class="navbar-text">Você acompanha <b>${session.contagemDeputados}</b></p>
					</g:if>
					<g:else>
					<p class="navbar-text">Não acompanha nenhum(a)</p>
					</g:else>
				</div>
			</nav>
			
			<nav class="navbar navbar-default">
				<div class="nav navbar-nav">
					<p class="navbar-text">
					<span class="glyphicon glyphicon-link"></span>
					<g:link controller="partido">Partidos</g:link>
					</p>
				</div>
				<div class="nav navbar-nav navbar-right">
					<g:if test="${session.contagemPartidos}">
					<p class="navbar-text">Você acompanha <b id="uContPartidos">${session.contagemPartidos}</b></p>
					</g:if>
					<g:else>
					<p class="navbar-text">Não acompanha nenhum</p>
					</g:else>
				</div>
			</nav>
			
			
			<nav class="navbar navbar-default">
				<div class="nav navbar-nav">
					<p class="navbar-text">
					<span class="glyphicon glyphicon-transfer"></span>
					<g:link controller="proposicao">Proposições</g:link>
					</p>
				</div>
				<div class="nav navbar-nav navbar-right">
					<g:if test="${session.contagemProposicoes}">
					<p class="navbar-text">Você acompanha <b>${session.contagemProposicoes}</b></p>
					</g:if>
					<g:else>
					<p class="navbar-text">Não acompanha nenhuma</p>
					</g:else>
				</div>
			</nav>
			
		</div>

	</body>
</html>