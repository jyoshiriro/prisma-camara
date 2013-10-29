<g:if test="${!params.q}">
	<p>Proposições que você já acompanha</p>
</g:if>
<g:if test="${request.message}">
	<div class="errors">
	<ul><li>${request.message}</li></ul>
	</div>
</g:if>

<div id="divDetalhes"></div>

<g:each in="${mapa}" var="m">
	<g:set var="e" value="${m.key}"/>
	<div class="lado-a-lado pequeno ${m.value?'':'nao'}">
	
		<g:remoteLink action="detalhes" id="${e.id}" update="divDetalhes" title="Clique para detalhes">${e.descricao}</g:remoteLink>
		
		<g:remoteLink action="toogleAssociar" id="${e.id}" asynchronous="false" after="toogleAssociar(this)" 
		update="nada" elementId="link${e.id}">
		<span id="slink${e.id}" class="glyphicon ${m.value?'glyphicon-check':'glyphicon-plus'}"></span>
		</g:remoteLink>
	</div>
</g:each>

<div id="nada"></div>