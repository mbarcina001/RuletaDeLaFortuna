package ruleta;

import java.util.ArrayList;
import java.util.Iterator;

public class Panel {
	private ArrayList<Character> pregunta;
	private ArrayList<Character> acertadas;
	private String pista;
	private String letras;
	public Panel(String pLetras, String pPista) {
			acertadas=new ArrayList<Character>();
			pregunta= new ArrayList<Character>();
			for(int i=0;i<pLetras.length();i++){
				pregunta.add(i,pLetras.charAt(i));
				acertadas.add(' ');
			}
			pista=pPista;
			letras=pLetras;
	}
	public ArrayList<Character> getPregunta() {
		return pregunta;
	}
	public String getPista() {
		return pista;
	}
	public String getLetras(){
		return this.letras;
	}
	public int comprobarLetra(Character pLetra){
		Iterator<Character> it=pregunta.iterator();
		Character letra;
		
		int contador=0;
		int aciertos=0;
		while(it.hasNext()){
			letra=it.next();
			if(letra==pLetra){
				acertadas.add(contador,letra);
				aciertos++;
			}
			contador++;
		}
		if(aciertos==0){
			//////////////////////////////////////////////
			//////////////////////////////////////////////
			//////////GENERAR SI NO ACIERTA///////////////
			//////////////////////////////////////////////
			//////////////////////////////////////////////
		}
		return aciertos;
	}
	public boolean tieneDemasiadosCaracteres(){
		return this.pregunta.size()>60;
	}
	public int calcularPuntuacion(int pDinero, Character pLetra){
		return comprobarLetra(pLetra)*pDinero;
	}
	public boolean comprobarSolucion(ArrayList<Character> pSolucion){
		Iterator<Character> it=pSolucion.iterator();
		Iterator<Character> it2=this.pregunta.iterator();
		Character letraS;
		Character letraP;
		boolean acierto=false;
		
		if(pSolucion.size()==this.pregunta.size()){
		acierto=true;
			while(acierto&&it.hasNext()){
				letraS=it.next();
				letraP=it2.next();
				if(!letraS.equals(letraP)){
					acierto=false;
				}
			}
		}
		return acierto;
	}
}
