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
<g:deputadoPrefix dep="${dep}"/> proferiu ${discursos.size()} discurso${discursos.size()>1?'s':''} na Câmara em <g:formatDate date="${discursos.first().data}" format="dd/MM/yyyy"/>: 
<g:each in="${discursos}" var="discurso" status="st">
 ${st+1}. Com inicio às ${discurso.horaInicio}
 
 Sumário: 
 ${discurso.sumario.trim()}

 Contatos: ${dep.contatos}.
 
 Mais detalhes em ${discurso.urlDetalhes}
 
</g:each>
 Mensagem gerada pelo "Olho na Câmara" (http://goo.gl/fWAHmG)
