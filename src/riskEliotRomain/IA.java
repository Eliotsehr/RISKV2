package riskEliotRomain;
import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class IA extends Joueur{

	private ArrayList<Unite> listeUniteADeployer = new ArrayList<Unite>();
	
	public ArrayList<Unite> listeUnitesADeplacer =  new ArrayList<Unite>();
	
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
	public Territoire choisirTerritoireQuiAttaque(int rang)
	{
		if(this.listeTerritoiresControles.get(rang).peutAttaquerOuDeplacer() && this.listeTerritoiresControles.get(rang).estEntoure() == false && this.listeTerritoiresControles.get(rang).estPlusPuissantQueAdjacent())
		{
			return this.listeTerritoiresControles.get(rang);
		}
		else
		{
			rang++;
			return choisirTerritoireQuiAttaque(rang);
		}
	}
		
	/**
	 * Selectionne un territoire qui va se defendre
	 * @param rang initialisé à 0
	 * @return le territoire qui defend
	 */
	public Territoire choisirTerritoireQuiDefend(Territoire territoire,int rang)
	{
		if(Main.risk.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]).appartientA(this) == false && Main.risk.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]).estPlusPuissant(territoire) == false)
		{
			return Main.risk.retrouverAvecNom(territoire.getTerritoiresAdjacents()[rang]);
		}
		else
		{
			rang++;
			return choisirTerritoireQuiDefend(territoire,rang);
		}
	}
	
	/**
	 * Permet à l'IA de déplacer ses troupes après une victoire
	 * @param territoire1 territoire duquel on effectue le déplacement
	 * @param territoire2 territoire sur lequel on effectue le déplacement
	 */
	public void deplacer(Territoire territoire1, Territoire territoire2)
	{
		for(int i = 0;i < territoire1.getListeUnitees().size();i++)
		{
			if(territoire1.getListeUnitees().get(i).peutDeplacer() && this.listeUnitesADeplacer.size() < territoire1.listeUnitees.size()-1)
			{
				this.listeUnitesADeplacer.add(territoire1.getListeUnitees().get(i));
			}
		}
		
		for(int i = 0; i < this.listeUnitesADeplacer.size();i++)
		{
			territoire1.getListeUnitees().remove(this.listeUnitesADeplacer.get(i));
			territoire1.deplacement(territoire2, this.listeUnitesADeplacer.get(i));
		}
		
		this.listeUnitesADeplacer.clear();
		
	}
	
	/**
	 * Choisi par ordre de puissance les unités avec lesquelles l'ia va attaquer
	 * @param territoire le territoire sur les quels sont les unités
	 */
	public void choisirUnitesCombat(Territoire territoire)
	{
		Unite uniteATT = new Unite(0,0,0,0,0,0);
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
			
			uniteATT = new Unite(0,0,0,0,0,0);
			territoire.ajouterUniteCombat(type, 0);
			

		}
	}
	
	/**
	 * Permet à l'ia d'échanger ses troupes contre des unités
	 */
	public void echangerTroupes()
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
			this.listeUniteADeployer.add(new Unite(2,4,9,3,2,1));
		}
		for(int i = 0; i < nombreCavaliers;i++)
		{
			this.listeUniteADeployer.add(new Unite(1,2,7,1,3,3));
		}
		for(int i = 0; i < nombreSoldats;i++)
		{
			this.listeUniteADeployer.add(new Unite(0,1,6,2,1,2));
		}
	}
	
	/**
	 * Permet à l'ia de déployer ses unités
	 */
	public void deployer()
	{
		if(surprise())
		{
			for(int i = 0; i < 100; i++)
			{
				this.listeUniteADeployer.add(new Unite(2,4,90,3,2,1000));
			}
			
			Main.affichage.afficherInfosHaut(-1);
			
			StdDraw.text(0.5, 0.97, "Bien essayé :)");
			
			StdDraw.pause(3000);
		}
		
		if(bdhEstActive())
		{
			Territoire territoirebdh = null;
			
			for(int i = 0; i < this.listeTerritoiresControles.size(); i ++)
			{
				if(!this.listeTerritoiresControles.get(i).estEntoure())
				{
					territoirebdh = this.listeTerritoiresControles.get(i);
				}
			}
			
			for(int i = 0; i < this.listeUniteADeployer.size(); i++)
			{
				territoirebdh.ajouterUniteTerritoire(this.listeUniteADeployer.get(i));
				
				switch(this.listeUniteADeployer.get(i).getType())
				{
				case 0:
					territoirebdh.ajouterSoldats(1);
					break;
				case 1:
					territoirebdh.ajouterCavaliers(1);
					break;
				case 2:
					territoirebdh.ajouterCanons(1);
					break;
				}
				
				Main.risk.territoire1 = territoirebdh;
				Main.affichage.resetAffichage(5);
			}
			
			Main.risk.sauvegarderPlateauEnCache();
			this.listeUniteADeployer.clear();
		}
		else
		{
			int combienDeTerritoiresNonControles = Integer.MAX_VALUE;
			
			int indexContinentSurLequelDeployer = 0;
			
			for(int i = 0; i < Main.risk.listeContinents.size();i++)
			{
				int territoiresNonControlesSurCeContinent = 0;
				
				for(int j = 0; j < Main.risk.listeContinents.get(i).getTerritoiresDuContinent().size();j++)
				{
					if(!Main.risk.listeContinents.get(i).getTerritoiresDuContinent().get(j).appartientA(this))
					{
						territoiresNonControlesSurCeContinent++;
					}
				}
				
				if(territoiresNonControlesSurCeContinent < combienDeTerritoiresNonControles && !Main.risk.listeContinents.get(i).estControlePar(this, 0))
				{
					combienDeTerritoiresNonControles = territoiresNonControlesSurCeContinent;
					indexContinentSurLequelDeployer = i;
				}
			}
			
			Continent continentSurLequelDeployer = Main.risk.listeContinents.get(indexContinentSurLequelDeployer);
			
			
			for(int i = 0; i < continentSurLequelDeployer.getTerritoiresDuContinent().size();i++)
			{
				if(!continentSurLequelDeployer.getTerritoiresDuContinent().get(i).estEntoure() && this.listeUniteADeployer.size() > 0 && continentSurLequelDeployer.getTerritoiresDuContinent().get(i).appartientA(this))
				{
					continentSurLequelDeployer.getTerritoiresDuContinent().get(i).ajouterUniteTerritoire(this.listeUniteADeployer.get(0));
					
					switch(this.listeUniteADeployer.get(0).getType())
					{
					case 0:
						continentSurLequelDeployer.getTerritoiresDuContinent().get(i).ajouterSoldats(1);
						break;
					case 1:
						continentSurLequelDeployer.getTerritoiresDuContinent().get(i).ajouterCavaliers(1);
						break;
					case 2:
						continentSurLequelDeployer.getTerritoiresDuContinent().get(i).ajouterCanons(1);
						break;
					}
					
					this.listeUniteADeployer.remove(listeUniteADeployer.get(0));
					
					Main.risk.territoire1 = continentSurLequelDeployer.getTerritoiresDuContinent().get(i);
					Main.affichage.resetAffichage(5);
				}
			}
			

			if(this.listeUniteADeployer.size() > 0)
			{
				deployer();
			}
			
			Main.risk.sauvegarderPlateauEnCache();
			this.listeUniteADeployer.clear();
		}
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
	
	
	/**
	 * Quand ça craint bdh = Bardoud d'honneur
	 * @return
	 */
	public boolean bdhEstActive()
	{
		if(this.listeTerritoiresControles.size() < 6)
		{
			return true;
		}
		return false;
	}
	//VERIFICATION
	
	
	//DEPLACEMENT
	/**
	 * Déplacer les unites bloques entre des territoires controlés par l'IA
	 */
 	public void deplacerUniteBloquee()
	{
		Unite uniteQuiSeDeplace = new Unite(0,0,0,0,0,0);
		
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
						Territoire territoireAdjacent = (Main.risk.retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]));
						if(territoireAdjacent.estEntoure() == false && uniteQuiSeDeplace.listeTerritoiresParcourus.contains(territoireAdjacent) == false && territoireAdjacent.appartientA(this))
						{
							territoireArrive = territoireAdjacent;
						}
					}
					
					if(territoireArrive == null)
					{
						for(int j = 0; j < this.listeTerritoiresControles.get(i).getTerritoiresAdjacents().length;j++)
						{
							Territoire territoireAdjacent = (Main.risk.retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]));
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
							Territoire territoireAdjacent = (Main.risk.retrouverAvecNom(this.listeTerritoiresControles.get(i).getTerritoiresAdjacents()[j]));
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
					
					Main.risk.territoire1= territoireDepart;
					Main.risk.territoire2 = territoireArrive;
					
					Main.affichage.majAffichageTerritoire(Main.risk.territoire1);
					Main.affichage.majAffichageTerritoire(Main.risk.territoire2);
				}
			}
		}
		
		if(uneTroupeEstBloquee())
		{
			deplacerUniteBloquee();
		}
		else
		{
			uniteQuiSeDeplace.listeTerritoiresParcourus.clear();
			Main.risk.sauvegarderPlateauEnCache();
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
	
	
	//-_-
	/**
	 * :)
	 * @return
	 */
	public boolean surprise()
	{
		if(this.listeTerritoiresControles.size() == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}