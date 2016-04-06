package ruleta;

import interfaz.InterfazRuleta;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Juego extends Observable{

	private static Juego mJuego = new Juego();
	private Panel panelactual;
	private Ruleta r;
	private Jugador jugActual;
	private int contadorpaneles;
	private String rdo;
	private boolean panelresuelto;
	private boolean fin;
	private ArrayList<Character> letrasElegidas=new ArrayList<Character>();
	
	private Juego() {  }
	public static Juego getJuego(){
		return mJuego;
	}
	public Panel getPanelActual(){
		return panelactual;
	}
	public Jugador getJugadorActual() {
		return jugActual;
	}
	public boolean getFin(){
		return fin;
	}
	public String getRdo() {
		return rdo;
	}
	public boolean getPanelResuelto(){
		return panelresuelto;
	}
	public void jugar() {
		ListaJugadores.getListaJugadores().inicializarJugadores();
		r = Ruleta.getRuleta();
		contadorpaneles=0;
		fin=false;
		InterfazRuleta.main(null);
		panelresuelto=false;
		cargarSiguientePanel();
		
	}
	public void cargarSiguientePanel(){
		panelresuelto=false;
		letrasElegidas=new ArrayList<Character>();
		jugActual = ListaJugadores.getListaJugadores().obtenerPrimerJugador();
		panelactual=ListaPaneles.getListaPaneles().elegirPanelAleatorio();
		ListaCasillas.getListaCasillas().resetearListaCasillas();
		ListaCasillas.getListaCasillas().iniciarLista(panelactual.getLetras());
		setChanged();
		notifyObservers();
	}
	public void tirarRuleta(){
		rdo = r.girarRuleta();
		setChanged();
		notifyObservers();
		if(rdo.equalsIgnoreCase("Pierde Turno")){
			this.reproducirSonido(false);
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
			this.reproducirSonido(false);
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
			if(letrasElegidas.contains(letra)){
				this.reproducirSonido(false);
				if(jugActual.getComodines()>0){
					if(this.pedirComodin().equalsIgnoreCase("Y")){
						jugActual.setComodines(jugActual.getComodines()-1);
					}
					else{
						jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
						
					}
				}
			}
			else{
				while(letra.equals('A')||letra.equals('E')||letra.equals('I')||letra.equals('O')||letra.equals('U')){
					JOptionPane.showMessageDialog(null, "No se puede introducir una vocal", "Error", JOptionPane.ERROR_MESSAGE);
					letra=this.pedirLetra();
				}
				letrasElegidas.add(letra);
				if(panelactual.comprobarLetra(letra)==0){
					this.reproducirSonido(false);						
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
					this.reproducirSonido(true);
					ListaCasillas.getListaCasillas().destaparLetra(letra);
					int	puntos=panelactual.calcularPuntuacion(dinero, letra);
					jugActual.setPuntuacion(jugActual.getPuntuacion()+puntos);
				}
			}
		}
		setChanged();
		notifyObservers();
	}
	public void comprarVocal(){
		if(jugActual.getPuntuacion()<50){
			JOptionPane.showMessageDialog(null, "Para comprar una vocal debes tener un mínimo de 50 puntos", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			Character letra=this.pedirLetra();
			while(!letra.equals('A')&&!letra.equals('E')&&!letra.equals('I')&&!letra.equals('O')&&!letra.equals('U')){
				JOptionPane.showMessageDialog(null, "No se ha introducido una vocal", "Error", JOptionPane.ERROR_MESSAGE);
				letra=this.pedirLetra();
			}
			letrasElegidas.add(letra);
			jugActual.setPuntuacion(jugActual.getPuntuacion()-50);
			ListaCasillas.getListaCasillas().destaparLetra(letra);
			setChanged();
			notifyObservers();
		}
	}
	
	public void resolverPanel(){
		if(panelactual.comprobarSolucion(this.pedirSolucion())){
			ListaCasillas.getListaCasillas().destaparTodo();
			panelresuelto=true;
			//SUMAR PUNTOS
			ListaJugadores.getListaJugadores().actualizarPuntuaciones(jugActual);
			contadorpaneles++;
			System.out.println(contadorpaneles);
			if(contadorpaneles>=5){
				ListaPuntuaciones.getListaPuntuaciones().actualizarPuntuaciones();
				ListaPuntuaciones.getListaPuntuaciones().guardarPuntuaciones();
				fin=true;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "La solución es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
			if(jugActual.getComodines()>0){
				if(this.pedirComodin().equalsIgnoreCase("Y")){
					jugActual.setComodines(jugActual.getComodines()-1);
				}
				else{
					jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
					
				}
			}
			else{
				System.out.println(jugActual.getNombre());
				jugActual = ListaJugadores.getListaJugadores().obtenerSiguienteJugador();
				System.out.println(jugActual.getNombre());
			}
		}
		setChanged();
		notifyObservers();
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
		String respuesta=JOptionPane.showInputDialog(null, "Escribe la solución al panel", "Solución", JOptionPane.QUESTION_MESSAGE).toUpperCase();
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
	
	@SuppressWarnings("restriction")
	private void reproducirSonido(boolean pAcertado){
		try {
			String fila;
			if(pAcertado){
				 fila = "sounds/correct.wav";
			}else{
				fila = "sounds/wrong.wav";
			}
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fila).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}

