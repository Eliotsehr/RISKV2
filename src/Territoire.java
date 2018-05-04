import java.util.ArrayList;

public class Territoire {
	

	
	private String nom = new String();
	
	private int nombreTroupesTotal;
	private int nombreSoldats;
	private int nombreCavaliers;
	private int nombreCanons;
	
	private Joueur proprietaire;
	
	private String[] territoiresAdjacents;
	
	private ArrayList<Unitee> listeUnitees;
	
	Jeu risk = Main.risk;
	
	
	/**
	 * Constructueur de l'objet Territoire
	 * @param String nom Nom du territoire
	 * @param Joueur proprietaire Proprietaire du territoire
	 * @param int nombreTroupesTotal Nombre de troupes sur le territoire
	 * @param String[] territoiresAdjacents Liste des territoires adjacents à celui-là
	 * @param int nombreSoldats Nombre de soldats sur le territoire
	 * @param int nombreCavaliers Nombre de cavaliers sur le territoire
	 * @param int nombreCanons Nombre de canons sur le territoire
	 * @param ArrayList<Unitee> listeUnitees liste des unités sur le territoire
	 */
	public Territoire(String nom,Joueur proprietaire, int nombreTroupesTotal, String[] territoiresAdjacents, int nombreSoldats, int nombreCavaliers, int nombreCanons, ArrayList<Unitee> listeUnitees) {
		this.nom = nom;
		this.nombreTroupesTotal = nombreTroupesTotal;
		this.proprietaire = proprietaire;
		this.territoiresAdjacents = territoiresAdjacents;
		
		this.nombreSoldats = nombreSoldats;
		this.nombreCavaliers = nombreCavaliers;
		this.nombreCanons =nombreCanons;
		
		this.listeUnitees = listeUnitees;
	}
	
	
	//VERIFICATIONS
	/**
	 * Permet de savoir si un territoire peut attaquer ou pas
	 * @return true ou false s'il peut ou non attaquer
	 */
	public boolean peutAttaquer()
	{
		if((this.nombreSoldats + this.nombreCanons + this.nombreCavaliers < 2) && this.unitesPeuventAttaquer())
		{
			return false;
		}
		else
		{
			return true;
		}		
	}

	
	/**
	 * Verifie si au moins une unité peut, en cas de victoire, se déplacer sur le territoire conqui avant de lancer l'attaque
	 * @return true si elles peuvent, false sinon
	 */
	public boolean unitesPeuventAttaquer()
	{
		for(int i = 0;i < this.listeUnitees.size(); i++)
		{
			if(this.listeUnitees.get(i).peutDeplacer())
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * Permet de savoir si un territoire est conquis à l'issue d'un combat
	 * @return true ou false s'il est conquis ou non
	 */
	public boolean estConquis()
	{
		if(this.nombreSoldats + this.nombreCanons + this.nombreCavaliers == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * Permet de vérifier qu'un territoire appartient à un joueur donné
	 * @param joueur
	 * @return true s'il appartient false sinon
	 */
	public boolean appartientA(Joueur joueur)
	{
		if(this.proprietaire == joueur)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * Permet de savoir si deux territoires sont adjacents
	 * @param territoire
	 * @return true s'ils sont adjacents false sinon
	 */
	public boolean estAdjacentA(Territoire territoire)
	{
		for(int i = 0;i < this.territoiresAdjacents.length;i++)
		{
			if(this.territoiresAdjacents[i].equals(territoire.getNom()))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remet les troupes sur le territoire après un combat
	 */
	public void resetTroupes()
	{
		this.nombreSoldats = 0;
		this.nombreCavaliers = 0;
		this.nombreCanons = 0;
		
		for(int i = 0; i<this.listeUnitees.size();i++)
		{
			if(this.listeUnitees.get(i).getType() == 0)
			{
				this.nombreSoldats++;
			}
			else if(this.listeUnitees.get(i).getType() == 1)
			{
				this.nombreCavaliers++;
			}
			if(this.listeUnitees.get(i).getType() == 2)
			{
				this.nombreCanons++;
			}
		}
	}
	//VERIFICATIONS
	
	public void afficheUnitee()
	{
		for(int i = 0; i < this.listeUnitees.size();i++)
		{
			System.out.println(this.listeUnitees.get(i));
		}
	}
	
	//ATTAQUE
	/**
	 * Permet de savoir combien de dés seront utilisés en attaque
	 * @return le nombre de dés utilisés en attaque
	 */
	public int nombreDesATT()
	{
		if(this.nombreSoldats + this.nombreCanons + this.nombreCavaliers == 2)
		{
			return 1;
		}
		else if(this.nombreSoldats + this.nombreCanons + this.nombreCavaliers == 3)
		{
			return 2;
		}
		else
		{
			return 3;
		}
	}

	
	/**
	 * Permet de savoir combien de dés seront utilisés en défense
	 * @return le nombre de dés utilisés en défense
	 */
	public int nombreDesDEF()
	{
		if(this.nombreSoldats + this.nombreCanons + this.nombreCavaliers == 1)
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}
	//ATTAQUE

	
	//MODIFICATIONS
	/**
	 * Permet d'ajouter des troupes sur un territoire
	 * @param nombreTroupes le nombre de troupes qu'on veut ajouter
	 */
	public void ajouterTroupe(int nombreTroupes)
	{
		this.nombreTroupesTotal = this.nombreTroupesTotal + nombreTroupes;
	}

	/**
	 * Permet d'ajouter des soldats sur un territoire
	 * @param nombreSoldats
	 */ 
	public void ajouterSoldats(int nombreSoldats)
	{
		this.nombreSoldats = this.nombreSoldats + nombreSoldats;
	}
	
	
	/**
	 * Permet d'ajouter des Cavaliers sur un territoire
	 * @param nombreCavaliers
	 */ 
	public void ajouterCavaliers(int nombreCavaliers)
	{
		this.nombreCavaliers = this.nombreCavaliers + nombreCavaliers;
	}
	
	
	/**
	 * Permet d'ajouter des Canons sur un territoire
	 * @param nombreCavaliers
	 */ 
	public void ajouterCanons(int nombreCanons)
	{
		this.nombreCanons = this.nombreCanons + nombreCanons;
	}
	
	
	/**
	 * Ajoute une unite sur un territoire
	 * @param unite
	 */
	public void ajouterUnite(Unitee unite)
	{
		this.listeUnitees.add(unite);
	}
	
	/**
	 * Supprime aléatoirement une unité sur le territoire
	 * @param int type Type de l'unité qu'on supprime
	 * @param int rang 0 par défaut
	 */
	public void supprimerUniteAleat(int type, int rang)
	{
		if(this.listeUnitees.get(rang).getType() == type)
		{
				this.listeUnitees.remove(rang);
		}
		else
		{
			supprimerUniteAleat(type,rang+1);
		}
		
	}
	
	/**
	 * Suppprimer une unitee précise
	 * @param unitee
	 * @param rang
	 */
	public void supprimerUnite(Unitee unitee, int rang)
	{
		if(this.listeUnitees.get(rang).equals(unitee))
		{
			this.listeUnitees.remove(rang);
		}
		else
		{
			supprimerUnite(unitee,rang+1);
		}
	}

	
	/**
	 * Renvoit une unitée qui peut se déplacer
	 * @param int type Le type de l'unité
	 * @param int rang 0 par défaut
	 * @return l'unité ou null si aucune ne peut se déplacer
	 */
	public Unitee uniteDeplacement(int type, int rang)
	{
		if(rang == this.listeUnitees.size())
		{
			return null;
		}
		else if(this.listeUnitees.get(rang).getType() == type && this.listeUnitees.get(rang).peutDeplacer())
		{
			return this.listeUnitees.get(rang);
		}
		else
		{
			return uniteDeplacement(type,rang+1);
		}
	}
	

	/**
	 * Remet à zéro les déplacement des unités sur un territoire
	 */
	public void resetDeplacements()
	{
		for(int i = 0;i<this.listeUnitees.size();i++)
		{
			this.listeUnitees.get(i).resetDeplacement();
		}
	}

	
	/**
	 * Passe le territoire conquis sous le controle du propriétaire du territoire vainqueur
	 * @param territoireVainqueur territoire de l'attaquant
	 */
	public void changeProprietaire(Territoire territoireVainqueur)
	{
		this.proprietaire.ajouterTerritoire(-1);
		territoireVainqueur.proprietaire.ajouterTerritoire(1);
		this.proprietaire = territoireVainqueur.proprietaire;
		
	}
	//MODIFICATIONS
	
	
	//IA
	public boolean estEntoure()
	{
		for(int i = 0;i<this.territoiresAdjacents.length;i++)
		{
			if(retrouverAvecNom(this.territoiresAdjacents[i]).getProprietaire() != this.getProprietaire())
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	public Territoire retrouverAvecNom(String nom)
	{
		for(int i = 0;i < risk.listeTerritoires.size();i++)
		{
			if(risk.listeTerritoires.get(i).getNom() == nom)
			{
				return risk.listeTerritoires.get(i);
			}
		}
		
		return null;
	}
	
	//Getters Setters
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Joueur getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}


	public int getNombreTroupesTotal()
	{
		return this.nombreTroupesTotal;
	}
	
	public void setNombreTroupesTotal(int nombreTroupesTotal)
	{
		this.nombreTroupesTotal = nombreTroupesTotal;
	}



	public String[] getTerritoiresAdjacents() {
		return territoiresAdjacents;
	}



	public void setTerritoiresAdjacents(String[] territoiresAdjacents) {
		this.territoiresAdjacents = territoiresAdjacents;
	}


	public int getNombreSoldats() {
		return nombreSoldats;
	}


	public void setNombreSoldats(int nombreSoldats) {
		this.nombreSoldats = nombreSoldats;
	}


	public int getNombreCavaliers() {
		return nombreCavaliers;
	}


	public void setNombreCavaliers(int nombreCavaliers) {
		this.nombreCavaliers = nombreCavaliers;
	}


	public int getNombreCanons() {
		return nombreCanons;
	}


	public void setNombreCanons(int nombreCanons) {
		this.nombreCanons = nombreCanons;
	}


	public ArrayList<Unitee> getListeUnitees() {
		return listeUnitees;
	}


	public void setListeUnitees(ArrayList<Unitee> listeUnitees) {
		this.listeUnitees = listeUnitees;
	}

}
