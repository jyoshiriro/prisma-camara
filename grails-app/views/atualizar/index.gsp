<%@page import="hackathon2013.Parametro"%>
<style>
	li{
	margin-bottom: 1em;
	}
</style>

<h2>Qual entidade deseja Atualizar agora?</h2>
<ul>
	<li><g:link action="executar" id="deputado">Deputados</g:link> 
	(Ativos: ${deputadosA}. Inativos: ${deputadosI}. Total: ${deputadosA+deputadosI})
	</li>
	
	<li><g:link action="executar" id="frequenciaDia">Frequências de Deputados</g:link>
	(Última foi em ${Parametro.findBySigla('ultimo_dia_frequencia')?.valor?:'-'})
	</li>
	
	<li><g:link action="executar" id="discurso">Discursos de Deputados</g:link>
	(Última foi em ${Parametro.findBySigla('ultimo_dia_discurso')?.valor?:'-'})
	</li>
	
	<li><g:link action="executar" id="tipoProposicao">Tipos de Proposições</g:link>
	(Total: ${tiposProp})
	</li>
	
	<li><g:link action="executar" id="proposicao">Proposições</g:link>
	(Total, somente não arquivadas: ${proposicoes})
	</li>
	
	<li><g:link action="executar" id="votacao">Votações de Proposições</g:link></li>
	
	<li><g:link action="executar" id="despesa">Gastos dos Deputados (Ano Atual)</g:link> - Ajuste manualmente no "AtualizarDespesaService" o caminho do XML</li>
</ul>

<br>
<h2 style="color:maroon;">Qual entidade deseja Limpar agora?</h2>
<ul>
	<li><g:link action="limpar" id="despesa">Gastos dos Deputados</g:link></li>
</ul>

${flash.message}
${flash.error}