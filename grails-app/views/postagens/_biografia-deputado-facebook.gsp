<g:set var="prefix" value="${dep.sexo?(dep.sexo.startsWith('f')?'A':'O'):'O(A)'}"/>
${prefix} Deputad${prefix.toLowerCase()} ${dep.descricao} participa de 
&bull; ${dep.comissoesTitular.size()} comissões como titular e de
&bull; ${dep.comissoesSuplente.size()} comissões como suplente. 

Saiba mais em ${dep.urlDetalhes}.
