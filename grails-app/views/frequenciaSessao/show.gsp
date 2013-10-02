
<%@ page import="hackathon2013.FrequenciaSessao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-frequenciaSessao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-frequenciaSessao" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list frequenciaSessao">
			
				<g:if test="${frequenciaSessaoInstance?.descricao}">
				<li class="fieldcontain">
					<span id="descricao-label" class="property-label"><g:message code="frequenciaSessao.descricao.label" default="Descricao" /></span>
					
						<span class="property-value" aria-labelledby="descricao-label"><g:fieldValue bean="${frequenciaSessaoInstance}" field="descricao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${frequenciaSessaoInstance?.frequencia}">
				<li class="fieldcontain">
					<span id="frequencia-label" class="property-label"><g:message code="frequenciaSessao.frequencia.label" default="Frequencia" /></span>
					
						<span class="property-value" aria-labelledby="frequencia-label"><g:fieldValue bean="${frequenciaSessaoInstance}" field="frequencia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${frequenciaSessaoInstance?.frequenciaDia}">
				<li class="fieldcontain">
					<span id="frequenciaDia-label" class="property-label"><g:message code="frequenciaSessao.frequenciaDia.label" default="Frequencia Dia" /></span>
					
						<span class="property-value" aria-labelledby="frequenciaDia-label"><g:link controller="frequenciaDia" action="show" id="${frequenciaSessaoInstance?.frequenciaDia?.id}">${frequenciaSessaoInstance?.frequenciaDia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${frequenciaSessaoInstance?.id}" />
					<g:link class="edit" action="edit" id="${frequenciaSessaoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
