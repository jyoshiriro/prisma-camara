
<%@ page import="hackathon2013.OrientacaoBancada" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-orientacaoBancada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-orientacaoBancada" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list orientacaoBancada">
			
				<g:if test="${orientacaoBancadaInstance?.bancada}">
				<li class="fieldcontain">
					<span id="bancada-label" class="property-label"><g:message code="orientacaoBancada.bancada.label" default="Bancada" /></span>
					
						<span class="property-value" aria-labelledby="bancada-label"><g:fieldValue bean="${orientacaoBancadaInstance}" field="bancada"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${orientacaoBancadaInstance?.orientacao}">
				<li class="fieldcontain">
					<span id="orientacao-label" class="property-label"><g:message code="orientacaoBancada.orientacao.label" default="Orientacao" /></span>
					
						<span class="property-value" aria-labelledby="orientacao-label"><g:fieldValue bean="${orientacaoBancadaInstance}" field="orientacao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${orientacaoBancadaInstance?.votacao}">
				<li class="fieldcontain">
					<span id="votacao-label" class="property-label"><g:message code="orientacaoBancada.votacao.label" default="Votacao" /></span>
					
						<span class="property-value" aria-labelledby="votacao-label"><g:link controller="votacao" action="show" id="${orientacaoBancadaInstance?.votacao?.id}">${orientacaoBancadaInstance?.votacao?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${orientacaoBancadaInstance?.id}" />
					<g:link class="edit" action="edit" id="${orientacaoBancadaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
