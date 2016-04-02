package ruleta;

public class Casilla {
	
	private char letra;
	private boolean oculta;
	private boolean vacia;
	
	public Casilla(char pLetra){ 
		this.letra = pLetra;
		if(letra==' '){
			vacia = true;
		}
		if(letra==','){
			oculta = false;
		}else{
			oculta=true;
		}
	}
	
	public Casilla(boolean pVacia){ 
		this.vacia = pVacia;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public boolean isOculta() {
		return oculta;
	}

	public void setOculta(boolean oculta) {
		this.oculta = oculta;
	}

	public boolean isVacia() {
		return vacia;
	}

	public void setVacia(boolean vacia) {
		this.vacia = vacia;
	}
}
