package ruleta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

public class ListaJugadores {
	
	private static ListaJugadores mListaJugadores = new ListaJugadores();
	private ArrayList<Jugador> listajugadores;
	private int jugActual;
	
	private ListaJugadores(){
		this.listajugadores = new ArrayList<Jugador>();
	}
	
	public static ListaJugadores getListaJugadores(){
		return mListaJugadores;
	}
	public Iterator<Jugador> getIterador(){
		return this.listajugadores.iterator(); 
	}
	public Jugador obtenerPrimerJuador(){
		Random rg = new Random();
		int r = rg.nextInt(listajugadores.size());
		return this.listajugadores.get(r);
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
			jugador.setPuntuacionTotal(jugador.getPuntuacionTotal()+jugador.getPuntuacion());
			if(!jugador.equals(pJugador)){
				jugador.setPuntuacion(0);
				
			}
		}
		
	}
	
	private void anadirJugador(Jugador j){
		this.listajugadores.add(j);
	}
	
	public void inicializarJugadores(){
		boolean correcto = false;
		int jugadores = 0;
		do{
			jugadores = Integer.valueOf(JOptionPane.showInputDialog(null, "¿Cuántos jugadores sois? (Min. 2/Max. 6)", "Elegir número de jugadores", JOptionPane.QUESTION_MESSAGE));
			if(jugadores<2){
				JOptionPane.showMessageDialog(null, "El número mínimo de jugadores es 2", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(jugadores>6){
				JOptionPane.showMessageDialog(null, "El número máximo de jugadores es 6", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				correcto = true;
			}
		}while(!correcto);
		
		for(int i=1; i<=jugadores; i++){
			correcto = false;
			do{
				String nombrejugador = JOptionPane.showInputDialog(null, "¿Cuál es el nombre del jugador "+i+"?", "Elegir nombre del jugador "+i, JOptionPane.QUESTION_MESSAGE);
				if(nombrejugador!=null){
					correcto = true;
					Jugador j = new Jugador(nombrejugador);
					anadirJugador(j);
				}
			}while(!correcto);
		}
		
	}

}
