package ruleta;

import java.util.ArrayList;
import java.util.Random;

public class ListaJugadores {
	
	private static ListaJugadores mListaJugadores = new ListaJugadores();
	private ArrayList<Jugador> listajugadores;
	private int jugActual;
	
	private ListaJugadores(){
		this.listajugadores = new ArrayList<Jugador>();
		Random rg = new Random();
		this.jugActual = rg.nextInt(listajugadores.size());
	}
	
	public static ListaJugadores getListaJugadores(){
		return mListaJugadores;
	}
	
	public Jugador obtenerSiguienteJugador(){
		jugActual++;
		if(jugActual==this.listajugadores.size()){
			jugActual=0;
		}
		return this.listajugadores.get(jugActual);
	}

}
