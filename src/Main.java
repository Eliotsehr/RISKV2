public class Main {

	static Interface interf;

	static Jeu risk;

	static boolean jeu = true;

	public static void main(String[] args){
		risk = new Jeu();
		
		interf = new Interface(1080,720,0);
		interf.ecranMenu();
		
		while(jeu)
		{
			interf.cliqueCouche(interf.couche);
		}

		interf.coucheVictoire();
	}
}
