package ruleta;

import java.awt.Panel;
import java.util.HashMap;
import java.util.Random;

public class ListaPaneles {
	private HashMap<Integer,ruleta.Panel> lista=new HashMap<Integer,ruleta.Panel>();
	private static ListaPaneles mListaPaneles=new ListaPaneles();
	private ListaPaneles() {
		
	}
	public static ListaPaneles getListaPaneles(){
		return mListaPaneles;
	}
	public ruleta.Panel elegirPanelAleatorio(){
		Random r=new Random();
		return lista.get(r.nextInt(lista.size()-1));
	}
	public void aniadirPanel(int pPosicion, ruleta.Panel pPanel){
		lista.put(pPosicion,pPanel);
	}
	public void eliminarPanel(int pPosicion){
		lista.remove(pPosicion);
	}
}
