<g:set var="presente" value="${freq?.frequenciaDia.startsWith('P')}"/>
Novo registro de frequência d<g:deputadoPrefix dep="${dep}" minusculo="true"/> na Câmara dos Deputados:

• Dia <g:formatDate date="${freq.dia}"/> -> ${presente?'Presente':'Ausente'}. 
<g:if test="${!presente}">
<g:if test="${freq.justificativa}">* Justificativa para ausência: ${freq.justificativa} *</g:if>
<g:else>* A ausência não foi justificada! *</g:else>
</g:if>
<g:if test="${presente}">Participação na${souma?'':'s'} ${souma?'':qsessoes+' '}Sess${souma?'ão':'ões'} Realizada${souma?'':'s'} neste dia:</g:if>
<g:else>Como faltou, perdeu ${qsessoes} Sess${souma?'ão':'ões'}:</g:else>
<g:each in="${freq.frequenciasSessao}" var="fs" status="st">
${st+1}. ${fs.descricao} <g:formatDate date="${fs.inicio}" format="HH:mm"/><g:if test="${presente}"> -> ${fs.frequencia?.startsWith('P')?'Presente':'Ausente'}</g:if>
</g:each>

Contatos: ${dep.contatos}.

Mais detalhes em ${freq.urlDetalhes}

Mensagem gerada pelo "Olho na Câmara" (http://goo.gl/fWAHmG)