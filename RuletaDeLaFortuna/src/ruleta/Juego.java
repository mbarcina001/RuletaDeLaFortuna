package ruleta;

public class Juego {

	public Juego() {
		// TODO Auto-generated constructor stub
	}
	public  void jugar() {
		Ruleta r = Ruleta.getRuleta();
		Jugador jugActual = null;
		boolean seguir = false;
		int dinero = 0;
		while(!seguir){
			String rdo = r.girarRuleta();
			if(rdo.equalsIgnoreCase("Pierde Turno")){
				jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
			}else if(rdo.equalsIgnoreCase("Comodin")){
				jugActual.setComodines(jugActual.getComodines()+1);
			}else if(rdo.equalsIgnoreCase("Quiebra")){
				jugActual.setPuntuacion(0);
				jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador(); 
			}else{
				dinero = Integer.valueOf(rdo);
				//SEGUIR
				
			}
		}
	}

}

