
<%@ page import="hackathon2013.Deputado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'deputado.label', default: 'Deputado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-deputado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-deputado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="condicao" title="${message(code: 'deputado.condicao.label', default: 'Condicao')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'deputado.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="fone" title="${message(code: 'deputado.fone.label', default: 'Fone')}" />
					
						<g:sortableColumn property="ideCadastro" title="${message(code: 'deputado.ideCadastro.label', default: 'Ide Cadastro')}" />
					
						<g:sortableColumn property="matricula" title="${message(code: 'deputado.matricula.label', default: 'Matricula')}" />
					
						<g:sortableColumn property="nome" title="${message(code: 'deputado.nome.label', default: 'Nome')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${deputadoInstanceList}" status="i" var="deputadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${deputadoInstance.id}">${fieldValue(bean: deputadoInstance, field: "condicao")}</g:link></td>
					
						<td>${fieldValue(bean: deputadoInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: deputadoInstance, field: "fone")}</td>
					
						<td>${fieldValue(bean: deputadoInstance, field: "ideCadastro")}</td>
					
						<td>${fieldValue(bean: deputadoInstance, field: "matricula")}</td>
					
						<td>${fieldValue(bean: deputadoInstance, field: "nome")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${deputadoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
