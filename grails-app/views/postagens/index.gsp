<%--
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 --%>
<%@page import="br.org.prismaCamara.modelo.Deputado"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
	li{
	margin-bottom: 1em;
	}
</style>
</head>
<body>

<g:set var="sDeputados">
	<g:select name="idDeputado" value="${params.idDeputado}" from="${deputados}" optionKey="id" optionValue="descricao"/>
</g:set>
	
<h2>Mensagens deseja ver agora?</h2>
<ul>
	<li><g:link action="biografiaDeputado">Biografia aleatória de Deputado</g:link> 
	</li>
	<li>
	<g:form action="frequenciaDeputado" method="get">
		${sDeputados}
		<input type="submit" value="Última Frequência"/> 
	</g:form>
	</li>
	<li>
	<g:form action="gastoDeputado" method="get">
		${sDeputados}
		<input type="submit" value="Últimos Gastos"/> 
	</g:form>
	</li>
	<li>
	<g:form action="discursoDeputado" method="get">
		${sDeputados}
		<input type="submit" value="Últimos Discursos"/> 
	</g:form>
	</li>
	<li>
	<g:form action="votacaoProposicao" method="get">
		<g:select name="idProposicao" value="${params.idProposicao}" 
		from="${proposicoes}" 
		optionKey="id" optionValue="descricao"/>
		<input type="submit" value="Últimos Votos"/> 
	</g:form>
	</li>
</ul>

<b>Formato Facebook</b>
<pre>${flash.postagem1}</pre>
<g:if test="${flash.postagem1}">
	<g:form controller="facebookPost" action="postarNoMural">
		<input type="hidden" name="mp" value="${flash.postagem1}"/>
		<input type="submit" value="Enviar postagem para o Facebook!"/> 
	</g:form>
</g:if>
<br>
<p>
<b>Formato Twitter</b>
<pre>${flash.postagem2}</pre>
<g:if test="${flash.postagem2}">
	<g:form controller="twitterPost" action="postarNoMural">
		<input type="hidden" name="mp" value="${flash.postagem2}"/>
		<input type="submit" value="Enviar postagem para o Twitter!"/> 
	</g:form>
</g:if>
</p>
${flash.error}

</body>
</html>