
<h3 style="cursor: pointer;" onclick="$('#divOqueFazemos').toggle()"><u>O que fazemos por aqui?</u></h3>
<div id="divOqueFazemos" style="display: none;">
	<p>
	Este sistema nasceu no <b>Hackathon 2013 da Câmara dos Deputados</b>, onde foram apresentados sistemas que deveriam, segundo o 
	<a href="http://www2.camara.leg.br/responsabilidade-social/edulegislativa/hackathon/copy_of_HACKATHONRegulamento.pdf"
	target="_blank">regulamento</a>:
	</p>
	
	<p>
	<i>"1.6. (...)além de colaborar na transparência das informações sobre a atividade parlamentar, mobilizar a sociedade na busca de melhorias do processo legislativo e do trabalho da Câmara dos Deputados"</i>
	</p>

	<p>
	O primeiro critério na avaliação do sistema foi o <b>Interesse Público</b>, onde seria 
	<i>"3.2.1. (...)avaliado se o projeto contribuirá para a melhor compreensão do processo legislativo e da atuação parlamentar pela sociedade"</i>
	</p>
	
	<p>
	Foi então que as seguintes estatísticas nos chamaram atenção:
	</p>
	<p>
	&bull; Segundo <a href="http://tinyurl.com/orcfqmq">pesquisa recente do IBOPE</a>, 46 dos 53,5 milhões de usuários de internet do Brasil usam redes sociais. Quase 9 em cada 10.
	</p>
	<p>
	&bull; Segundo <a href="http://www.clientesa.com.br/estatisticas/51758/a-rede-social-mais-acessada-e/ler.aspx">pesquisa recente da Cliente SA</a>, Facebook e Twitter, juntos, correspondem a quase 72% do acessos de redes sociais no Brasil. Ainda: O tempo médio de visitas ao Facebook foi de 27 minutos e 12 segundos em agosto de 2013.
	</p>
	<p>
	&bull; Sistemas do mesmo gênero, mesmo muito interessantes, possuem baixíssimo número de instalações, tais como <a href="https://play.google.com/store/apps/details?id=com.gamfig.monitorabrasil">Monitora, Brasil!</a> (cerca de 5 mil instalações), 
	<a href="https://play.google.com/store/apps/details?id=marcocivil.interagentes.danielbryan">Marco Civil</a> (cerca de 5 mil instalações) e 
	<a href="https://play.google.com/store/apps/details?id=net.inovare.camaranoticias">Câmara Notícias</a> (cerca de 1000 instalações) 
	</p>
	<p>
	Chegamos à conclusão de que por mais "belo", "divertido", e "avançado" que fosse nossa solução, não há como competir com a redes sociais. Acreditamos que um sistema "clássico" (site ou app para celular/tablet) teria pouco acesso e menor ainda taxa de continuidade de uso, por melhor que fosse. 
	</p>
	<p>
	Por fim chegamos ao produto que batizamos de <b>"Olho na Câmara"</b>, que usa o alcançe e a popularidade das Redes Sociais para atingir seus objetivos que são:<br>
	<b>1.</b> Contribuir para a melhor compreensão do processo legislativo e da atuação parlamentar pela sociedade<br>
	<b>2.</b> Tornar os dados sobre gastos e atividades de parlamentares e votações de proposições mais transparentes e com uma rápida e imensa divulgação que só as redes sociais podem oferecer.
	</p>
	<p>
	Além das estatísticas favoráveis, há ainda o fato de as Redes Sociais possuirem interfaces para micros comuns, Tablets e SmartPhones de todos os fabricantes. E sempre com funções para acessibilidade. Assim, nosso alcançe depende do alcançe da tecnologia das Redes Sociais. Ou seja, é imenso. 
	</p>
	<p>A ideia é simples:</p>
	<p>
	O usuário se conecta com sua conta da Rede Social a nosso sitema e facilmente escolhe Deputados(as) e/ou Partidos e/ou Proposições que quer acompanhar. Mensagens relativas a eles chegam diariamente em sua rede social, automaticamente.  
	</p>
	<p>
	A seguir, os detalhes dos acompanhamentos <b>*</b>.
	</p>
	<nav class="navbar navbar-default">
		<div class="nav navbar-nav ">
			<p class="navbar-text">
			<span class="glyphicon glyphicon-user"></span>
			<g:link controller="deputado">Deputados</g:link> 
			</p>
		</div>
		
		<div class="alert alert-warning painel">
		    <p>
		    Pesquise e adicione <b>Deputados</b> à sua conta.
			</p>
			<p>
			De cada Deputado que escolher acompanhar, receberá sem sua rede social, diariamente as suas:
			</p>
			<ol>
				<li><span>Frequência diária</span></li>
				<li><span>Gastos que realizou</span></li>
				<li><span>Discursos que proferiu</span></li>
			</ol>
		</div>
	</nav>
	
	<nav class="navbar navbar-default">
		<div class="nav navbar-nav">
			<p class="navbar-text">
			<span class="glyphicon glyphicon-link"></span>
			<g:link controller="partido">Partidos</g:link>
			</p>
		</div>
		
		<div class="alert alert-success painel">
		    <p>
		    Pesquise e adicione <b>Partidos</b> à sua conta.
			</p>
			<p>
			De cada Partido que escolher acompanhar, receberá sem sua rede social, diariamente informações de todos os seus Deputados:
			</p>
			<ol>
				<li><span>Frequência diária</span></li>
				<li><span>Gastos que realizou</span></li>
				<li><span>Discursos que proferiu</span></li>
			</ol>
			</p>
		</div>
	</nav>
	
	
	<nav class="navbar navbar-default">
		<div class="nav navbar-nav">
			<p class="navbar-text">
			<span class="glyphicon glyphicon-transfer"></span>
			<g:link controller="proposicao">Proposições</g:link>
			</p>
		</div>
		
		<div class="alert alert-info painel">
		    <p>
		    Pesquise e adicione <b>Proposições</b> à sua conta.
			</p>
			<p>
			Sempre que uma Proposição que você acompanha sofrer uma votação, você redeberá os seguintes detalhes em sua rede social 
			</p>
			<ol>
				<li><span>Objeto da Votação</span></li>
				<li><span>Resumo ${asterisco}</span></li>
				<li><span>Quantitativo de cada voto</span></li>
				<li><span>Nomes dos Deputados em cada um dos votos ${asterisco}</span></li>
			</ol>
			</p>
		</div>				
	</nav>
	<div style="clear: both;"></div>			
	<div>
	<i></i><b>*</b> Os detalhes dos acompanhamentos são menores nos "post" para Twitter, dada a limitação da Rede. Porém um link com detalhes no próprio site da Câmara é enviado no "post".</i>  
	</div>
	<p>
	As informações "postadas" nas redes sociais dos usuários são lidas nos 
	<a href="http://www2.camara.leg.br/transparencia/dados-abertos/" target="_blank">Dados Abertos da Câmara dos Deputados</a> diariamente.
	Após lidas e tratadas são envidas às redes sociais de todos os usuários cadastrados.
	</p>
	<p>
	Assim, acreditamos que informações, principalmente polêmicas e "estranhas" (como gastos altíssimos com gasolina, por exemplo), serão divulgadas de maneira "viral" pelas Redes Sociais, pois isso lhe é característico.
	</p>
			
</div>