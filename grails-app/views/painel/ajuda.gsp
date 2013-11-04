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
					allowfullscreen> </iframe></div>
			</div>
	</body>
</html>