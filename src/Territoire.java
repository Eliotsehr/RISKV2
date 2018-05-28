import java.util.ArrayList;

public class Territoire {
	
	private String nom;

	private int nombreSoldats = 1;
	private int nombreCavaliers = 0;
	private int nombreCanons = 0;
	private int nombreTroupesATT;
	
	private Joueur proprietaire;
	
	private String[] territoiresAdjacents;
	
	public ArrayList<Unite> listeUnitees = new ArrayList<Unite>();
	
	ArrayList<Unite> uniteCombat = new ArrayList<Unite>();
	
	
	/**
	 * Constructueur de l'objet Territoire
	 * @param String nom Nom du territoire
	 * @param String[] territoiresAdjacents Liste des territoires adjacents à celui-là
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
	 * Verifie si sur le territoire au moins une troupe du type "type" peut attaquer
	 * @param type
	 * @return true si oui false sinon
	 */
	public boolean unitePeutAttaquer(int type)
	{
		for(int i = 0;i < this.listeUnitees.size(); i++)
		{
			if(this.listeUnitees.get(i).getType() == type && this.listeUnitees.get(i).peutDeplacer())
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
	//VERIFICATIONS
	
	
	//ATTAQUE
	/**
	 * Permet de savoir combien de dés seront utilisés en attaque
	 * @return le nombre de dés utilisés en attaque
	 */
	public int calculerNombreDesATT()
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
	public int calculerNombreDesDEF()
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
	public void attaquer(Territoire territoireDEF)
	{
		//On determine le nombre de troupes qui vont défendre
		int nombreDesDEF = territoireDEF.calculerNombreDesDEF();
	
		//On determine quelles troupes en fonction de leur priorité
		territoireDEF.choisirUniteDeCombatDEF(nombreDesDEF);
		
		//On lance les dés
		this.lancerDes();
		territoireDEF.lancerDes();
		
		//On tri le lancer par ordre décroissant
		this.trierDes();
		territoireDEF.lancerDes();
		
		//On check si on doit gérer les priorités
		this.trierParPrioriteATT();
		territoireDEF.trierParPrioriteDEF();
		
		//Resultat du combat
		this.determinerResultatCombat(territoireDEF);
		
		
		//On met à jour les variables (unités et propriétaire)
		this.majProprietaireTerritoire(territoireDEF);
	}
	
	/**
	 * Définit la liste des unités qui vont défendre
	 * @param nombreDesDEF nombre de des en défense
	 * @return la liste
	 */
	public void choisirUniteDeCombatDEF(int nombreDesDEF)
	{
		int soldats = this.nombreSoldats;
		int canons = this.nombreCanons;
		int cavaliers = this.nombreCavaliers;
		
		for(int i = 0;i<nombreDesDEF;i++)
		{
			if(soldats > 0)
			{
				this.ajouterUniteCombat(0,0);
				soldats--;
			}
			else if(canons > 0)
			{
				this.ajouterUniteCombat(2,0);
				canons--;
			}
			else if(cavaliers > 0)
			{
				this.ajouterUniteCombat(1,0);
				cavaliers--;
			}
		}

	}

	/**
	 * Permet de réaliser n lancement de des
	 * @param nombreDes int le nombre de des qu'on veut lancer
	 * @return une liste avec les résultats
	 */
	public void lancerDes()
	{
		for(int i = 0;i<this.uniteCombat.size();i++)
		{
			int scoreMin = this.uniteCombat.get(i).getpMin();
			int scoreMax = this.uniteCombat.get(i).getpMax();	
			
			int random = (int) (Math.random() * (scoreMax - scoreMin)+1 - 0) + scoreMin;

			this.uniteCombat.get(i).setScoreDES(random);
		}
	}
	
	/**
	 * Tri une liste de dés dans l'ordre décroissant
	 * @param listeDes ArrayList<Integer>  la liste à trier
	 * @return la liste triée dans l'ordre croissant
	 */
	public void trierDes()
	{
		for(int i = 0;i < this.uniteCombat.size();i++)
		{
			for(int j = 0;j < this.uniteCombat.size();j++)
			{
				if(this.uniteCombat.get(i).getScoreDES() > this.uniteCombat.get(j).getScoreDES())
				{
					Unite pivot = this.uniteCombat.get(i);
					
					this.uniteCombat.set(i, this.uniteCombat.get(j));
					this.uniteCombat.set(j, pivot);
				}
			}
		}
	}

	/**
	 * Trie la liste des unités d'attaque en cas d'égalité en fonction des priorités
	 * @param liste
	 */
	public void trierParPrioriteATT()
	{
		for(int i = 0; i < this.uniteCombat.size();i++)
		{
			for(int j = 0;j < this.uniteCombat.size();j++)
			{
				if(this.uniteCombat.get(i).getpATT() < this.uniteCombat.get(j).getpATT() && this.uniteCombat.get(i).getScoreDES() == this.uniteCombat.get(j).getScoreDES())
				{
					Unite pivot = this.uniteCombat.get(i);
					
					this.uniteCombat.set(i, this.uniteCombat.get(j));
					this.uniteCombat.set(j, pivot);
				}
			}
		}
	}
	
	/**
	 * Trie la liste des unités d'attaque en cas d'égalité en fonction des priorités
	 * @param liste
	 */
	public void trierParPrioriteDEF()
	{
		for(int i = 0; i < this.uniteCombat.size();i++)
		{
			for(int j = 0;j < this.uniteCombat.size();j++)
			{
				if(this.uniteCombat.get(i).getpDEF() < this.uniteCombat.get(j).getpDEF() && this.uniteCombat.get(i).getScoreDES() == this.uniteCombat.get(j).getScoreDES())
				{
					Unite pivot = this.uniteCombat.get(i);
					
					this.uniteCombat.set(i, this.uniteCombat.get(j));
					this.uniteCombat.set(j, pivot);
				}
			}
		}
	}
	
	/**
	 * Effectue la comparaison des valeurs des dés
	 * @param listeDesATT les valeurs des dés d'attaque
	 * @param listeDesDEF les valeurs des dés de défense
	 * @param territoireDEF le territoire qui se défend
	 * @return la liste des territoires qui ont gagné
	 */
	public void determinerResultatCombat(Territoire territoireDEF)
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
				territoireDEF.majUnitesSurTerritoire(territoireDEF.uniteCombat.get(i));
			}
			else
			{
				listeGagnants.add(territoireDEF);
				this.majUnitesSurTerritoire(this.uniteCombat.get(i));
			}
		}
		
		Main.risk.listeGagnants = listeGagnants;
	}

	/**
	 * Met à jour l'affichage et le nombre d'unités
	 * @param Unite unitee
	 * @param Territoire territoire
	 */
	public void majUnitesSurTerritoire(Unite unitee)
	{
		this.listeUnitees.remove(unitee);
		if(unitee.getType() == 0)
		{
			this.ajouterSoldats(-1);
		}
		else if(unitee.getType() == 1)
		{
			this.ajouterCavaliers(-1);
		}
		else if(unitee.getType() == 2)
		{
			this.ajouterCanons(-1);
		}
	}
	
	/**
	 * Met à jour le proprietaire d'un territoire
	 * @param territoireATT le territoire qui attaque
	 * @param territoireDEF territoire qui était attaqué
	 */
	public void majProprietaireTerritoire(Territoire territoireDEF)
	{	
		if(territoireDEF.estConquis())
		{
			territoireDEF.changeProprietaire(this);
			
		}
	}
	//ATTAQUE

	
	//MODIFICATIONS
	/**
	 * Deploie des toupes sur le territoire séléctionné
	 */
	public void deployerUnite(Joueur joueur)
	{
		if(joueur.getNombreCanonsDeploiement() > 0)
		{
			this.ajouterUniteTerritoire(new Unite(2,4,9,3,2,1));//Canon
			this.ajouterCanons(1);
			
			joueur.setNombreCanonsDeploiement(joueur.getNombreCanonsDeploiement() - 1);
		}
		else if(joueur.getNombreCavaliersDeploiement() > 0)
		{
			this.ajouterUniteTerritoire(new Unite(1,2,7,1,3,3));//Cavalier
			this.ajouterCavaliers(1);
			joueur.setNombreCavaliersDeploiement(joueur.getNombreCavaliersDeploiement() - 1);
		}
		else
		{
			this.ajouterUniteTerritoire(new Unite(0,1,6,2,1,2));//Soldat
			this.ajouterSoldats(1);
			joueur.setNombreSoldatsDeploiement(joueur.getNombreSoldatsDeploiement() - 1);
		}
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
	public void ajouterUniteTerritoire(Unite unite)
	{
		this.listeUnitees.add(unite);
	}
	
	/**
	 * Ajoute des unités dans la liste
	 * @param type le type d'unité
	 * @param rang 0 par défaut
	 * @param listeUniteeDEF
	 * @param territoireDEF
	 * @return l'unité à ajouter
	 */
	public void ajouterUniteCombat(int type,int rang)
	{
		if(this.uniteCombat.size() == 0 && this.listeUnitees.get(rang).getType() == type)
		{
			this.uniteCombat.add(this.listeUnitees.get(rang));
		}
		else if(this.listeUnitees.get(rang).getType() == type && this.uniteCombat.contains(this.listeUnitees.get(rang)) == false && this.listeUnitees.size() > 0)
		{
			this.uniteCombat.add(this.listeUnitees.get(rang));
		}
		else
		{
			ajouterUniteCombat(type, rang+1);
		}
	}
		
	/**
	 * Renvoit une unitée qui peut se déplacer
	 * @param int type Le type de l'unité
	 * @param int rang 0 par défaut
	 * @return l'unité ou null si aucune ne peut se déplacer
	 */
	public Unite uniteDeplacement(int type, int rang)
	{
		if(rang == this.listeUnitees.size())
		{
			return null;
		}
		else if(this.listeUnitees.get(rang).getType() == type && this.listeUnitees.get(rang).peutDeplacer() && this.peutAttaquerOuDeplacer())
		{
			Unite uniteDeplacement = this.listeUnitees.get(rang);
			this.listeUnitees.remove(rang);
			
			switch(uniteDeplacement.getType())
			{
			case 0:
				this.ajouterSoldats(-1);
				break;
			case 1:
				this.ajouterCavaliers(-1);
				break;
			case 2:
				this.ajouterCanons(-1);
				break;
			}
			return uniteDeplacement;
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
			this.listeUnitees.get(i).listeTerritoiresParcourus.clear();
		}
	}

	/**
	 * Passe le territoire conquis sous le controle du propriétaire du territoire vainqueur
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
	public void deplacement(Territoire territoire2, Unite deplacement)
	{
		deplacement.setNombreDeplacement(deplacement.getNombreDeplacement() + 1);
		territoire2.ajouterUniteTerritoire(deplacement);
		
		if(deplacement.getType() == 0)
		{
			territoire2.ajouterSoldats(1);
		}
		else if(deplacement.getType()  == 1)
		{
			territoire2.ajouterCavaliers(1);
		}
		else if(deplacement.getType() == 2)
		{
			territoire2.ajouterCanons(1);
		}
		
	}
	//MODIFICATIONS
	
	
	//DIVERS
	/**
	 * Renvoit l'index du territoire
	 * @return l'index
	 */
	public int getIndexTerritoire()
	{
		for(int i = 0; i < Main.risk.listeTerritoires.size();i++)
		{
			if(Main.risk.listeTerritoires.get(i).equals(this))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Remet les troupes sur le territoire après un combat
	 */
	public void resetTroupes()
	{
		this.nombreSoldats = 0;
		this.nombreCavaliers = 0;
		this.nombreCanons = 0;
		
		this.uniteCombat.clear();
		
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
	//DIVERS
	
	
	//IA
	/**
	 * Check si un territoire est entoure ou non
	 * @return true s'il l'est false sinon
	 */
	public boolean estEntoure()
	{
		for(int i = 0;i<this.territoiresAdjacents.length;i++)
		{
			if(Main.risk.retrouverAvecNom(this.territoiresAdjacents[i]).getProprietaire() != this.getProprietaire())
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Check si le territoire qui veut attaquer est plus puissant que le territoire qui défend
	 * @param territoire2 territoire que l'ia attaque
	 * @return true si oui, false sinon
	 */
	public boolean estPlusPuissant(Territoire territoire2)
	{
		double puissanceAttaque = 0;
		double puissanceDefense = 0;
		
		for(int i = 0; i < this.listeUnitees.size();i++)
		{
			puissanceAttaque = puissanceAttaque + this.listeUnitees.get(i).getpMax();
		}
		for(int i = 0; i < territoire2.listeUnitees.size();i++)
		{
			puissanceDefense = puissanceDefense + territoire2.listeUnitees.get(i).getpMax();
		}
		
		double puissanceAttaqueMoyenne = puissanceAttaque / this.listeUnitees.size();
		
		double puissanceDefenseMoyenne = puissanceDefense / territoire2.listeUnitees.size();
		
		
		if(puissanceAttaque > puissanceDefense && puissanceAttaqueMoyenne >= puissanceDefenseMoyenne/2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Check si le territoire est plus puissant qu'au moins un territoire adjacent
	 * @return
	 */
	public boolean estPlusPuissantQueAdjacent()
	{
		for(int i = 0; i < this.territoiresAdjacents.length;i++)
		{
			if(this.estPlusPuissant(Main.risk.retrouverAvecNom(this.territoiresAdjacents[i])) && Main.risk.retrouverAvecNom(this.territoiresAdjacents[i]).appartientA(this.proprietaire) == false)
			{
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Check si un territoire appartient a un continent
	 * @param continent le continent en question
	 * @return true s'il appartient false sinon
	 */
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
	//IA


	//Getters Setters
	public String getNom() {
		return nom;
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public int getNombreTroupesTotal()
	{
		return this.nombreCanons + this.nombreCavaliers + this.nombreSoldats;
	}
	
	public String[] getTerritoiresAdjacents() {
		return territoiresAdjacents;
	}
	
	public int getNombreSoldats() {
		return nombreSoldats;
	}

	public int getNombreCavaliers() {
		return nombreCavaliers;
	}

	public int getNombreCanons() {
		return nombreCanons;
	}

	public ArrayList<Unite> getListeUnitees() {
		return listeUnitees;
	}

	public void setListeUnitees(ArrayList<Unite> listeUnitees) {
		this.listeUnitees = listeUnitees;
	}

	public int getNombreTroupesATT() {
		return nombreTroupesATT;
	}

	public void setNombreTroupesATT(int nombreTroupesATT) {
		this.nombreTroupesATT = nombreTroupesATT;
	}

}
