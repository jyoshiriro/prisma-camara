
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
        Câmara dos Deputados
      </div>
    </div>
    <div class="item">
      <img src="${resource(dir:'images',file:'foto-home-02.jpg')}" alt="Transparência com os gastos de Cota Parlamentar">
      <div class="carousel-caption">
        Transparência com os gastos de Cota Parlamentar
      </div>
    </div>
    <div class="item">
      <img src="${resource(dir:'images',file:'foto-home-03.jpg')}" alt="Acompanhamento de Proposições">
      <div class="carousel-caption">
        Acompanhamento de Proposições
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