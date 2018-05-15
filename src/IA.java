import java.util.ArrayList;
public class IA extends Joueur{

	Jeu risk = Main.risk;
	
	private ArrayList<Unitee> listeUniteADeployer = new ArrayList<Unitee>();
	
	/**
	 * Constructeur d'un objet IA
	 * @param nom
	 * @param numeroDeJoueur
	 */
	public IA(String nom, int numeroDeJoueur) {
		super(nom, numeroDeJoueur);
	}
	
	
	//deploiement
	
	//while(peut attaque)

	//while peut deplacer
	
	//fin de tour
	
	
	//Attaque
	/**
	 * Selectionne un territoire duquel lancer une attaque
	 * @param rang initialisé à 0
	 * @return le territoire qui attaque
	 */
	public Territoire territoireQuiAttaque(int rang)
	{
		if(this.listeTerritoiresControles.get(rang).peutAttaquerOuDeplacer() && this.listeTerritoiresControles.get(rang).estEntoure() == false)
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
		if(territoire.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]).appartientA(this) == false)
		{
			return territoire.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]);
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
				territoire1.deplacement(territoire2, territoire1.getListeUnitees().get(i));
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
			territoire.ajouterUniteListe(type, 0);
			
			switch(type)
			{
			case 0:
				territoire.ajouterSoldats(-1);
				break;
			case 1:
				territoire.ajouterCavaliers(-1);
				break;
			case 2:
				territoire.ajouterCanons(-1);
				break;
			}
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
			if(true)
			{
				nombreCavaliers = nombreCavaliers + 2;
				troupes = troupes -1;
			}
			else if((int) troupes/7 >= 1)
			{
				nombreCanons++;
				troupes = troupes - 7;
			}
			else if((int)troupes/3 >= 1)
			{
				nombreCavaliers++;
				troupes = troupes -3;
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
	public void deploiement()
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
					this.listeTerritoiresControles.get(i).ajouterTroupe(1);
					break;
				case 1:
					this.listeTerritoiresControles.get(i).ajouterCavaliers(1);
					this.listeTerritoiresControles.get(i).ajouterTroupe(3);
					break;
				case 2:
					this.listeTerritoiresControles.get(i).ajouterCanons(1);
					this.listeTerritoiresControles.get(i).ajouterTroupe(7);
					break;
				}
				
				this.listeUniteADeployer.remove(listeUniteADeployer.get(0));
			}
		}
		

		if(this.listeUniteADeployer.size() > 0)
		{
			deploiement();
		}
		
		this.listeUniteADeployer.clear();
	}
	//Divers
	/**
	 * Check si l'IA peut encore attaquer
	 * @return true si elle peut, false sinon
	 */
	public boolean peutAttaquerOuDeplacer()
	{
		for(int i = 0; i < this.listeTerritoiresControles.size();i++)
		{
			if(this.listeTerritoiresControles.get(i).peutAttaquerOuDeplacer() && this.listeTerritoiresControles.get(i).estEntoure() == false)
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	//Deplacement
	
	/**
	 * Déplacer les unites bloques entre des territoires controlés par l'IA
	 */
	public void deplaceUniteeBloquee()
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
	
					
					uniteQuiSeDeplace.listeTerritoiresParcourus.add(this.listeTerritoiresControles.get(i));
					

					for(int j = 0; j < this.listeTerritoiresControles.get(i).getTerritoiresAdjacents().length;j++)
					{
						if(this.listeTerritoiresControles.get(i).retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]).estEntoure() == false && uniteQuiSeDeplace.listeTerritoiresParcourus.contains(this.listeTerritoiresControles.get(i).retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j])))
						{
							territoireArrive = this.listeTerritoiresControles.get(i).retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]);
						}
					}
					
					if(territoireArrive == null)
					{
						territoireArrive = this.listeTerritoiresControles.get(i).retrouverAvecNom(this.listeTerritoiresControles.get(0).getTerritoiresAdjacents()[0]);
					}//Fin choix territoire
					

					
					
					territoireDepart.deplacement(territoireArrive, uniteQuiSeDeplace);
					
				}
			}
		}
		
		if(uneTroupeEstBloquee())
		{
			deplaceUniteeBloquee();
		}
		else
		{
			uniteQuiSeDeplace.listeTerritoiresParcourus.clear();
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

}
