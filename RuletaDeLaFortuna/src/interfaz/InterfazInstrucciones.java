package interfaz;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazInstrucciones extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnSalir, btnVolver;
	JLabel lblCabecera, lblReglas;
	int xSize, ySize;

	public static void main(String[] args) {
		try {
			InterfazInstrucciones dialog = new InterfazInstrucciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazInstrucciones() {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		xSize = ((int) tk.getScreenSize().getWidth());  
		ySize = ((int) tk.getScreenSize().getHeight());  
		setSize(xSize,ySize);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{xSize/4, xSize/4, xSize/4, xSize/4};
		gridBagLayout.rowHeights = new int[]{(ySize)/7, (ySize*4)/7, (ySize)/7, (ySize)/7};
		getContentPane().setLayout(gridBagLayout);
		
		lblCabecera = new JLabel("New label");
		lblCabecera.setText("<html><h1><b><center>Reglas del juego:</center></b></h1></html>");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblCabecera, gbc_lblNewLabel);
		
		lblReglas = new JLabel("New label");
		lblReglas.setText("<html>Se juega con tiradas a la ruleta, por lo que el concursante la hace girar, y cuando deje de girar la ruleta, la flecha " 
		+"indicar� el premio que le ha tocado. Por cada letra que haya en el panel, el concursante ser� recompensado con la cantidad "
		+"de dinero multiplicada por las veces que se encuentre en el panel. Si la letra no est� en el panel, el jugador perder� el "
		+"turno. Tambi�n para ayudar a resolver el panel se pueden comprar vocales por 50 puntos antes de tirar la ruleta. Si no est� "
		+"la vocal pedida, se pierden los 50 puntos por comprarla y el turno por equivocarse. El panel toma fin cuando el jugador "
		+"pide Resolver y resuelve correctamente.<br><br>"		
		+"Gajos de la ruleta:<br>"
		+"<ul><li>Puntos, se sumar� la cantidad indicada por el n�mero de apariciones de la letra pedida.</li>"
		+"<li>Pierde turno, se ceder� el turno al siguiente jugador.</li>"
		+"<li>Quiebra, se ceder� el turno al siguiente jugador y se perder�n todos los puntos.</li>"
		+"<li>Comod�n, se utilizar� para salvarse de cualquier situaci�n desfavorable (fallar, perder turno, o quebrar(s�lo se salva de "
		+"perder turno, los puntos ser�n 0 siempre).</li>"
		+"<li>Los puntos de los paneles se van acumulando en un marcador total cual no le afectar� la quiebra.</li>"
		+"<li>Ganar� el jugador con m�s puntos en el marcador total.</li></ul></html>");
		lblReglas.setVerticalAlignment(JLabel.TOP);
		lblReglas.setVerticalTextPosition(JLabel.TOP);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		getContentPane().add(lblReglas, gbc_lblNewLabel_1);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		
		btnVolver = new JButton("New button");
		btnVolver.setText("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Principal.main(null);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		getContentPane().add(btnVolver, gbc_btnNewButton);
		
		btnSalir = new JButton("Salir");
		btnSalir.setText("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		getContentPane().add(btnSalir, gbc_btnNewButton_1);
	}

}