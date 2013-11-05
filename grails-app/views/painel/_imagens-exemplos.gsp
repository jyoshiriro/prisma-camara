<%@ page contentType="text/html;charset=UTF-8" %>

<h3 style="cursor: pointer;" onclick="$('#divExemplos').toggle()"><u>Exemplos de Postagens Face/Twitter</u></h3>
<div id="divExemplos" style="display: none;">
	<p><i><b>Obs:</b> os dados exibidos nas imagens são reais</i></p>
	<p>
	<img src="${resource(dir:'images',file:'ss-exemplo-post-facebook1.png')}" alt="Exemplo 1 (Facebook)">
	</p>
	<p>
	<img src="${resource(dir:'images',file:'ss-exemplo-post-facebook2.png')}" alt="Exemplo 2 (Facebook)">
	</p>
	<p>
	<img src="${resource(dir:'images',file:'ss-exemplo-post-facebook3.png')}" alt="Exemplo 3 (Facebook)">
	</p>
	<p>
	<img src="${resource(dir:'images',file:'ss-exemplo-post-twitter1.png')}" alt="Exemplo 1 (Twitter)">
	</p>
	<p>
	<img src="${resource(dir:'images',file:'ss-exemplo-post-twitter2.png')}" alt="Exemplo 2 (Twitter)">
	</p>
</div>