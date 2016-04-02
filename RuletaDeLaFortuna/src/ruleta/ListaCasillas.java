package ruleta;

import java.util.ArrayList;


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
	
	public void iniciarLista(String letras){
		char[] arrayLetras = letras.toCharArray();
		ArrayList<Casilla> todascasillas = new ArrayList<Casilla>();
		Casilla c;
		for(int i=0;i<arrayLetras.length-1;i++){
			c = new Casilla(arrayLetras[i]);
			todascasillas.add(c);
		}
		
		ArrayList<Integer> cuentas = new ArrayList<Integer>();
		int cuenta = 0;
		int max = 12;
		int i = 0;
		for(int j=0;j<todascasillas.size()-1;j++){
			if(todascasillas.get(j+1).getLetra()==' '){
				cuenta = j+1;
			}
			if(j+1>max){
				j=cuenta+1;
				cuentas.add(cuenta);
				i++;
				switch(i){
				case 1: max+=14;
						break;
				case 2: max+=14;
						break;
				case 3: max+=12;
				}
			}
			if(j+1>=todascasillas.size()-1 && j+1<=max){
				cuenta=j+2;
				cuentas.add(cuenta);
			}
		}
		
		System.out.println(cuentas);
		
		int empieza=0;
		for(int j=0;j<cuentas.size();j++){
			switch(j){
			case 0:
				empieza = obtenerPosEmpieza12(cuentas.get(j));
				max=12;
				break;
			case 1:
				empieza = obtenerPosEmpieza14(cuentas.get(j));
				max=14;
				break;
			case 2:
				empieza = obtenerPosEmpieza14(cuentas.get(j));
				max=14;
				break;
			case 3:
				empieza = obtenerPosEmpieza12(cuentas.get(j));
				max=12;
				break;
			}
			int acaba = empieza+cuentas.get(j);
			
			System.out.println("Empieza: "+empieza);
			System.out.println("Acaba: "+acaba);

			ArrayList<Casilla> lista = listacasillas.get(j);
			int pos = 0;
			Casilla cas;
			for(int k=0; k<max; k++){
				if(k>=empieza && k<=acaba){
					cas = new Casilla(arrayLetras[pos]);
					System.out.println(arrayLetras[pos]);
					pos++;
				}else{
					cas = new Casilla(true);
				}
				lista.add(cas);
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

	public static ListaCasillas getListaCasillas() {
		return mListaCasillas;
	}

}
