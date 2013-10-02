
<%@ page import="hackathon2013.Voto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'voto.label', default: 'Voto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-voto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-voto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="voto.deputado.label" default="Deputado" /></th>
					
						<th><g:message code="voto.votacao.label" default="Votacao" /></th>
					
						<g:sortableColumn property="voto" title="${message(code: 'voto.voto.label', default: 'Voto')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${votoInstanceList}" status="i" var="votoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${votoInstance.id}">${fieldValue(bean: votoInstance, field: "deputado")}</g:link></td>
					
						<td>${fieldValue(bean: votoInstance, field: "votacao")}</td>
					
						<td>${fieldValue(bean: votoInstance, field: "voto")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${votoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
