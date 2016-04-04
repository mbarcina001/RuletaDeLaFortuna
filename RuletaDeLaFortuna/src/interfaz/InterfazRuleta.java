package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ruleta.Casilla;
import ruleta.Juego;
import ruleta.Jugador;
import ruleta.ListaCasillas;
import ruleta.ListaJugadores;
import ruleta.Panel;

public class InterfazRuleta extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> labels;
	private ArrayList<JLabel> labelsjugadores;
	private JLabel labelpista;
	private JLabel labeljugador;
	private JLabel labelruleta;
	private JButton btnSiguiente, botonvocal, botonresolver, botontirar;

	public static void main(String[] args) { 
		InterfazRuleta dialog = new InterfazRuleta(); 
		dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* este metodo indica que la aplicacion termina al cerrarse la ventana */ 
		
	}
	
	public InterfazRuleta(){
		ponerComponentes();
		//Toolkit tk = Toolkit.getDefaultToolkit();   
		//setSize(840,320);

		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
	}
	
	public void ponerComponentes(){
		Juego.getJuego().addObserver(this);
		labels = new ArrayList<JLabel>();
		BoxLayout layoutpadre = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS); /* obtenemos el contenedor del JFrame con el metodo getContentPane() */ 
		getContentPane().setLayout(layoutpadre);
		
		labeljugador = new JLabel("");
		labeljugador.setForeground(Color.blue);
		labeljugador.setBackground(Color.lightGray);
		labeljugador.setFont(new Font("Serif", Font.PLAIN, 20));
		labeljugador.setText("Turno de: ");
		labeljugador.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.add(labeljugador);
		
		labelruleta = new JLabel("");
		labelruleta.setForeground(Color.blue);
		labelruleta.setBackground(Color.lightGray);
		labelruleta.setFont(new Font("Serif", Font.PLAIN, 20));
		labelruleta.setText("Ruleta: -----");
		labelruleta.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add(labelruleta);
		
		//Panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 14, 1, 1 ));
		
		JLabel labelaux;
		Casilla casillaaux;
		
		for(int i=0;i<4;i++){
			int cont=0;
			int max = 0;
			switch(i){
			case 0:
				max=12;
				break;
			case 1:
				max=14;
				break;
			case 2:
				max=14;
				break;
			case 3:
				max=12;
				break;
			}
			
			if(i==0 || i==3){
				JLabel oculto = new JLabel();
				oculto.setVisible(false);
				panel.add(oculto);
			}
			
			
			while(cont<max){
				labelaux = new JLabel();
				labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel1.png"))); 
				labelaux.setVisible(true);
				panel.add(labelaux);
				labels.add(labelaux);
				cont++;
			}
			
			if(i==0 || i==3){
				JLabel oculto = new JLabel();
				oculto.setVisible(false);
				panel.add(oculto);
			}
		}
		
		this.add(panel);
		
		//PISTA
		labelpista = new JLabel("");
		labelpista.setForeground(Color.blue);
		labelpista.setBackground(Color.lightGray);
		labelpista.setFont(new Font("Serif", Font.PLAIN, 20));
		labelpista.setText("A");
		labelpista.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add(labelpista);
		
		//Opciones
		JPanel opciones = new JPanel();
		opciones.setLayout(new FlowLayout());
		
		botontirar = new JButton("Tirar Ruleta");
		botontirar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Juego.getJuego().tirarRuleta();
				
			}
		});
		opciones.add(botontirar);
		
		botonvocal = new JButton("Comprar Vocal");
		botonvocal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Juego.getJuego().comprarVocal();
				
			}
		});
		opciones.add(botonvocal);
		
		botonresolver = new JButton("Resolver Panel");
		botonresolver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Juego.getJuego().resolverPanel();
				
			}
		});
		opciones.add(botonresolver);		
		this.add(opciones);
		
		JPanel panelSiguiente = new JPanel();
		panelSiguiente.setLayout(new FlowLayout());
		btnSiguiente = new JButton("Cargar siguiente panel");
		panelSiguiente.add(btnSiguiente);
		btnSiguiente.setEnabled(false);
		
		btnSiguiente.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Juego.getJuego().cargarSiguientePanel();
			}
		});
		this.add(panelSiguiente);

		JPanel panelpuntuaciones = new JPanel();
		panelpuntuaciones.setLayout(new FlowLayout());
		labelsjugadores = new ArrayList<JLabel>();
		Iterator<Jugador> iterador = ListaJugadores.getListaJugadores().getIterador();
		while(iterador.hasNext()){
			iterador.next();
			JLabel labeljugador = new JLabel("<html>Nombre:<br>Puntuación:<br>Puntuación Total:</html>");
			labelsjugadores.add(labeljugador);
			panelpuntuaciones.add(labeljugador);
		}
		this.add(panelpuntuaciones);
	}
	
	private void actualizarLabels(){
		Iterator<JLabel> iteradorlabels = labels.iterator();
		
		for(int i=0;i<4;i++){
			ArrayList<Casilla> listacasillas = ListaCasillas.getListaCasillas().getListaFila(i);
			Iterator<Casilla> iteradorcasillas = listacasillas.iterator();

			while(iteradorcasillas.hasNext()){
				JLabel labelaux = iteradorlabels.next();
				Casilla casillaaux = iteradorcasillas.next();
				if(casillaaux.isVacia()){
					labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel1.png"))); 
				}else{
					if(casillaaux.getLetra()==' '){
						labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel1.png")));
					}else{
						if(casillaaux.isOculta()){
							labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel3.png")));
						}else{
							labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel"+casillaaux.getLetra()+".png")));
						}
						
					}				 
				}
			}
		}
		if(Juego.getJuego().getPanelActual()!=null){
			labelpista.setText(Juego.getJuego().getPanelActual().getPista());
		}
		if(Juego.getJuego().getJugadorActual()!=null){
			labeljugador.setText("Turno de: "+Juego.getJuego().getJugadorActual().getNombre());
		}
		if(Juego.getJuego().getRdo()!=null){
			labelruleta.setText("Ruleta: "+Juego.getJuego().getRdo());
		}
		
		if(Juego.getJuego().getPanelResuelto()){
			btnSiguiente.setEnabled(true);
			botonresolver.setEnabled(false);;
			botontirar.setEnabled(false);
			botonvocal.setEnabled(false);
		}else{
			btnSiguiente.setEnabled(false);
			botonresolver.setEnabled(true);;
			botontirar.setEnabled(true);
			botonvocal.setEnabled(true);
		}
		
		Iterator<Jugador> iterador1 = ListaJugadores.getListaJugadores().getIterador();
		Iterator<JLabel> iterador2 = labelsjugadores.iterator();
		while(iterador1.hasNext()){
			Jugador aux = iterador1.next();
			JLabel aux2 = iterador2.next();
			aux2.setText("<html>Nombre: "+aux.getNombre()+"<br>Puntuación: "+aux.getPuntuacion()+"<br>Puntuación Total: "+aux.getPuntuacionTotal()+"</html>");;
		}
	}
	
	public void update(Observable arg0, Object arg1) {
		actualizarLabels();
	}
}
