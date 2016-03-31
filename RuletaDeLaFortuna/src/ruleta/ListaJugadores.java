package ruleta;

import java.util.ArrayList;
import java.util.Iterator;
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
	public int obtenerNumJugadores(){
		return this.listajugadores.size();
	}
	public Jugador obtenerJugador(int pPos){
		return this.listajugadores.get(pPos);
	}
	public void actualizarPuntuaciones(Jugador pJugador){
		Iterator<Jugador> it=this.listajugadores.iterator();
		Jugador jugador;
		while(it.hasNext()){
			jugador=it.next();
			if(!jugador.equals(pJugador)){
				jugador.setPuntuacion(0);
				
			}
			jugador.setPuntuacionTotal(jugador.getPuntuacionTotal()+jugador.getPuntuacion());
		}
		
	}

}
