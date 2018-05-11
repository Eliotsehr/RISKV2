import java.util.ArrayList;

public class IA extends Joueur{

	Jeu risk = Main.risk;
	
	private ArrayList<Territoire> listeTerritoiresControles;
	
	public IA(String nom, int nombreDeTerritoires, int numeroDeJoueur, int nombreTerritoiresCaptures, Mission mission,ArrayList<Territoire> listeTerritoiresControles) {
		super(nom, nombreDeTerritoires, numeroDeJoueur, nombreTerritoiresCaptures, mission);
		
		this.listeTerritoiresControles = listeTerritoiresControles;
	}

	
	
	
	//Attaque
	
	public Territoire territoireQuiAttaque(int rang)
	{
		if(this.listeTerritoiresControles.get(rang).peutAttaquer() && this.listeTerritoiresControles.get(rang).estEntoure() == false)
		{
			return this.listeTerritoiresControles.get(rang);
		}
		else
		{
			rang++;
			return territoireQuiAttaque(rang);
		}
	}
	
	public Territoire territoireQuiDefend(Territoire territoire,int rang)
	{
		if(territoire.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]).appartientA(this) == false)
		{
			return territoire.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]);
		}
		else
		{
			rang++;
			return territoireQuiDefend(territoire,rang);
		}
	}
	
	public void choixUnitesAttaque(Territoire territoire)
	{
		Unitee uniteATT = new Unitee(0,0,0,0,0,0,0,0);
		int type = 0;
		for(int i = 0;i < territoire.getListeUnitees().size() || i < 3;i++)
		{
			for(int j = 0;j < territoire.getListeUnitees().size();j++)
			{
				if(territoire.getListeUnitees().get(j).getpMax() > uniteATT.getpMax())
				{
					uniteATT = territoire.getListeUnitees().get(j);
					type = uniteATT.getType();
				}
			}
			
			territoire.ajouterUniteListe(type, 0);
		}
	}
	//Divers
	/**
	 * Check si l'IA peut encore attaquer
	 * @return true si elle peut, false sinon
	 */
	public boolean peutAttaquer()
	{
		for(int i = 0; i < this.listeTerritoiresControles.size();i++)
		{
			if(this.listeTerritoiresControles.get(i).peutAttaquer() && this.listeTerritoiresControles.get(i).estEntoure() == false)
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
