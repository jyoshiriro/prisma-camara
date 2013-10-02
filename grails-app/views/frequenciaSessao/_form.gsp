<%@ page import="hackathon2013.FrequenciaSessao" %>



<div class="fieldcontain ${hasErrors(bean: frequenciaSessaoInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="frequenciaSessao.descricao.label" default="Descricao" />
		
	</label>
	<g:textField name="descricao" value="${frequenciaSessaoInstance?.descricao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: frequenciaSessaoInstance, field: 'frequencia', 'error')} ">
	<label for="frequencia">
		<g:message code="frequenciaSessao.frequencia.label" default="Frequencia" />
		
	</label>
	<g:textField name="frequencia" value="${frequenciaSessaoInstance?.frequencia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: frequenciaSessaoInstance, field: 'frequenciaDia', 'error')} required">
	<label for="frequenciaDia">
		<g:message code="frequenciaSessao.frequenciaDia.label" default="Frequencia Dia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="frequenciaDia" name="frequenciaDia.id" from="${hackathon2013.FrequenciaDia.list()}" optionKey="id" required="" value="${frequenciaSessaoInstance?.frequenciaDia?.id}" class="many-to-one"/>
</div>

