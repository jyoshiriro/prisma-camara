<%@ page import="hackathon2013.Votacao" %>



<div class="fieldcontain ${hasErrors(bean: votacaoInstance, field: 'dataHoraVotacao', 'error')} required">
	<label for="dataHoraVotacao">
		<g:message code="votacao.dataHoraVotacao.label" default="Data Hora Votacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataHoraVotacao" precision="day"  value="${votacaoInstance?.dataHoraVotacao}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: votacaoInstance, field: 'objVotacao', 'error')} ">
	<label for="objVotacao">
		<g:message code="votacao.objVotacao.label" default="Obj Votacao" />
		
	</label>
	<g:textField name="objVotacao" value="${votacaoInstance?.objVotacao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: votacaoInstance, field: 'proposicao', 'error')} required">
	<label for="proposicao">
		<g:message code="votacao.proposicao.label" default="Proposicao" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="proposicao" name="proposicao.id" from="${hackathon2013.Proposicao.list()}" optionKey="id" required="" value="${votacaoInstance?.proposicao?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: votacaoInstance, field: 'resumo', 'error')} ">
	<label for="resumo">
		<g:message code="votacao.resumo.label" default="Resumo" />
		
	</label>
	<g:textField name="resumo" value="${votacaoInstance?.resumo}"/>
</div>

