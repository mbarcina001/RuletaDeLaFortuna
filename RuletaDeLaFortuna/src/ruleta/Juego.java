package ruleta;

import java.util.Random;
import java.util.Scanner;

public class Juego {

	public Juego() {
		// TODO Auto-generated constructor stub
	}
	public  void jugar() {
		Ruleta r = Ruleta.getRuleta();
		Random jAleatorio=new Random();
		Jugador jugActual = ListaJugadores.getListaJugadores().obtenerJugador(jAleatorio.nextInt(ListaJugadores.getListaJugadores().obtenerNumJugadores()-1));
		Panel panel=ListaPaneles.getListaPaneles().elegirPanelAleatorio();
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
				Character letra=this.pedirLetra();
				panel.calcularPuntuacion(dinero, letra);
				
				
			}
		}
	}
	private Character pedirLetra(){
		Scanner sc=new Scanner();
		Character letra=sc.next();
		return letra;
	}

}

