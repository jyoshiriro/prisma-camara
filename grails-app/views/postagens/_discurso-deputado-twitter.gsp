<g:each in="${discursos}" var="discurso" status="st">
Discurso d<g:deputadoPrefix dep="${dep}" minusculo="true"/> em <g:formatDate date="${discursos.first().data}" format="dd/MM"/> ${discurso.horaInicio[0..4]}
${discurso.sumario?.size()>=37?discurso.sumario[0..38]+"...":discurso.sumario}
${discurso.urlDetalhes}
<hr></g:each>Via http://goo.gl/Drr2jj
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