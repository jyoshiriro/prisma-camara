package hackathon2013

class Voto {
	Deputado deputado
	String voto 
	
	Votacao votacao
	
	static mapping = {
		votacao(cascade:'all')
	}
}
