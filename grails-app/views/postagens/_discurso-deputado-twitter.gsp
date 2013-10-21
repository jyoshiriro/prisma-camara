<g:each in="${discursos}" var="discurso" status="st">
Discurso d<g:deputadoPrefix dep="${dep}" minusculo="true"/> em <g:formatDate date="${discursos.first().data}" format="dd/MM"/> ${discurso.horaInicio[0..4]}
${discurso.sumario[0..38]}...
${discurso.urlDetalhes}
<hr></g:each>
