<g:if test="${!params.q}">
	<g:if test="${mapa}">
		<p>Deputados que você já acompanha</p>
	</g:if>
	<g:else>
		<p><i>Você ainda não acompanha nenhum(a) Deputado(a). Pesquise acima.</i></p>
	</g:else>
</g:if>
<g:else>
<p>
Encontrados:
<span class="label label-primary">${mapa.size()}</span>
&nbsp; <a href="javascript:;" onclick="limparPesquisa()"
			id="linkLimparPesquisa"><span
			class="glyphicon glyphicon-collapse-up"></span> 
			Ver só os(as) que já Acompanho</a>
	</p>
</g:else>

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
		<br><span class="sDeps" data-toggle="tooltip" title="${e.nome}" data-placement="bottom">${e.descricao}</span>
		<br>
		<a href="${e.urlDetalhes}" target="_blank" class="curto" title="Detalhes no site da Câmara"><span class="glyphicon glyphicon-share-alt"></span> <b>Detalhes</b></a>
		
	</div>
</g:each>
<g:remoteLink elementId="linkContagem" 
controller="painel" action="contagem" id="Deputados" update="pContagem"></g:remoteLink>
<div id="nada"></div>
