package ruleta;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Juego {

	public Juego() {
		// TODO Auto-generated constructor stub
	}
	public  void jugar() {
		Ruleta r = Ruleta.getRuleta();
		Random jAleatorio=new Random();
		int contadorPaneles=0;
		
		int dinero = 0;
		while(contadorPaneles<=5){
			Jugador jugActual = ListaJugadores.getListaJugadores().obtenerJugador(jAleatorio.nextInt(ListaJugadores.getListaJugadores().obtenerNumJugadores()-1));
			Panel panel=ListaPaneles.getListaPaneles().elegirPanelAleatorio();
			boolean siguientePanel=false;
			while(!siguientePanel){
				if(this.pedirRespuesta().equalsIgnoreCase("n")){
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
						int puntos=panel.calcularPuntuacion(dinero, letra);
						jugActual.setPuntuacion(jugActual.getPuntuacion()+puntos);
					}
				}
				else{
					if(panel.comprobarSolucion(this.pedirSolucion())){
						siguientePanel=true;
					}
					else{
						jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
					}
				}
			}
			contadorPaneles++;
		}
	}
	private Character pedirLetra(){
		//este metodo devuelve la letra que elige el jugador  cuando se le pregunta por que elija una letra
		System.out.println("ELIJA UNA LETRA:");
		Scanner sc=new Scanner(System.in);
		Character letra=sc.next().charAt(0);
		return letra;
	}
	private String pedirRespuesta(){
		//este metodo devuelve la respuesta a si quiere resolver el panel o seguir jugando
		System.out.println("¿QUIERES RESOLVER EL PANEL?(Y/N)");
		Scanner sc=new Scanner(System.in);
		String respuesta=sc.next();
		return respuesta;
	}
	private ArrayList<Character> pedirSolucion(){
		//este método devuelve la solución al panel que escribe el jugador
		System.out.println("ESCRIBA LA SOLUCIÓN AL PANEL:");
		Scanner sc=new Scanner(System.in);
		String respuesta=sc.next();
		int contador=0;
		ArrayList<Character> solucion=new ArrayList<Character>();
		while(contador<respuesta.length()){
			solucion.add(respuesta.charAt(contador));
			contador++;
		}		
		return solucion;
	}

}

