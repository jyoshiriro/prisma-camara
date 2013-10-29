<g:if test="${!params.q}">
	<p>Deputados que você já acompanha</p>
</g:if>
<g:if test="${request.message}">
	<div class="errors">
	<ul><li>${request.message}</li></ul>
	</div>
</g:if>
<g:each in="${mapa}" var="m">
	<g:set var="e" value="${m.key}"/>
	<div class="lado-a-lado ${m.value?'':'nao'}">
		<img src="${createLink(action:'foto',id:e.id)}" alt="foto" align="top">
		<g:remoteLink action="toogleAssociar" id="${e.id}" asynchronous="false" after="toogleAssociar(this)" 
		update="nada" elementId="link${e.id}">
		<span id="slink${e.id}" class="glyphicon ${m.value?'glyphicon-check':'glyphicon-plus'}"></span>
		</g:remoteLink>
		<br>${e.descricao}
		<br>
		<a href="${e.urlDetalhes}" target="_blank" class="curto" title="Detalhes no site da Câmara"><span class="glyphicon glyphicon-share-alt"></span> <b>Detalhes</b></a>
		
	</div>
</g:each>

<div id="nada"></div>