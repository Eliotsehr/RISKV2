//Code Eliot Sadrin

package riskEliotRomain;
import java.util.ArrayList;

public class Joueur {
	
	private String nom = new String();
	
	private int nombreDeTerritoires;
	private int numeroDeJoueur;
	private int nombreTerritoiresCaptures;
	
	private Mission mission;
	
	private int nombreTroupesDeploiement;
	
	private int nombreSoldatsDeploiement;
	private int nombreCavaliersDeploiement;
	private int nombreCanonsDeploiement;
	
	public ArrayList<Territoire> listeTerritoiresControles = new ArrayList<Territoire>();

	/**
	 * Constructeur de l'objet joueur 
	 * @param String nom Le nom du joueur
	 * @param int numeroDeJoueur L'index du joueur
	 */
 	public Joueur(String nom,int numeroDeJoueur) {
		this.nom = nom;
		this.numeroDeJoueur = numeroDeJoueur;
	}

	
	//VERIFICATIONS
	/**
	 * Permet de savoir si le joueur a perdu
	 * @return true ou false selon si le joueur à perdu
	 */
	public boolean aPerdu()
	{
		if(this.nombreDeTerritoires == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Met fin à la partie si un joueur a terminé sa mission
	 * @param Joueur joueur
	 */
	public void aComplteSaMission()
	{
		if(this.mission.estComplete(this))
		{
			Main.jeu = false;
		}
	}
	//VERIFICATIONS
	

	//MODIFICATIONS
	/**
	 * Ajoute un territoire au nombre total de territoires controlés par le joueur
	 * @param nombreTerritoire nombre qu'on veut ajouter au total
	 */
	public void ajouterTerritoire(int nombreTerritoire)
	{
		this.nombreDeTerritoires = this.nombreDeTerritoires + nombreTerritoire;
	}
	
	/**
	 * Ajouter un territoire au nombre total de territoires capturé par le joueur ce tour ci
	 */
	public void ajouterTerritoireCapture()
	{
		this.nombreTerritoiresCaptures++;
	}
	
	/**
	 * Remet à zero le nombre de territoires capturés au début du tour
	 */
	public void resetTerritoireCapture()
	{
		this.nombreTerritoiresCaptures = 0;
	}
	
	/**
	 * Attribue au joueur un mission
	 * @param mission
	 */
	public void definirMission(Mission mission)
	{
		this.mission = mission;
	}
	
	/**
	 * Ajouter un territoire de la liste des territoires controler par le joueur
	 * @param territoire le territoire qu'on veut ajouter
	 */
	public void ajouterTerritoireControle(Territoire territoire)
	{
		this.listeTerritoiresControles.add(territoire);
	}
	
	/**
	 * Supprimer un territoire de la liste des territoires controler par le joueur
	 * @param territoire le territoire qu'on veut supprimer
	 */
	public void supprimerTerritoireControle(Territoire territoire)
	{
		this.listeTerritoiresControles.remove(territoire);
	}
	//MODIFICATIONS

	
	//RENFORT
	/**
	 * Renvoit le nombre de troupes que le joueur doit recevoir
	 * @param joueur joueur pour lequel on veut savoir le nombre de troupe qu'il obtient au début du tour
	 * @param type type d'apport, 0 début de partie 1 jeu
	 * @return nombre de troupes
	 */
	public void calculerNombreTroupesDeploiement(boolean debut)
	{
		if(debut)//Debut de partie
		{
			if(Main.risk.listeJoueurs.size() == 2)
			{
				this.setNombreTroupesDeploiement(19);
			}
			else if(Main.risk.listeJoueurs.size() == 3)
			{
				this.setNombreTroupesDeploiement(21);
			}
			else if(Main.risk.listeJoueurs.size() == 4)
			{
				this.setNombreTroupesDeploiement(20);
			}
			else if(Main.risk.listeJoueurs.size() == 5)
			{
				this.setNombreTroupesDeploiement(17);
			}
			else
			{
				this.setNombreTroupesDeploiement(13);
			}
		}
		else
		{

			this.setNombreTroupesDeploiement((int) (Math.floor(this.nombreDeTerritoires/3) + this.calculerBonusContinent() + this.calculerBonusCapture()));
			
			
		}
	}
	
	/**
	 * Donne un bonus de troupe en cas de controle d'un continent
	 * @param joueur le joueur auquel on donne le bonus
	 * @return le bonus (int)
	 */
	public int calculerBonusContinent()
	{
		int bonus = 0;
		
		for(int i = 0; i < Main.risk.listeContinents.size();i++)
		{
			if(Main.risk.listeContinents.get(i).estControlePar(this, 0))
			{
				if(i == 0)
				{
					bonus = bonus + 3;
				}
				else if(i == 1)
				{
					bonus = bonus + 3;
				}
				else if(i == 2)
				{
					bonus = bonus + 6;
				}
				else if(i == 3)
				{
					bonus = bonus + 5;
				}
				else if(i == 4)
				{
					bonus = bonus + 2;
				}
				else
				{
					bonus = bonus + 2;
				}
				
			}
		}
		
		return bonus;
	}

	/**
	 * Bonus des territoires capturés au tour précédent
	 * @param Joueur joueur
	 * @return le bonus
	 */
	public int calculerBonusCapture()
	{
		int bonus = 0;
		
		for(int i = 0;i<this.nombreTerritoiresCaptures;i++)
		{
			bonus = bonus + (int) (Math.random() * 2 - 0) + 0;
		}
		
		return bonus;
	}
	//RENFORT
	
	
	//Getters Setters
	public String getNom() {
		return nom;
	}

	public int getNumeroDeJoueur() {
		return numeroDeJoueur;
	}

	public int getNombreDeTerritoires()
	{
		return this.nombreDeTerritoires;
	}
	
	public int getNombreTerritoiresCaptures() {
		return nombreTerritoiresCaptures;
	}

	public Mission getMission() {
		return mission;
	}

	public int getNombreTroupesDeploiement() {
		return nombreTroupesDeploiement;
	}

	public void setNombreTroupesDeploiement(int nombreTroupesDeploiement) {
		this.nombreTroupesDeploiement = nombreTroupesDeploiement;
	}

	public int getNombreSoldatsDeploiement() {
		return nombreSoldatsDeploiement;
	}

	public void setNombreSoldatsDeploiement(int nombreSoldatsDeploiement) {
		this.nombreSoldatsDeploiement = nombreSoldatsDeploiement;
	}

	public int getNombreCavaliersDeploiement() {
		return nombreCavaliersDeploiement;
	}

	public void setNombreCavaliersDeploiement(int nombreCavaliersDeploiement) {
		this.nombreCavaliersDeploiement = nombreCavaliersDeploiement;
	}

	public int getNombreCanonsDeploiement() {
		return nombreCanonsDeploiement;
	}

	public void setNombreCanonsDeploiement(int nombreCanonsDeploiement) {
		this.nombreCanonsDeploiement = nombreCanonsDeploiement;
	}

	public ArrayList<Territoire> getListeTerritoiresControles() {
		return listeTerritoiresControles;
	}

}