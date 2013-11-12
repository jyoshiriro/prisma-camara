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
Mensagem gerada pelo "Olho na Câmara" (http://goo.gl/Drr2jj)

Você conheçe <g:deputadoPrefix dep="${dep}" minusculo="true"/>?

Nome completo: ${dep.nome}.

Contatos: ${dep.contatos}.

<g:if test="${dep.comissoesTitular || dep.comissoesSuplente}">
É membro de Comissões:

 • ${dep.comissoesTitular.size()?:'Nenhuma'} como Titular
   <g:each in="${dep.comissoesTitular}" var="c">- ${c.descricao}
   </g:each>
 • ${dep.comissoesSuplente.size()?:'Nenhuma'} como Suplente 
   <g:each in="${dep.comissoesSuplente}" var="c">- ${c.descricao}
   </g:each></g:if>
  <g:else>
Não faz parte de nenhuma comissão.
  </g:else>

Mais detalhes em ${dep.urlDetalhes}