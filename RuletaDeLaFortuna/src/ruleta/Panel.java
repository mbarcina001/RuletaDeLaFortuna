package ruleta;

import java.util.ArrayList;
import java.util.Iterator;

public class Panel {
	private ArrayList<Character> pregunta;
	private ArrayList<Character> acertadas;
	private String pista;
	public Panel(String pLetras, String pPista) {
			acertadas=new ArrayList<Character>();
			pregunta= new ArrayList<Character>();
			for(int i=0;i<pLetras.length();i++){
				pregunta.add(i,pLetras.charAt(i));
				acertadas.add(' ');
			}
			pista=pPista;
	}
	public String verPista(){
		return this.pista;
	}
	public void comprobarLetra(Character pLetra){
		Iterator<Character> it=pregunta.iterator();
		Character letra;
		boolean haAcertado=false;
		int contador=0;
		while(it.hasNext()){
			letra=it.next();
			if(letra==pLetra){
				acertadas.add(contador,letra);
				haAcertado=true;
			}
			contador++;
		}
		if(!haAcertado){
			//////////////////////////////////////////////
			//////////////////////////////////////////////
			//////////GENERAR SI NO ACIERTA///////////////
			//////////////////////////////////////////////
			//////////////////////////////////////////////
		}
	}
	public boolean tieneDemasiadosCaracteres(){
		return this.pregunta.size()>60;
	}
}
