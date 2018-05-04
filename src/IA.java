public class IA extends Joueur{

	Jeu risk = Main.risk;
	
	public IA(String nom, int nombreDeTerritoires, int numeroDeJoueur, int nombreTerritoiresCaptures, Mission mission,int test) {
		super(nom, nombreDeTerritoires, numeroDeJoueur, nombreTerritoiresCaptures, mission);
		
		
	}

	public void deplaceUniteeBloquee()
	{
		for(int i = 0; i < risk.listeTerritoires.size(); i++)
		{
			Territoire territoire = risk.listeTerritoires.get(i);
			
			if(territoire.getProprietaire().equals(this) && territoire.estEntoure())
			{
				
			}
		}
	}
	
	//Comparer unités 
	
	//Controler des regions
	
	//Choix troupes attaque
	
	//Choix placement
	
	

}
