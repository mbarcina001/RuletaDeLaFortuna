package ruleta;

import interfaz.InterfazRuleta;
import interfaz.Principal;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Juego extends Observable{

	private static Juego mJuego = new Juego();
	private Panel panelactual;
	
	private Juego() {
		// TODO Auto-generated constructor stub
	}
	public static Juego getJuego(){
		return mJuego;
	}
	public Panel getPanelActual(){
		return panelactual;
	}
	public  void jugar() {
		ListaJugadores.getListaJugadores().inicializarJugadores();
		Ruleta r = Ruleta.getRuleta();
		Random jAleatorio=new Random();
		int contadorPaneles=0;
		InterfazRuleta.main(null);
		
		int dinero = 0;
		while(contadorPaneles<=5){
			Jugador jugActual = ListaJugadores.getListaJugadores().obtenerJugador(jAleatorio.nextInt(ListaJugadores.getListaJugadores().obtenerNumJugadores()-1));
			panelactual=ListaPaneles.getListaPaneles().elegirPanelAleatorio();
			System.out.println(panelactual.getLetras());
			setChanged();
			notifyObservers();
			boolean siguientePanel=false;
			while(!siguientePanel){
				if(this.pedirRespuesta().equalsIgnoreCase("n")){
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
						dinero = Integer.valueOf(rdo);
						Character letra=this.pedirLetra();
						while(letra.equals('A')||letra.equals('E')||letra.equals('I')||letra.equals('O')||letra.equals('U')){
							letra=this.pedirLetra();
						}
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
						int	puntos=panelactual.calcularPuntuacion(dinero, letra);
						jugActual.setPuntuacion(jugActual.getPuntuacion()+puntos);
						}
					}
				}
				else{
					if(panelactual.comprobarSolucion(this.pedirSolucion())){
						siguientePanel=true;
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
			}
			contadorPaneles++;
		}
	}
	private Character pedirLetra(){
		//este metodo devuelve la letra que elige el jugador  cuando se le pregunta por que elija una letra
		Character letra=JOptionPane.showInputDialog(null, "Elije una letra", "Letra", JOptionPane.QUESTION_MESSAGE).toCharArray()[0];
		return letra;
	}
	private String pedirRespuesta(){
		//este metodo devuelve la respuesta a si quiere resolver el panel o seguir jugando
		String respuesta=JOptionPane.showInputDialog(null, "�Quieres resolver el panel? (Y/N)", "Resolver", JOptionPane.QUESTION_MESSAGE);
		return respuesta;
	}
	private ArrayList<Character> pedirSolucion(){
		//este m�todo devuelve la soluci�n al panel que escribe el jugador
		String respuesta=JOptionPane.showInputDialog(null, "Escribe la soluci�n al panel", "Soluci�n", JOptionPane.QUESTION_MESSAGE);
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
		String respuesta=JOptionPane.showInputDialog(null, "�Quieres usar un comod�n? (Y/N)", "Comod�n", JOptionPane.QUESTION_MESSAGE);
		return respuesta;
	}

}

