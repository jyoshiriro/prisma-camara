
<%@ page import="hackathon2013.Votacao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'votacao.label', default: 'Votacao')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-votacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-votacao" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list votacao">
			
				<g:if test="${votacaoInstance?.dataHoraVotacao}">
				<li class="fieldcontain">
					<span id="dataHoraVotacao-label" class="property-label"><g:message code="votacao.dataHoraVotacao.label" default="Data Hora Votacao" /></span>
					
						<span class="property-value" aria-labelledby="dataHoraVotacao-label"><g:formatDate date="${votacaoInstance?.dataHoraVotacao}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${votacaoInstance?.objVotacao}">
				<li class="fieldcontain">
					<span id="objVotacao-label" class="property-label"><g:message code="votacao.objVotacao.label" default="Obj Votacao" /></span>
					
						<span class="property-value" aria-labelledby="objVotacao-label"><g:fieldValue bean="${votacaoInstance}" field="objVotacao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${votacaoInstance?.proposicao}">
				<li class="fieldcontain">
					<span id="proposicao-label" class="property-label"><g:message code="votacao.proposicao.label" default="Proposicao" /></span>
					
						<span class="property-value" aria-labelledby="proposicao-label"><g:link controller="proposicao" action="show" id="${votacaoInstance?.proposicao?.id}">${votacaoInstance?.proposicao?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${votacaoInstance?.resumo}">
				<li class="fieldcontain">
					<span id="resumo-label" class="property-label"><g:message code="votacao.resumo.label" default="Resumo" /></span>
					
						<span class="property-value" aria-labelledby="resumo-label"><g:fieldValue bean="${votacaoInstance}" field="resumo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${votacaoInstance?.id}" />
					<g:link class="edit" action="edit" id="${votacaoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
