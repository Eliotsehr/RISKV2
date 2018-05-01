
import java.util.ArrayList;
// pas a pas jme dis c'est pas pas vrai
public class Continent {

	private int continent;
	private ArrayList<Territoire> territoiresDuContinent;

/**
 *
 * @param continent numero permettant d'identifier (comme un nom mais plus simple)
 * @param territoiresDuContinent liste des territoires sur le continent
 */
	public Continent(int continent,ArrayList<Territoire> territoiresDuContinent) {
		this.continent = continent;
		this.territoiresDuContinent = territoiresDuContinent;
	}


	//VERIFICATIONS
	/**
	 * Permet de savoir si un joueur controle un continent ou non
	 *
	 * @param joueur le joueur que l'on veut tester
	 * @param rangTerritoire le rang de depart (toujours zero)
	 * @return true ou false selon si le joueur controle le continent ou non
	 */
	public boolean estControlePar(Joueur joueur, int rangTerritoire)
	{
		for(int i = 0;i<this.territoiresDuContinent.size();i++)
		{
			if(this.territoiresDuContinent.get(i).getProprietaire() != joueur)
			{
				return false;
			}
		}

		return true;
	}
	//VERIFICATIONS


	//Getters Setters
	public ArrayList<Territoire> getTerritoiresDuContinent() {
		return territoiresDuContinent;
	}
	public void setTerritoiresDuContinent(ArrayList<Territoire> listeTerritoires) {
		this.territoiresDuContinent = listeTerritoires;
	}

	public int getContinent() {
		return continent;
	}

	public void setContinent(int continent) {
		this.continent = continent;
	}

}
