package ruleta;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LectorPaneles {
	
	private static String pregunta;
	private static String respuesta;
	private static int indice = 0;

	public static void main(String argv[]) {
		 
	    try {
	    	
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
		 
			DefaultHandler handler = new DefaultHandler() {
		 
			boolean bpanel = false;
			boolean bpregunta = false;
			boolean brespuesta = false;
			
			public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {
	 
				if (qName.equalsIgnoreCase("Panel")) {
					bpanel = true;
				}
				
				if(qName.equalsIgnoreCase("Pregunta")){
					bpregunta=true;
				}
		 
				if (qName.equalsIgnoreCase("Respuesta")) {
					brespuesta = true;
				}
			}
	 
			public void endElement(String uri, String localName,
				String qName) throws SAXException {  }
	 
			public void characters(char ch[], int start, int length) throws SAXException {
				
				if(bpanel){
					if (bpregunta) {
						pregunta=new String(ch, start, length);
						bpregunta=false;
					}
			 
					if (brespuesta) {
						respuesta=new String(ch, start, length);
						Panel p = new Panel(respuesta, pregunta);
						ListaPaneles.getListaPaneles().aniadirPanel(indice, p);
						indice++;
						brespuesta=false;
						bpanel=false;
					}
			     } 
				}
			};
			
			saxParser.parse("res\\paneles.xml", handler);
			
		} catch(Exception e){
			e.printStackTrace();
		}	
	}

}
