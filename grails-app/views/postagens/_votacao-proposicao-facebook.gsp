A Proposição ${prop.descricao} passou por uma nova votação em ${votacao.dataHoraVotacao.format("dd/MM/yy' às 'HH:mm")}

Objeto da Votação: ${votacao.objVotacao}
<g:if test="${votacao.resumo}">
Resumo: ${votacao.resumo}</g:if>
<g:each in="${mvotos}" var="voto">
&bull; ${voto.value.size()} voto${voto.value.size()==1?'':'s'} "${voto.key}": 
  <g:each in="${(voto.value-voto.value.last())}" var="vdep">${vdep.descricao}, </g:each>${voto.value.last().descricao} 
</g:each>

Mais detalhes em ${prop.urlDetalhes}.
