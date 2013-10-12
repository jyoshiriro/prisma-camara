<g:deputadoPrefix dep="${dep}"/> proferiu ${discursos.size()} discurso${discursos?'s':''} na Câmara em <g:formatDate date="${discursos.first().data}" format="dd/MM/yyyy"/>: 
<g:each in="${discursos}" var="discurso" status="st">
 ${st+1}. Com inicio às ${discurso.horaInicio}
 
 Sumário: 
 ${discurso.sumario.trim()}

 Mais detalhes em ${discurso.urlDetalhes}
</g:each>
