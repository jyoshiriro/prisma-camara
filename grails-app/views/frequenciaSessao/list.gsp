
<%@ page import="hackathon2013.FrequenciaSessao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-frequenciaSessao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-frequenciaSessao" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descricao" title="${message(code: 'frequenciaSessao.descricao.label', default: 'Descricao')}" />
					
						<g:sortableColumn property="frequencia" title="${message(code: 'frequenciaSessao.frequencia.label', default: 'Frequencia')}" />
					
						<th><g:message code="frequenciaSessao.frequenciaDia.label" default="Frequencia Dia" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${frequenciaSessaoInstanceList}" status="i" var="frequenciaSessaoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${frequenciaSessaoInstance.id}">${fieldValue(bean: frequenciaSessaoInstance, field: "descricao")}</g:link></td>
					
						<td>${fieldValue(bean: frequenciaSessaoInstance, field: "frequencia")}</td>
					
						<td>${fieldValue(bean: frequenciaSessaoInstance, field: "frequenciaDia")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${frequenciaSessaoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
