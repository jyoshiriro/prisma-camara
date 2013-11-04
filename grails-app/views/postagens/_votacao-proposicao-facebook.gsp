<%--
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 --%>
A Proposição ${prop.descricao} passou por uma nova votação em ${votacao.dataHoraVotacao.format("dd/MM/yy' às 'HH:mm")}

Objeto da Votação: ${votacao.objVotacao}
<g:if test="${votacao.resumo}">
Resumo: ${votacao.resumo}</g:if>
<g:each in="${mvotos}" var="voto">
• ${voto.value.size()} voto${voto.value.size()==1?'':'s'} "${voto.key}": 
  <g:each in="${(voto.value-voto.value.last())}" var="vdep">${vdep.descricaoSemCaixaAlta}, </g:each>${voto.value.last().descricaoSemCaixaAlta} 
</g:each>

Mais detalhes em ${prop.urlDetalhes}


Mensagem gerada pelo "Olho na Câmara" (http://goo.gl/Drr2jj)