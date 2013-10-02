
<%@ page import="hackathon2013.FrequenciaDia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'frequenciaDia.label', default: 'FrequenciaDia')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-frequenciaDia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-frequenciaDia" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="frequenciaDia.deputado.label" default="Deputado" /></th>
					
						<g:sortableColumn property="dia" title="${message(code: 'frequenciaDia.dia.label', default: 'Dia')}" />
					
						<g:sortableColumn property="frequenciaDia" title="${message(code: 'frequenciaDia.frequenciaDia.label', default: 'Frequencia Dia')}" />
					
						<g:sortableColumn property="justificativa" title="${message(code: 'frequenciaDia.justificativa.label', default: 'Justificativa')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${frequenciaDiaInstanceList}" status="i" var="frequenciaDiaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${frequenciaDiaInstance.id}">${fieldValue(bean: frequenciaDiaInstance, field: "deputado")}</g:link></td>
					
						<td><g:formatDate date="${frequenciaDiaInstance.dia}" /></td>
					
						<td>${fieldValue(bean: frequenciaDiaInstance, field: "frequenciaDia")}</td>
					
						<td>${fieldValue(bean: frequenciaDiaInstance, field: "justificativa")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${frequenciaDiaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
