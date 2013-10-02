
<%@ page import="hackathon2013.Deputado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'deputado.label', default: 'Deputado')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-deputado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-deputado" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list deputado">
			
				<g:if test="${deputadoInstance?.condicao}">
				<li class="fieldcontain">
					<span id="condicao-label" class="property-label"><g:message code="deputado.condicao.label" default="Condicao" /></span>
					
						<span class="property-value" aria-labelledby="condicao-label"><g:fieldValue bean="${deputadoInstance}" field="condicao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="deputado.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${deputadoInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.fone}">
				<li class="fieldcontain">
					<span id="fone-label" class="property-label"><g:message code="deputado.fone.label" default="Fone" /></span>
					
						<span class="property-value" aria-labelledby="fone-label"><g:fieldValue bean="${deputadoInstance}" field="fone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.ideCadastro}">
				<li class="fieldcontain">
					<span id="ideCadastro-label" class="property-label"><g:message code="deputado.ideCadastro.label" default="Ide Cadastro" /></span>
					
						<span class="property-value" aria-labelledby="ideCadastro-label"><g:fieldValue bean="${deputadoInstance}" field="ideCadastro"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.matricula}">
				<li class="fieldcontain">
					<span id="matricula-label" class="property-label"><g:message code="deputado.matricula.label" default="Matricula" /></span>
					
						<span class="property-value" aria-labelledby="matricula-label"><g:fieldValue bean="${deputadoInstance}" field="matricula"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="deputado.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${deputadoInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.nomeParlamentar}">
				<li class="fieldcontain">
					<span id="nomeParlamentar-label" class="property-label"><g:message code="deputado.nomeParlamentar.label" default="Nome Parlamentar" /></span>
					
						<span class="property-value" aria-labelledby="nomeParlamentar-label"><g:fieldValue bean="${deputadoInstance}" field="nomeParlamentar"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.partido}">
				<li class="fieldcontain">
					<span id="partido-label" class="property-label"><g:message code="deputado.partido.label" default="Partido" /></span>
					
						<span class="property-value" aria-labelledby="partido-label"><g:fieldValue bean="${deputadoInstance}" field="partido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.sexo}">
				<li class="fieldcontain">
					<span id="sexo-label" class="property-label"><g:message code="deputado.sexo.label" default="Sexo" /></span>
					
						<span class="property-value" aria-labelledby="sexo-label"><g:fieldValue bean="${deputadoInstance}" field="sexo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deputadoInstance?.uf}">
				<li class="fieldcontain">
					<span id="uf-label" class="property-label"><g:message code="deputado.uf.label" default="Uf" /></span>
					
						<span class="property-value" aria-labelledby="uf-label"><g:fieldValue bean="${deputadoInstance}" field="uf"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${deputadoInstance?.id}" />
					<g:link class="edit" action="edit" id="${deputadoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
