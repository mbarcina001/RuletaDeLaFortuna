package ruleta;


public class Puntuacion {
	String nombre;
	int puntos;
	
	public Puntuacion(String pNombre, int pPuntos){
		this.nombre=pNombre;
		this.puntos=pPuntos;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public int getPuntos(){
		return this.puntos;
	}
	
	public void setPuntos(int pPuntos){
		this.puntos=pPuntos;
	}
		
	public void imprimir(){
		System.out.println("El jugador "+this.nombre +" ha obtenido una puntuacion de: "+this.puntos+" puntos.");
	}
}
