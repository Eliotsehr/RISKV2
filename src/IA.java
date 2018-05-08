public class IA extends Joueur{

	Jeu risk = Main.risk;
	
	public IA(String nom, int nombreDeTerritoires, int numeroDeJoueur, int nombreTerritoiresCaptures, Mission mission,int test) {
		super(nom, nombreDeTerritoires, numeroDeJoueur, nombreTerritoiresCaptures, mission);
		
		
	}

	
	
	
	//Comparer unités 
	/**
	 * Attaque les territoires plus fabile que lui
	 */
	public void attaqueBasique()
	{
		for(int i = 0; i < risk.listeTerritoires.size(); i++)
		{
			if(risk.listeTerritoires.get(i).getProprietaire().equals(this) && risk.listeTerritoires.get(i).peutAttaquer())
			{
				for(int j = 0; j < risk.listeTerritoires.get(i).getTerritoiresAdjacents().length;j++)
				{
					Territoire territoireDEF = null;
					
					territoireDEF = territoireDEF.retrouverAvecNom(risk.listeTerritoires.get(i).getTerritoiresAdjacents()[j]);
					
					if(risk.listeTerritoires.get(i).rapportUnités(territoireDEF) >= 2 && territoireDEF.getProprietaire() != this)
					{
						risk.listeTerritoires.get(i).attaque(territoireDEF);
					}
				}
			}
		}
	}
	
	
	
	//Deplacement
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
	
	
	//Controler des regions
	
	//Choix troupes attaque
	
	//Choix placement
	public void placement()
	{
		for(int i = 0; i < risk.listeTerritoires.size();i++)
		{
			if(risk.listeTerritoires.get(i).appartientA(this) && risk.listeTerritoires.get(i).estEntoure() == false)
			{
				
				
				
			}
		}
	}
	

}
