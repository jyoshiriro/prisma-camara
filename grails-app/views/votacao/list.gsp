
<%@ page import="hackathon2013.Votacao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'votacao.label', default: 'Votacao')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-votacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-votacao" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dataHoraVotacao" title="${message(code: 'votacao.dataHoraVotacao.label', default: 'Data Hora Votacao')}" />
					
						<g:sortableColumn property="objVotacao" title="${message(code: 'votacao.objVotacao.label', default: 'Obj Votacao')}" />
					
						<th><g:message code="votacao.proposicao.label" default="Proposicao" /></th>
					
						<g:sortableColumn property="resumo" title="${message(code: 'votacao.resumo.label', default: 'Resumo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${votacaoInstanceList}" status="i" var="votacaoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${votacaoInstance.id}">${fieldValue(bean: votacaoInstance, field: "dataHoraVotacao")}</g:link></td>
					
						<td>${fieldValue(bean: votacaoInstance, field: "objVotacao")}</td>
					
						<td>${fieldValue(bean: votacaoInstance, field: "proposicao")}</td>
					
						<td>${fieldValue(bean: votacaoInstance, field: "resumo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${votacaoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
