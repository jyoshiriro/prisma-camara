<style>
	li{
	margin-bottom: 1em;
	}
</style>

<h2>Qual entidade deseja atualizar agora?</h2>
<ul>
	<li><g:link action="executar" id="deputado">Deputados</g:link> 
	(Ativos: ${deputadosA}. Inativos: ${deputadosI}. Total: ${deputadosA+deputadosI})
	</li>
	
	<li><g:link action="executar" id="frequenciaDia">Frequências de Deputados</g:link>
	</li>
	
	<li><g:link action="executar" id="tipoProposicao">Tipos de Proposições</g:link>
	(Total: ${tiposProp})
	</li>
	
	<li><g:link action="executar" id="proposicao">Proposições</g:link>
	(Total, somente não arquivadas: ${proposicoes})
	</li>
	
	<li><g:link action="executar" id="votacao">Votações de Proposições</g:link></li>
	
	<li><g:link action="executar" id="despesa">Gastos dos Deputados (Ano Atual)</g:link></li>
</ul>

${flash.message}
${flash.error}