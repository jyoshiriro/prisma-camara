<%@ page import="hackathon2013.Comissao" %>



<div class="fieldcontain ${hasErrors(bean: comissaoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="comissao.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${comissaoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: comissaoInstance, field: 'sigla', 'error')} ">
	<label for="sigla">
		<g:message code="comissao.sigla.label" default="Sigla" />
		
	</label>
	<g:textField name="sigla" value="${comissaoInstance?.sigla}"/>
</div>

