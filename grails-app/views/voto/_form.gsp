<%@ page import="hackathon2013.Voto" %>



<div class="fieldcontain ${hasErrors(bean: votoInstance, field: 'deputado', 'error')} required">
	<label for="deputado">
		<g:message code="voto.deputado.label" default="Deputado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="deputado" name="deputado.id" from="${hackathon2013.Deputado.list()}" optionKey="id" required="" value="${votoInstance?.deputado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: votoInstance, field: 'votacao', 'error')} required">
	<label for="votacao">
		<g:message code="voto.votacao.label" default="Votacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="votacao" name="votacao.id" from="${hackathon2013.Votacao.list()}" optionKey="id" required="" value="${votoInstance?.votacao?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: votoInstance, field: 'voto', 'error')} ">
	<label for="voto">
		<g:message code="voto.voto.label" default="Voto" />
		
	</label>
	<g:textField name="voto" value="${votoInstance?.voto}"/>
</div>

