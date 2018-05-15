import java.util.ArrayList;

public class Territoire {
	
	private String nom = new String();
	
	private int nombreTroupesTotal = 1;
	
	private int nombreSoldats = 1;
	private int nombreCavaliers = 0;
	private int nombreCanons = 0;
	
	private Joueur proprietaire;
	
	private String[] territoiresAdjacents;
	
	private ArrayList<Unitee> listeUnitees = new ArrayList<Unitee>();
	
	ArrayList<Unitee> uniteCombat = new ArrayList<Unitee>();
	
	Jeu risk = Main.risk;
	
	
	/**
	 * Constructueur de l'objet Territoire
	 * @param String nom Nom du territoire
	 * @param Joueur proprietaire Proprietaire du territoire
	 * @param int nombreTroupesTotal Nombre de troupes sur le territoire
	 * @param String[] territoiresAdjacents Liste des territoires adjacents � celui-l�
	 * @param int nombreSoldats Nombre de soldats sur le territoire
	 * @param int nombreCavaliers Nombre de cavaliers sur le territoire
	 * @param int nombreCanons Nombre de canons sur le territoire
	 * @param ArrayList<Unitee> listeUnitees liste des unit�s sur le territoire
	 */
	public Territoire(String nom, String[] territoiresAdjacents) {
		this.nom = nom;
		this.territoiresAdjacents = territoiresAdjacents;
	}
	
	
	//VERIFICATIONS
	/**
	 * Permet de savoir si un territoire peut attaquer ou pas
	 * @return true ou false s'il peut ou non attaquer
	 */
	public boolean peutAttaquerOuDeplacer()
	{
		if((this.nombreSoldats + this.nombreCanons + this.nombreCavaliers < 2) || this.unitesPeuventAttaquer() == false)
		{
			return false;
		}
		else
		{
			return true;
		}		
	}

	
	/**
	 * Verifie si au moins une unit� peut, en cas de victoire, se d�placer sur le territoire conqui avant de lancer l'attaque
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
	 * Permet de savoir si un territoire est conquis � l'issue d'un combat
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
	 * Permet de v�rifier qu'un territoire appartient � un joueur donn�
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
	//VERIFICATIONS
	
	
	/**
	 * Remet les troupes sur le territoire apr�s un combat
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
	
	

	
	//ATTAQUE
	/**
	 * Permet de savoir combien de d�s seront utilis�s en attaque
	 * @return le nombre de d�s utilis�s en attaque
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
	 * Permet de savoir combien de d�s seront utilis�s en d�fense
	 * @return le nombre de d�s utilis�s en d�fense
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
	
	
	
	/**
	 * Lance une attaque
	 * @param territoireDEF
	 */
	public void attaque(Territoire territoireDEF)
	{
		//On determine le nombre de troupes qui vont d�fendre
		int nombreDesDEF = territoireDEF.nombreDesDEF();
	
		//On determine quelles troupes en fonction de leur priorit�
		territoireDEF.listeUniteeDEF(nombreDesDEF);
		
		//On lance les d�s
		this.lanceDes();
		territoireDEF.lanceDes();
		
		//On tri le lancer par ordre d�croissant
		this.triDes();
		territoireDEF.lanceDes();
		
		//On check si on doit g�rer les priorit�s
		this.prioriteATT();
		territoireDEF.prioriteDEF();
		
		//Resultat du combat
		this.gagnant(territoireDEF);
		
		
		//On met � jour les variables (unit�s et propri�taire)
		this.miseAJourProprietaire(territoireDEF);
	}
	
	
	/**
	 * D�finit la liste des unit�s qui vont d�fendre
	 * @param nombreDesDEF nombre de des en d�fense
	 * @return la liste
	 */
	public void listeUniteeDEF(int nombreDesDEF)
	{
		int soldats = this.nombreSoldats;
		int canons = this.nombreCanons;
		int cavaliers = this.nombreCavaliers;
		
		for(int i = 0;i<nombreDesDEF;i++)
		{
			if(soldats > 0)
			{
				this.ajouterUniteListe(0,0);
				soldats--;
			}
			else if(canons > 0)
			{
				this.ajouterUniteListe(2,0);
				canons--;
			}
			else if(cavaliers > 0)
			{
				this.ajouterUniteListe(1,0);
				cavaliers--;
			}
		}

	}

	
	/**
	 * Permet de r�aliser n lancement de des
	 * @param nombreDes int le nombre de des qu'on veut lancer
	 * @return une liste avec les r�sultats
	 */
	public void lanceDes()
	{
		for(int i = 0;i<this.uniteCombat.size();i++)
		{
			Des de = new Des(this.uniteCombat.get(i).getpMin(),this.uniteCombat.get(i).getpMax());	
			int random = de.lanceDes();
			this.uniteCombat.get(i).setScoreDES(random);
		}
	}
	
	
	/**
	 * Tri une liste de d�s dans l'ordre d�croissant
	 * @param listeDes ArrayList<Integer>  la liste � trier
	 * @return la liste tri�e dans l'ordre croissant
	 */
	public void triDes()
	{
		for(int i = 0;i < this.uniteCombat.size();i++)
		{
			for(int j = 0;j < this.uniteCombat.size();j++)
			{
				if(this.uniteCombat.get(i).getScoreDES() > this.uniteCombat.get(j).getScoreDES())
				{
					Unitee pivot = this.uniteCombat.get(i);
					
					this.uniteCombat.set(i, this.uniteCombat.get(j));
					this.uniteCombat.set(j, pivot);
				}
			}
		}
	}

	
	/**
	 * Trie la liste des unit�s d'attaque en cas d'�galit� en fonction des priorit�s
	 * @param liste
	 */
	public void prioriteATT()
	{
		for(int i = 0; i < this.uniteCombat.size();i++)
		{
			for(int j = 0;j < this.uniteCombat.size();j++)
			{
				if(this.uniteCombat.get(i).getpATT() < this.uniteCombat.get(j).getpATT() && this.uniteCombat.get(i).getScoreDES() == this.uniteCombat.get(j).getScoreDES())
				{
					Unitee pivot = this.uniteCombat.get(i);
					
					this.uniteCombat.set(i, this.uniteCombat.get(j));
					this.uniteCombat.set(j, pivot);
				}
			}
		}
	}
	
	
	/**
	 * Trie la liste des unit�s d'attaque en cas d'�galit� en fonction des priorit�s
	 * @param liste
	 */
	public void prioriteDEF()
	{
		for(int i = 0; i < this.uniteCombat.size();i++)
		{
			for(int j = 0;j < this.uniteCombat.size();j++)
			{
				if(this.uniteCombat.get(i).getpDEF() < this.uniteCombat.get(j).getpDEF() && this.uniteCombat.get(i).getScoreDES() == this.uniteCombat.get(j).getScoreDES())
				{
					Unitee pivot = this.uniteCombat.get(i);
					
					this.uniteCombat.set(i, this.uniteCombat.get(j));
					this.uniteCombat.set(j, pivot);
				}
			}
		}
	}
	
	
	/**
	 * Effectue la comparaison des valeurs des d�s
	 * @param listeDesATT les valeurs des d�s d'attaque
	 * @param listeDesDEF les valeurs des d�s de d�fense
	 * @param territoireDEF le territoire qui se d�fend
	 * @return la liste des territoires qui ont gagn�
	 */
	public void gagnant(Territoire territoireDEF)
	{
		ArrayList<Territoire> listeGagnants = new ArrayList<Territoire>();
		
		int compare;
		
		if(this.uniteCombat.size() > territoireDEF.uniteCombat.size())
		{
			compare = territoireDEF.uniteCombat.size();
		}
		else
		{
			compare = this.uniteCombat.size();
		}
		
		for(int i = 0; i < compare;i++)
		{
			if(this.uniteCombat.get(i).getScoreDES() > territoireDEF.uniteCombat.get(i).getScoreDES())
			{
				listeGagnants.add(this);
				territoireDEF.miseAJourUnitee(territoireDEF.uniteCombat.get(i));
			}
			else
			{
				listeGagnants.add(territoireDEF);
				this.miseAJourUnitee(this.uniteCombat.get(i));
			}
		}
		
		risk.listeGagnants = listeGagnants;
	}
	
	
	
	/**
	 * Met � jour l'affichage et le nombre d'unit�s
	 * @param Unitee unitee
	 * @param Territoire territoire
	 */
	public void miseAJourUnitee(Unitee unitee)
	{
		if(unitee.getType() == 0)
		{
			this.supprimerUniteAleat(0, 0);
			
			this.ajouterSoldats(-1);
			this.ajouterTroupe(-1);
		}
		else if(unitee.getType() == 1)
		{
			this.supprimerUniteAleat(1, 0);
			
			this.ajouterCavaliers(-1);
			this.ajouterTroupe(-3);
		}
		else if(unitee.getType() == 2)
		{
			this.supprimerUniteAleat(2, 0);
			
			this.ajouterCanons(-1);
			this.ajouterTroupe(-7);
		}
	}
	
	
	/**
	 * Met � jour le proprietaire d'un territoire
	 * @param territoireATT le territoire qui attaque
	 * @param territoireDEF territoire qui �tait attaqu�
	 */
	public void miseAJourProprietaire(Territoire territoireDEF)
	{	
		if(territoireDEF.estConquis())
		{
			territoireDEF.changeProprietaire(this);
			
		}
	}
	//ATTAQUE

	
	//MODIFICATIONS
	/**
	 * Deploie des toupes sur le territoire s�l�ctionn�
	 */
	public void deploiement(Joueur joueur)
	{
		if(joueur.getNombreCanonsDeploiement() > 0)
		{
			this.ajouterUniteTerritoire(new Unitee(2,4,9,3,2,1));//Canon
			
			this.ajouterTroupe(7);
			this.ajouterCanons(1);
			
			joueur.setNombreCanonsDeploiement(joueur.getNombreCanonsDeploiement() - 1);
		}
		else if(joueur.getNombreCavaliersDeploiement() > 0)
		{
			this.ajouterUniteTerritoire(new Unitee(1,2,7,1,3,3));//Cavalier
			
			this.ajouterTroupe(3);
			this.ajouterCavaliers(1);
			joueur.setNombreCavaliersDeploiement(joueur.getNombreCavaliersDeploiement() - 1);
		}
		else
		{
			this.ajouterUniteTerritoire(new Unitee(0,1,6,2,1,2));//Soldat
			
			this.ajouterTroupe(1);
			this.ajouterSoldats(1);
			joueur.setNombreSoldatsDeploiement(joueur.getNombreSoldatsDeploiement() - 1);
		}
	}
	
	
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
	public void ajouterUniteTerritoire(Unitee unite)
	{
		this.listeUnitees.add(unite);
	}
	
	
	/**
	 * Ajoute des unit�s dans la liste
	 * @param type le type d'unit�
	 * @param rang 0 par d�faut
	 * @param listeUniteeDEF
	 * @param territoireDEF
	 * @return l'unit� � ajouter
	 */
	public void ajouterUniteListe(int type,int rang)
	{
		if(this.uniteCombat.size() == 0 && this.listeUnitees.get(rang).getType() == type)
		{
			this.uniteCombat.add(this.listeUnitees.get(rang));
		}
		else if(this.listeUnitees.get(rang).getType() == type && this.uniteCombat.contains(this.listeUnitees.get(rang)) == false)
		{
			this.uniteCombat.add(this.listeUnitees.get(rang));
		}
		else
		{
			ajouterUniteListe(type, rang+1);
		}
	}
		

	
	
	
	/**
	 * Supprime al�atoirement une unit� sur le territoire
	 * @param int type Type de l'unit� qu'on supprime
	 * @param int rang 0 par d�faut
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
	 * Suppprimer une unitee pr�cise
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
	 * Renvoit une unit�e qui peut se d�placer
	 * @param int type Le type de l'unit�
	 * @param int rang 0 par d�faut
	 * @return l'unit� ou null si aucune ne peut se d�placer
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
	 * Remet � z�ro les d�placement des unit�s sur un territoire
	 */
	public void resetDeplacements()
	{
		for(int i = 0;i<this.listeUnitees.size();i++)
		{
			this.listeUnitees.get(i).resetDeplacement();
		}
	}

	
	/**
	 * Passe le territoire conquis sous le controle du propri�taire du territoire vainqueur
	 * @param territoireVainqueur territoire de l'attaquant
	 */
	public void changeProprietaire(Territoire territoireVainqueur)
	{
		this.proprietaire.ajouterTerritoire(-1);
		this.proprietaire.supprimerTerritoireControle(this);
		
		territoireVainqueur.proprietaire.ajouterTerritoire(1);
		territoireVainqueur.proprietaire.ajouterTerritoireControle(this);
		
		this.proprietaire = territoireVainqueur.proprietaire;
	}
	
	
	/**
	 * 
	 * @param territoire2
	 * @param deplacement
	 */
	public void deplacement(Territoire territoire2, Unitee deplacement)
	{
		this.supprimerUnite(deplacement,0);
		deplacement.setNombreDeplacement(deplacement.getNombreDeplacement() + 1);
		territoire2.ajouterUniteTerritoire(deplacement);
		
		if(deplacement.getType() == 0)
		{
			this.ajouterTroupe(-1);
			this.ajouterSoldats(-1);
			territoire2.ajouterTroupe(1);
			territoire2.ajouterSoldats(1);
		}
		else if(deplacement.getType()  == 1)
		{
			this.ajouterTroupe(-3);
			this.ajouterCavaliers(-1);
			territoire2.ajouterTroupe(3);
			territoire2.ajouterCavaliers(1);
		}
		else if(deplacement.getType() == 2)
		{
			this.ajouterTroupe(-7);
			this.ajouterCanons(-1);
			territoire2.ajouterTroupe(7);
			territoire2.ajouterCanons(1);
		}
		
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
	
	public boolean appartientAuContinent(Continent continent)
	{
		for(int i = 0;i < continent.getTerritoiresDuContinent().size();i++)
		{
			if(this.equals(continent.getTerritoiresDuContinent().get(i)))
			{
				return true;
			}
		}
		
		return false;
	}

	
	public int rapportUnit�s(Territoire territoireDEF)
	{
		int force = 0;
		
		if(this.nombreCanons > territoireDEF.nombreCanons)
		{
			force++;
		}
		if(this.nombreCavaliers > territoireDEF.nombreCavaliers)
		{
			force++;
		}
		if(this.nombreSoldats > territoireDEF.nombreSoldats)
		{
			force++;
		}
	
		return force;
	}
	
	//IA

	
	
	
	
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
