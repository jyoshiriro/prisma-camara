<g:each in="${despesas}" var="despesa" status="st">
Novo gasto d<g:deputadoPrefix dep="${dep}" minusculo="true"/>: ${despesa.txtDescricao} <g:formatDate date="${despesa.dataEmissao}" format="d/M/yy"/>. R${'$'}${despesa.valor}
${despesas.first().urlDetalhesCurta}
<hr></g:each> Via http://goo.gl/fWAHmG