package ruleta;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaPuntuaciones {
	
	private static ListaPuntuaciones mListaPuntuaciones=new ListaPuntuaciones();
	private ArrayList<Puntuacion> listaPuntuaciones;
	
	private ListaPuntuaciones(){
		this.listaPuntuaciones=new ArrayList<Puntuacion>();
	}
	
	public static ListaPuntuaciones getListaPuntuaciones(){
		return mListaPuntuaciones;
	}
	
	public void cargarPuntuaciones(){
		LectorPuntuaciones.main(null);
	}
	
	public void anadirPuntuacion(String pNombre, int pPuntos){
		Puntuacion p = new Puntuacion(pNombre, pPuntos);
		this.listaPuntuaciones.add(p);
	}	
	
	public Puntuacion getPuntuacion(int pIndice){
		if(pIndice<this.listaPuntuaciones.size()){
			return this.listaPuntuaciones.get(pIndice);
		}else{
			return null;
		}
	}
	
	public void imprimir(){
		Iterator<Puntuacion> it = this.listaPuntuaciones.iterator();
		while(it.hasNext()){
			it.next().imprimir();
		}
	}
}