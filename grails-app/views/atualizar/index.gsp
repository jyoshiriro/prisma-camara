<%--
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 --%>
<%@page import="br.org.prismaCamara.modelo.Parametro"%>
<style>
	li{
	margin-bottom: 1em;
	}
</style>

${flash.message}
${flash.error}

<h2 style="color: navy">Qual entidade deseja Atualizar Completamente agora?</h2>
<ul>
	<li><g:link action="geral" id="frequenciaDia">Frequências de Deputados</g:link>
	(Última foi em ${Parametro.findBySigla('ultimo_dia_frequencia')?.valor?:'-'})
	</li>
	
	<li><g:link action="geral" id="discurso">Discursos de Deputados</g:link></li>
		
	<li><g:link action="geral" id="votacao">Votações de Proposições</g:link></li>
	
	<li><g:link action="geral" id="despesa">Gastos dos Deputados (Ano Atual)</g:link> - Ajuste manualmente no "AtualizarDespesaService" o caminho do XML</li>
</ul>


<h2>Qual entidade deseja Atualizar agora?</h2>
<ul>
	<li><g:link action="executar" id="partido">Partidos</g:link> 
	</li>
	
	<li><g:link action="executar" id="deputado">Deputados</g:link> 
	(Ativos: ${deputadosA}. Inativos: ${deputadosI}. Total: ${deputadosA+deputadosI})
	</li>
	
	<li><g:link action="executar" id="frequenciaDia">Frequências de Deputados</g:link>
	(Última foi em ${Parametro.findBySigla('ultimo_dia_frequencia')?.valor?:'-'})
	</li>
	
	<li><g:link action="executar" id="discurso">Discursos de Deputados</g:link></li>
	
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
	
	<li><g:link action="limpar" id="frequenciaDia">Frequencias dos Deputados</g:link></li>
	
	<li><g:link action="limpar" id="discurso">Discursos de Deputados</g:link></li>
	
	<li><g:link action="limpar" id="votacao">Votações de Proposições</g:link></li>
</ul>
