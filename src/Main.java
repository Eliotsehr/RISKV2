public class Main {

	static Interface interf;

	static boolean jeu = true;

	public static void main(String[] args){
		
		Jeu.effacerCache();
		
		interf = new Interface(1080,720,0);
		interf.ecranMenu();
		
		while(jeu)
		{
			interf.cliqueCouche(interf.couche);
		}

		Jeu.effacerCache();
		
		interf.coucheVictoire();
	}
}
