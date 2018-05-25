import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Affichage {

	private int xMax;
	private int yMax;

	/*Couches
	 * 0 - Menu
	 * 1 - Nombre Joueurs
	 * 2 - Noms Joueurs
	 * 3 - Deploiement
	 * 4 - Choix
	 * 5 - Modes
	 * 6 - Choix troupes
	 * 7 - Confirmation attaque
	 * 8 - Deplacement
	 * 9 - Lettre de mission
	 * 10 - Mission
	 * 11 - Cartes
	 * 12 - IA
	*/
	public int couche;

	/*Modes
	 * 0 - Deploiement
	 * 1 - Mode choix du territoire pour attaquer
	 * 2 - Mode choix du territoire a attaquer
	 * 3 - Mode deplacement choix du territoire de depart
	 * 4 - Mode deplacement choix du territoire d'arrive
	 */
	public int mode = 0;

	public double sourisX;
	public double sourisY;
	
	public double[][] cooTerritoiresClassique = {{0.37,0.77},{0.44,0.79},{0.35,0.66},{0.36,0.55},{0.44,0.57},{0.44,0.64},{0.53,0.73},{0.47,0.425},{0.4,0.37},{0.52,0.32}, {0.47,0.26},{0.47,0.15},{0.56,0.15},{0.55,0.49},{0.64,0.48},{0.6,0.62},{0.61,0.73},{0.65,0.81},{0.72,0.44},{0.7,0.55}, {0.73,0.84},{0.72,0.64},{0.82,0.62},{0.8,0.84},{0.72,0.73},{0.04,0.81},{0.15,0.81},{0.1,0.74},{0.18,0.72},{0.3,0.86}, {0.26,0.72},{0.13,0.65},{0.19,0.6},{0.14,0.51},{0.2,0.44},{0.26,0.35},{0.18,0.31},{0.23,0.22},{0.73,0.29},{0.81,0.32},{0.76,0.15},{0.83,0.19}};
	public double [][] cooTerritoiresGOT = {{0.2,0.84},{0.26,0.86},{0.24,0.79},{0.11,0.77},{0.22,0.74},{0.1,0.7},{0.12,0.64},{0.16,0.67},{0.22,0.56},{0.08,0.56},{0.12,0.53},{0.2,0.47},{0.13,0.47},{0.11,0.39},{0.17,0.41},{0.23,0.38},{0.09,0.32},{0.16,0.31},{0.23,0.27},{0.36,0.57},{0.36,0.47},{0.4,0.51},{0.45,0.48},{0.51,0.48},{0.41,0.38},{0.39,0.29},{0.49,0.41},{0.47,0.31},{0.54,0.35},{0.54,0.26},{0.57,0.22},{0.55,0.13},{0.64,0.33},{0.71,0.28},{0.78,0.32},{0.87,0.38},{0.71,0.18},{0.79,0.24},{0.87,0.29},{0.88,0.22},{0.57,0.54},{0.65,0.49},{0.74,0.49},{0.81,0.58},{0.84,0.75},{0.94,0.57},{0.84,0.5},{0.6,0.4},{0.7,0.4},{0.8,0.42}};
	
	public Territoire territoire1;
	public Territoire territoire2;
	
	public Joueur joueurEnCours;
	
	ArrayList<Unitee> unitesDeDeplacement = new ArrayList<Unitee>();

	/**
	 *
	 * @param xMax int largeur de la fenetre
	 * @param yMax int hauteur de la fenetre
	 * @param couche int couche sur laquelle on travail
	 */
	public Affichage(int xMax,int yMax, int couche) {
		this.xMax = xMax;
		this.yMax = yMax;
		this.couche = couche;

		StdDraw.setXscale(0, this.xMax);
		StdDraw.setYscale(0, this.yMax);

	}


	//AFFICHAGE
	/**
	 * Affiche le menu de demarage
	 */
	public void ecranMenu()
	{
		StdDraw.clear();

		StdDraw.setCanvasSize(809, 801);

		StdDraw.picture(0.5, 0.5, "img/menu.png");
	}
	
	/**
	 * Affiche le menu de séléction des cartes
	 */
	public void ecranMap()
	{
		StdDraw.clear();
		
		StdDraw.picture(0.5, 0.5, "img/cartes.png");
	}

	/**
	 * Affiche le menu de saisi du nombre de joueurs
	 */
	public void ecranNombreJoueurs()
	{
		StdDraw.clear();

		StdDraw.picture(0.5, 0.5, "img/nombreJoueurs.png");
	}

	/**
	 * Affiche le menu de saisi des noms des joueurs
	 */
	public void ecranNomJoueurs()
	{
		StdDraw.clear();

		StdDraw.picture(0.5, 0.5,"img/nomJoueurs.png");
	}

	/**
	 * Affiche le plateau de jeu
	 */
	public void couchePlateau()
	{
		StdDraw.clear();

		if(Jeu.map == 0)
		{
			StdDraw.picture(0.5, 0.5, "img/riskmap.jpg");
		}
		else if(Jeu.map == 1)
		{
			StdDraw.picture(0.5, 0.5, "img/mapGOT.png");
		}
	}
	
	/**
	 * Sauvegarde en cache l'etat du plateau
	 */
	public void savePlateau()
	{
		infosHaut(-1);
		infosBas(-1);
		coucheTour(Jeu.tour);
		String nom = "img/cache/plateau"+Jeu.cache+".png";
		StdDraw.save(nom);
		Jeu.cache++;
	}
	
	/**
	 * Charge la dernière sauvegarde en cache du plateau
	 */
	public void loadPlateau()
	{
		String nom = "img/cache/plateau"+(Jeu.cache-1)+".png";
		StdDraw.picture(0.5, 0.5, nom);
		coucheTour(Jeu.tour);
	}

	/**
 	* Affiche l'interface de choix pour le joueur
 	*/
	public void coucheChoix()
	{
		StdDraw.picture(0.5, 0.1, "img/cadreinfos.png");
	}

	/**
	 * Affiche le nom et la couleur du joueur dont c'est le tour
	 * @param tour
	 */
	public void coucheTour(int tour)
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(0, 1, 0.1, 0.055);//Tour
		StdDraw.setPenColor(StdDraw.BLACK);
		pionTour(0.075,0.975,tour);
	}
	
	
	public void pionTour(double posX, double posY, int couleur)
	{
		if(couleur == 0)
		{
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
	 * Interface qui permet de recuperer les soldats, les cavaliers, les canons
	 */
	public void coucheDeploiement()
	{
		StdDraw.picture(0.5, 0.5, "img/cadreChoixTroupes.png");
		StdDraw.text(0.487, 0.729, ""+joueurEnCours.getNombreTroupesDeploiement());
	}

	/**
	 * Affiche la croix
	 */
	public void croix()
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
	public void jeton(double posX, double posY, int couleur,int indexTerritoire)
	{
		if(couleur == 0)
		{
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
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
			if(Jeu.map == 0)
			{
				StdDraw.picture(posX,posY,"img/jetonGris.png");
			}
			else
			{
				StdDraw.picture(posX,posY,"img/GOTposson.png");
			}
		}

		StdDraw.text(posX-0.02, posY-0.015, "" + Jeu.listeTerritoires.get(indexTerritoire).getNombreSoldats());
		StdDraw.text(posX, posY-.015, "" + Jeu.listeTerritoires.get(indexTerritoire).getNombreCavaliers());
		StdDraw.text(posX+0.02, posY-.015, "" + Jeu.listeTerritoires.get(indexTerritoire).getNombreCanons());
		
		//douille = douille+",{"+posX+","+posY+"}";

	}

	/**
	 * Affiche des informations en haut de l'ecran
	 * @param message int  Valeur pour savoir quel type de message afficher
	 */
	public void infosHaut(int message)
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(0.5, 1, 0.3, 0.05);
		StdDraw.setPenColor(StdDraw.BLACK);

		switch(message)
		{
		case 0://Selection
			StdDraw.text(0.5, 0.97, "Territoire selectionne: "+territoire1.getNom());
			break;
		case 1://Attaque
			StdDraw.text(0.5, 0.97, territoire1.getNom()+" attaque "+territoire2.getNom()+" !");
			break;
		case 2:
			if(Jeu.listeGagnants.size() == 3)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+Jeu.listeGagnants.get(0).getProprietaire().getNom()+" Tour 2 VICTOIRE: "+Jeu.listeGagnants.get(1).getProprietaire().getNom()+" Tour 3 VICTOIRE: "+Jeu.listeGagnants.get(2));
			}
			else if(Jeu.listeGagnants.size() == 2)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+Jeu.listeGagnants.get(0).getProprietaire().getNom()+" Tour 2 VICTOIRE: "+Jeu.listeGagnants.get(1).getProprietaire().getNom());
			}
			else
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+Jeu.listeGagnants.get(0).getProprietaire().getNom());
			}
			break;
		case 3:
			int affiche;
			if(territoire1.uniteCombat.size() > territoire2.uniteCombat.size())
			{
				affiche = territoire2.uniteCombat.size();
			}
			else
			{
				affiche = territoire1.uniteCombat.size();
			}

			if(affiche == 2)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 - ATT "+territoire1.uniteCombat.get(0).getScoreDES()+" / "+territoire2.uniteCombat.get(0).getScoreDES()+" DEF  //  "+"Tour 2 - ATT "+territoire1.uniteCombat.get(1).getScoreDES()+" / "+territoire2.uniteCombat.get(1).getScoreDES());
			}
			else
			{
				StdDraw.text(0.5, 0.97, "Tour 1 - ATT "+territoire1.uniteCombat.get(0).getScoreDES()+" / "+territoire2.uniteCombat.get(0).getScoreDES()+" DEF");
			}
			break;
		case 4:
			StdDraw.text(0.5, 0.97, "Deplacement depuis "+territoire1.getNom()+" vers "+territoire2.getNom());
			break;
		case 5:
			StdDraw.text(0.5, 0.97, "Il te reste "+joueurEnCours.getNombreTroupesDeploiement()+" troupes a placer !");
			break;
		case 7:
			StdDraw.text(0.5, 0.97, "Il te reste "+joueurEnCours.getNombreCanonsDeploiement()+" canons a placer !");
			break;
		case 8:
			StdDraw.text(0.5, 0.97, "Il te reste "+joueurEnCours.getNombreCavaliersDeploiement()+" cavaliers a placer !");
			break;
		case 9:
			StdDraw.text(0.5, 0.97, "Il te reste "+joueurEnCours.getNombreSoldatsDeploiement()+" soldats a placer !");
			break;
		case 10:
			StdDraw.text(0.5, 0.97, "Territoire selectionne: "+territoire2.getNom());
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
	public void infosBas(int message)
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
	 * Reset l'affichage du plateau de jeu
	 * @param mode 0: Deploiement // 1: Choix // 2: Croix //3: Mision //4: Lettre Mission//5: Maj affichage deploiement//6: Maj affichage combat
	 */
	public void reset(int mode)
	{
		
		switch(mode)
		{
		case 0:
		{
			coucheDeploiement();
			break;
		}
		case  1:
		{
			loadPlateau();
			coucheChoix();
			break;
		}
		case  2:
		{
			loadPlateau();
			croix();
			break;
		}
		case  3:
		{
			coucheMission(joueurEnCours.getMission().getIndex());
			break;
		}
		case  4:
		{
			coucheMissionLettre();
			break;
		}
		case  5:
		{
			maj(territoire1);
			break;
		}
		case  6:
		{
			loadPlateau();
			maj(territoire1);
			maj(territoire2);
			croix();
			break;
		}
			
		}
	}

	/**
	 * Affiche la couche des pions avec le nombre d'unitees correspondant a chaque pion
	 */
	public void couchePions()
	{
		
		if(Jeu.map == 0)
		{
			
			for(int i = 0; i < cooTerritoiresClassique.length;i++)
			{
				jeton(cooTerritoiresClassique[i][0], cooTerritoiresClassique[i][1],Jeu.listeTerritoires.get(i).getProprietaire().getNumeroDeJoueur(),i);
			}

		}
		else if(Jeu.map == 1)
		{
			for(int i = 0; i < cooTerritoiresGOT.length;i++)
			{
				jeton(cooTerritoiresGOT[i][0], cooTerritoiresGOT[i][1],Jeu.listeTerritoires.get(i).getProprietaire().getNumeroDeJoueur(),i);
			}
		}

	}

	/**
	 * Met à jour l'affichage d'un territoire
	 * @param territoire
	 */
	public void maj(Territoire territoire)
	{
		int index = territoire.getIndexTerritoire();
		
		String nom = "img/mask/"+index+".png";
		
		StdDraw.picture(0.5, 0.5, nom);
		jeton(cooTerritoiresClassique[index][0], cooTerritoiresClassique[index][1],territoire.getProprietaire().getNumeroDeJoueur(),index);
	}

	/**
	 * Ecran de victoire
	 */
	public void coucheVictoire()
	{
		StdDraw.clear();

		StdDraw.picture(0.5, 0.5, "img/victoire.png");
		StdDraw.setPenColor(StdDraw.BLACK);

		int index = 0;
		
		if(Jeu.listeJoueurs.size() > 1)
		{
			index = Jeu.tour;
		}

		StdDraw.text(0.86, 0.255,"Victoire de "+Jeu.listeJoueurs.get(index).getNom());
		
		StdDraw.text(0.5, 0.55, Jeu.listeJoueurs.get(index).getMission().getIntitule());
	}

	/**
	 * Infos de deploiement
	 */
	public void infosDeploiement()
	{
		if(joueurEnCours.getNombreCanonsDeploiement() > 0)
		{
			infosHaut(7);
		}
		else if(joueurEnCours.getNombreCavaliersDeploiement() > 0)
		{
			infosHaut(8);
		}
		else if(joueurEnCours.getNombreSoldatsDeploiement() > 0)
		{
			infosHaut(9);
		}
	}

	/**
	 * Affiche les fenetres de confirmation d'attaque
	 */
	public void confirmationAttaque()
	{
		StdDraw.picture(0.24, 0.5, "img/troupesDefense.png");
		StdDraw.text(0.1, 0.35, ""+territoire2.getNombreSoldats());
		StdDraw.text(0.24, 0.35, ""+territoire2.getNombreCavaliers());
		StdDraw.text(0.38, 0.35, ""+territoire2.getNombreCanons());

		StdDraw.picture(0.76, 0.5, "img/troupesAttaque.png");
		StdDraw.text(0.62, 0.35, ""+territoire1.getNombreSoldats());
		StdDraw.text(0.76, 0.35, ""+territoire1.getNombreCavaliers());
		StdDraw.text(0.9, 0.35, ""+territoire1.getNombreCanons());
	}

	/**
	 * Affiche la fenetre de depalcement
	 */
	public void coucheDeplacement()
	{
		StdDraw.picture(0.5, 0.5, "img/troupesDeplacement.png");
		StdDraw.text(0.37, 0.25, ""+territoire1.getNombreSoldats());
		StdDraw.text(0.5, 0.25, ""+territoire1.getNombreCavaliers());
		StdDraw.text(0.63, 0.25, ""+territoire1.getNombreCanons());
	}

	/**
	 * Affiche la fenetre de mission
	 * @param index index de la mission
	 */
	public void coucheMission(int index)
	{
		StdDraw.picture(0.5, 0.5, "img/mission.png");
		StdDraw.text(0.5, 0.5, joueurEnCours.getMission().getIntitule());
	}

	/**
	 * Affiche la lettre contenant la mission
	 */
	public void coucheMissionLettre()
	{
		StdDraw.picture(0.5, 0.5, "img/lettreMission.png");
	}
	//AFFICHAGE

	
	//COUCHES
	/**
	 * Permet au programme de savoir sur quel couche il doit travailler
	 * @param couche
	 * 0: Menu
	 * 1: Nombre de joueurs
	 * 2: Noms des joueurs
	 * 3: Deploiment
	 * 4: Choix d'action
	 * 5: Attaque/deplacement
	 * 6: Choix troupes
	 * 7: Confirmation attaque
	 * 8: Deplacement
	 * 9: Lettre de mission
	 * 10: Mission
	 * 11: Choix cartes
	 * 12: IA
	 */
	public void cliqueCouche(int couche)
	{
		sourisX = 0;
		sourisY = 0;

		switch(couche)
		{

		case 0:

			while(cliqueMenu(sourisX, sourisY))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
				
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}

			break;

		case 1:
			
			Jeu.saisieTexte = true;

			String nombreJoueur = new String();

			while(Jeu.saisieTexte)
			{
				if(StdDraw.hasNextKeyTyped())
				{
					char touche = StdDraw.nextKeyTyped();
					double curseur = 0.5;
					if(touche == '!')
					{
						Jeu.saisieTexte = false;
					}
					else if(touche == 'ù')
					{
						nombreJoueur = nombreJoueur.substring(0, nombreJoueur.length()-1);

						StdDraw.setPenColor(StdDraw.WHITE);
						StdDraw.filledRectangle(curseur, 0.45, 0.03, 0.03);
						curseur = curseur - 0.02;
						StdDraw.setPenColor(StdDraw.BLACK);
					}
					else
					{
						nombreJoueur = nombreJoueur +  touche;

						StdDraw.text(curseur, 0.45, ""+touche);
						curseur = curseur +0.02;
					}
				}
			}
			
			Jeu.nombreJoueurs = Integer.parseInt(nombreJoueur);
			
			if((Jeu.nombreJoueurs > 6 || Jeu.nombreJoueurs < 2) && Jeu.ia == false)
			{
				Jeu.nombreJoueurs = 2;
			}
			this.couche = 2;//Nom joueurs
			ecranNomJoueurs();

			break;

		case 2:

			for(int i = 1;i<=Jeu.nombreJoueurs;i++)
			{
				Jeu.saisieTexte = true;
				String nomJoueur = new String();
				double curseur = 0.3;

				StdDraw.text(0.4, 0.7, ""+i);

				while(Jeu.saisieTexte)
				{
					if(StdDraw.hasNextKeyTyped())
					{
						char touche = StdDraw.nextKeyTyped();
						if(touche == '!')
						{
							Jeu.saisieTexte = false;
						}
						else if(touche == 'ù')
						{
							nomJoueur = nomJoueur.substring(0, nomJoueur.length()-1);

							StdDraw.setPenColor(StdDraw.WHITE);
							StdDraw.filledRectangle(curseur, 0.45, 0.03, 0.03);
							curseur = curseur - 0.02;
							StdDraw.setPenColor(StdDraw.BLACK);
						}
						else
						{
							nomJoueur = nomJoueur +  touche;

							StdDraw.text(curseur, 0.45, ""+touche);
							curseur = curseur +0.02;
						}
					}
				}

				Jeu.listeJoueurs.add(new Joueur(nomJoueur,i-1));
				
				
				//Jeu.listeJoueurs.add(new IA(nomJoueur,i-1));
				ecranNomJoueurs();
			}
			
			/*if(Jeu.ia)
			{
				Jeu.nombreJoueurs++;
				Jeu.listeJoueurs.add(new IA("Patricia", Jeu.listeJoueurs.size()));
			}*/

			this.couche= 9;//Mission

			Jeu.creerTerritoires();
			Jeu.attributionTerritoire();
			Jeu.attributionMission();
			
			joueurEnCours = Jeu.listeJoueurs.get(Jeu.tour);
			joueurEnCours.combienTroupe(Jeu.debutPartie);

			StdDraw.setCanvasSize(this.xMax, this.yMax);
			
			couchePlateau();
			couchePions();
			

			savePlateau();
			//reset(4);//Mission lettre
			joueurEnCours = Jeu.listeJoueurs.get(Jeu.tour);
			joueurEnCours.combienTroupe(Jeu.debutPartie);


			break;

		case 3:

			while(cliqueDeploiement(sourisX,sourisY))
			{
				if(clique())
				{
					sourisX = this.sourisX();
					sourisY = this.sourisY();

					attendre();
				}
				
			}

			break;

		case 4:

			while(cliqueChoix(sourisX, sourisY))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
			}

			break;

		case 5:

			while(cliqueTerritoires(sourisX, sourisY,mode))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}



			break;

		case 6:

			while(cliqueChoixTroupes(sourisX,sourisY))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
			}


			break;

		case 7:

			while(cliqueConfirmationAttaque(sourisX,sourisY))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}

			break;

		case 8:

			while(cliqueDeplacement(sourisX,sourisY))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}

			break;

		case 9:
			
			while(cliqueMissionLettre(sourisX,sourisY))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}

			break;

		case 10:

			while(cliqueMission(sourisX,sourisY))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}

				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}
			
			break;
			
		case 11:
			
			while(cliqueCarte(sourisX,sourisY))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}
			
			break;
			
		case 12:
			
			//IA IA = (IA) Jeu.listeJoueurs.get(Jeu.listeJoueurs.size()-1);
			IA IA = (IA) joueurEnCours;
			
			IA.echange();
			
			IA.deploiement(this);

			while(IA.peutAttaquerOuDeplacer() && Main.jeu)
			{

				
				
				territoire1 = IA.territoireQuiAttaque(0);
				territoire2 = IA.territoireQuiDefend(territoire1, 0);
				

				
				IA.choixUnitesAttaque(territoire1);
				

				territoire1.attaque(territoire2);
				
				infosHaut(1);
				infosBas(-1);

				StdDraw.pause(2000);//On attend un petit peu avant d'afficher le resultat du combat
				
				infosHaut(1);

				
				if(territoire2.estConquis())
				{
					IA.ajouterTerritoireCapture();
					IA.deplacement(territoire1, territoire2);
				}

				
				StdDraw.pause(2000);
				
				territoire1.uniteCombat.clear();
				territoire2.uniteCombat.clear();

				territoire1.resetTroupes();
				
				maj(territoire1);
				maj(territoire2);
				
				
				
				Jeu.defaiteJoueur();
				Jeu.finPartie();
			}
				
			IA.deplaceUniteeBloquee(this);
			
			if(Main.jeu)
			{
				
				//this.couche = 3;//Deploiement
				//mode = 0;//Deploiement
				
				this.couche = 12;//Deploiement
				mode = 0;//Deploiement

				joueurEnCours.missionComplete();
				Jeu.resetDeplacement(joueurEnCours);
				Jeu.tourSuivant();
				
				joueurEnCours = Jeu.listeJoueurs.get(Jeu.tour);
				joueurEnCours.combienTroupe(Jeu.debutPartie);
				
				if(joueurEnCours.getNombreTroupesDeploiement() == 0)
				{
					joueurEnCours.setNombreTroupesDeploiement(1);
				}
				joueurEnCours.resetTerritoireCapture();
				
				//reset(0);//Deploiement
			}
			
		}
	}

	/**
	 * Hitbox du menu
	 * @param sourisX valeur en x de la souris
	 * @param sourisY valeur en y de la souris
	 * @return false si on a clique sur un element pertinent de la fenetre du menu true sinon
	 */
	public boolean cliqueMenu(double sourisX, double sourisY)
	{
		if((sourisX > 0.18 && sourisX < 0.82) && (sourisY > 0.51 && sourisY < 0.59))//Jeu multi hurisk
		{
			couche = 1;//Nombres joueurs
			ecranNombreJoueurs();
			return false;
		}
		else if((sourisX > 0.18 && sourisX < 0.82) && (sourisY > 0.39 && sourisY < 0.46))//Cartes
		{
			couche = 11;//Cartes
			
			ecranMap();
			return false;
		}
		else if((sourisX > 0.18 && sourisX < 0.82) && (sourisY > 0.24 && sourisY < 0.31))//IA
		{
			couche = 1;//Nombre joueurs
			Jeu.ia = true;
			
			ecranNombreJoueurs();
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Hitbox du choix des cartes
	 * @param sourisX coordonee en x de la souris
	 * @param sourisY coordonee en y de la souris
	 * @return
	 */
	public boolean cliqueCarte(double sourisX, double sourisY)
	{
		if((sourisX > 0.53 && sourisX < 0.79) && (sourisY > 0.48 && sourisY < 0.64))//Map classique
		{
			couche = 0;//Menu
			
			Jeu.map = 0;
			
			ecranMenu();
			return false;
		}
		else if((sourisX > 0.53 && sourisX < 0.79) && (sourisY > 0.17 && sourisY < 0.33))//Map GOT
		{
			couche = 0;//Menu
			
			Jeu.map = 1;
			
			ecranMenu();
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Hitbox du panneau de deploiement
	 * @param sourisX valeur en x de la souris
	 * @param sourisY valeur en y de la souris
	 * @return false si on a clique sur un element pertinent de la fenetre de l'interface true sinon
	 */
	public boolean cliqueDeploiement(double sourisX,double sourisY)
	{
		this.sourisX = 0;
		this.sourisY = 0;
		
		//System.out.println(joueurEnCours.nombreTroupesDeploiement);

		if(joueurEnCours.getNombreTroupesDeploiement()== 0)
		{
			mode = 0;//Deploiement
			couche = 5;//Modes

			loadPlateau();
			
			infosDeploiement();

			return false;
		}
		if((sourisX > 0.304 && sourisX < 0.425) && (sourisY > 0.227 && sourisY < 0.523))//Soldat
		{
			joueurEnCours.setNombreTroupesDeploiement(joueurEnCours.getNombreTroupesDeploiement() - 1);
			joueurEnCours.setNombreSoldatsDeploiement(joueurEnCours.getNombreSoldatsDeploiement() + 1);

			coucheDeploiement();

			return false;
		}
		else if((sourisX > 0.445 && sourisX < 0.56) && (sourisY > 0.227 && sourisY < 0.523))//Cavalier
		{
			if(joueurEnCours.getNombreTroupesDeploiement() > 2)
			{
				joueurEnCours.setNombreTroupesDeploiement(joueurEnCours.getNombreTroupesDeploiement() - 3);
				joueurEnCours.setNombreCavaliersDeploiement(joueurEnCours.getNombreCavaliersDeploiement() + 1);

				coucheDeploiement();

				return false;
			}
			else
			{
				infosBas(8);
				return true;
			}
		}
		else if((sourisX > 0.58 && sourisX < 0.69) && (sourisY > 0.227 && sourisY < 0.523))//Canon
		{

			if(joueurEnCours.getNombreTroupesDeploiement() > 6)
			{
				joueurEnCours.setNombreTroupesDeploiement(joueurEnCours.getNombreTroupesDeploiement() - 7);
				joueurEnCours.setNombreCanonsDeploiement(joueurEnCours.getNombreCanonsDeploiement() + 1);

				coucheDeploiement();

				return false;
			}
			else
			{
				infosBas(8);
				return true;
			}
		}
		else
		{
			return true;
		}
	}

	/**
	 * Hitbox de l'interface de choix
	 * @param sourisX valeur en x de la souris
	 * @param sourisY valeur en y de la souris
	 * @return false si on a clique sur un element pertinent de la fenetre de l'interface true sinon
	 */
	public boolean cliqueChoix(double sourisX, double sourisY)
	{
		if((sourisX > 0.22 && sourisX < 0.35) && (sourisY > 0.06 && sourisY < 0.11))//Attaquer
		{
			mode = 1;//Attaque 1
			couche = 5;//Modes

			reset(2);//Croix
			infosBas(0);

			return false;
		}
		else if((sourisX > 0.44 && sourisX < 0.57) && (sourisY > 0.06 && sourisY < 0.11))//Deplacer
		{
			mode = 3;//Deplacement 1
			couche = 5;//Modes

			reset(2);//Croix
			infosBas(0);

			return false;
		}
		else if((sourisX > 0.65 && sourisX < 0.80) && (sourisY > 0.06 && sourisY < 0.11))//Fin de tour
		{
			
			joueurEnCours.missionComplete();
			Jeu.resetDeplacement(joueurEnCours);
			Jeu.defaiteJoueur();
			Jeu.finPartie();
			Jeu.tourSuivant();
			
			if(Jeu.ia && Jeu.tour == Jeu.listeJoueurs.size()-1)
			{
				couche = 12;//IA
				loadPlateau();
			}
			else
			{
				couche = 3;//Deploiement
				mode = 0;//Deploiement
				reset(0);//Choix

			}
			
			joueurEnCours = Jeu.listeJoueurs.get(Jeu.tour);
			joueurEnCours.combienTroupe(Jeu.debutPartie);
			
			if(joueurEnCours.getNombreTroupesDeploiement() == 0)
			{
				joueurEnCours.setNombreTroupesDeploiement(1);
			}
			joueurEnCours.resetTerritoireCapture();
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Hitbox des territoires
	 * @param sourisX valeur en x de la souris
	 * @param sourisY valeur en y de la souris
	 * @param mode Mode de verification pour savoir ce qu'il faut verifier (ex: est-ce que le territoire appartient au joueur)
	 * @return false si on a clique sur un element pertinent de la fenetre de l'interface qui rempli les conditions du mode, true sinon
	 */
	public boolean cliqueTerritoires(double sourisX, double sourisY, int mode)
	{
		if(Jeu.map == 0)
		{
			if((sourisX > 0.35 && sourisX < 0.39) && (sourisY > 0.76 && sourisY < 0.78))//Island
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(0),mode);
			}
			else if((sourisX > 0.4 && sourisX < 0.47) && (sourisY > 0.72 && sourisY < 0.85))//Scandinavie
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(1),mode);
			}
			else if((sourisX > 0.3 && sourisX < 0.38) && (sourisY > 0.65 && sourisY < 0.68))//Grande-Bretagne
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(2),mode);
			}
			else if((sourisX > 0.34 && sourisX < 0.40) && (sourisY > 0.48 && sourisY < 0.61))//Europe de l'Ouest
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(3),mode);
			}
			else if((sourisX > 0.41 && sourisX < 0.47) && (sourisY > 0.55 && sourisY < 0.59))//Europe du Sud
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(4),mode);
			}
			else if((sourisX > 0.41 && sourisX < 0.46) && (sourisY > 0.62 && sourisY < 0.66))//Europe du Nord
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(5),mode);
			}
			else if((sourisX > 0.49 && sourisX < 0.55) && (sourisY > 0.72 && sourisY < 0.74))//Ukraine
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(6),mode);
			}
			else if((sourisX > 0.45 && sourisX < 0.49) && (sourisY > 0.41 && sourisY < 0.44))//Egypte
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(7),mode);
			}
			else if((sourisX > 0.36 && sourisX < 0.45) && (sourisY > 0.36 && sourisY < 0.38))//Afrique du Nord
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(8),mode);
			}
			else if((sourisX > 0.49 && sourisX < 0.54) && (sourisY > 0.30 && sourisY < 0.34))//Afrique de l'Est
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(9),mode);
			}
			else if((sourisX > 0.44 && sourisX < 0.49) && (sourisY > 0.255 && sourisY < 0.28))//Congo
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(10),mode);
			}
			else if((sourisX > 0.45 && sourisX < 0.49) && (sourisY > 0.13 && sourisY < 0.17))//Afrique du Sud
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(11),mode);
			}
			else if((sourisX > 0.54 && sourisX < 0.59) && (sourisY > 0.09 && sourisY < 0.2))//Madagascar
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(12),mode);
			}
			else if((sourisX > 0.51 && sourisX < 0.58) && (sourisY > 0.48 && sourisY < 0.51))//Moyen Orient
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(13),mode);
			}
			else if((sourisX > 0.62 && sourisX < 0.66) && (sourisY > 0.47 && sourisY < 0.49))//Inde
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(14),mode);
			}
			else if((sourisX > 0.56 && sourisX < 0.63) && (sourisY > 0.61 && sourisY < 0.63))//Afganistan
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(15),mode);
			}
			else if((sourisX > 0.59 && sourisX < 0.62) && (sourisY > 0.72 && sourisY < 0.75))//Oural
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(16),mode);
			}
			else if((sourisX > 0.63 && sourisX < 0.68) && (sourisY > 0.80 && sourisY < 0.82))//Siberie
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(17),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.73) && (sourisY > 0.43 && sourisY < 0.45))//Siam
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(18),mode);
			}
			else if((sourisX > 0.67 && sourisX < 0.72) && (sourisY > 0.54 && sourisY < 0.56))//Chine
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(19),mode);
			}
			else if((sourisX > 0.70 && sourisX < 0.75) && (sourisY > 0.836 && sourisY < 0.859))//Yakouti
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(20),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.75) && (sourisY > 0.63 && sourisY < 0.65))//Mongolie
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(21),mode);
			}
			else if((sourisX > 0.8 && sourisX < 0.84) && (sourisY > 0.61 && sourisY < 0.64))//Japon
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(22),mode);
			}
			else if((sourisX > 0.77 && sourisX < 0.83) && (sourisY > 0.83 && sourisY < 0.86))//Kamachatka
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(23),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.74) && (sourisY > 0.72 && sourisY < 0.74))//Irkutsk
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(24),mode);
			}
			else if((sourisX > 0.025 && sourisX < 0.07) && (sourisY > 0.8 && sourisY < 0.82))//Alaska
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(25),mode);
			}
			else if((sourisX > 0.07 && sourisX < 0.2) && (sourisY > 0.8 && sourisY < 0.83))//Territoires du Nord
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(26),mode);
			}
			else if((sourisX > 0.1 && sourisX < 0.15) && (sourisY > 0.73 && sourisY < 0.75))//Alberta
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(27),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.20) && (sourisY > 0.71 && sourisY < 0.73))//Ontario
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(28),mode);
			}
			else if((sourisX > 0.26 && sourisX < 0.33) && (sourisY > 0.85 && sourisY < 0.88))//Groenland
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(29),mode);
			}
			else if((sourisX > 0.22 && sourisX < 0.26) && (sourisY > 0.71 && sourisY < 0.73))//Quebec
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(30),mode);
			}
			else if((sourisX > 0.09 && sourisX < 0.16) && (sourisY > 0.63 && sourisY < 0.67))//Etats de l'Ouest
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(31),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.22) && (sourisY > 0.58 && sourisY < 0.62))//Etats de l'Est
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(32),mode);
			}
			else if((sourisX > 0.11 && sourisX < 0.17) && (sourisY > 0.45 && sourisY < 0.57))//Amerique Centrale
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(33),mode);
			}
			else if((sourisX > 0.16 && sourisX < 0.23) && (sourisY > 0.42 && sourisY < 0.45))//Venezuela
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(34),mode);
			}
			else if((sourisX > 0.23 && sourisX < 0.29) && (sourisY > 0.34 && sourisY < 0.37))//Bresil
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(35),mode);
			}
			else if((sourisX > 0.2 && sourisX < 0.23) && (sourisY > 0.30 && sourisY < 0.32))//Perou
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(36),mode);
			}
			else if((sourisX > 0.19 && sourisX < 0.25) && (sourisY > 0.21 && sourisY < 0.23))//Argentine
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(37),mode);
			}
			else if((sourisX > 0.68 && sourisX < 0.77) && (sourisY > 0.26 && sourisY < 0.32))//Indonesie
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(38),mode);
			}
			else if((sourisX > 0.78 && sourisX < 0.83) && (sourisY > 0.30 && sourisY < 0.35))//Nouvelle Guinnee
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(39),mode);
			}
			else if((sourisX > 0.73 && sourisX < 0.81) && (sourisY > 0.13 && sourisY < 0.18))//Australie de l'Ouest
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(40),mode);
			}
			else if((sourisX > 0.79 && sourisX < 0.86) && (sourisY > 0.17 && sourisY < 0.21))//Australie de l'Est
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(41),mode);
			}
			else if((sourisX > 0.89 && sourisX < 0.94) && (sourisY > 0.03 && sourisY < 0.15))//Annuler
			{
				savePlateau();
				
				reset(1);//Choix

				couche = 4;//Choix

				return false;
			}
			else
			{
				return true;
			}
		}
		else if(Jeu.map == 1)
		{
			if((sourisX > 0.18 && sourisX < 0.21) && (sourisY > 0.83 && sourisY < 0.85))//The Wall
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(0),mode);
			}
			else if((sourisX > 0.25 && sourisX < 0.28) && (sourisY > 0.83 && sourisY < 0.88))//Skagos
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(1),mode);
			}
			else if((sourisX > 0.2 && sourisX < 0.28) && (sourisY > 0.77 && sourisY < 0.81))//The Grev Cliffs
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(2),mode);
			}
			else if((sourisX > 0.08 && sourisX < 0.15) && (sourisY > 0.74 && sourisY < 0.79))//Wolfswood
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(3),mode);
			}
			else if((sourisX > 0.17 && sourisX < 0.23) && (sourisY > 0.71 && sourisY < 0.76))//Winterfell
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(4),mode);
			}
			else if((sourisX > 0.06 && sourisX < 0.13) && (sourisY > 0.67 && sourisY < 0.73))//The Rills
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(5),mode);
			}
			else if((sourisX > 0.09 && sourisX < 0.15) && (sourisY > 0.61 && sourisY < 0.65))//The Flint Cliff
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(6),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.18) && (sourisY > 0.66 && sourisY < 0.69))//The Neck
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(7),mode);
			}
			else if((sourisX > 0.19 && sourisX < 0.25) && (sourisY > 0.51 && sourisY < 0.60))//The Vale
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(8),mode);
			}
			else if((sourisX > 0.06 && sourisX < 0.1) && (sourisY > 0.54 && sourisY < 0.58))//Jron Islands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(9),mode);
			}
			else if((sourisX > 0.1 && sourisX < 0.15) && (sourisY > 0.52 && sourisY < 0.55))//Riverlands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(10),mode);
			}
			else if((sourisX > 0.17 && sourisX < 0.24) && (sourisY > 0.45 && sourisY < 0.50))//Crownlands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(11),mode);
			}
			else if((sourisX > 0.08 && sourisX < 0.16) && (sourisY > 0.44 && sourisY < 0.5))//Westerlands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(12),mode);
			}
			else if((sourisX > 0.08 && sourisX < 0.14) && (sourisY > 0.38 && sourisY < 0.41))//Shield Lands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(13),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.2) && (sourisY > 0.39 && sourisY < 0.44))//The Reach
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(14),mode);
			}
			else if((sourisX > 0.2 && sourisX < 0.25) && (sourisY > 0.36 && sourisY < 0.41))//Stromlands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(15),mode);
			}
			else if((sourisX > 0.06 && sourisX < 0.12) && (sourisY > 0.29 && sourisY < 0.35))//Whispering Sounds
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(16),mode);
			}
			else if((sourisX > 0.14 && sourisX < 0.18) && (sourisY > 0.3 && sourisY < 0.33))//Red Mountains
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(17),mode);
			}
			else if((sourisX > 0.18 && sourisX < 0.28) && (sourisY > 0.25 && sourisY < 0.29))//Dorne
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(18),mode);
			}
			else if((sourisX > 0.34 && sourisX < 0.38) && (sourisY > 0.54 && sourisY < 0.6))//Braavosian Coastland
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(19),mode);
			}
			else if((sourisX > 0.33 && sourisX < 0.38) && (sourisY > 0.45 && sourisY < 0.49))//Andalos
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(20),mode);
			}
			else if((sourisX > 0.38 && sourisX < 0.42) && (sourisY > 0.48 && sourisY < 0.54))//Fills Of Norvos
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(21),mode);
			}
			else if((sourisX > 0.43 && sourisX < 0.46) && (sourisY > 0.43 && sourisY < 0.52))//Qhoyne Lands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(22),mode);
			}
			else if((sourisX > 0.48 && sourisX < 0.54) && (sourisY > 0.46 && sourisY < 0.50))//Forrest Of Dohor
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(23),mode);
			}
			else if((sourisX > 0.38 && sourisX < 0.44) && (sourisY > 0.36 && sourisY < 0.41))//The Golden Fields
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(24),mode);
			}
			else if((sourisX > 0.34 && sourisX < 0.43) && (sourisY > 0.28 && sourisY < 0.32))//The Disputed Lands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(25),mode);
			}
			else if((sourisX > 0.46 && sourisX < 0.52) && (sourisY > 0.38 && sourisY < 0.44))//Rhoynian Veld
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(26),mode);
			}
			else if((sourisX > 0.45 && sourisX < 0.50) && (sourisY > 0.29 && sourisY < 0.33))//Sar Mell
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(27),mode);
			}
			else if((sourisX > 0.51 && sourisX < 0.57) && (sourisY > 0.33 && sourisY < 0.37))//Western Waste
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(28),mode);
			}
			else if((sourisX > 0.53 && sourisX < 0.56) && (sourisY > 0.21 && sourisY < 0.31))//Sea Of Sight
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(29),mode);
			}
			else if((sourisX > 0.56 && sourisX < 0.59) && (sourisY > 0.19 && sourisY < 0.25))//Elyria
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(30),mode);
			}
			else if((sourisX > 0.53 && sourisX < 0.57) && (sourisY > 0.1 && sourisY < 0.17))//Valyria
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(31),mode);
			}
			else if((sourisX > 0.61 && sourisX < 0.69) && (sourisY > 0.32 && sourisY < 0.35))//Painted Mountains
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(32),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.73) && (sourisY > 0.26 && sourisY < 0.30))//Slaver's Bay
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(33),mode);
			}
			else if((sourisX > 0.74 && sourisX < 0.81) && (sourisY > 0.30 && sourisY < 0.34))//Lhazar
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(34),mode);
			}
			else if((sourisX > 0.83 && sourisX < 0.90) && (sourisY > 0.36 && sourisY < 0.4))//Samyrian Fills
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(35),mode);
			}
			else if((sourisX > 0.68 && sourisX < 0.75) && (sourisY > 0.16 && sourisY < 0.21))//Ghiscar
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(36),mode);
			}
			else if((sourisX > 0.75 && sourisX < 0.83) && (sourisY > 0.21 && sourisY < 0.27))//Red Waste
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(37),mode);
			}
			else if((sourisX > 0.84 && sourisX < 0.9) && (sourisY > 0.27 && sourisY < 0.32))//Bayasabhad
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(38),mode);
			}
			else if((sourisX > 0.84 && sourisX < 0.91) && (sourisY > 0.19 && sourisY < 0.25))//Qarth
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(39),mode);
			}
			else if((sourisX > 0.55 && sourisX < 0.61) && (sourisY > 0.5 && sourisY < 0.59))//Sarnor
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(40),mode);
			}
			else if((sourisX > 0.62 && sourisX < 0.69) && (sourisY > 0.46 && sourisY < 0.55))//Abandoned Lands
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(41),mode);
			}
			else if((sourisX > 0.70 && sourisX < 0.78) && (sourisY > 0.45 && sourisY < 0.53))//Kingdoms Of The Jfeqevron
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(42),mode);
			}
			else if((sourisX > 0.75 && sourisX < 0.87) && (sourisY > 0.54 && sourisY < 0.61))//The Footprint
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(43),mode);
			}
			else if((sourisX > 0.79 && sourisX < 0.87) && (sourisY > 0.70 && sourisY < 0.80))//Jbben
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(44),mode);
			}
			else if((sourisX > 0.89 && sourisX < 0.96) && (sourisY > 0.49 && sourisY < 0.61))//Realms Of Jhogrvin
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(45),mode);
			}
			else if((sourisX > 0.8 && sourisX < 0.87) && (sourisY > 0.48 && sourisY < 0.52))//Vaes Dothrak
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(46),mode);
			}
			else if((sourisX > 0.57 && sourisX < 0.64) && (sourisY > 0.37 && sourisY < 0.44))//Parched Fields
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(47),mode);
			}
			else if((sourisX > 0.66 && sourisX < 0.75) && (sourisY > 0.36 && sourisY < 0.41))//Western Grass Sea
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(48),mode);
			}
			else if((sourisX > 0.76 && sourisX < 0.84) && (sourisY > 0.40 && sourisY < 0.44))//Easter Grass Sea
			{
				return Jeu.verifications(this,Jeu.listeTerritoires.get(49),mode);
			}
			else if((sourisX > 0.89 && sourisX < 0.94) && (sourisY > 0.03 && sourisY < 0.15))//Annuler
			{
				reset(1);//Choix

				couche = 4;//Choix

				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return true;
		}
	}

	/**
	 * Hitbox du choix des troupes d'attaque
	 * @param sourisX
	 * @param sourisY
	 * @return
	 */
	public boolean cliqueChoixTroupes(double sourisX, double sourisY)
	{

		this.sourisX = 0;
		this.sourisY = 0;

		if(territoire1.getNombreTroupesATT() == 0)
		{
			couche = 7;
			return false;
		}

		if((sourisX > 0.03 && sourisX < 0.23) && (sourisY > 0.19 && sourisY < 0.25))//Anuler
		{
			mode = 1;//Attaque etape 1
			couche = 5;//Choix territoire
			territoire1.resetTroupes();

			reset(2);
			infosBas(0);

			return false;
		}
		else if((sourisX > 0.56 && sourisX < 0.67) && (sourisY > 0.32 && sourisY < 0.61) && territoire1.getNombreSoldats() > 0  && territoire1.unitePeutAttaquer(0))//Soldat
		{
			territoire1.ajouterUniteCombat(0, 0);
			territoire1.setNombreTroupesATT(territoire1.getNombreTroupesATT() - 1);
			infosHaut(11);

			return false;
		}
		else if((sourisX > 0.70 && sourisX < 0.81) && (sourisY > 0.32 && sourisY < 0.61) && territoire1.getNombreCavaliers() > 0  && territoire1.unitePeutAttaquer(1))//Cavalier
		{
			territoire1.ajouterUniteCombat(1, 0);
			territoire1.setNombreTroupesATT(territoire1.getNombreTroupesATT() - 1);

			infosHaut(12);

			return false;
		}
		else if((sourisX > 0.83 && sourisX < 0.95) && (sourisY > 0.32 && sourisY < 0.61) && territoire1.getNombreCanons() > 0 && territoire1.unitePeutAttaquer(2))//Canon
		{
			territoire1.ajouterUniteCombat(2, 0);
			territoire1.setNombreTroupesATT(territoire1.getNombreTroupesATT() - 1);

			infosHaut(13);

			return false;
		}

		else if((sourisX > 0.24 && sourisX < 0.44) && (sourisY > 0.19 && sourisY < 0.25))//Confirmer
		{
			if(territoire1.uniteCombat.size() == 0)
			{
				infosBas(9);
				return true;
			}
			else
			{

				couche = 7;
				return false;
			}
		}

		else
		{
			return true;
		}
	}

	/**
	 *
	 * Hit box fenetres confirmation d'attaque
	 * @param sourisX valeur en x de la souris
	 * @param sourisY valeur en y de la souris
	 * @return true si on a clique sur un des deux boutons false sinon
	 */
	public boolean cliqueConfirmationAttaque(double sourisX, double sourisY)
	{
		if((sourisX > 0.03 && sourisX < 0.23) && (sourisY > 0.19 && sourisY < 0.25))//Anuler
		{
			mode = 1;//Attaque etape 1
			couche = 5;//Choix territoire
			territoire1.resetTroupes();

			reset(2);
			infosBas(0);

			return false;
		}
		else if((sourisX > 0.24 && sourisX < 0.44) && (sourisY > 0.19 && sourisY < 0.25))//Confirmer
		{
			mode = 1;//Attaque etape 1
			couche = 5;//Choix territoire

			territoire1.attaque(territoire2);

			infosHaut(1);
			infosBas(5000);

			StdDraw.pause(2000);//On attend un petit peu avant d'afficher le resultat du combat

			infosHaut(3);

			StdDraw.pause(3000);

			territoire1.uniteCombat.clear();
			territoire2.uniteCombat.clear();

			territoire1.resetTroupes();
			
			reset(6);
			
			savePlateau();

			infosHaut(2);
			infosBas(0);

			if(territoire2.estConquis())
			{
				coucheDeplacement();
				joueurEnCours.ajouterTerritoireCapture();
				couche = 8;
			}
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Hitbox du choix des troupes de deplacement
	 * @param sourisX
	 * @param sourisY
	 * @return
	 */
	public boolean cliqueDeplacement(double sourisX,double sourisY)
	{
		this.sourisX = 0;
		this.sourisY = 0;
		
		if((sourisX > 0.304 && sourisX < 0.425) && (sourisY > 0.227 && sourisY < 0.523) && territoire1.getNombreSoldats() > 0 && territoire1.peutAttaquerOuDeplacer())//Soldat
		{
			unitesDeDeplacement.add(territoire1.uniteDeplacement(0, 0));
			if(unitesDeDeplacement.get(unitesDeDeplacement.size()-1) == null)
			{
				unitesDeDeplacement.remove(unitesDeDeplacement.get((unitesDeDeplacement.size()-1)));
				infosBas(10);
			}
			else
			{
				infosHaut(11);
			}
		
			
		}
		else if((sourisX > 0.445 && sourisX < 0.56) && (sourisY > 0.227 && sourisY < 0.523) && territoire1.getNombreCavaliers() > 0 && territoire1.peutAttaquerOuDeplacer())//Cavalier
		{
			unitesDeDeplacement.add(territoire1.uniteDeplacement(1, 0));
			if(unitesDeDeplacement.get(unitesDeDeplacement.size()-1) == null)
			{
				unitesDeDeplacement.remove(unitesDeDeplacement.get((unitesDeDeplacement.size()-1)));
				infosBas(10);
			}
			else
			{
				infosHaut(12);
			}
		}
		else if((sourisX > 0.58 && sourisX < 0.69) && (sourisY > 0.227 && sourisY < 0.523) && territoire1.getNombreCanons() > 0 && territoire1.peutAttaquerOuDeplacer())//Canon
		{
			unitesDeDeplacement.add(territoire1.uniteDeplacement(2, 0));
			if(unitesDeDeplacement.get(unitesDeDeplacement.size()-1) == null)
			{
				unitesDeDeplacement.remove(unitesDeDeplacement.get((unitesDeDeplacement.size()-1)));
				infosBas(10);
			}
			else
			{
				infosHaut(13);
			}
			
		}
		
		
		if((sourisX > 0.30 && sourisX < 0.49) && (sourisY > 0.15 && sourisY < 0.21))//Anuler
		{

			couche = 5;
			mode = 3;

			reset(2);
			infosBas(0);
			
			for(int i = 0; i < unitesDeDeplacement.size(); i++)
			{
				switch(unitesDeDeplacement.get(i).getType())
				{
				case 0:
					territoire1.ajouterSoldats(1);
					break;
				case 1:
					territoire1.ajouterCavaliers(1);
					break;
				case 2:
					territoire1.ajouterCanons(1);
					break;
				}
			}

			unitesDeDeplacement.clear();
			
			return false;
		}
		else if((sourisX > 0.51 && sourisX < 0.69) && (sourisY > 0.15 && sourisY < 0.21))//Confirmer
		{
			if(unitesDeDeplacement.size() == 0)
			{
				infosBas(10);
				return false;
			}
			else
			{
				for(int i = 0;i < unitesDeDeplacement.size(); i ++)
				{
					territoire1.deplacement(territoire2, unitesDeDeplacement.get(i));
				}
				reset(6);

				couche = 5;
				mode = 3;

				unitesDeDeplacement.clear();
				
				return false;
			}
		}
		else
		{
			return true;
		}
		
	}

	/**
	 * Hitbox confirmation mission
	 * @param sourisX
	 * @param sourisY
	 * @return
	 */
	public boolean cliqueMission(double sourisX, double sourisY)
	{
		if((sourisX > 0.41 && sourisX < 0.59) && (sourisY > 0.19 && sourisY < 0.24))
		{
			mode = 0;
			couche = 3;

			reset(0);
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Hitbox lettre de mission
	 * @param sourisX
	 * @param sourisY
	 * @return
	 */
	public boolean cliqueMissionLettre(double sourisX, double sourisY)
	{
		if((sourisX > 0.3 && sourisX < 0.66) && (sourisY > 0.27 && sourisY < 0.68))
		{
			couche = 10;

			reset(3);//Mission intitule
			return false;
		}
		else
		{
			return true;
		}
	}
	//COUCHES


	//CLIQUE
	/**
	 *
	 * @return la position en x de la souris
	 */
	public double sourisX()
	{
		return StdDraw.mouseX();
	}

	/**
	 *
	 * @return la position en y de la souris
	 */
	public double sourisY()
	{
		return  StdDraw.mouseY();
	}

	/**
	 *
	 * @return true ou false selon s'il y a un clique ou non
	 */
	public boolean clique()
	{
		return StdDraw.isMousePressed();
	}

	/**
	 * Permet d'attendre que le joueur lache le clique
	 */
	public void attendre()
	{
		while(clique())
		{

		}
	}
	//CLIQUE

}
