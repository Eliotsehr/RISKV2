
public class Joueur {
	
	private String nom = new String();
	
	private int nombreDeTerritoires;
	private int numeroDeJoueur;
	private int nombreTerritoiresCaptures;
	
	private Mission mission;
	
	private Jeu risk = Main.risk;
	
	public int nombreTroupesDeploiement;
	
	public int nombreSoldatsDeploiement;
	public int nombreCavaliersDeploiement;
	public int nombreCanonsDeploiement;

	

	/**
	 * Constructeur de l'objet joueur 
	 * @param String nom Le nom du joueur
	 * @param int nombreDeTerritoires Le nombre de territoires control�s par le joueur
	 * @param int numeroDeJoueur L'index du joueur
	 * @param int nombreTerritoiresCaptures Le nombre de territoires captur�s par le joueur au tour pr�c�dent
	 * @param Mission mission La mission � effectuer par le joueur
	 */
 	public Joueur(String nom, int nombreDeTerritoires, int numeroDeJoueur, int nombreTerritoiresCaptures, Mission mission) {
		this.nom = nom;
		this.nombreDeTerritoires = nombreDeTerritoires;
		this.numeroDeJoueur = numeroDeJoueur;
		this.nombreTerritoiresCaptures = nombreTerritoiresCaptures;
		this.mission = mission;
	}

	
	//VERIFICATIONS
	/**
	 * Permet de savoir si le joueur a perdu
	 * @return true ou false selon si le joueur � perdu
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
	 * Met fin � la partie si un joueur a termin� sa mission
	 * @param Joueur joueur
	 */
	public void missionComplete()
	{
		if(this.mission.estComplete(this))
		{
			Main.jeu = false;
		}
	}
	//VERIFICATIONS
	

	//MODIFICATIONS
	/**
	 * Ajoute un territoire au nombre total de territoires control�s par le joueur
	 * @param nombreTerritoire nombre qu'on veut ajouter au total
	 */
	public void ajouterTerritoire(int nombreTerritoire)
	{
		this.nombreDeTerritoires = this.nombreDeTerritoires + nombreTerritoire;
	}
	
	
	/**
	 * Ajouter un territoire au nombre total de territoires captur� par le joueur ce tour ci
	 */
	public void ajouterTerritoireCapture()
	{
		this.nombreTerritoiresCaptures++;
	}
	
	
	/**
	 * Remet � zero le nombre de territoires captur�s au d�but du tour
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
	//MODIFICATIONS

	
	//RENFORT
	/**
	 * Renvoit le nombre de troupes que le joueur doit recevoir
	 * @param joueur joueur pour lequel on veut savoir le nombre de troupe qu'il obtient au d�but du tour
	 * @param type type d'apport, 0 d�but de partie 1 jeu
	 * @return nombre de troupes
	 */
	public void combienTroupe(boolean debut)
	{
		if(debut)//Debut de partie
		{
			if(risk.listeJoueurs.size() == 2)
			{
				this.nombreTroupesDeploiement = 19;
			}
			else if(risk.listeJoueurs.size() == 3)
			{
				this.nombreTroupesDeploiement = 21;
			}
			else if(risk.listeJoueurs.size() == 4)
			{
				this.nombreTroupesDeploiement = 20;
			}
			else if(risk.listeJoueurs.size() == 5)
			{
				this.nombreTroupesDeploiement = 17;
			}
			else
			{
				this.nombreTroupesDeploiement = 13;
			}
		}
		else
		{
			this.nombreTroupesDeploiement = (int) (Math.floor(this.nombreDeTerritoires/3) + this.bonusContinent() + this.bonusCapture());
		}
	}
	
	
	/**
	 * Donne un bonus de troupe en cas de controle d'un continent
	 * @param joueur le joueur auquel on donne le bonus
	 * @return le bonus (int)
	 */
	public int bonusContinent()
	{
		int bonus = 0;
		
		for(int i = 0; i < risk.listeContinents.size();i++)
		{
			if(risk.listeContinents.get(i).estControlePar(this, 0))
			{
				if(i == 0)
				{
					bonus = bonus + 4;
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
	 * Bonus des territoires captur�s au tour pr�c�dent
	 * @param Joueur joueur
	 * @return le bonus
	 */
	public int bonusCapture()
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

	public void setNom(String nom) {
		this.nom = nom;
	}



	public int getNumeroDeJoueur() {
		return numeroDeJoueur;
	}



	public void setNumeroDeJoueur(int numeroDeJoueur) {
		this.numeroDeJoueur = numeroDeJoueur;
	}
	
	public int getNombreDeTerritoires()
	{
		return this.nombreDeTerritoires;
	}
	
	public void setNombreDeTerritoires(int nombreDeTerritoires)
	{
		this.nombreDeTerritoires = nombreDeTerritoires;
	}


	public int getNombreTerritoiresCaptures() {
		return nombreTerritoiresCaptures;
	}


	public void setNombreTerritoiresCaptures(int nombreTerritoiresCaptures) {
		this.nombreTerritoiresCaptures = nombreTerritoiresCaptures;
	}


	public Mission getMission() {
		return mission;
	}


	public void setMission(Mission mission) {
		this.mission = mission;
	}
}
