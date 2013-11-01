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
<div class="alert alert-success fade in">

<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
<h4>Detalhes da ${prop.descricao}</h4>
<div>Tipo: <strong>${prop.tipoProposicao.descricao}</strong></div>
<div>Ementa: <strong>${prop.txtEmenta?.size()>144?prop.txtEmenta+"...":prop.txtEmenta}</strong></div>
<div>Explicação da Ementa: <strong>${prop.txtExplicacaoEmenta?.size()>144?prop.txtExplicacaoEmenta+"...":prop.txtExplicacaoEmenta}</strong></div>
<div>Apresentada em: <strong>${prop.dataApresentacao.format('dd/MM/yyyy')}</strong></div>
<div>Autor: <strong>${(prop.autor)?prop.autor.descricao:prop.nomeAutor+" (não é Deputado(a) atualmente)"}</strong></div>

<div style="margin-top: 1em">
<a href="${prop.urlDetalhes}" target="_blank" title="Detalhes no site da Câmara"><span class="glyphicon glyphicon-share-alt"></span> <b>Detalhes</b></a>
</div>
</div>