//Code Eliot Sadrin

package riskEliotRomain;
import java.util.ArrayList;

public class Mission {

	private String intitule;
	private int index;
	
	public Joueur joueurAEliminer;
	

	/**
	 * Constructeur de l'objet mission
	 * @param intitule String explication de la mission
	 * @param index int num�ro de la mission
	 */
	public Mission(String intitule, int index) {
		this.intitule = intitule;
		this.index = index;
	}


	//VERIFICATIONS
	/**
	 * Permet de savoir si un joueur a compl�t� une mission
	 * @param joueur le joueur sur lequeul on effectue le test
	 * @return true s'il a compl�t� la mission, false sinon
	 */
	public boolean estComplete(Joueur joueur)
	{
		if(joueur.getMission().index == 0)//Contr�ler 3 r�gions et au moins 18 territoires
		{
			ArrayList<Continent> continentsControles = new ArrayList<Continent>();
			
			for(int i = 0; i < Main.risk.listeContinents.size();i++)
			{
				if(Main.risk.listeContinents.get(i).estControlePar(joueur, 0))
				{
					continentsControles.add(Main.risk.listeContinents.get(i));
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
		else if(joueur.getMission().index == 1)//Contr�ler la plus grosse r�gion + 1 autre r�gion
		{
			ArrayList<Continent> continentsControles = new ArrayList<Continent>();
			
			for(int i = 0; i < Main.risk.listeContinents.size();i++)
			{
				if(Main.risk.listeContinents.get(i).estControlePar(joueur, 0))
				{
					continentsControles.add(Main.risk.listeContinents.get(i));
				}
			}
			
			if(Main.risk.listeContinents.get(2).estControlePar(joueur, 0) && continentsControles.size() >= 2)
			{
				return true;
			}
			else
			{
				
				return false;
			}
		}
		else if(joueur.getMission().index == 2)//Conqu�rir tous les territoires
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
		else if(joueur.getMission().index == 3)//Contr�ler 30 territoires
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
		else if(joueur.getMission().index == 4)//Detruire le joueur X
		{
			for(int i = 0;i < Main.risk.listeElimines.size();i++)
			{
				if(Main.risk.listeElimines.get(i).equals(joueurAEliminer))
				{
					return true;
				}
			}
			
			return false;
		}
		else if(joueur.getMission().index == 5)//Contr�ler 18 territoires avec au moins 2 arm�es
		{
			
			if(joueur.getNombreDeTerritoires() < 18)
			{
				return false;
			}
			else
			{
				int territoiresAvec2Troupes = 0;
				
				for(int i = 0; i < Main.risk.listeTerritoires.size();i++)
				{
					if(Main.risk.listeTerritoires.get(i).getProprietaire().equals(joueur) && (Main.risk.listeTerritoires.get(i).getNombreTroupesTotal() > 1))	
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
		
		
		else if(joueur.getMission().index == 6)//Contr�ler 24 territoires
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
		else//Contr�ler 21 territoires
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
	
	
	//DIVERS
	/**
	 * Utilis� pour la mission 4
	 * @param joueur
	 */
	public void definirJoueurAEliminer(Joueur joueur)
	{
		this.joueurAEliminer = joueur;
	}
	//DIVERS
	
	
	//Getter setters
	public String getIntitule() {
		return intitule;
	}

	public int getIndex()
	{
		return this.index;
	}
	
}