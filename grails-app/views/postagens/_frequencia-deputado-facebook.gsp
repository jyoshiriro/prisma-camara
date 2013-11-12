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
Mensagem gerada pelo "Olho na Câmara" http://goo.gl/Drr2jj

Veja os recentes registros de frequência d<g:deputadoPrefix dep="${dep}" minusculo="true"/> na Câmara dos Deputados:
<g:each in="${frequencias}" var="freq"><g:set var="presente" value="${freq?.frequenciaDia.startsWith('P')}"/><g:set var="qsessoes" value="${freq.frequenciasSessao?.size()}"/><g:set var="souma" value="${qsessoes==1}"/>

• Dia <g:formatDate date="${freq.dia}"/> -> ${presente?'Presente':'Ausente'}. 
<g:if test="${!presente}">
<g:if test="${freq.justificativa}">* Justificativa para ausência: ${freq.justificativa} *</g:if>
<g:else>* A ausência não foi justificada! *</g:else>
</g:if>
<g:if test="${presente}">Participação na${souma?'':'s'} ${souma?'':qsessoes+' '}Sess${souma?'ão':'ões'} Realizada${souma?'':'s'} neste dia:</g:if>
<g:else>Como faltou, perdeu ${qsessoes} Sess${souma?'ão':'ões'}:</g:else>
<g:each in="${freq.frequenciasSessao}" var="fs" status="st">
${st+1}. ${fs.descricao} <g:formatDate date="${fs.inicio}" format="HH:mm"/><g:if test="${presente}"> -> ${fs.frequencia?.startsWith('P')?'Presente':'Ausente'}</g:if>
</g:each></g:each>

Contatos: ${frequencias.first().deputado.contatos}.

Mais detalhes em ${frequencias.first().urlDetalhes}