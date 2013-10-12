Veja os gastos da cota parlamentar recentes d<g:deputadoPrefix dep="${dep}" minusculo="true"/>:
<g:each in="${despesas}" var="despesa" status="st">
${st+1}. Despesa: ${despesa.txtDescricao}
Pago a:	${despesa.txtBeneficiario} (${despesa.descDoc})
Número do documento: ${despesa.txtNumero}
Data da emissão: <g:formatDate date="${despesa.dataEmissao}" format="dd/MM/yyyy"/> 
Valor pago: R${'$'}${despesa.valor}
Parcela: ${despesa.numParcela?:'(única)'} 
</g:each>

Mais detalhes em ${despesas.first().urlDetalhes}.
