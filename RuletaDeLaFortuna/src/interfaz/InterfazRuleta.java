package interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ruleta.Casilla;
import ruleta.ListaCasillas;

public class InterfazRuleta extends JFrame{

	public static void main(String[] args) { 
		InterfazRuleta dialog = new InterfazRuleta(); 
		dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* este metodo indica que la aplicacion termina al cerrarse la ventana */ 
		//dialog.pack();
		dialog.setVisible(true);
	}
	
	public InterfazRuleta(){
		inicializarComponentes();
		//Toolkit tk = Toolkit.getDefaultToolkit();   
		//setSize(840,320);

		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	private void inicializarComponentes(){
		BoxLayout layoutpadre = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS); /* obtenemos el contenedor del JFrame con el metodo getContentPane() */ 
		getContentPane().setLayout(layoutpadre);
		
		//Panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 14, 1, 1 ));

		ListaCasillas.getListaCasillas().iniciarLista("VELOCIRAPTOR TYRANNOSAURUS REX Y TRICERATOPS");
		JLabel labelaux;
		Casilla casillaaux;
		
		for(int i=0;i<4;i++){
			int cont=0;
			ArrayList<Casilla> listalabels = ListaCasillas.getListaCasillas().getListaFila(i);
			Iterator<Casilla> iterador = listalabels.iterator();

			if((i==0 || i==3)&&(cont==0)){
				JLabel oculto = new JLabel();
				oculto.setVisible(false);
				panel.add(oculto);
			}
			
			while(iterador.hasNext()){
				casillaaux = iterador.next();
				labelaux = new JLabel();
				if(casillaaux.isVacia()){
					labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel1.png"))); 
				}else{
					//System.out.println(casillaaux.getLetra());
					if(casillaaux.getLetra()==' '){
						labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel1.png")));
					}else{
						labelaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Panel3.png")));
					}				 
				}
				labelaux.setVisible(true);
				panel.add(labelaux);
				
			}
			
			if(i==0 || i==3){
				JLabel oculto = new JLabel();
				oculto.setVisible(false);
				panel.add(oculto);
			}
		}
		
		this.add(panel);
		
		//Opciones
		JPanel opciones = new JPanel();
		opciones.setLayout(new FlowLayout());
		opciones.add(new JButton("Tirar Ruleta"));
		opciones.add(new JButton("Comprar Vocal"));
		opciones.add(new JButton("Resolver"));		
		this.add(opciones);
		
	}
}