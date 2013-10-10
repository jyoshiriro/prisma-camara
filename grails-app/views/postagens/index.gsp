<%@page import="hackathon2013.Deputado"%>
<style>
	li{
	margin-bottom: 1em;
	}
</style>

<h2>Mensagens deseja ver agora?</h2>
<ul>
	<li><g:link action="biografiaDeputado">Biografia aleatória de Deputado</g:link> 
	</li>
	<li>
	<g:form action="frequenciaDeputado" method="get">
		Qual ID no banco? <g:select name="idDeputado" value="${params.idDeputado}" from="${Deputado.findAllByAtivo(true,[sort:'nomeParlamentar'])}" optionKey="id" optionValue="descricao"/>
		<input type="submit" value="Última Frequência de Deputado"/> 
	</g:form> 
	</li>

</ul>

<pre>${flash.postagem}</pre>
${flash.error}

<g:if test="${flash.postagem}">
	<g:form controller="facebookPost" action="postarNoMural">
		<input type="hidden" name="mp" value="${flash.postagem}"/>
		<input type="submit" value="Enviar postagem para o face! Ah muleke!"/> 
	</g:form>
</g:if>