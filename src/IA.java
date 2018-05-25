import java.util.ArrayList;

public class IA extends Joueur{

	private ArrayList<Unitee> listeUniteADeployer = new ArrayList<Unitee>();
	
	/**
	 * Constructeur d'un objet IA
	 * @param nom
	 * @param numeroDeJoueur
	 */
	public IA(String nom, int numeroDeJoueur) {
		super(nom, numeroDeJoueur);
	}
	

	//ATTAQUE
	/**
	 * Selectionne un territoire duquel lancer une attaque
	 * @param rang initialisé à 0
	 * @return le territoire qui attaque
	 */
	public Territoire territoireQuiAttaque(int rang)
	{
		if(this.listeTerritoiresControles.get(rang).peutAttaquerOuDeplacer() && this.listeTerritoiresControles.get(rang).estEntoure() == false && this.listeTerritoiresControles.get(rang).estPlusPuissantQueAdjacent())
		{
			return this.listeTerritoiresControles.get(rang);
		}
		else
		{
			rang++;
			return territoireQuiAttaque(rang);
		}
	}
		
	/**
	 * Selectionne un territoire qui va se defendre
	 * @param rang initialisé à 0
	 * @return le territoire qui defend
	 */
	public Territoire territoireQuiDefend(Territoire territoire,int rang)
	{
		if(Jeu.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]).appartientA(this) == false && Jeu.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]).estPlusPuissant(territoire) == false)
		{
			return Jeu.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]);
		}
		else
		{
			rang++;
			return territoireQuiDefend(territoire,rang);
		}
	}
	
	/**
	 * Permet à l'IA de déplacer ses troupes après une victoire
	 * @param territoire1 territoire duquel on effectue le déplacement
	 * @param territoire2 territoire sur lequel on effectue le déplacement
	 */
	public void deplacement(Territoire territoire1, Territoire territoire2)
	{
		for(int i = 0;i < territoire1.getListeUnitees().size();i++)
		{
			if(territoire1.getListeUnitees().get(i).peutDeplacer())
			{
				Unitee uniteADeplacer = territoire1.getListeUnitees().get(i);
				territoire1.getListeUnitees().remove(i);
				territoire1.deplacement(territoire2, uniteADeplacer);
			}
		}
		
	}
	
	/**
	 * Choisi par ordre de puissance les unités avec lesquelles l'ia va attaquer
	 * @param territoire le territoire sur les quels sont les unités
	 */
	public void choixUnitesAttaque(Territoire territoire)
	{
		Unitee uniteATT = new Unitee(0,0,0,0,0,0);
		int type = 0;
		for(int i = 0;i < territoire.getListeUnitees().size()-1 && i < 3;i++)
		{
			for(int j = 0;j < territoire.getListeUnitees().size();j++)
			{
				if(territoire.getListeUnitees().get(j).getpMax() > uniteATT.getpMax() && territoire.uniteCombat.contains(territoire.getListeUnitees().get(j)) == false)
				{
					uniteATT = territoire.getListeUnitees().get(j);
					type = uniteATT.getType();
				}
			}
			
			uniteATT = new Unitee(0,0,0,0,0,0);
			territoire.ajouterUniteCombat(type, 0);
			

		}
	}
	
	/**
	 * Permet à l'ia d'échanger ses troupes contre des unités
	 */
	public void echange()
	{
		int troupes = this.getNombreTroupesDeploiement();
		int nombreCanons = 0;
		int nombreCavaliers = 0;
		int nombreSoldats = 0;
		
		while(troupes > 0)
		{

			if((int) troupes > 30)
			{
				nombreCanons++;
				troupes = troupes - 7;
			}
			else if((int)troupes > 10)
			{
				nombreCavaliers++;
				troupes = troupes -3;
			}
			else
			{
				nombreSoldats++;
				troupes = troupes -1;
			}
		}
		

		for(int i = 0; i < nombreCanons;i++)
		{
			this.listeUniteADeployer.add(new Unitee(2,4,9,3,2,1));
		}
		for(int i = 0; i < nombreCavaliers;i++)
		{
			this.listeUniteADeployer.add(new Unitee(1,2,7,1,3,3));
		}
		for(int i = 0; i < nombreSoldats;i++)
		{
			this.listeUniteADeployer.add(new Unitee(0,1,6,2,1,2));
		}
	}
	
	/**
	 * Permet à l'ia de déployer ses unités
	 */
	public void deploiement(Affichage interf)
	{
		
		for(int i = 0; i < this.listeTerritoiresControles.size();i++)
		{
			if(this.listeTerritoiresControles.get(i).estEntoure() == false && this.listeUniteADeployer.size() > 0)
			{
				this.listeTerritoiresControles.get(i).ajouterUniteTerritoire(this.listeUniteADeployer.get(0));
				
				switch(this.listeUniteADeployer.get(0).getType())
				{
				case 0:
					this.listeTerritoiresControles.get(i).ajouterSoldats(1);
					break;
				case 1:
					this.listeTerritoiresControles.get(i).ajouterCavaliers(1);
					break;
				case 2:
					this.listeTerritoiresControles.get(i).ajouterCanons(1);

					break;
				}
				
				this.listeUniteADeployer.remove(listeUniteADeployer.get(0));
				
				interf.territoire1 = this.listeTerritoiresControles.get(i);
				interf.reset(5);
			}
		}
		

		if(this.listeUniteADeployer.size() > 0)
		{
			deploiement(interf);
		}
		
		interf.savePlateau();
		this.listeUniteADeployer.clear();
	}
	//ATTAQUE
	
	
	//VERIFICATION
	/**
	 * Check si l'IA peut encore attaquer
	 * @return true si elle peut, false sinon
	 */
	public boolean peutAttaquerOuDeplacer()
	{
		for(int i = 0; i < this.listeTerritoiresControles.size();i++)
		{
			if(this.listeTerritoiresControles.get(i).peutAttaquerOuDeplacer() && this.listeTerritoiresControles.get(i).estEntoure() == false && this.listeTerritoiresControles.get(i).estPlusPuissantQueAdjacent())
			{
				return true;
			}
		}
		
		return false;
	}
	//VERIFICATION
	
	
	//DEPLACEMENT
	/**
	 * Déplacer les unites bloques entre des territoires controlés par l'IA
	 */
	public void deplaceUniteeBloquee(Affichage interf)
	{
		Unitee uniteQuiSeDeplace = new Unitee(0,0,0,0,0,0);
		if(uneTroupeEstBloquee())
		{
			for(int i = 0; i < this.listeTerritoiresControles.size();i++)
			{
				if(this.listeTerritoiresControles.get(i).estEntoure() && this.listeTerritoiresControles.get(i).peutAttaquerOuDeplacer())
				{
					
					

					//Choix du territoire duquel on effectue le déplacement et le territoire sur lequel on fait le dépalacement
					Territoire territoireArrive = null;
					Territoire territoireDepart = this.listeTerritoiresControles.get(i);
					
					//Choix de l'unité qui se déplace
					for(int j = 0; j < territoireDepart.getListeUnitees().size();j++)
					{
						if(territoireDepart.getListeUnitees().get(j).peutDeplacer())
						{
							uniteQuiSeDeplace = territoireDepart.getListeUnitees().get(j);
						}
					}
	
					
					uniteQuiSeDeplace.listeTerritoiresParcourus.add(territoireDepart);
					

					for(int j = 0; j < this.listeTerritoiresControles.get(i).getTerritoiresAdjacents().length;j++)
					{
						Territoire territoireAdjacent = (Jeu.retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]));
						if(territoireAdjacent.estEntoure() == false && uniteQuiSeDeplace.listeTerritoiresParcourus.contains(territoireAdjacent) == false && territoireAdjacent.appartientA(this))
						{
							territoireArrive = territoireAdjacent;
						}
					}
					
					if(territoireArrive == null)
					{
						for(int j = 0; j < this.listeTerritoiresControles.get(i).getTerritoiresAdjacents().length;j++)
						{
							Territoire territoireAdjacent = (Jeu.retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]));
							if(uniteQuiSeDeplace.listeTerritoiresParcourus.contains(territoireAdjacent) == false && territoireAdjacent.appartientA(this))
							{
								territoireArrive = territoireAdjacent;
							}	
						}
					}
					
					if(territoireArrive == null)
					{
						for(int j = 0; j < this.listeTerritoiresControles.get(i).getTerritoiresAdjacents().length;j++)
						{
							Territoire territoireAdjacent = (Jeu.retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]));
							if(territoireAdjacent.appartientA(this))
							{
								territoireArrive = territoireAdjacent;
							}	
						}
					}//Fin choix territoire
					//Fin choix territoire
					
					
					
					

					uniteQuiSeDeplace.setNombreDeplacement(uniteQuiSeDeplace.getNombreDeplacement()+1);
					
					
				/*	for(int i = 0; i < territoireDepart.listeUnitees.size(); i++)
					{
						if(territoireDepart.listeUnitees.remove(uniteQuiSeDeplace));
					}
					*/
					
					
					territoireDepart.listeUnitees.remove(uniteQuiSeDeplace);
					territoireArrive.listeUnitees.add(uniteQuiSeDeplace);
					
					switch(uniteQuiSeDeplace.getType())
					{
					case 0:
						territoireDepart.ajouterSoldats(-1);
						territoireArrive.ajouterSoldats(1);
						break;
					case 1:
						territoireDepart.ajouterCavaliers(-1);
						territoireArrive.ajouterCavaliers(1);
						break;
					case 2:
						territoireDepart.ajouterCanons(-1);
						territoireArrive.ajouterCanons(1);
						break;
					}
					
					interf.territoire1= territoireDepart;
					interf.territoire2 = territoireArrive;
					
					interf.maj(interf.territoire1);
					interf.maj(interf.territoire2);
				}
			}
		}
		
		if(uneTroupeEstBloquee())
		{
			deplaceUniteeBloquee(interf);
		}
		else
		{
			uniteQuiSeDeplace.listeTerritoiresParcourus.clear();
			interf.savePlateau();
		}
		
	}
	
	/**
	 * Check si une troupe est bloquée
	 * @return true si ou, false sinon
	 */
	public boolean uneTroupeEstBloquee()
	{
		for(int i = 0; i < this.listeTerritoiresControles.size();i++)
		{
			if(this.listeTerritoiresControles.get(i).estEntoure() && this.listeTerritoiresControles.get(i).peutAttaquerOuDeplacer())
			{
				return true;
			}
		}
		
		return false;
	}
	//DEPLACEMENT
	
}
