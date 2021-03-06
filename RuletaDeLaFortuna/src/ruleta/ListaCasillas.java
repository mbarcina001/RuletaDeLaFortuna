package ruleta;

import java.util.ArrayList;
import java.util.Iterator;


public class ListaCasillas {
	
	private static ListaCasillas mListaCasillas = new ListaCasillas();
	private ArrayList<ArrayList<Casilla>> listacasillas;
	
	private ListaCasillas(){
		listacasillas = new ArrayList<ArrayList<Casilla>>();
		ArrayList<Casilla> aux;
		for(int i=0; i<4; i++){
			aux = new ArrayList<Casilla>();
			listacasillas.add(aux);
		}
	}
	
	public ArrayList<Casilla> getListaFila(int indice){
		return this.listacasillas.get(indice);
	}
	
	public void resetearListaCasillas(){
		this.listacasillas = new ArrayList<ArrayList<Casilla>>();
		ArrayList<Casilla> aux;
		for(int i=0; i<4; i++){
			aux = new ArrayList<Casilla>();
			listacasillas.add(aux);
		}
	}
	
	public void iniciarLista(String letras){
		char[] arrayLetras = letras.toCharArray();

		ArrayList<Integer> listaEmpiezaEnArray = new ArrayList<Integer>();
		listaEmpiezaEnArray.add(0);
		ArrayList<Integer> listaAcabaEnArray = new ArrayList<Integer>();
		int cuenta = 0;
		int fila = 0;
		int max = 12;
		
		for(int j=0;j<arrayLetras.length-1;j++){
			if(arrayLetras[j+1]==' '){
				cuenta = j;
			}
			
			if(j+1>max){
				j=cuenta+1;
				if(arrayLetras[j]==' '){
					j=j+1;
				}
				listaAcabaEnArray.add(cuenta+1);
				listaEmpiezaEnArray.add(j);
				fila++;
				switch(fila){
				case 1: max+=14;
						break;
				case 2: max+=14;
						break;
				case 3: max+=12;
				}
			}
			
			if(j+1>=arrayLetras.length-1 && j+1<=max){
				cuenta = arrayLetras.length-1;
				listaAcabaEnArray.add(cuenta);
			}
		}
		
		int empieza=0;
		for(int j=0;j<listaEmpiezaEnArray.size();j++){
			switch(j){
			case 0:
				empieza = obtenerPosEmpieza12(listaAcabaEnArray.get(j)-listaEmpiezaEnArray.get(j));
				max=12;
				break;
			case 1:
				empieza = obtenerPosEmpieza14(listaAcabaEnArray.get(j)-listaEmpiezaEnArray.get(j));
				max=14;
				break;
			case 2:
				empieza = obtenerPosEmpieza14(listaAcabaEnArray.get(j)-listaEmpiezaEnArray.get(j));
				max=14;
				break;
			case 3:
				empieza = obtenerPosEmpieza12(listaAcabaEnArray.get(j)-listaEmpiezaEnArray.get(j));
				max=12;
				break;
			}
			int acaba = empieza+listaAcabaEnArray.get(j)-listaEmpiezaEnArray.get(j);

			ArrayList<Casilla> lista = listacasillas.get(j);
			int pos = listaEmpiezaEnArray.get(j);
			Casilla cas;
			for(int k=0; k<max; k++){
				if(pos>arrayLetras.length-1){
					cas = new Casilla(true);
				}
				if(k>=empieza && k<=acaba){
					cas = new Casilla(arrayLetras[pos]);
					pos++;
				}else{
					cas = new Casilla(true);
				}
				lista.add(cas);
			}
		}
		for(int p=listaEmpiezaEnArray.size();p<4; p++){
			switch(p){
			case 0:
				max=12;
				break;
			case 1:
				max=14;
				break;
			case 2:
				max=14;
				break;
			case 3:
				max=12;
				break;
			}
			for(int p2 = 0; p2<max; p2++){
				Casilla cas = new Casilla(true);
				this.listacasillas.get(p).add(cas);
			}
		}
		
	}
	
	private int obtenerPosEmpieza12(int tamano){
		int empieza = 0;
		switch(tamano){
		case 12:
			empieza = 0;
			break;
		case 11:
			empieza = 0;
			break;
		case 10:
			empieza = 1;
			break;
		case 9:
			empieza = 1;
			break;
		case 8:
			empieza = 2;
			break;
		case 7:
			empieza = 2;
			break;
		case 6:
			empieza = 3;
			break;
		case 5:
			empieza = 3;
			break;
		case 4:
			empieza = 4;
			break;
		case 3:
			empieza = 4;
			break;
		case 2:
			empieza = 5;
			break;
		case 1:
			empieza = 5;
			break;
		}
		return empieza;
	}
	
	private int obtenerPosEmpieza14(int tamano){
		int empieza = 0;
		switch(tamano){
		case 14:
			empieza = 0;
			break;
		case 13:
			empieza = 0;
			break;
		case 12:
			empieza = 1;
			break;
		case 11:
			empieza = 1;
			break;
		case 10:
			empieza = 2;
			break;
		case 9:
			empieza = 2;
			break;
		case 8:
			empieza = 3;
			break;
		case 7:
			empieza = 3;
			break;
		case 6:
			empieza = 4;
			break;
		case 5:
			empieza = 4;
			break;
		case 4:
			empieza = 5;
			break;
		case 3:
			empieza = 5;
			break;
		case 2:
			empieza = 6;
			break;
		case 1:
			empieza = 6;
			break;
		}
		return empieza;
	}

	public void destaparLetra(char pLetra){
		Iterator<ArrayList<Casilla>> it = this.listacasillas.iterator();
		while(it.hasNext()){
			Iterator<Casilla> it2 = it.next().iterator();
			while(it2.hasNext()){
				Casilla c = it2.next();
				if(c.getLetra()==pLetra){
					c.setOculta(false);
				}
			}
		}
	}
	
	public void destaparTodo(){
		Iterator<ArrayList<Casilla>> it = this.listacasillas.iterator();
		while(it.hasNext()){
			Iterator<Casilla> it2 = it.next().iterator();
			while(it2.hasNext()){
				Casilla c = it2.next();
				c.setOculta(false);
			}
		}
	}
	
	public static ListaCasillas getListaCasillas() {
		return mListaCasillas;
	}

}
