<g:if test="${!params.q}">
	<p>Deputados que você já acompanha</p>
</g:if>
<g:each in="${mapa}" var="m">
	<g:set var="e" value="${m.key}"/>
	<g:set var="mapeado" value="marcado${m.value?'':' nao'}"/>
	<div class="lado-a-lado">
		<div style="text-align: center">
		<img src="${createLink(action:'foto',id:e.id)}" alt="foto" align="top">
		<g:remoteLink action="toogleAssociar" id="${e.id}" asynchronous="false" after="toogleAssociar(this)" 
		update="nada" elementId="link${e.id}">
		<b class="${mapeado}" id="slink${e.id}" style="float: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
		</g:remoteLink>
		</div>
		${e.descricao}
	</div>
</g:each>

<div id="nada"></div>
<script>
	function toogleAssociar(elemento) {
		$("#s"+elemento.id).toggleClass('nao');
	}
</script>