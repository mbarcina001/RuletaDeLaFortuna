package ruleta;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LectorPuntuaciones {
	
	private static String nombre;
	private static int puntos;

	public static void main(String argv[]) {
		 
	    try {
	    	
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
		 
			DefaultHandler handler = new DefaultHandler() {
		 
			boolean bpuntuacion = false;
			boolean bnombre = false;
			boolean bpuntos = false;
			
			public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {
	 
				if (qName.equalsIgnoreCase("Puntuacion")) {
					bpuntuacion = true;
				}
				
				if(qName.equalsIgnoreCase("Nombre")){
					bnombre=true;
				}
		 
				if (qName.equalsIgnoreCase("Puntos")) {
					bpuntos = true;
				}
			}
	 
			public void endElement(String uri, String localName,
				String qName) throws SAXException {  }
	 
			public void characters(char ch[], int start, int length) throws SAXException {
				
				if(bpuntuacion){
					if (bnombre) {
						nombre=new String(ch, start, length);
						bnombre=false;
					}
			 
					if (bpuntos) {
						puntos=Integer.parseInt(new String(ch, start, length));
						ListaPuntuaciones.getListaPuntuaciones().anadirPuntuacion(nombre, puntos);
						bpuntuacion=false;
						bpuntos=false;
					}
			     } 
				}
			};
			
			saxParser.parse("res\\puntuaciones.xml", handler);
			
		} catch(Exception e){
			e.printStackTrace();
		}	
	}
}
