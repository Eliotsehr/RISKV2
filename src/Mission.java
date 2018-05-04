import java.util.ArrayList;

public class Mission {

	private String intitule;
	private int index;
	
	private Jeu risk = Main.risk;
	
	public Joueur joueurAEliminer;
	
	
	/**
	 * Constructeur de l'objet mission
	 * @param intitule String explication de la mission
	 * @param index int numéro de la mission
	 */
	public Mission(String intitule, int index) {
		this.setIntitule(intitule);
		this.index = index;
	}

	/**
	 * Utilisé pour la mission 4
	 * @param joueur
	 */
	public void definirJoueurAEliminer(Joueur joueur)
	{
		this.joueurAEliminer = joueur;
	}
	
	//VERIFICATIONS
	/**
	 * Permet de savoir si un joueur a complété une mission
	 * @param joueur le joueur sur lequeul on effectue le test
	 * @return true s'il a complété la mission, false sinon
	 */
	public boolean estComplete(Joueur joueur)
	{
		if(this.index == 0)//Contrôler 3 régions et au moins 18 territoires
		{
			ArrayList<Continent> continentsControles = new ArrayList<Continent>();
			
			for(int i = 0; i < risk.listeContinents.size();i++)
			{
				if(risk.listeContinents.get(i).estControlePar(joueur, 0))
				{
					continentsControles.add(risk.listeContinents.get(i));
				}
			}
			
			if(continentsControles.size() == 3 && joueur.getNombreDeTerritoires() == 18)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(this.index == 1)//Contrôler la plus grosse région + 1 autre région
		{
			ArrayList<Continent> continentsControles = new ArrayList<Continent>();
			
			for(int i = 0; i < risk.listeContinents.size();i++)
			{
				if(risk.listeContinents.get(i).estControlePar(joueur, 0))
				{
					continentsControles.add(risk.listeContinents.get(i));
				}
			}
			
			if(risk.listeContinents.get(2).estControlePar(joueur, 0) && continentsControles.size() >= 2)
			{
				return true;
			}
			else
			{
				
				return false;
			}
		}
		else if(this.index == 2)//Conquérir tous les territoires
		{
			if(joueur.getNombreDeTerritoires() == 42)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(this.index == 3)//Contrôler 30 territoires
		{
			if(joueur.getNombreDeTerritoires() == 30)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(this.index == 4)//Detruire le joueur X
		{
			for(int i = 0;i < risk.listeElimines.size();i++)
			{
				if(risk.listeElimines.get(i).equals(joueurAEliminer))
				{
					return true;
				}
			}
			
			return false;
		}
		else if(this.index == 5)//Contrôler 18 territoires avec au moins 2 armées
		{
			
			if(joueur.getNombreDeTerritoires() < 18)
			{
				return false;
			}
			else
			{
				int territoiresAvec2Troupes = 0;
				
				for(int i = 0; i < risk.listeTerritoires.size();i++)
				{
					if(risk.listeTerritoires.get(i).getProprietaire().equals(joueur) && (risk.listeTerritoires.get(i).getNombreTroupesTotal() > 1))	
					{
						territoiresAvec2Troupes++;
					}
				}
				
				if(territoiresAvec2Troupes > 17)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		
		else if(this.index == 6)//Contrôler 24 territoires
		{
			if(joueur.getNombreDeTerritoires() == 24)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else//Contrôler 21 territoires
		{
			if(joueur.getNombreDeTerritoires() == 21)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		
	}
	//VERIFICATIONS
	
	
	//Getter setters
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	public int getIndex()
	{
		return this.index;
	}
	
}
