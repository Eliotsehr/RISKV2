import java.util.ArrayList;

public class IA extends Joueur{

	Jeu risk = Main.risk;
	
	private ArrayList<Territoire> listeTerritoiresControles;
	
	public IA(String nom, int nombreDeTerritoires, int numeroDeJoueur, int nombreTerritoiresCaptures, Mission mission,ArrayList<Territoire> listeTerritoiresControles) {
		super(nom, nombreDeTerritoires, numeroDeJoueur, nombreTerritoiresCaptures, mission);
		
		this.listeTerritoiresControles = listeTerritoiresControles;
	}

	
	
	
	//Comparer unités 
	/**
	 * Attaque les territoires plus fabile que lui
	 */
	public Territoire attaqueBasique()
	{
		for(int i = 0; i < this.listeTerritoiresControles.size();i++)
		{
			if(this.listeTerritoiresControles.get(i).peutAttaquer())
			{
				for(int j = 0;j < this.listeTerritoiresControles.get(i).getTerritoiresAdjacents().length;j++)
				{
					if(listeTerritoiresControles.get(i).retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]).appartientA(this) == false)
					{
						return listeTerritoiresControles.get(i).retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]);
					}
				}
			}
		}
		
		return null;
	}
	
	
	//Divers
	public boolean peutAttaquer()
	{
		for(int i = 0; i < this.listeTerritoiresControles.size();i++)
		{
			if(this.listeTerritoiresControles.get(i).peutAttaquer())
			{
				return true;
			}
		}
		
		return false;
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




	public ArrayList<Territoire> getListeTerritoiresControles() {
		return listeTerritoiresControles;
	}




	public void setListeTerritoiresControles(ArrayList<Territoire> listeTerritoiresControles) {
		this.listeTerritoiresControles = listeTerritoiresControles;
	}
	

}
