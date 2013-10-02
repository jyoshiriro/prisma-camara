
<%@ page import="hackathon2013.FrequenciaDia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'frequenciaDia.label', default: 'FrequenciaDia')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-frequenciaDia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-frequenciaDia" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list frequenciaDia">
			
				<g:if test="${frequenciaDiaInstance?.deputado}">
				<li class="fieldcontain">
					<span id="deputado-label" class="property-label"><g:message code="frequenciaDia.deputado.label" default="Deputado" /></span>
					
						<span class="property-value" aria-labelledby="deputado-label"><g:link controller="deputado" action="show" id="${frequenciaDiaInstance?.deputado?.id}">${frequenciaDiaInstance?.deputado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${frequenciaDiaInstance?.dia}">
				<li class="fieldcontain">
					<span id="dia-label" class="property-label"><g:message code="frequenciaDia.dia.label" default="Dia" /></span>
					
						<span class="property-value" aria-labelledby="dia-label"><g:formatDate date="${frequenciaDiaInstance?.dia}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${frequenciaDiaInstance?.frequenciaDia}">
				<li class="fieldcontain">
					<span id="frequenciaDia-label" class="property-label"><g:message code="frequenciaDia.frequenciaDia.label" default="Frequencia Dia" /></span>
					
						<span class="property-value" aria-labelledby="frequenciaDia-label"><g:fieldValue bean="${frequenciaDiaInstance}" field="frequenciaDia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${frequenciaDiaInstance?.justificativa}">
				<li class="fieldcontain">
					<span id="justificativa-label" class="property-label"><g:message code="frequenciaDia.justificativa.label" default="Justificativa" /></span>
					
						<span class="property-value" aria-labelledby="justificativa-label"><g:fieldValue bean="${frequenciaDiaInstance}" field="justificativa"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${frequenciaDiaInstance?.id}" />
					<g:link class="edit" action="edit" id="${frequenciaDiaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
