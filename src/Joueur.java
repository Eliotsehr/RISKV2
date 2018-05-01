
public class Joueur {
	
	private String nom = new String();
	
	private int nombreDeTerritoires;
	private int numeroDeJoueur;
	private int nombreTerritoiresCaptures;
	
	private Mission mission;
	
	

	/**
	 * Constructeur de l'objet joueur 
	 * @param String nom Le nom du joueur
	 * @param int nombreDeTerritoires Le nombre de territoires controlés par le joueur
	 * @param int numeroDeJoueur L'index du joueur
	 * @param int nombreTerritoiresCaptures Le nombre de territoires capturés par le joueur au tour précédent
	 * @param Mission mission La mission à effectuer par le joueur
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
	//MODIFICATIONS

	
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
