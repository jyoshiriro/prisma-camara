<g:set var="etwitter" value="${tipoRedeUsuario()=='twitter'}"/>
<g:set var="asterisco" value="${etwitter?'*':''}"/>
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
				<div class="nav navbar-nav ">
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
				
				<div class="alert alert-warning painel">
				    <p>
				    Pesquise e adicione <b>Deputados</b> à sua conta.
					</p>
					<p>
					De cada Deputado que escolher acompanhar, receberá sem sua rede social, diariamente as suas:
					</p>
					<ol>
						<li><span>Frequência diária</span></li>
						<li><span>Gastos que realizou</span></li>
						<li><span>Discursos que proferiu</span></li>
					</ol>
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
				
				<div class="alert alert-success painel">
				    <p>
				    Pesquise e adicione <b>Partidos</b> à sua conta.
					</p>
					<p>
					De cada Partido que escolher acompanhar, receberá sem sua rede social, diariamente informações de todos os seus Deputados:
					</p>
					<ol>
						<li><span>Frequência diária</span></li>
						<li><span>Gastos que realizou</span></li>
						<li><span>Discursos que proferiu</span></li>
					</ol>
					</p>
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
				
				<div class="alert alert-info painel">
				    <p>
				    Pesquise e adicione <b>Proposições</b> à sua conta.
					</p>
					<p>
					Sempre que uma Proposição que você acompanha sofrer uma votação, você redeberá os seguintes detalhes em sua rede social 
					</p>
					<ol>
						<li><span>Objeto da Votação</span></li>
						<li><span>Resumo ${asterisco}</span></li>
						<li><span>Quantitativo de cada voto</span></li>
						<li><span>Nomes dos Deputados em cada um dos votos ${asterisco}</span></li>
					</ol>
					</p>
				</div>				
			</nav>
			
			<g:if test="${etwitter}">
			
			<div style="clear: both;"></div>
			
			<div class="alert alert-danger">
				<b>*</b> Alguns detalhes só chegam para contas do <b>Facebook</b>. Contas do <b>Twitter</b> recebem informações bem mais resumidas.
			</div>
			
			</g:if>
			
		</div>

	</body>
</html>