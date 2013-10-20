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
