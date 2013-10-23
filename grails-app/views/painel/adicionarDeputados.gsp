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
			<g:form action="adicionarDeputados" id="searchableForm" name="searchableForm" method="get">
		        <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Search" />
		    </g:form>
			<div>
				<g:form action="gravarDeputados">
					<table id="tbDeputados">
					    <thead>
					        <tr>
					            <th width="60%">Deputado</th>
					            <th width="10%">Partido</th>
					            <th width="10%">Estado</th>
					            <th width="10%">Marcar</th>
					        </tr>
					    </thead>
					    <tbody>
					    	<g:if test="${listaDeputados.isEmpty()}">
					    		<tr>
							    	<td colspan="5">Nenhum deputado.</td>
								</tr>
					    	</g:if>
					    	<g:else>
						    	<g:each var="deputado" in="${listaDeputados}">
							    	<tr>
							        	<td>${deputado.nomeParlamentar} (${deputado.nome})</td>
							        	<td>${deputado.partido?.sigla}</td>
							        	<td>${deputado.uf}</td>
							        	<td><g:checkBox name="deputadosSelecionados" value="${deputado.id}" checked="false" /></td>
							        </tr>
								</g:each>
							</g:else>
					    </tbody>
					</table>
					<g:submitButton name="adicionar" value="Adicionar Selecionados" />
				</g:form>
			</div>
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