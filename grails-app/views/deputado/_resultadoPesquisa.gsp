<g:each in="${mapa}" var="m">
	<g:set var="e" value="${m.key}"/>
	<g:set var="mapeado" value="${m.value}"/>
	<div class="lado-a-lado">
		<img src="${createLink(action:'foto',id:e.id)}">
		<g:remoteLink action="toogleAssociar" id="${e.id}" asynchronous="false" after="toogleAssociar(this)" 
		update="nada" elementId="link${e.id}"
		class="marcado ${mapeado?'':'nao'}">&nbsp;&nbsp;&nbsp;&nbsp;
		</g:remoteLink>
		<br>
		${e.descricao}
	</div>
</g:each>
<div id="nada"></div>
<script>
	function toogleAssociar(elemento) {
		$("#"+elemento.id).toggleClass('nao');
	}
</script>