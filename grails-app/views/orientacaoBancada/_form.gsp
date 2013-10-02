<%@ page import="hackathon2013.OrientacaoBancada" %>



<div class="fieldcontain ${hasErrors(bean: orientacaoBancadaInstance, field: 'bancada', 'error')} ">
	<label for="bancada">
		<g:message code="orientacaoBancada.bancada.label" default="Bancada" />
		
	</label>
	<g:textField name="bancada" value="${orientacaoBancadaInstance?.bancada}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: orientacaoBancadaInstance, field: 'orientacao', 'error')} ">
	<label for="orientacao">
		<g:message code="orientacaoBancada.orientacao.label" default="Orientacao" />
		
	</label>
	<g:textField name="orientacao" value="${orientacaoBancadaInstance?.orientacao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: orientacaoBancadaInstance, field: 'votacao', 'error')} required">
	<label for="votacao">
		<g:message code="orientacaoBancada.votacao.label" default="Votacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="votacao" name="votacao.id" from="${hackathon2013.Votacao.list()}" optionKey="id" required="" value="${orientacaoBancadaInstance?.votacao?.id}" class="many-to-one"/>
</div>

