package ruleta;

public class Jugador {
	
	private String nombre;
	private int comodines;
	private int puntuacionTotal;
	private int puntuacion;
	
	public Jugador(String pNombre){
		this.nombre = pNombre;
		this.puntuacion = 0;
		this.puntuacionTotal = 0;
	}

	public int getComodines() {
		return comodines;
	}

	public void setComodines(int comodines) {
		this.comodines = comodines;
	}

	public int getPuntuacionTotal() {
		return puntuacionTotal;
	}

	public void setPuntuacionTotal(int puntuacionTotal) {
		this.puntuacionTotal = puntuacionTotal;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
