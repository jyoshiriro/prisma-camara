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
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>:: Ajuda</title>
	</head>
	<body>
		<div class="container">
			<h2>Ajuda - "Olho na Câmara"</h2>
			<g:render template="oque-fazemos"/>
			<g:render template="imagens-exemplos"/>
			<h3 style="cursor: pointer;" onclick="$('#divPassoAPasso').toggle()">
				<u>Passo-a-passo de uso</u>
			</h3>
			<p>
			<div id="divPassoAPasso" style="display: none;">
				<a href="../docs/passo-a-passo-olho-na-camara.pdf" target="_blank">Versão para Download (PDF)</a> <br><br>
				<iframe src="http://www.slideshare.net/slideshow/embed_code/27849979"
					frameborder="0" marginwidth="0" marginheight="0" scrolling="no"
					style="border: 1px solid #CCC; border-width: 1px 1px 0; margin-bottom: 5px; width:35em; height:45em;"
					allowfullscreen> </iframe>
			</div>
			</p>
			<h3 style="cursor: pointer;" onclick="$('#divManualTecnico').toggle()">
				<u>Mini Guia Técnico</u>
			</h3>
			<p>
			<div id="divManualTecnico" style="display: none;">
				<a href="../docs/mini-guia-tecnico-olho-na-camara.pdf" target="_blank">Versão para Download (PDF)</a> <br><br>
				<iframe src="http://www.slideshare.net/slideshow/embed_code/28019876"
					frameborder="0" marginwidth="0" marginheight="0" scrolling="no"
					style="border: 1px solid #CCC; border-width: 1px 1px 0; margin-bottom: 5px; width:50em; height:35em;"
					allowfullscreen> </iframe>
			</div>
			
			<h3 style="cursor: pointer;" onclick="$('#divErrosDadosAbertos').toggle()">
				<u>Há algo errado...</u>
			</h3>
			<p>
			<div id="divErrosDadosAbertos" style="display: none;">
				
				<h4 style="color:maroon;">Só chegam gastos com Combustíveis na minha rede social, mas no site da Câmara há muito mais</h4>

				<p>Isso é falha nos 
				<a href="http://www2.camara.leg.br/transparencia/cota-para-exercicio-da-atividade-parlamentar/dados-abertos-cota-parlamentar"
				target="_blank">Dados Abertos das Cotas Parlamentares</a> 
				que só nos disponibilizam esse tipo de informação. Raramente outro tipo de gasto é disponibilizado lá.
				<br><br>
				</p>
				
				<h4 style="color:maroon;">Nunca chega a Frequência de 2ª nem 6ª feira dos Deputados que acompanho</h4>

				<p>Isso é falha nos 
				<a href="http://www2.camara.leg.br/transparencia/dados-abertos/dados-abertos-legislativo/webservices/sessoesreunioes-2/listarpresencasparlamentar"
				target="_blank">Dados Abertos de Listas de Presença de Parlamentar</a> 
				que simplesmente não contém NENHUM registro das frequências de 2ª e 6ª feira.</p>
				</p>
				
				<p><br>Já entramos em contato com a TI da Câmara durante o Hackathon mas ainda não tivemos resposta.</p>
			</div>
			
			</p>
	</body>
</html>