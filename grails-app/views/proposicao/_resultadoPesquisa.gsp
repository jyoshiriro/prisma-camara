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
<g:if test="${!params.q}">
	<g:if test="${mapa}">
		<p>Proposições que você já acompanha</p>
	</g:if>
	<g:else>
		<p><i>Você ainda não acompanha nenhuma Proposição. Pesquise acima.</i></p>
	</g:else>
</g:if>
<g:else>
<p>
Encontradas:
<span class="label label-primary">${mapa.size()}</span>
&nbsp; <a href="javascript:;" onclick="limparPesquisa()"
			id="linkLimparPesquisa"><span
			class="glyphicon glyphicon-collapse-up"></span> 
			Ver só as que já Acompanho</a>
	</p>
</g:else>
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
<g:remoteLink elementId="linkContagem" 
controller="painel" action="contagem" id="Proposicoes" update="pContagem"></g:remoteLink>
<div id="nada"></div>