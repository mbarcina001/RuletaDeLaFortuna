package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
		
		System.out.println("1");
		
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
		

		
		System.out.println("2");
		
		this.add(panel);
		
		//PISTA
		labelpista = new JLabel("");
		labelpista.setForeground(Color.blue);
		labelpista.setBackground(Color.lightGray);
		labelpista.setText("A");
		
		this.add(labelpista);
		
		System.out.println("3");
		
		//Opciones
		JPanel opciones = new JPanel();
		opciones.setLayout(new FlowLayout());
		opciones.add(new JButton("Tirar Ruleta"));
		opciones.add(new JButton("Comprar Vocal"));
		opciones.add(new JButton("Resolver"));		
		this.add(opciones);
		
		System.out.println("4");
	}
	
	private void actualizarLabels(){
		Panel p = Juego.getJuego().getPanelActual();
		ListaCasillas.getListaCasillas().iniciarLista(p.getLetras());
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
		labelpista.setText(p.getPista());
	}
	
	public void update(Observable arg0, Object arg1) {
		System.out.println("UPDATE");
		actualizarLabels();
	}
}
