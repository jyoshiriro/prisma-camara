<g:if test="${!params.q && mapa}">
	<p>Partidos que você já acompanha</p>
</g:if>
<g:if test="${request.message}">
	<div class="errors">
	<ul><li>${request.message}</li></ul>
	</div>
</g:if>

<g:each in="${mapa}" var="m">
	<g:set var="e" value="${m.key}"/>
	<div class="lado-a-lado medio ${m.value?'':'nao'}">
	
		${e.sigla}
		<g:remoteLink action="toogleAssociar" id="${e.id}" asynchronous="false" after="toogleAssociar(this)" 
		update="nada" elementId="link${e.id}">
		<span id="slink${e.id}" class="glyphicon ${m.value?'glyphicon-check':'glyphicon-plus'}"></span>
		</g:remoteLink>
		
		<br>
		<span>${e.nome}</span>
	</div>
</g:each>

<g:remoteLink elementId="linkContagem" 
controller="painel" action="contagem" id="Partidos" update="pContagem"></g:remoteLink>
<div id="nada"></div>