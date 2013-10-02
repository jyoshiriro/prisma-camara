package hackathon2013

class OrientacaoBancada {
	
	String bancada
	String orientacao
	
	Votacao votacao
	
	static mapping = {
		votacao(cascade:'all')
	}
}
