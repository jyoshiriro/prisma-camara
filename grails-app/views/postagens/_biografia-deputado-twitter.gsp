<g:deputadoPrefix dep="${dep}"/>:<g:if test="${dep.comissoesTitular || dep.comissoesSuplente}">
É membro de Comissões: 
• ${dep.comissoesTitular.size()?:'Nenhuma'} como Titular
${dep.comissoesSuplente.size()?'• '+dep.comissoesSuplente.size():'Nenhuma'} como Suplente 
</g:if><g:else>
Não faz parte de nenhuma comissão.
</g:else>${dep.urlDetalhesCurta}
