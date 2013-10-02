
<%@ page import="hackathon2013.Proposicao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proposicao.label', default: 'Proposicao')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-proposicao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-proposicao" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list proposicao">
			
				<g:if test="${proposicaoInstance?.ano}">
				<li class="fieldcontain">
					<span id="ano-label" class="property-label"><g:message code="proposicao.ano.label" default="Ano" /></span>
					
						<span class="property-value" aria-labelledby="ano-label"><g:fieldValue bean="${proposicaoInstance}" field="ano"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.autor}">
				<li class="fieldcontain">
					<span id="autor-label" class="property-label"><g:message code="proposicao.autor.label" default="Autor" /></span>
					
						<span class="property-value" aria-labelledby="autor-label"><g:link controller="deputado" action="show" id="${proposicaoInstance?.autor?.id}">${proposicaoInstance?.autor?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.dataApresentacao}">
				<li class="fieldcontain">
					<span id="dataApresentacao-label" class="property-label"><g:message code="proposicao.dataApresentacao.label" default="Data Apresentacao" /></span>
					
						<span class="property-value" aria-labelledby="dataApresentacao-label"><g:formatDate date="${proposicaoInstance?.dataApresentacao}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="proposicao.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${proposicaoInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="proposicao.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${proposicaoInstance}" field="numero"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.sigla}">
				<li class="fieldcontain">
					<span id="sigla-label" class="property-label"><g:message code="proposicao.sigla.label" default="Sigla" /></span>
					
						<span class="property-value" aria-labelledby="sigla-label"><g:fieldValue bean="${proposicaoInstance}" field="sigla"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.situacao}">
				<li class="fieldcontain">
					<span id="situacao-label" class="property-label"><g:message code="proposicao.situacao.label" default="Situacao" /></span>
					
						<span class="property-value" aria-labelledby="situacao-label"><g:fieldValue bean="${proposicaoInstance}" field="situacao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.txtApreciacao}">
				<li class="fieldcontain">
					<span id="txtApreciacao-label" class="property-label"><g:message code="proposicao.txtApreciacao.label" default="Txt Apreciacao" /></span>
					
						<span class="property-value" aria-labelledby="txtApreciacao-label"><g:fieldValue bean="${proposicaoInstance}" field="txtApreciacao"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.txtDespacho}">
				<li class="fieldcontain">
					<span id="txtDespacho-label" class="property-label"><g:message code="proposicao.txtDespacho.label" default="Txt Despacho" /></span>
					
						<span class="property-value" aria-labelledby="txtDespacho-label"><g:fieldValue bean="${proposicaoInstance}" field="txtDespacho"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.txtEmenta}">
				<li class="fieldcontain">
					<span id="txtEmenta-label" class="property-label"><g:message code="proposicao.txtEmenta.label" default="Txt Ementa" /></span>
					
						<span class="property-value" aria-labelledby="txtEmenta-label"><g:fieldValue bean="${proposicaoInstance}" field="txtEmenta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.txtExplicacaoEmenta}">
				<li class="fieldcontain">
					<span id="txtExplicacaoEmenta-label" class="property-label"><g:message code="proposicao.txtExplicacaoEmenta.label" default="Txt Explicacao Ementa" /></span>
					
						<span class="property-value" aria-labelledby="txtExplicacaoEmenta-label"><g:fieldValue bean="${proposicaoInstance}" field="txtExplicacaoEmenta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${proposicaoInstance?.ultimoDespacho}">
				<li class="fieldcontain">
					<span id="ultimoDespacho-label" class="property-label"><g:message code="proposicao.ultimoDespacho.label" default="Ultimo Despacho" /></span>
					
						<span class="property-value" aria-labelledby="ultimoDespacho-label"><g:formatDate date="${proposicaoInstance?.ultimoDespacho}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${proposicaoInstance?.id}" />
					<g:link class="edit" action="edit" id="${proposicaoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
