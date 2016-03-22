package interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import ruleta.Juego;

public class Principal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JButton btnJugar, btnPuntuaciones, btnReglas;
	GroupLayout layout; 
	int xSize;
	int ySize;
	
	public static void main(String[] args) { 
		Principal dialog = new Principal(); 
		dialog.alinearComoColumna(); 
		dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* este metodo indica que la aplicacion termina al cerrarse la ventana */ 
		dialog.setVisible(true);
	}
	
	public Principal(){ 
		inicializarComponentes(); 
		setVisible(true); 
		Toolkit tk = Toolkit.getDefaultToolkit();  
		xSize = ((int) tk.getScreenSize().getWidth());  
		ySize = ((int) tk.getScreenSize().getHeight());  
		setSize(xSize,ySize);
	} 
	
	private void inicializarComponentes() {
		
		btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Juego juego=new Juego();
				juego.jugar();
			}
		});
		
		btnPuntuaciones= new JButton("Puntuaciones"); 
		btnPuntuaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				//TODO
			}
		});
		
		btnReglas = new JButton("Reglas");
		btnReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				dispose();
				//TODO
			}
		});
		
		layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout); 
	} 
		
	public void alinearComoColumna(){ 
		ParallelGroup parallelGroupA= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER); 
		ParallelGroup parallelGroupAuxiliar= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER);  
		
		parallelGroupAuxiliar.addComponent(btnJugar, 10, 400, 400); 
		parallelGroupAuxiliar.addComponent(btnPuntuaciones, 100, 100, 400); 
		parallelGroupAuxiliar.addComponent(btnReglas, 10, 400, 400);
		
		SequentialGroup sequentialGroupHorizontal= layout.createSequentialGroup(); 
		sequentialGroupHorizontal.addGap(((xSize/2)-200));  
		sequentialGroupHorizontal.addGroup(parallelGroupAuxiliar);
		
		parallelGroupA.addGroup(sequentialGroupHorizontal); 
		layout.setHorizontalGroup(parallelGroupA); 
		
		ParallelGroup parallelGroupB= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER); 
		
		SequentialGroup sequentialGroupVertical= layout.createSequentialGroup(); 
		sequentialGroupVertical.addGap(((ySize/2)-230)); 
		sequentialGroupVertical.addComponent(btnJugar,100,100,100); 
		sequentialGroupVertical.addGap(20);
		sequentialGroupVertical.addComponent(btnPuntuaciones,100,100,100);
		sequentialGroupVertical.addGap(20);
		sequentialGroupVertical.addComponent(btnReglas,100,100,100);
		sequentialGroupVertical.addGap(20);
		
		parallelGroupB.addGroup(sequentialGroupVertical); 
		layout.setVerticalGroup(parallelGroupB); 
	}  

}
