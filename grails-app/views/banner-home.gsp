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

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img src="${resource(dir:'images',file:'foto-home-01.jpg')}" alt="Câmara dos Deputados">
      <div class="carousel-caption">
        Acompanhe atividades de Deputados e andamento de <br>Proposições da Câmara dos Deputados
      </div>
    </div>
    <div class="item">
      <img src="${resource(dir:'images',file:'foto-home-02.jpg')}" alt="Transparência com os gastos de Cota Parlamentar">
      <div class="carousel-caption">
        Acompanhe uso de Cota Parlamentar, Frequências e <br>Discursos dos Deputados
      </div>
    </div>
    <div class="item">
      <img src="${resource(dir:'images',file:'foto-home-03.jpg')}" alt="Acompanhamento de Proposições">
      <div class="carousel-caption">
        Acompanhe a realização das votações<br> das Proposições (PEC, PL, PLP, PRO, etc.)
      </div>
    </div>
    
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
  </a>
</div>