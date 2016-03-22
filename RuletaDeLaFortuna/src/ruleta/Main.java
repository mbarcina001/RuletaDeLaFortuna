package ruleta;

import interfaz.Principal;

public class Main {
	
	public static void main(String[] args) {
		//TODO CARGAR PANELES Y ESAS COSAS.
		ListaPuntuaciones.getListaPuntuaciones().cargarPuntuaciones();
		ListaPuntuaciones.getListaPuntuaciones().imprimir();
		
		Principal.main(null);
	}

}
