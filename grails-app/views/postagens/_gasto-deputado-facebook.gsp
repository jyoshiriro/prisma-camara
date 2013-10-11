Como andam os gastos da cota parlamentar d<g:deputadoPrefix dep="${dep}" minusculo="true"/>?
<g:each in="${despesas}" var="despesa">
Despesa: ${despesa.txtDescricao}
Pago a:	${despesa.txtBeneficiario} (${despesa.descDoc})
Número do documento: ${despesa.txtNumero}
Data da emissão: <g:formatDate date="${despesa.dataEmissao}" format="dd/MM/yyyy"/> 
Valor pago: R${'$'}${despesa.valor}
Parcela: ${despesa.numParcela?:'(única)'} 
</g:each>

Mais detalhes em ${despesas.first().urlDetalhes}.
