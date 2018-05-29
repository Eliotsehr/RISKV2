import edu.princeton.cs.introcs.StdDraw;

public class Affichage {

	public int xMax;
	public int yMax;

	public double[][] cooTerritoiresClassique = {{0.37,0.77},{0.44,0.79},{0.35,0.66},{0.36,0.55},{0.44,0.57},{0.44,0.64},{0.53,0.73},{0.47,0.425},{0.4,0.37},{0.52,0.32}, {0.47,0.26},{0.47,0.15},{0.56,0.15},{0.55,0.49},{0.64,0.48},{0.6,0.62},{0.61,0.73},{0.65,0.81},{0.72,0.44},{0.7,0.55}, {0.73,0.84},{0.72,0.64},{0.82,0.62},{0.8,0.84},{0.72,0.73},{0.04,0.81},{0.15,0.81},{0.1,0.74},{0.18,0.72},{0.3,0.86}, {0.26,0.72},{0.13,0.65},{0.19,0.6},{0.14,0.51},{0.2,0.44},{0.26,0.35},{0.18,0.31},{0.23,0.22},{0.73,0.29},{0.81,0.32},{0.76,0.15},{0.83,0.19}};
	public double [][] cooTerritoiresGOT = {{0.2,0.84},{0.26,0.86},{0.24,0.79},{0.11,0.77},{0.22,0.74},{0.1,0.7},{0.12,0.64},{0.16,0.67},{0.22,0.56},{0.08,0.56},{0.12,0.53},{0.2,0.47},{0.13,0.47},{0.11,0.39},{0.17,0.41},{0.23,0.38},{0.09,0.32},{0.16,0.31},{0.23,0.27},{0.36,0.57},{0.36,0.47},{0.4,0.51},{0.45,0.48},{0.51,0.48},{0.41,0.38},{0.39,0.29},{0.49,0.41},{0.47,0.31},{0.54,0.35},{0.54,0.26},{0.57,0.22},{0.55,0.13},{0.64,0.33},{0.71,0.28},{0.78,0.32},{0.87,0.38},{0.71,0.18},{0.79,0.24},{0.87,0.29},{0.88,0.22},{0.57,0.54},{0.65,0.49},{0.74,0.49},{0.81,0.58},{0.84,0.75},{0.94,0.57},{0.84,0.5},{0.6,0.4},{0.7,0.4},{0.8,0.42}};
	
	public int affichageSelectionSoldat = 0;
	public int affichageSelectionCavalier = 0;
	public int affichageSelectionCanon = 0;
	
	/**
	 *
	 * @param xMax int largeur de la fenetre
	 * @param yMax int hauteur de la fenetre
	 * @param couche int couche sur laquelle on travail
	 */
	public Affichage(int xMax,int yMax, int couche) {
		this.xMax = xMax;
		this.yMax = yMax;
		
		StdDraw.setXscale(0, this.xMax);
		StdDraw.setYscale(0, this.yMax);

	}


	//AFFICHAGE
	/**
	 * Affiche le menu de demarage
	 */
	public void afficherEcranMenu()
	{
		StdDraw.clear();

		StdDraw.setCanvasSize(809, 801);

		StdDraw.picture(0.5, 0.5, "img/menu.png");
	}
	
	/**
	 * Affiche le menu de séléction des cartes
	 */
	public void afficherEcranChoixMap()
	{
		StdDraw.clear();
		
		StdDraw.picture(0.5, 0.5, "img/cartes.png");
	}

	/**
	 * Affiche le menu de saisi du nombre de joueurs
	 */
	public void afficherEcranChoixNombreJoueurs()
	{
		StdDraw.clear();

		StdDraw.picture(0.5, 0.5, "img/nombreJoueurs.png");
	}

	/**
	 * Affiche le menu de saisi des noms des joueurs
	 */
	public void afficherEcranSaisieNomJoueurs()
	{
		StdDraw.clear();

		StdDraw.picture(0.5, 0.5,"img/nomJoueurs.png");
	}

	/**
	 * Affiche le plateau de jeu
	 */
	public void afficherPlateau()
	{
		StdDraw.clear();

		if(Main.risk.map == 0)
		{
			StdDraw.picture(0.5, 0.5, "img/riskmap.jpg");
		}
		else if(Main.risk.map == 1)
		{
			StdDraw.picture(0.5, 0.5, "img/mapGOT.png");
		}
	}
	
	/**
 	* Affiche l'interface de choix pour le joueur
 	*/
	public void afficherCadreChoix()
	{
		StdDraw.picture(0.5, 0.1, "img/cadreinfos.png");
	}

	/**
	 * Affiche le nom et la couleur du joueur dont c'est le tour
	 * @param tour
	 */
	public void afficherCouleurJoueurEnCours(int tour)
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(0, 1, 0.1, 0.055);//Tour
		StdDraw.setPenColor(StdDraw.BLACK);
		couleurJoueurEnCours(0.075,0.975,tour);
	}
	
	/**
	 * Interface qui permet de recuperer les soldats, les cavaliers, les canons
	 */
	public void afficherCadreChoixTroupesDeploiement()
	{
		StdDraw.picture(0.5, 0.5, "img/cadreChoixTroupes.png");
		StdDraw.text(0.487, 0.729, ""+Main.risk.joueurEnCours.getNombreTroupesDeploiement());
	}

	/**
	 * Couleur du joueur dont c'est le tour
	 * @param posX
	 * @param posY
	 * @param couleur
	 */
	public void couleurJoueurEnCours(double posX, double posY, int couleur)
	{
		if(couleur == 0)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/rondbleu.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTbleu.png");
			}
			
			
		}
		else if(couleur == 1)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/rondrouge.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTorange.png");
			}
		}
		else if(couleur == 2)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/rondjaune.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTjaune.png");
			}
		}
		else if(couleur == 3)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/rondrose.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTblanc.png");
			}
		}
		else if(couleur == 4)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/rondvert.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTvert.png");
			}
				
		}
		else if(couleur == 5)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/rondgris.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTposson.png");
			}
		}
	}

	/**
	 * Affiche la croix
	 */
	public void afficherCroix()
	{
		StdDraw.picture(0.9, 0.1, "img/croix.png");
	}

	/**
	 *	Pion normal ou GOT
	 * @param posX position en x du centre
	 * @param posY position en y du centre
	 * @param couleur
	 * @param indexTerritoire
	 * 1. bleu
	 * 2. rouge
	 * 3. jaune
	 * 4. rose
	 * 5. vert
	 * 6. gris
	 */
	public void couleurJeton(double posX, double posY, int couleur,int indexTerritoire)
	{
		if(couleur == 0)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/jetonBleu.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTbleu.png");
			}
			
			
		}
		else if(couleur == 1)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/jetonRouge.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTorange.png");
			}
		}
		else if(couleur == 2)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/jetonJaune.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTjaune.png");
			}
		}
		else if(couleur == 3)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/jetonRose.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTblanc.png");
			}
		}
		else if(couleur == 4)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/jetonVert.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTvert.png");
			}
				
		}
		else if(couleur == 5)
		{
			if(Main.risk.map == 0)
			{
				StdDraw.picture(posX,posY,"img/jetonGris.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTposson.png");
			}
		}

		StdDraw.text(posX-0.02, posY-0.015, "" + Main.risk.listeTerritoires.get(indexTerritoire).getNombreSoldats());
		StdDraw.text(posX, posY-.015, "" + Main.risk.listeTerritoires.get(indexTerritoire).getNombreCavaliers());
		StdDraw.text(posX+0.02, posY-.015, "" + Main.risk.listeTerritoires.get(indexTerritoire).getNombreCanons());
		
		//douille = douille+",{"+posX+","+posY+"}";

	}

	/**
	 * Affiche des informations en haut de l'ecran
	 * @param message int  Valeur pour savoir quel type de message afficher
	 */
	public void afficherInfosHaut(int message)
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(0.5, 1, 0.3, 0.05);
		StdDraw.setPenColor(StdDraw.BLACK);

		switch(message)
		{
		case 0://Selection
			StdDraw.text(0.5, 0.97, "Territoire selectionne: "+Main.risk.territoire1.getNom());
			break;
		case 1://Attaque
			StdDraw.text(0.5, 0.97, Main.risk.territoire1.getNom()+" attaque "+Main.risk.territoire2.getNom()+" !");
			break;
		case 2:
			if(Main.risk.listeGagnants.size() == 3)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+Main.risk.listeGagnants.get(0).getProprietaire().getNom()+" Tour 2 VICTOIRE: "+Main.risk.listeGagnants.get(1).getProprietaire().getNom()+" Tour 3 VICTOIRE: "+Main.risk.listeGagnants.get(2));
			}
			else if(Main.risk.listeGagnants.size() == 2)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+Main.risk.listeGagnants.get(0).getProprietaire().getNom()+" Tour 2 VICTOIRE: "+Main.risk.listeGagnants.get(1).getProprietaire().getNom());
			}
			else
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+Main.risk.listeGagnants.get(0).getProprietaire().getNom());
			}
			break;
		case 3:
			int affiche;
			if(Main.risk.territoire1.uniteCombat.size() > Main.risk.territoire2.uniteCombat.size())
			{
				affiche = Main.risk.territoire2.uniteCombat.size();
			}
			else
			{
				affiche = Main.risk.territoire1.uniteCombat.size();
			}

			if(affiche == 2)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 - ATT "+Main.risk.territoire1.uniteCombat.get(0).getScoreDES()+" / "+Main.risk.territoire2.uniteCombat.get(0).getScoreDES()+" DEF  //  "+"Tour 2 - ATT "+Main.risk.territoire1.uniteCombat.get(1).getScoreDES()+" / "+Main.risk.territoire2.uniteCombat.get(1).getScoreDES());
			}
			else
			{
				StdDraw.text(0.5, 0.97, "Tour 1 - ATT "+Main.risk.territoire1.uniteCombat.get(0).getScoreDES()+" / "+Main.risk.territoire2.uniteCombat.get(0).getScoreDES()+" DEF");
			}
			break;
		case 4:
			StdDraw.text(0.5, 0.97, "Deplacement depuis "+Main.risk.territoire1.getNom()+" vers "+Main.risk.territoire2.getNom());
			break;
		case 5:
			StdDraw.text(0.5, 0.97, "Il te reste "+Main.risk.joueurEnCours.getNombreTroupesDeploiement()+" troupes a placer !");
			break;
		case 7:
			StdDraw.text(0.5, 0.97, "Il te reste "+Main.risk.joueurEnCours.getNombreCanonsDeploiement()+" canons a placer !");
			break;
		case 8:
			StdDraw.text(0.5, 0.97, "Il te reste "+Main.risk.joueurEnCours.getNombreCavaliersDeploiement()+" cavaliers a placer !");
			break;
		case 9:
			StdDraw.text(0.5, 0.97, "Il te reste "+Main.risk.joueurEnCours.getNombreSoldatsDeploiement()+" soldats a placer !");
			break;
		case 10:
			StdDraw.text(0.5, 0.97, "Territoire selectionne: "+Main.risk.territoire2.getNom());
			break;
		case 11:
			StdDraw.text(0.5, 0.97, "Tu as selectione un soldat !");
			break;
		case 12:
			StdDraw.text(0.5, 0.97, "Tu as selectione un cavalier !");
			break;
		case 13:
			StdDraw.text(0.5, 0.97, "Tu as selectione un canon !");
			break;

		}



	}

	/**
	 * Affiche des informations en bas de l'ecran
	 * @param joueur Joueur  S'il y a besoin d'afficher des infos sur un joueur
	 * @param territoire Territoire  S'il y a besoin d'afficher des infos sur un territoire
	 * @param message int  Valeur pour savoir quel type de message afficher
	 */
	public void afficherInfosBas(int message)
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(0.5, 0, 0.5, 0.05);
		StdDraw.setPenColor(StdDraw.BLACK);
 
		switch(message)
		{
		case 0:
			StdDraw.text(0.5, 0.03, "Selectione un de tes territoires");
			break;
		case 1:
			StdDraw.text(0.5, 0.03, "Selectione un territoire adverse");
			break;
		case 2:
			StdDraw.text(0.5, 0.03, "CE TERRITOIRE NE T'APPARTIENT PAS OU NE PEUT PAS ATTAQUER !");
			break;
		case 3:
			StdDraw.text(0.5, 0.03, "CE TERRITOIRE T'APPARTIENT !");
			break;
		case 4:
			StdDraw.text(0.5, 0.03, "CES TERRITOIRES NE SONT PAS ADJACENTS !");
			break;
		case 5:
			StdDraw.text(0.5, 0.03, "CE TERRITOIRE NE T'APPARTIENT PAS OU N'A PAS ASSEZ DE TROUPES !");
			break;
		case 6:
			StdDraw.text(0.5, 0.03, "CE TERRITOIRE NE T'APPARTIENT PAS OU N'EST PAS ADJACENT !");
			break;
		case 7:
			StdDraw.text(0.5, 0.03, "Place tes troupes !");
			break;
		case 8:
			StdDraw.text(0.5, 0.03, "Tu n'as pas assez de troupes pour ça !");
			break;
		case 9:
			StdDraw.text(0.5, 0.03, "Selectionne tes troupes d'attaque d'abord !");
			break;
		case 10:
			StdDraw.text(0.5, 0.03, "Cette troupe a deja effectue tout ses deplacements !");
			break;
		default:
			break;
		}
	}

	/**
	 * Reset l'affichage du plateau de Main.risk
	 * @param mode 0: Deploiement // 1: Choix // 2: Croix //3: Mision //4: Lettre Mission//5: Maj affichage deploiement//6: Maj affichage combat
	 */
	public void resetAffichage(int mode)
	{
		switch(mode)
		{
		case 0:
		{
			afficherCadreChoixTroupesDeploiement();
			break;
		}
		case  1:
		{
			Main.risk.chargerPlateauEnCache();
			afficherCadreChoix();
			break;
		}
		case  2:
		{
			Main.risk.chargerPlateauEnCache();
			afficherCroix();
			break;
		}
		case  3:
		{
			afficherCadreMission(Main.risk.joueurEnCours.getMission().getIndex());
			break;
		}
		case  4:
		{
			afficherCadreLettreMission();
			break;
		}
		case  5:
		{
			majAffichageTerritoire(Main.risk.territoire1);
			break;
		}
		case  6:
		{
			Main.risk.chargerPlateauEnCache();
			majAffichageTerritoire(Main.risk.territoire1);
			majAffichageTerritoire(Main.risk.territoire2);
			afficherCroix();
			break;
		}	
		}
	}

	/**
	 * Reset les variables d'affichage des troupes
	 */
	public void resetAffichageTroupesSelectionees()
	{
		this.affichageSelectionCanon = 0;
		this.affichageSelectionCavalier = 0;
		this.affichageSelectionSoldat = 0;
	}
	
	/**
	 * Affiche la couche des pions avec le nombre d'unitees correspondant a chaque pion
	 */
	public void afficherJetons()
	{
		
		if(Main.risk.map == 0)
		{
			
			for(int i = 0; i < cooTerritoiresClassique.length;i++)
			{
				couleurJeton(cooTerritoiresClassique[i][0], cooTerritoiresClassique[i][1],Main.risk.listeTerritoires.get(i).getProprietaire().getNumeroDeJoueur(),i);
			}

		}
		else if(Main.risk.map == 1)
		{
			for(int i = 0; i < cooTerritoiresGOT.length;i++)
			{
				couleurJeton(cooTerritoiresGOT[i][0], cooTerritoiresGOT[i][1],Main.risk.listeTerritoires.get(i).getProprietaire().getNumeroDeJoueur(),i);
			}
		}

	}

	/**
	 * Met à jour l'affichage d'un territoire
	 * @param territoire
	 */
	public void majAffichageTerritoire(Territoire territoire)
	{
		int index = territoire.getIndexTerritoire();
		
		String nom = "img/mask/"+index+".png";
		
		StdDraw.picture(0.5, 0.5, nom);
		couleurJeton(cooTerritoiresClassique[index][0], cooTerritoiresClassique[index][1],territoire.getProprietaire().getNumeroDeJoueur(),index);
	}

	/**
	 * Ecran de victoire
	 */
	public void afficherEcranVictoire()
	{
		StdDraw.clear();

		StdDraw.picture(0.5, 0.5, "img/victoire.png");
		StdDraw.setPenColor(StdDraw.BLACK);

		int index = 0;
		
		if(Main.risk.listeJoueurs.size() > 1)
		{
			index = Main.risk.tour;
		}

		StdDraw.text(0.86, 0.255,"Victoire de "+Main.risk.listeJoueurs.get(index).getNom());
		
		StdDraw.text(0.5, 0.55, Main.risk.listeJoueurs.get(index).getMission().getIntitule());
	}

	/**
	 * Infos de deploiement
	 */
	public void afficherInfosDeploiement()
	{
		if(Main.risk.joueurEnCours.getNombreCanonsDeploiement() > 0)
		{
			afficherInfosHaut(7);
		}
		else if(Main.risk.joueurEnCours.getNombreCavaliersDeploiement() > 0)
		{
			afficherInfosHaut(8);
		}
		else if(Main.risk.joueurEnCours.getNombreSoldatsDeploiement() > 0)
		{
			afficherInfosHaut(9);
		}
	}

	/**
	 * Affiche les fenetres de confirmation d'attaque
	 */
	public void affichrNombreTroupesTerritoire()
	{
		StdDraw.picture(0.24, 0.5, "img/troupesDefense.png");
		StdDraw.text(0.1, 0.35, ""+Main.risk.territoire2.getNombreSoldats());
		StdDraw.text(0.24, 0.35, ""+Main.risk.territoire2.getNombreCavaliers());
		StdDraw.text(0.38, 0.35, ""+Main.risk.territoire2.getNombreCanons());

		StdDraw.picture(0.76, 0.5, "img/troupesAttaque.png");
		StdDraw.text(0.62, 0.35, ""+Main.risk.territoire1.getNombreSoldats());
		StdDraw.text(0.76, 0.35, ""+Main.risk.territoire1.getNombreCavaliers());
		StdDraw.text(0.9, 0.35, ""+Main.risk.territoire1.getNombreCanons());
		
		StdDraw.text(0.64, 0.27, ""+this.affichageSelectionSoldat);
		StdDraw.text(0.78, 0.27, ""+this.affichageSelectionCavalier);
		StdDraw.text(0.92, 0.27, ""+this.affichageSelectionCanon);
	}

	/**
	 * Affiche la fenetre de depalcement
	 */
	public void afficherCadreDeplacement()
	{
		StdDraw.picture(0.5, 0.5, "img/troupesDeplacement.png");
		
		StdDraw.text(0.37, 0.35, ""+this.affichageSelectionSoldat);
		StdDraw.text(0.5, 0.35, ""+this.affichageSelectionCavalier);
		StdDraw.text(0.63, 0.35, ""+this.affichageSelectionCanon);
		
		StdDraw.text(0.37, 0.25, ""+Main.risk.territoire1.getNombreSoldats());
		StdDraw.text(0.5, 0.25, ""+Main.risk.territoire1.getNombreCavaliers());
		StdDraw.text(0.63, 0.25, ""+Main.risk.territoire1.getNombreCanons());
	}

	/**
	 * Affiche la fenetre de mission
	 * @param index index de la mission
	 */
	public void afficherCadreMission(int index)
	{
		StdDraw.picture(0.5, 0.5, "img/mission.png");
		StdDraw.text(0.5, 0.5, Main.risk.joueurEnCours.getMission().getIntitule());
	}

	/**
	 * Affiche la lettre contenant la mission
	 */
	public void afficherCadreLettreMission()
	{
		StdDraw.picture(0.5, 0.5, "img/lettreMission.png");
	}
	//AFFICHAGE

}
