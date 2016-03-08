package ruleta;

import java.awt.Panel;
import java.util.HashMap;
import java.util.Random;

public class ListaPaneles {
	private HashMap<Integer, Panel> lista=new HashMap<Integer,Panel>();
	private static ListaPaneles mListaPaneles=new ListaPaneles();
	private ListaPaneles() {
		
	}
	public static ListaPaneles getListaPaneles(){
		return mListaPaneles;
	}
	public Panel elegirPanelAleatorio(){
		Random r=new Random(lista.size());
		return lista.get(r.nextInt());
	}
	public void aniadirPanel(int pPosicion, Panel pPanel){
		lista.put(pPosicion,pPanel);
	}
	public void eliminarPanel(int pPosicion){
		lista.remove(pPosicion);
	}
}
