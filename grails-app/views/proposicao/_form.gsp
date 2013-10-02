<%@ page import="hackathon2013.Proposicao" %>



<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'ano', 'error')} ">
	<label for="ano">
		<g:message code="proposicao.ano.label" default="Ano" />
		
	</label>
	<g:textField name="ano" value="${proposicaoInstance?.ano}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'autor', 'error')} required">
	<label for="autor">
		<g:message code="proposicao.autor.label" default="Autor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="autor" name="autor.id" from="${hackathon2013.Deputado.list()}" optionKey="id" required="" value="${proposicaoInstance?.autor?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'dataApresentacao', 'error')} required">
	<label for="dataApresentacao">
		<g:message code="proposicao.dataApresentacao.label" default="Data Apresentacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataApresentacao" precision="day"  value="${proposicaoInstance?.dataApresentacao}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="proposicao.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${proposicaoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="proposicao.numero.label" default="Numero" />
		
	</label>
	<g:textField name="numero" value="${proposicaoInstance?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'sigla', 'error')} ">
	<label for="sigla">
		<g:message code="proposicao.sigla.label" default="Sigla" />
		
	</label>
	<g:textField name="sigla" value="${proposicaoInstance?.sigla}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'situacao', 'error')} ">
	<label for="situacao">
		<g:message code="proposicao.situacao.label" default="Situacao" />
		
	</label>
	<g:textField name="situacao" value="${proposicaoInstance?.situacao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'txtApreciacao', 'error')} ">
	<label for="txtApreciacao">
		<g:message code="proposicao.txtApreciacao.label" default="Txt Apreciacao" />
		
	</label>
	<g:textField name="txtApreciacao" value="${proposicaoInstance?.txtApreciacao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'txtDespacho', 'error')} ">
	<label for="txtDespacho">
		<g:message code="proposicao.txtDespacho.label" default="Txt Despacho" />
		
	</label>
	<g:textField name="txtDespacho" value="${proposicaoInstance?.txtDespacho}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'txtEmenta', 'error')} ">
	<label for="txtEmenta">
		<g:message code="proposicao.txtEmenta.label" default="Txt Ementa" />
		
	</label>
	<g:textField name="txtEmenta" value="${proposicaoInstance?.txtEmenta}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'txtExplicacaoEmenta', 'error')} ">
	<label for="txtExplicacaoEmenta">
		<g:message code="proposicao.txtExplicacaoEmenta.label" default="Txt Explicacao Ementa" />
		
	</label>
	<g:textField name="txtExplicacaoEmenta" value="${proposicaoInstance?.txtExplicacaoEmenta}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proposicaoInstance, field: 'ultimoDespacho', 'error')} required">
	<label for="ultimoDespacho">
		<g:message code="proposicao.ultimoDespacho.label" default="Ultimo Despacho" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="ultimoDespacho" precision="day"  value="${proposicaoInstance?.ultimoDespacho}"  />
</div>

