<%@ page import="hackathon2013.Deputado" %>



<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'condicao', 'error')} ">
	<label for="condicao">
		<g:message code="deputado.condicao.label" default="Condicao" />
		
	</label>
	<g:textField name="condicao" value="${deputadoInstance?.condicao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="deputado.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${deputadoInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'fone', 'error')} ">
	<label for="fone">
		<g:message code="deputado.fone.label" default="Fone" />
		
	</label>
	<g:textField name="fone" value="${deputadoInstance?.fone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'ideCadastro', 'error')} required">
	<label for="ideCadastro">
		<g:message code="deputado.ideCadastro.label" default="Ide Cadastro" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="ideCadastro" type="number" value="${deputadoInstance.ideCadastro}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'matricula', 'error')} required">
	<label for="matricula">
		<g:message code="deputado.matricula.label" default="Matricula" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="matricula" type="number" value="${deputadoInstance.matricula}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="deputado.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${deputadoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'nomeParlamentar', 'error')} ">
	<label for="nomeParlamentar">
		<g:message code="deputado.nomeParlamentar.label" default="Nome Parlamentar" />
		
	</label>
	<g:textField name="nomeParlamentar" value="${deputadoInstance?.nomeParlamentar}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'partido', 'error')} ">
	<label for="partido">
		<g:message code="deputado.partido.label" default="Partido" />
		
	</label>
	<g:textField name="partido" value="${deputadoInstance?.partido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'sexo', 'error')} ">
	<label for="sexo">
		<g:message code="deputado.sexo.label" default="Sexo" />
		
	</label>
	<g:textField name="sexo" value="${deputadoInstance?.sexo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: deputadoInstance, field: 'uf', 'error')} ">
	<label for="uf">
		<g:message code="deputado.uf.label" default="Uf" />
		
	</label>
	<g:textField name="uf" value="${deputadoInstance?.uf}"/>
</div>

