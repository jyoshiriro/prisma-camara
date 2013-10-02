<%@ page import="hackathon2013.FrequenciaDia" %>



<div class="fieldcontain ${hasErrors(bean: frequenciaDiaInstance, field: 'deputado', 'error')} required">
	<label for="deputado">
		<g:message code="frequenciaDia.deputado.label" default="Deputado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="deputado" name="deputado.id" from="${hackathon2013.Deputado.list()}" optionKey="id" required="" value="${frequenciaDiaInstance?.deputado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: frequenciaDiaInstance, field: 'dia', 'error')} required">
	<label for="dia">
		<g:message code="frequenciaDia.dia.label" default="Dia" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dia" precision="day"  value="${frequenciaDiaInstance?.dia}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: frequenciaDiaInstance, field: 'frequenciaDia', 'error')} ">
	<label for="frequenciaDia">
		<g:message code="frequenciaDia.frequenciaDia.label" default="Frequencia Dia" />
		
	</label>
	<g:textField name="frequenciaDia" value="${frequenciaDiaInstance?.frequenciaDia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: frequenciaDiaInstance, field: 'justificativa', 'error')} ">
	<label for="justificativa">
		<g:message code="frequenciaDia.justificativa.label" default="Justificativa" />
		
	</label>
	<g:textField name="justificativa" value="${frequenciaDiaInstance?.justificativa}"/>
</div>

