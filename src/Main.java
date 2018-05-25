public class Main {

	static Affichage affichage;

	static boolean jeu = true;
	
	static Jeu risk = new Jeu();

	public static void main(String[] args){
		
		risk.effacerCache();
		
		affichage = new Affichage(1080,720,0);
		affichage.ecranMenu();
		
		while(jeu)
		{
			risk.cliqueCouche(risk.couche);
		}

		risk.effacerCache();
		
		affichage.coucheVictoire();
	}
}
