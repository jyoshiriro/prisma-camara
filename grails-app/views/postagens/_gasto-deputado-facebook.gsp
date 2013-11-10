<%--
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 --%>
Veja os recentes gastos da cota parlamentar d<g:deputadoPrefix dep="${dep}" minusculo="true"/>:
<g:each in="${despesas}" var="despesa" status="st">
${st+1}. Despesa: ${despesa.txtDescricao}
Pago a:	${despesa.txtBeneficiario} (${despesa.descDoc})
Número do documento: ${despesa.txtNumero}
Data da emissão: <g:formatDate date="${despesa.dataEmissao}" format="dd/MM/yyyy"/> 
Valor solicitado: R${'$'}${despesa.valor}
Valor glosa: R${'$'}${despesa.valorGlosa}
Parcela: ${despesa.numParcela?:'(única)'}
Mais detalhes em ${despesa.urlDetalhes}
</g:each>

Contatos: ${dep.contatos}.

Mensagem gerada pelo "Olho na Câmara" (http://goo.gl/Drr2jj)