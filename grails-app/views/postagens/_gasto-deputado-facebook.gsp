Veja os recentes gastos da cota parlamentar d<g:deputadoPrefix dep="${dep}" minusculo="true"/>:
<g:each in="${despesas}" var="despesa" status="st">
${st+1}. Despesa: ${despesa.txtDescricao}
Pago a:	${despesa.txtBeneficiario} (${despesa.descDoc})
Número do documento: ${despesa.txtNumero}
Data da emissão: <g:formatDate date="${despesa.dataEmissao}" format="dd/MM/yyyy"/> 
Valor solicitado: R${'$'}${despesa.valor}
Valor glosa: R${'$'}${despesa.valorGlosa}
Parcela: ${despesa.numParcela?:'(única)'} 
</g:each>

Contatos: ${dep.contatos}.

Mais detalhes em ${despesas.first().urlDetalhes}