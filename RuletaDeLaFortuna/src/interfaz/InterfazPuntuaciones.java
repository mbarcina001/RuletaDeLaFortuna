package interfaz;

import javax.swing.JButton; 
import javax.swing.JFrame; 

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout; 
import javax.swing.GroupLayout.ParallelGroup; 
import javax.swing.GroupLayout.SequentialGroup; 
import javax.swing.JLabel;

import ruleta.ListaPuntuaciones;
import ruleta.Puntuacion;

public class InterfazPuntuaciones extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblCabecera, lblPuntuacion1, lblPuntuacion2, lblPuntuacion3, lblPuntuacion4, 
	lblPuntuacion5, lblPuntuacion6, lblPuntuacion7, lblPuntuacion8, lblPuntuacion9, 
	lblPuntuacion10;
	JLabel[] labels = {lblPuntuacion1, lblPuntuacion2, lblPuntuacion3, lblPuntuacion4, 
			lblPuntuacion5, lblPuntuacion6, lblPuntuacion7, lblPuntuacion8, lblPuntuacion9, 
			lblPuntuacion10};
	JButton btnVolver, btnSalir; 
	GroupLayout layout; 
	int xSize;
	int ySize;
	

	public static void main(String[] args) { 
		InterfazPuntuaciones dialog = new InterfazPuntuaciones(); 
		dialog.alinearComoColumna(); 
		dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* este metodo indica que la aplicacion termina al cerrarse la ventana */ 
		dialog.setVisible(true);
	}
	
	public InterfazPuntuaciones(){ 
		inicializarComponentes(); 
		setVisible(true); 
		Toolkit tk = Toolkit.getDefaultToolkit();  
		xSize = ((int) tk.getScreenSize().getWidth())-400;  
		ySize = ((int) tk.getScreenSize().getHeight())-300;  
		setSize(xSize,ySize);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	} 
	
	private void inicializarComponentes() { 
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnVolver= new JButton("Volver"); 
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Principal.main(null);
			}
		});
		
		lblCabecera = new JLabel("<html><h1>Puntuaciones</html></h1>");
		for(int i = 0; i<labels.length; i++){
			labels[i] = new JLabel();
			String punt = "\t\t";
			Puntuacion p = ListaPuntuaciones.getListaPuntuaciones().getPuntuacion(i);
			if(p==null){
				break;
			}else{
				punt = punt+p.getNombre();
				for(int j = punt.length(); j<=38; j++){
					punt = punt+'.';
				}
				if(p.getPuntos()<100){
					punt = punt+'0'+p.getPuntos();
				}else{
					punt = punt+p.getPuntos();
				}
				labels[i].setText(punt);
			}
		}
		
		layout = new GroupLayout(getContentPane()); /* obtenemos el contenedor del JFrame con el metodo getContentPane() */ 
		
		getContentPane().setLayout(layout); 
	} 
		
	public void alinearComoColumna(){ 
		ParallelGroup parallelGroupA= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER); 
		
		ParallelGroup parallelGroupAuxiliar= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER);  
		ParallelGroup parallelGroupAuxiliar2= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER);
		
		parallelGroupAuxiliar.addComponent(lblCabecera, 10, 100, 200); 
		for(int i = 0; i<labels.length; i++){
			if(labels[i]==null){
				break;
			}else{
				parallelGroupAuxiliar.addComponent(labels[i], 100, 100, 200);
			}
		}
		parallelGroupAuxiliar2.addComponent(btnVolver, 50,100,200);
		parallelGroupAuxiliar2.addComponent(btnSalir, 50,100,200);
		SequentialGroup sequentialGroupHorizontal= layout.createSequentialGroup(); 
		sequentialGroupHorizontal.addGap(50);  
		sequentialGroupHorizontal.addGroup(parallelGroupAuxiliar);
		sequentialGroupHorizontal.addGap(200);  
		sequentialGroupHorizontal.addGroup(parallelGroupAuxiliar2);
		
		parallelGroupA.addGroup(sequentialGroupHorizontal); 
		layout.setHorizontalGroup(parallelGroupA); 
		
		ParallelGroup parallelGroupB= layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER); 
		
		SequentialGroup sequentialGroupVertical= layout.createSequentialGroup();
		SequentialGroup sequentialGroupVertical2= layout.createSequentialGroup();
		
		sequentialGroupVertical.addComponent(lblCabecera,40,60,80); 
		for(int j = 0; j<labels.length; j++){
			if(labels[j]==null){
				break;
			}else{
				sequentialGroupVertical.addComponent(labels[j], 20, 30, 30);
			}
		}
		sequentialGroupVertical2.addComponent(btnVolver, 30,40,50);
		sequentialGroupVertical2.addGap(20);
		sequentialGroupVertical2.addComponent(btnSalir, 30,40,50);
		parallelGroupB.addGroup(sequentialGroupVertical);
		parallelGroupB.addGroup(sequentialGroupVertical2);
		layout.setVerticalGroup(parallelGroupB); 
	}  
} 