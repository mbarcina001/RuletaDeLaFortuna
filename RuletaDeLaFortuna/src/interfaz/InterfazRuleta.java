package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import ruleta.ListaCasillas;
import ruleta.Panel;

public class InterfazRuleta extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> labels;
	private JLabel labelpista;

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
		labelpista.setText("A");
		
		this.add(labelpista);
		
		//Opciones
		JPanel opciones = new JPanel();
		opciones.setLayout(new FlowLayout());
		
		JButton botontirar = new JButton("Tirar Ruleta");
		botontirar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Juego.getJuego().tirarRuleta();
				
			}
		});
		opciones.add(botontirar);
		
		JButton botonvocal = new JButton("Comprar Vocal");
		botonvocal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Juego.getJuego().tirarRuleta();
				
			}
		});
		opciones.add(botonvocal);
		
		JButton botonresolver = new JButton("Comprar Vocal");
		botonresolver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Juego.getJuego().tirarRuleta();
				
			}
		});
		opciones.add(botonresolver);		
		this.add(opciones);
	}
	
	private void actualizarLabels(){
		Panel p = Juego.getJuego().getPanelActual();
		Iterator<JLabel> iteradorlabels = labels.iterator();
		
		for(int i=0;i<4;i++){
			ArrayList<Casilla> listacasillas = ListaCasillas.getListaCasillas().getListaFila(i);
			Iterator<Casilla> iteradorcasillas = listacasillas.iterator();

			while(iteradorcasillas.hasNext()){
				JLabel labelaux = iteradorlabels.next();
				Casilla casillaaux = iteradorcasillas.next();
				System.out.println(casillaaux.getLetra());
				if(casillaaux.isVacia()){
					labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel1.png"))); 
				}else{
					if(casillaaux.getLetra()==' '){
						labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel1.png")));
					}else{
						if(casillaaux.isOculta()){
							labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel3.png")));
						}else{
							System.out.println("HEY!");
							labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel"+casillaaux.getLetra()+".png")));
						}
						
					}				 
				}
			}
		}
		labelpista.setText(p.getPista());
	}
	
	public void update(Observable arg0, Object arg1) {
		System.out.println("UPDATE");
		actualizarLabels();
	}
}
