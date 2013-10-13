package hackathon2013

class UsuarioTests {

	void ntestNaoRepetirPartido() {
		Usuario u = Usuario.get(2L)
		u.addToPartidos(Partido.get(2L))
		u.addToPartidos(Partido.get(3L))
		try {
			u.save()
			assert u.errors.errorCount==0
		} catch (Exception e) {
			e.printStackTrace()
			assert false
		}
	}
	
}
