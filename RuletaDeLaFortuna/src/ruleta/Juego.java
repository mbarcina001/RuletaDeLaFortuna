package ruleta;

import interfaz.InterfazRuleta;
import interfaz.Principal;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;

public class Juego extends Observable{

	private static Juego mJuego = new Juego();
	private Panel panelactual;
	private Ruleta r;
	private Jugador jugActual;
	
	private Juego() {  }
	public static Juego getJuego(){
		return mJuego;
	}
	public Panel getPanelActual(){
		return panelactual;
	}
	public  void jugar() {
		ListaJugadores.getListaJugadores().inicializarJugadores();
		r = Ruleta.getRuleta();
		Random jAleatorio=new Random();
		int contadorPaneles=0;
		InterfazRuleta.main(null);
		
		jugActual = ListaJugadores.getListaJugadores().obtenerJugador(jAleatorio.nextInt(ListaJugadores.getListaJugadores().obtenerNumJugadores()-1));
		panelactual=ListaPaneles.getListaPaneles().elegirPanelAleatorio();
		ListaCasillas.getListaCasillas().iniciarLista(panelactual.getLetras());
		setChanged();
		notifyObservers();
	}
	public void tirarRuleta(){
		String rdo = r.girarRuleta();
		if(rdo.equalsIgnoreCase("Pierde Turno")){
			if(jugActual.getComodines()>0){
				if(this.pedirComodin().equalsIgnoreCase("Y")){
					jugActual.setComodines(jugActual.getComodines()-1);
				}
				else{
					jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
					
				}
			}
			else{
				jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
			}
			
		}else if(rdo.equalsIgnoreCase("Comodin")){
			jugActual.setComodines(jugActual.getComodines()+1);
		}else if(rdo.equalsIgnoreCase("Quiebra")){
			jugActual.setPuntuacion(0);
			if(jugActual.getComodines()>0){
				if(this.pedirComodin().equalsIgnoreCase("Y")){
					jugActual.setComodines(jugActual.getComodines()-1);
				}
				else{
					jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
					
				}
			}
			else{
				jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
			}
		}else{
			int dinero = Integer.valueOf(rdo);
			Character letra=this.pedirLetra();
			if(panelactual.comprobarLetra(letra)==0){						
				if(jugActual.getComodines()>0){
					if(this.pedirComodin().equalsIgnoreCase("Y")){
						jugActual.setComodines(jugActual.getComodines()-1);
					}
					else{
						jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
						
					}
				}
				else{
					jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
				}
			}
			else{
			ListaCasillas.getListaCasillas().destaparLetra(letra);
			int	puntos=panelactual.calcularPuntuacion(dinero, letra);
			jugActual.setPuntuacion(jugActual.getPuntuacion()+puntos);
			}
		}
		setChanged();
		notifyObservers();
	}
	public void comprarVocal(){
		
	}
	public void resolverPanel(){
		if(panelactual.comprobarSolucion(this.pedirSolucion())){
			ListaJugadores.getListaJugadores().actualizarPuntuaciones(jugActual);						
		}
		else{
			if(jugActual.getComodines()>0){
				if(this.pedirComodin().equalsIgnoreCase("Y")){
					jugActual.setComodines(jugActual.getComodines()-1);
				}
				else{
					jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
					
				}
			}
			else{
				jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
			}
		}
	}
	private Character pedirLetra(){
		//este metodo devuelve la letra que elige el jugador  cuando se le pregunta por que elija una letra
		Character letra=JOptionPane.showInputDialog(null, "Elije una letra", "Letra", JOptionPane.QUESTION_MESSAGE).toUpperCase().toCharArray()[0];
		return letra;
	}
	private String pedirRespuesta(){
		//este metodo devuelve la respuesta a si quiere resolver el panel o seguir jugando
		String respuesta=JOptionPane.showInputDialog(null, "¿Quieres resolver el panel? (Y/N)", "Resolver", JOptionPane.QUESTION_MESSAGE);
		return respuesta;
	}
	private ArrayList<Character> pedirSolucion(){
		//este método devuelve la solución al panel que escribe el jugador
		String respuesta=JOptionPane.showInputDialog(null, "Escribe la solución al panel", "Solución", JOptionPane.QUESTION_MESSAGE);
		int contador=0;
		ArrayList<Character> solucion=new ArrayList<Character>();
		while(contador<respuesta.length()){
			solucion.add(respuesta.charAt(contador));
			contador++;
		}		
		return solucion;
	}
	private String pedirComodin(){
		//este metodo devuelve la respuesta a si quiere resolver el panel o seguir jugando
		String respuesta=JOptionPane.showInputDialog(null, "¿Quieres usar un comodín? (Y/N)", "Comodín", JOptionPane.QUESTION_MESSAGE);
		return respuesta;
	}

}

