import java.util.ArrayList;

public class IA extends Joueur{

	
	
	public IA(String nom, int nombreDeTerritoires, int numeroDeJoueur, int nombreTerritoiresCaptures, Mission mission) {
		super(nom, nombreDeTerritoires, numeroDeJoueur, nombreTerritoiresCaptures, mission);
		
		
	}

	public void deplaceUniteeBloquee(ArrayList<Territoire> listeTerritoires)
	{
		for(int i = 0; i < listeTerritoires.size(); i++)
		{
			if(listeTerritoires.get(i).getProprietaire().equals(this) && listeTerritoires.get(i).estEntoure())
			{
				
			}
		}
	}
	
	

}
