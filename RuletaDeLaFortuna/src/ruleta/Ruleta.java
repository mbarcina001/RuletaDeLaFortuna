package ruleta;

import java.util.ArrayList;
import java.util.Random;

public class Ruleta {
	
	private ArrayList<String> ruleta;
	private static Ruleta mRuleta = new Ruleta();
	
	private Ruleta(){
		ruleta = new ArrayList<String>();
		ruleta.add("Pierde turno");
		ruleta.add("800");
		ruleta.add("350");
		ruleta.add("450");
		ruleta.add("700");
		ruleta.add("300");
		ruleta.add("600");
		ruleta.add("5000");
		ruleta.add("300");
		ruleta.add("600");
		ruleta.add("300");
		ruleta.add("500");
		ruleta.add("800");
		ruleta.add("550");
		ruleta.add("400");
		ruleta.add("300");
		ruleta.add("900");
		ruleta.add("500");
		ruleta.add("Comodin");
		ruleta.add("900");
		ruleta.add("Quiebra");
		ruleta.add("600");
		ruleta.add("400");
		ruleta.add("300");
	}
	
	public static Ruleta getRuleta(){
		return mRuleta;
	}
	
	public String girarRuleta(){
		Random rg = new Random();
		return this.ruleta.get(rg.nextInt(this.ruleta.size()));
	}

}
