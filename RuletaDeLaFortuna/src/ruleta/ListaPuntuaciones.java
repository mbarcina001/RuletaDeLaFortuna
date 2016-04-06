package ruleta;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaPuntuaciones {
	
	private static ListaPuntuaciones mListaPuntuaciones=new ListaPuntuaciones();
	private ArrayList<Puntuacion> listaPuntuaciones;
	private final int TAMANO_MAXIMO=10;
	
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
	
	public void guardarPuntuaciones(){
		try{ 
			FileWriter archivo = new FileWriter("res\\puntuaciones.xml"); 

			PrintWriter escritura = new PrintWriter(archivo);
			
			//escribimos un archivo de texto con la estructura de xml

			escritura.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			escritura.print("\t<listapuntuaciones>\n");
			Iterator<Puntuacion> it = this.listaPuntuaciones.iterator();
			while(it.hasNext()){
				Puntuacion aux = it.next();
				escritura.print("\t\t<puntuacion>\n");
				escritura.print("\t\t\t<nombre>");
				escritura.print(aux.getNombre());
				escritura.print("</nombre>\n");
				escritura.print("\t\t\t<puntos>");
				escritura.print(aux.getPuntos());
				escritura.print("</puntos>\n");
				escritura.print("\t\t</puntuacion>\n");
			}
			escritura.print("\t</listapuntuaciones>\n");

			archivo.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertarPuntuacionOrdenada(Puntuacion pPuntuacion){
		Iterator<Puntuacion> it = this.listaPuntuaciones.iterator();
		if(this.listaPuntuaciones.size()==0){
			this.listaPuntuaciones.add(pPuntuacion);
		}else{
			ArrayList<Puntuacion> nuevasPuntuaciones = new ArrayList<Puntuacion>();
			int cont = 0;
			boolean anadido = false;
			while(it.hasNext()){
				Puntuacion aux = it.next();
				if(pPuntuacion.getPuntos()>aux.getPuntos() && !anadido){
					anadido = true;
					nuevasPuntuaciones.add(pPuntuacion);
					cont++;
					if(cont==TAMANO_MAXIMO){
						nuevasPuntuaciones.add(aux);
						cont++;
					}
				}else{
					nuevasPuntuaciones.add(aux);
					cont++;
				}
				
				if(nuevasPuntuaciones.size()==TAMANO_MAXIMO){
					break;
				}
			}
			this.listaPuntuaciones = nuevasPuntuaciones;
		}
	}
	
	public void actualizarPuntuaciones(){
		Iterator<Jugador> it = ListaJugadores.getListaJugadores().getIterador();
		while(it.hasNext()){
			Jugador aux = it.next();
			this.insertarPuntuacionOrdenada(new Puntuacion(aux.getNombre(),aux.getPuntuacionTotal()));
		}
	}
	
	public void imprimir(){
		Iterator<Puntuacion> it = this.listaPuntuaciones.iterator();
		while(it.hasNext()){
			it.next().imprimir();
		}
	}
}