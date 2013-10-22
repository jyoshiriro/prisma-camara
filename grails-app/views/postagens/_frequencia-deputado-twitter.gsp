<g:set var="presente" value="${freq?.frequenciaDia.startsWith('P')}"/>
Frequência d<g:deputadoPrefix dep="${dep}" minusculo="true"/> em <g:formatDate date="${freq.dia}" format="d/M/yy"/>: ${presente?'Presente':'Faltou'}<g:if test="${!presente}"><g:if test="${freq.justificativa}"> (${freq.justificativa})</g:if><g:else> (não justificou)</g:else>
</g:if>
${freq.urlDetalhesCurta}