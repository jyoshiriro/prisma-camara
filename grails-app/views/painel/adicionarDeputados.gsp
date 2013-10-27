<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/> 
		<title>Painel</title>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<h1>Deputados</h1>
			<g:formRemote url="[action:'list',controller:'deputado']" name="searchableForm" update="resultado">
		        <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Search" />
		    </g:formRemote>
			<div id="resultado"></div>
			
			<%--<div>
				<g:form name="config" url="[action:'gravarConfiguracoes']">
					<h2>Partidos de Interesse</h2>
					<g:each in="${partidos}" var="partido" status="i">
					    <g:checkBox name="partidosSelecionados" value="${partido.id}" checked="${usuario.usuarioPartidos.find{it.partido.id==partido.id}}" />
					    <label for="partidosSelecionados">${partido.sigla}</label><br/>
					</g:each>
					<br/>
					<br/>
					<h2>Deputados de Interesse</h2>
					<g:each in="${deputados}" var="deputado" status="i">
					    <g:checkBox name="deputadosSelecionados" value="${deputado.id}" checked="${usuario.usuarioDeputados.find{it.deputado.id==deputado.id}}" />
					    <label for="deputadosSelecionados">${deputado.descricao}</label><br/>
					</g:each>
					<br/>
					<g:submitButton name="gravar" value="Gravar"/>
				</g:form>
			</div>--%>
			<br/>
			<g:link action="configurarPostagens">Voltar</g:link>			
		</div>
	</body>
</html>