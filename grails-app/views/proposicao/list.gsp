
<%@ page import="hackathon2013.Proposicao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proposicao.label', default: 'Proposicao')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-proposicao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-proposicao" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="ano" title="${message(code: 'proposicao.ano.label', default: 'Ano')}" />
					
						<th><g:message code="proposicao.autor.label" default="Autor" /></th>
					
						<g:sortableColumn property="dataApresentacao" title="${message(code: 'proposicao.dataApresentacao.label', default: 'Data Apresentacao')}" />
					
						<g:sortableColumn property="nome" title="${message(code: 'proposicao.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="numero" title="${message(code: 'proposicao.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="sigla" title="${message(code: 'proposicao.sigla.label', default: 'Sigla')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${proposicaoInstanceList}" status="i" var="proposicaoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${proposicaoInstance.id}">${fieldValue(bean: proposicaoInstance, field: "ano")}</g:link></td>
					
						<td>${fieldValue(bean: proposicaoInstance, field: "autor")}</td>
					
						<td><g:formatDate date="${proposicaoInstance.dataApresentacao}" /></td>
					
						<td>${fieldValue(bean: proposicaoInstance, field: "nome")}</td>
					
						<td>${fieldValue(bean: proposicaoInstance, field: "numero")}</td>
					
						<td>${fieldValue(bean: proposicaoInstance, field: "sigla")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${proposicaoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
