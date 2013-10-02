
<%@ page import="hackathon2013.OrientacaoBancada" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-orientacaoBancada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-orientacaoBancada" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="bancada" title="${message(code: 'orientacaoBancada.bancada.label', default: 'Bancada')}" />
					
						<g:sortableColumn property="orientacao" title="${message(code: 'orientacaoBancada.orientacao.label', default: 'Orientacao')}" />
					
						<th><g:message code="orientacaoBancada.votacao.label" default="Votacao" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${orientacaoBancadaInstanceList}" status="i" var="orientacaoBancadaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${orientacaoBancadaInstance.id}">${fieldValue(bean: orientacaoBancadaInstance, field: "bancada")}</g:link></td>
					
						<td>${fieldValue(bean: orientacaoBancadaInstance, field: "orientacao")}</td>
					
						<td>${fieldValue(bean: orientacaoBancadaInstance, field: "votacao")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${orientacaoBancadaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
