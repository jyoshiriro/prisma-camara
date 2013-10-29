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