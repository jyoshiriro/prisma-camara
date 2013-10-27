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
		        <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Pesquisar" id="bPesquisar"/>
		    </g:formRemote>
			<div id="resultado">
				<g:include controller="deputado" action="list"/>
			</div>						
		</div>
	</body>
</html>