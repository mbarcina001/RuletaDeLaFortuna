package ruleta;

import java.util.ArrayList;
import java.util.Random;

public class Ruleta {
	
	private ArrayList<String> ruleta;
	private static Ruleta mRuleta = new Ruleta();
	
	private Ruleta(){
		ruleta = new ArrayList<String>();
		ruleta.add("Pierde turno");
		ruleta.add("Comodin");
		ruleta.add("25");
		ruleta.add("75");
		ruleta.add("50");
		ruleta.add("Quiebra");
		ruleta.add("200");
		ruleta.add("25");
		ruleta.add("100");
		ruleta.add("Pierde turno");
		ruleta.add("50");
		ruleta.add("150");
		ruleta.add("75");
		ruleta.add("25");
		ruleta.add("100");
		ruleta.add("25");
		ruleta.add("150");
		ruleta.add("100");
		ruleta.add("Comodin");
		ruleta.add("50");
		ruleta.add("Quiebra");
		ruleta.add("75");
		ruleta.add("25");
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
