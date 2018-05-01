import edu.princeton.cs.introcs.StdDraw;

public class Interface {

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
	*/
	private int couche;

	/*Modes
	 * 0 - Deploiement
	 * 1 - Mode choix du territoire pour attaquer
	 * 2 - Mode choix du territoire ï¿½ attaquer
	 * 3 - Mode dï¿½placement choix du territoire de dï¿½part
	 * 4 - Mode dï¿½placement choix du territoire d'arrivï¿½
	 */
	private int mode = 0;

	private boolean continuer = false;
	private boolean debut = true;

	private double sourisX;
	private double sourisY;
	private double curseur = 0.5;

	private Territoire territoire1;
	private Territoire territoire2;

	Jeu risk = Main.risk;

	/**
	 *
	 * @param xMax int largeur de la fenï¿½tre
	 * @param yMax int hauteur de la fenï¿½tre
	 * @param couche int couche sur laquelle on travail
	 */
	public Interface(int xMax,int yMax, int couche) {
		this.xMax = xMax;
		this.yMax = yMax;
		this.couche = couche;

		StdDraw.setXscale(0, this.xMax);
		StdDraw.setYscale(0, this.yMax);

	}


	//AFFICHAGE
	/**
	 * Affiche le menu de dï¿½marage
	 */
	public void ecranMenu()
	{
		StdDraw.clear();

		StdDraw.setCanvasSize(809, 801);

		StdDraw.picture(0.5, 0.5, "img/menu.png");
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

		if(risk.map == 0)
		{
			StdDraw.picture(0.5, 0.5, "img/riskmap.jpg");
		}
		else if(risk.map == 1)
		{
			StdDraw.picture(0.5, 0.5, "img/mapGOT.png");
		}
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
		StdDraw.text(0.06, 0.97, "img/TOUR:               "+risk.listeJoueurs.get(tour).getNom());
		pion(0.073,0.975,tour);
	}


	/**
	 * Interface qui permet de rï¿½cupï¿½rer les soldats, les cavaliers, les canons
	 */
	public void coucheDeploiement()
	{
		StdDraw.picture(0.5, 0.5, "img/cadreChoixTroupes.png");
		StdDraw.text(0.487, 0.729, ""+risk.nombreTroupesDeploiement);
	}


	/**
	 * Affiche la croix
	 */
	public void croix()
	{
		StdDraw.picture(0.9, 0.1, "img/croix.png");
	}


	/**
	 *
	 * @param posX position en x du centre
	 * @param posY position en y du centre
	 * @param couleur
	 * 1. bleu
	 * 2. rouge
	 * 3. jaune
	 * 4. rose
	 * 5. vert
	 * 6. gris
	 */
	public void pion(double posX, double posY, int couleur)
	{
		if(couleur == 0)
		{
			if(risk.map == 0)
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
			if(risk.map == 0)
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
			if(risk.map == 0)
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
			if(risk.map == 0)
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
			if(risk.map == 0)
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
			if(risk.map == 0)
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
	 * Affiche des informations en haut de l'ï¿½cran
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
			StdDraw.text(0.5, 0.97, "Territoire sï¿½lï¿½ctionnï¿½: "+territoire1.getNom());
			break;
		case 1://Attaque
			StdDraw.text(0.5, 0.97, territoire1.getNom()+" attaque "+territoire2.getNom()+" !");
			break;
		case 2:
			if(risk.listeGagnants.size() == 3)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+risk.listeGagnants.get(0).getProprietaire().getNom()+" Tour 2 VICTOIRE: "+risk.listeGagnants.get(1).getProprietaire().getNom()+" Tour 3 VICTOIRE: "+risk.listeGagnants.get(2));
			}
			else if(risk.listeGagnants.size() == 2)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+risk.listeGagnants.get(0).getProprietaire().getNom()+" Tour 2 VICTOIRE: "+risk.listeGagnants.get(1).getProprietaire().getNom());
			}
			else
			{
				StdDraw.text(0.5, 0.97, "Tour 1 VICTOIRE: "+risk.listeGagnants.get(0).getProprietaire().getNom());
			}
			break;
		case 3:
			int affiche;
			if(risk.uniteeATT.size() > risk.uniteeDEF.size())
			{
				affiche = risk.uniteeDEF.size();
			}
			else
			{
				affiche = risk.uniteeATT.size();
			}

			if(affiche == 2)
			{
				StdDraw.text(0.5, 0.97, "Tour 1 - ATT "+risk.uniteeATT.get(0).getScoreDES()+" / "+risk.uniteeDEF.get(0).getScoreDES()+" DEF  //  "+"Tour 2 - ATT "+risk.uniteeATT.get(1).getScoreDES()+" / "+risk.uniteeDEF.get(1).getScoreDES());
			}
			else
			{
				StdDraw.text(0.5, 0.97, "Tour 1 - ATT "+risk.uniteeATT.get(0).getScoreDES()+" / "+risk.uniteeDEF.get(0).getScoreDES()+" DEF");
			}
			break;
		case 4:
			StdDraw.text(0.5, 0.97, "Dï¿½placement depuis "+territoire1.getNom()+" vers "+territoire2.getNom());
			break;
		case 5:
			StdDraw.text(0.5, 0.97, "Il te reste "+risk.nombreTroupesDeploiement+" troupes ï¿½ placer !");
			break;
		case 7:
			StdDraw.text(0.5, 0.97, "Il te reste "+risk.nombreCanons+" canons ï¿½ placer !");
			break;
		case 8:
			StdDraw.text(0.5, 0.97, "Il te reste "+risk.nombreCavaliers+" cavaliers ï¿½ placer !");
			break;
		case 9:
			StdDraw.text(0.5, 0.97, "Il te reste "+risk.nombreSoldats+" soldats ï¿½ placer !");
			break;
		case 10:
			StdDraw.text(0.5, 0.97, "Territoire sï¿½lï¿½ctionnï¿½: "+territoire2.getNom());
			break;
		case 11:
			StdDraw.text(0.5, 0.97, "Tu as sï¿½lï¿½ctionï¿½ un soldat !");
			break;
		case 12:
			StdDraw.text(0.5, 0.97, "Tu as sï¿½lï¿½ctionï¿½ un cavalier !");
			break;
		case 13:
			StdDraw.text(0.5, 0.97, "Tu as sï¿½lï¿½ctionï¿½ un canon !");
			break;

		}



	}


	/**
	 * Affiche des informations en bas de l'ï¿½cran
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
			StdDraw.text(0.5, 0.03, "Sï¿½lï¿½ctione un de tes territoires");
			break;
		case 1:
			StdDraw.text(0.5, 0.03, "Sï¿½lï¿½ctione un territoire adverse");
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
			StdDraw.text(0.5, 0.03, "Tu n'as pas assez de troupes pour ï¿½a !");
			break;
		case 9:
			StdDraw.text(0.5, 0.03, "Selectionne tes troupes d'attaque d'abord !");
			break;
		case 10:
			StdDraw.text(0.5, 0.03, "Cette troupe a dï¿½jï¿½ effectuï¿½ tout ses dï¿½placements !");
			break;
		default:
			break;
		}
	}


	/**
	 * Reset l'affichage du plateau de jeu
	 * @param mode 0: Deploiement // 1: Choix // 2: Croix //3: Mision
	 */
	public void reset(int mode)
	{
		couchePlateau();
		couchePions();
		coucheTour(risk.tour);

		if(mode == 0)
		{
			coucheDeploiement();
		}
		else if(mode == 1)
		{
			coucheChoix();
		}
		else if(mode == 2)
		{
			croix();
		}
		else if(mode == 3)
		{
			coucheMission(risk.listeJoueurs.get(risk.tour).getMission().getIndex());
		}
		else if(mode == 4)
		{
			coucheMissionLettre();
		}
	}


	/**
	 * Affiche la couche des pions avec le nombre d'unitï¿½es correspondant ï¿½ chaque pion
	 */
	public void couchePions()
	{
		if(risk.map == 0)
		{
			pion(0.37, 0.77, risk.listeTerritoires.get(0).getProprietaire().getNumeroDeJoueur());//Island
			StdDraw.text(0.37,0.77, ""+risk.listeTerritoires.get(0).getNombreTroupesTotal());

			pion(0.44, 0.79, risk.listeTerritoires.get(1).getProprietaire().getNumeroDeJoueur());//Scandinavie
			StdDraw.text(0.44,0.79, ""+risk.listeTerritoires.get(1).getNombreTroupesTotal());

			pion(0.35, 0.66, risk.listeTerritoires.get(2).getProprietaire().getNumeroDeJoueur());//Grande-Bretagne
			StdDraw.text(0.35,0.66, ""+risk.listeTerritoires.get(2).getNombreTroupesTotal());

			pion(0.38, 0.55, risk.listeTerritoires.get(3).getProprietaire().getNumeroDeJoueur());//Europe de l'Ouest
			StdDraw.text(0.38,0.55, ""+risk.listeTerritoires.get(3).getNombreTroupesTotal());

			pion(0.44, 0.57, risk.listeTerritoires.get(4).getProprietaire().getNumeroDeJoueur());//Europe du Sud
			StdDraw.text(0.44,0.57, ""+risk.listeTerritoires.get(4).getNombreTroupesTotal());

			pion(0.44, 0.64, risk.listeTerritoires.get(5).getProprietaire().getNumeroDeJoueur());//Europe du Nord
			StdDraw.text(0.44,0.64, ""+risk.listeTerritoires.get(5).getNombreTroupesTotal());

			pion(0.53, 0.73, risk.listeTerritoires.get(6).getProprietaire().getNumeroDeJoueur());//Ukraine
			StdDraw.text(0.53,0.73, ""+risk.listeTerritoires.get(6).getNombreTroupesTotal());

			pion(0.47, 0.425, risk.listeTerritoires.get(7).getProprietaire().getNumeroDeJoueur());//Egypte
			StdDraw.text(0.47,0.425, ""+risk.listeTerritoires.get(7).getNombreTroupesTotal());

			pion(0.4, 0.37, risk.listeTerritoires.get(8).getProprietaire().getNumeroDeJoueur());//Afrique du Sud
			StdDraw.text(0.4,0.37, ""+risk.listeTerritoires.get(8).getNombreTroupesTotal());

			pion(0.52, 0.32, risk.listeTerritoires.get(9).getProprietaire().getNumeroDeJoueur());//Afrique de l'Est
			StdDraw.text(0.52,0.32, ""+risk.listeTerritoires.get(9).getNombreTroupesTotal());

			pion(0.47, 0.26, risk.listeTerritoires.get(10).getProprietaire().getNumeroDeJoueur());//Congo
			StdDraw.text(0.47,0.26, ""+risk.listeTerritoires.get(10).getNombreTroupesTotal());

			pion(0.47, 0.15, risk.listeTerritoires.get(11).getProprietaire().getNumeroDeJoueur());//Afrique du Sud
			StdDraw.text(0.47,0.15, ""+risk.listeTerritoires.get(11).getNombreTroupesTotal());

			pion(0.56, 0.15, risk.listeTerritoires.get(12).getProprietaire().getNumeroDeJoueur());//Madagascar
			StdDraw.text(0.56,0.15, ""+risk.listeTerritoires.get(12).getNombreTroupesTotal());

			pion(0.55, 0.49, risk.listeTerritoires.get(13).getProprietaire().getNumeroDeJoueur());//Moyen Orient
			StdDraw.text(0.55,0.49, ""+risk.listeTerritoires.get(13).getNombreTroupesTotal());

			pion(0.64, 0.48, risk.listeTerritoires.get(14).getProprietaire().getNumeroDeJoueur());//Inde
			StdDraw.text(0.64,0.48, ""+risk.listeTerritoires.get(14).getNombreTroupesTotal());

			pion(0.60, 0.62, risk.listeTerritoires.get(15).getProprietaire().getNumeroDeJoueur());//Afganistan
			StdDraw.text(0.60,0.62, ""+risk.listeTerritoires.get(15).getNombreTroupesTotal());

			pion(0.61, 0.73, risk.listeTerritoires.get(16).getProprietaire().getNumeroDeJoueur());//Oural
			StdDraw.text(0.61,0.73, ""+risk.listeTerritoires.get(16).getNombreTroupesTotal());

			pion(0.65, 0.81, risk.listeTerritoires.get(17).getProprietaire().getNumeroDeJoueur());//Sibï¿½rie
			StdDraw.text(0.65,0.81, ""+risk.listeTerritoires.get(17).getNombreTroupesTotal());

			pion(0.72, 0.44, risk.listeTerritoires.get(18).getProprietaire().getNumeroDeJoueur());//Siam
			StdDraw.text(0.72,0.44, ""+risk.listeTerritoires.get(18).getNombreTroupesTotal());

			pion(0.70, 0.55, risk.listeTerritoires.get(19).getProprietaire().getNumeroDeJoueur());//Chine
			StdDraw.text(0.70,0.55, ""+risk.listeTerritoires.get(19).getNombreTroupesTotal());

			pion(0.73, 0.84, risk.listeTerritoires.get(20).getProprietaire().getNumeroDeJoueur());//Yakouti
			StdDraw.text(0.73,0.84, ""+risk.listeTerritoires.get(20).getNombreTroupesTotal());

			pion(0.72, 0.64, risk.listeTerritoires.get(21).getProprietaire().getNumeroDeJoueur());//Mongolie
			StdDraw.text(0.72,0.64, ""+risk.listeTerritoires.get(21).getNombreTroupesTotal());

			pion(0.82, 0.62, risk.listeTerritoires.get(22).getProprietaire().getNumeroDeJoueur());//Japon
			StdDraw.text(0.82,0.62, ""+risk.listeTerritoires.get(22).getNombreTroupesTotal());

			pion(0.80, 0.84, risk.listeTerritoires.get(23).getProprietaire().getNumeroDeJoueur());//Kamchatka
			StdDraw.text(0.8,0.84, ""+risk.listeTerritoires.get(23).getNombreTroupesTotal());

			pion(0.72, 0.73, risk.listeTerritoires.get(24).getProprietaire().getNumeroDeJoueur());//Irkustk
			StdDraw.text(0.72,0.73, ""+risk.listeTerritoires.get(24).getNombreTroupesTotal());

			pion(0.04, 0.81, risk.listeTerritoires.get(25).getProprietaire().getNumeroDeJoueur());//Alaska
			StdDraw.text(0.04,0.81, ""+risk.listeTerritoires.get(25).getNombreTroupesTotal());

			pion(0.15, 0.81, risk.listeTerritoires.get(26).getProprietaire().getNumeroDeJoueur());//Territoires du Nord
			StdDraw.text(0.15,0.81, ""+risk.listeTerritoires.get(26).getNombreTroupesTotal());

			pion(0.13, 0.74, risk.listeTerritoires.get(27).getProprietaire().getNumeroDeJoueur());//Alberta
			StdDraw.text(0.13,0.74, ""+risk.listeTerritoires.get(27).getNombreTroupesTotal());

			pion(0.18, 0.72, risk.listeTerritoires.get(28).getProprietaire().getNumeroDeJoueur());//Ontario
			StdDraw.text(0.18,0.72, ""+risk.listeTerritoires.get(28).getNombreTroupesTotal());

			pion(0.30, 0.86, risk.listeTerritoires.get(29).getProprietaire().getNumeroDeJoueur());//Groenland
			StdDraw.text(0.30,0.86, ""+risk.listeTerritoires.get(29).getNombreTroupesTotal());

			pion(0.24, 0.72, risk.listeTerritoires.get(30).getProprietaire().getNumeroDeJoueur());//Quebec
			StdDraw.text(0.24,0.72, ""+risk.listeTerritoires.get(30).getNombreTroupesTotal());

			pion(0.13, 0.65, risk.listeTerritoires.get(31).getProprietaire().getNumeroDeJoueur());//Etats de L'Ouest
			StdDraw.text(0.13,0.65, ""+risk.listeTerritoires.get(31).getNombreTroupesTotal());

			pion(0.19, 0.60, risk.listeTerritoires.get(32).getProprietaire().getNumeroDeJoueur());//Etats de l'Est
			StdDraw.text(0.19,0.6, ""+risk.listeTerritoires.get(32).getNombreTroupesTotal());

			pion(0.14, 0.51, risk.listeTerritoires.get(33).getProprietaire().getNumeroDeJoueur());//Amï¿½rique Centrale
			StdDraw.text(0.14,0.51, ""+risk.listeTerritoires.get(33).getNombreTroupesTotal());

			pion(0.2, 0.44, risk.listeTerritoires.get(34).getProprietaire().getNumeroDeJoueur());//Venezuela
			StdDraw.text(0.2,0.44, ""+risk.listeTerritoires.get(34).getNombreTroupesTotal());

			pion(0.26, 0.35, risk.listeTerritoires.get(35).getProprietaire().getNumeroDeJoueur());//Brï¿½sil
			StdDraw.text(0.26,0.35, ""+risk.listeTerritoires.get(35).getNombreTroupesTotal());

			pion(0.22, 0.31, risk.listeTerritoires.get(36).getProprietaire().getNumeroDeJoueur());//Pï¿½rou
			StdDraw.text(0.22,0.31, ""+risk.listeTerritoires.get(36).getNombreTroupesTotal());

			pion(0.23, 0.22, risk.listeTerritoires.get(37).getProprietaire().getNumeroDeJoueur());//Argentine
			StdDraw.text(0.23,0.22, ""+risk.listeTerritoires.get(37).getNombreTroupesTotal());

			pion(0.73, 0.29, risk.listeTerritoires.get(38).getProprietaire().getNumeroDeJoueur());//Indonï¿½sie
			StdDraw.text(0.73,0.29, ""+risk.listeTerritoires.get(38).getNombreTroupesTotal());

			pion(0.81, 0.32, risk.listeTerritoires.get(39).getProprietaire().getNumeroDeJoueur());//Nouvelle Guinï¿½e
			StdDraw.text(0.81,0.32, ""+risk.listeTerritoires.get(39).getNombreTroupesTotal());

			pion(0.78, 0.15, risk.listeTerritoires.get(40).getProprietaire().getNumeroDeJoueur());//Australie de l'Ouest
			StdDraw.text(0.78,0.15, ""+risk.listeTerritoires.get(40).getNombreTroupesTotal());

			pion(0.83, 0.19, risk.listeTerritoires.get(41).getProprietaire().getNumeroDeJoueur());//Australie de l'Est
			StdDraw.text(0.83,0.19, ""+risk.listeTerritoires.get(41).getNombreTroupesTotal());
		}

	}


	/**
	 * Ecran de victoire
	 */
	public void coucheVictoire()
	{
		StdDraw.clear();

		StdDraw.picture(0.5, 0.5, "victoire.png");
		StdDraw.setPenColor(StdDraw.BLACK);

		int tour;

		if(risk.tour == 0)
		{
			tour = risk.listeJoueurs.size()-1;
		}
		else
		{
			tour = risk.tour-1;
		}
		StdDraw.text(0.86, 0.255,"Victoire de "+risk.listeJoueurs.get(tour).getNom());
	}




	/**
	 * Infos de deploiement
	 */
	public void infosDeploiement()
	{
		if(risk.nombreCanons > 0)
		{
			infosHaut(7);
		}
		else if(risk.nombreCavaliers > 0)
		{
			infosHaut(8);
		}
		else if(risk.nombreSoldats > 0)
		{
			infosHaut(9);
		}
	}




	/**
	 * Affiche les fenï¿½tres de confirmation d'attaque
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
	 * Affiche la fenï¿½tre de depalcement
	 */
	public void coucheDeplacement()
	{
		StdDraw.picture(0.5, 0.5, "img/troupesDeplacement.png");
		StdDraw.text(0.37, 0.25, ""+territoire1.getNombreSoldats());
		StdDraw.text(0.5, 0.25, ""+territoire1.getNombreCavaliers());
		StdDraw.text(0.63, 0.25, ""+territoire1.getNombreCanons());
	}


	/**
	 * Affiche la fenï¿½tre de mission
	 * @param index index de la mission
	 */
	public void coucheMission(int index)
	{
		StdDraw.picture(0.5, 0.5, "img/mission.png");
		StdDraw.text(0.5, 0.5, risk.listeJoueurs.get(risk.tour).getMission().getIntitule());
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
	 * 5: Attaque/dï¿½placement
	 * 6: Choix troupes
	 * 7: Confirmation attaque
	 * 8: Deplacement
	 * 9: Lettre de mission
	 * 10: Mission
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
			}

			break;

		case 1:

			continuer = true;

			String nombreJoueur = new String();

			while(continuer)
			{
				if(StdDraw.hasNextKeyTyped())
				{
					char touche = StdDraw.nextKeyTyped();

					if(touche == '!')
					{
						continuer = false;
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

			risk.nombreJoueurs = Integer.parseInt(nombreJoueur);
			this.couche = 2;//Nom joueurs
			ecranNomJoueurs();

			break;

		case 2:

			for(int i = 1;i<=risk.nombreJoueurs;i++)
			{
				continuer = true;
				String nomJoueur = new String();
				curseur = 0.5;

				StdDraw.text(0.4, 0.7, ""+i);

				while(continuer)
				{
					if(StdDraw.hasNextKeyTyped())
					{
						char touche = StdDraw.nextKeyTyped();
						if(touche == '!')
						{
							continuer = false;
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

				risk.listeJoueurs.add(new Joueur(nomJoueur,0,i-1,0,null));

				ecranNomJoueurs();
			}

			this.couche= 9;//Mission


			risk.attributionTerritoire();
			risk.attributionMission();
			risk.nombreTroupesDeploiement = risk.combienTroupe(risk.listeJoueurs.get(risk.tour),debut);


			StdDraw.setCanvasSize(this.xMax, this.yMax);


			reset(4);//Mission lettre


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

			while(cliqueTerritoires(sourisX, sourisY, risk.listeJoueurs.get(risk.tour),mode))
			{
				if(clique())
				{
					sourisX = sourisX();
					sourisY = sourisY();

					attendre();
				}
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
		}
	}




	/**
	 * Hitbox du menu
	 * @param sourisX valeur en x de la souris
	 * @param sourisY valeur en y de la souris
	 * @return false si on a cliquï¿½ sur un ï¿½lï¿½ment pertinent de la fenï¿½tre du menu true sinon
	 */
	public boolean cliqueMenu(double sourisX, double sourisY)
	{
		if((sourisX > 0.18 && sourisX < 0.82) && (sourisY > 0.51 && sourisY < 0.59))//Jeu multi hurisk
		{
			couche = 1;//Nombres joueurs

			ecranNombreJoueurs();
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
	 * @return false si on a cliquï¿½ sur un ï¿½lï¿½ment pertinent de la fenï¿½tre de l'interface true sinon
	 */
	public boolean cliqueDeploiement(double sourisX,double sourisY)
	{
		this.sourisX = 0;
		this.sourisY = 0;

		if(risk.nombreTroupesDeploiement == 0)
		{
			mode = 0;//Deploiement
			couche = 5;//Modes

			reset(-1);//Vide

			infosDeploiement();

			return false;
		}
		if((sourisX > 0.304 && sourisX < 0.425) && (sourisY > 0.227 && sourisY < 0.523))//Soldat
		{
			risk.nombreTroupesDeploiement--;
			risk.nombreSoldats++;

			coucheDeploiement();

			return false;
		}
		else if((sourisX > 0.445 && sourisX < 0.56) && (sourisY > 0.227 && sourisY < 0.523))//Cavalier
		{
			if(risk.nombreTroupesDeploiement > 2)
			{
				risk.nombreTroupesDeploiement = risk.nombreTroupesDeploiement - 3;
				risk.nombreCavaliers++;

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

			if(risk.nombreTroupesDeploiement > 6)
			{
				risk.nombreTroupesDeploiement = risk.nombreTroupesDeploiement - 7;
				risk.nombreCanons++;

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
	 * @return false si on a cliquï¿½ sur un ï¿½lï¿½ment pertinent de la fenï¿½tre de l'interface true sinon
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
		else if((sourisX > 0.44 && sourisX < 0.57) && (sourisY > 0.06 && sourisY < 0.11))//Dï¿½placer
		{
			mode = 3;//Deplacement 1
			couche = 5;//Modes

			reset(2);//Croix
			infosBas(0);

			return false;
		}
		else if((sourisX > 0.65 && sourisX < 0.80) && (sourisY > 0.06 && sourisY < 0.11))//Fin de tour
		{
			couche = 3;//Deploiement
			mode = 0;//Deploiement

			risk.missionComplete(risk.listeJoueurs.get(risk.tour));
			risk.resetDeplacement(risk.listeJoueurs.get(risk.tour));
			risk.defaiteJoueur();
			risk.finPartie();
			risk.tour(risk.tour);
			risk.nombreTroupesDeploiement = risk.combienTroupe(risk.listeJoueurs.get(risk.tour),debut);
			risk.listeJoueurs.get(risk.tour).resetTerritoireCapture();

			reset(0);//Choix

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
	 * @param mode Mode de verification pour savoir ce qu'il faut vï¿½rifier (ex: est-ce que le territoire appartient au joueur)
	 * @return false si on a cliquï¿½ sur un ï¿½lï¿½ment pertinent de la fenï¿½tre de l'interface qui rempli les conditions du mode, true sinon
	 */
	public boolean cliqueTerritoires(double sourisX, double sourisY, Joueur joueur, int mode)
	{
		if(risk.map == 0)
		{
			if((sourisX > 0.35 && sourisX < 0.39) && (sourisY > 0.76 && sourisY < 0.78))//Island
			{
				return verifications(joueur,risk.listeTerritoires.get(0),mode);
			}
			else if((sourisX > 0.4 && sourisX < 0.47) && (sourisY > 0.72 && sourisY < 0.85))//Scandinavie
			{
				return verifications(joueur,risk.listeTerritoires.get(1),mode);
			}
			else if((sourisX > 0.3 && sourisX < 0.38) && (sourisY > 0.65 && sourisY < 0.68))//Grande-Bretagne
			{
				return verifications(joueur,risk.listeTerritoires.get(2),mode);
			}
			else if((sourisX > 0.34 && sourisX < 0.40) && (sourisY > 0.48 && sourisY < 0.61))//Europe de l'Ouest
			{
				return verifications(joueur,risk.listeTerritoires.get(3),mode);
			}
			else if((sourisX > 0.41 && sourisX < 0.47) && (sourisY > 0.55 && sourisY < 0.59))//Europe du Sud
			{
				return verifications(joueur,risk.listeTerritoires.get(4),mode);
			}
			else if((sourisX > 0.41 && sourisX < 0.46) && (sourisY > 0.62 && sourisY < 0.66))//Europe du Nord
			{
				return verifications(joueur,risk.listeTerritoires.get(5),mode);
			}
			else if((sourisX > 0.49 && sourisX < 0.55) && (sourisY > 0.72 && sourisY < 0.74))//Ukraine
			{
				return verifications(joueur,risk.listeTerritoires.get(6),mode);
			}
			else if((sourisX > 0.45 && sourisX < 0.49) && (sourisY > 0.41 && sourisY < 0.44))//Egypte
			{
				return verifications(joueur,risk.listeTerritoires.get(7),mode);
			}
			else if((sourisX > 0.36 && sourisX < 0.45) && (sourisY > 0.36 && sourisY < 0.38))//Afrique du Nord
			{
				return verifications(joueur,risk.listeTerritoires.get(8),mode);
			}
			else if((sourisX > 0.49 && sourisX < 0.54) && (sourisY > 0.30 && sourisY < 0.34))//Afrique de l'Est
			{
				return verifications(joueur,risk.listeTerritoires.get(9),mode);
			}
			else if((sourisX > 0.44 && sourisX < 0.49) && (sourisY > 0.255 && sourisY < 0.28))//Congo
			{
				return verifications(joueur,risk.listeTerritoires.get(10),mode);
			}
			else if((sourisX > 0.45 && sourisX < 0.49) && (sourisY > 0.13 && sourisY < 0.17))//Afrique du Sud
			{
				return verifications(joueur,risk.listeTerritoires.get(11),mode);
			}
			else if((sourisX > 0.54 && sourisX < 0.59) && (sourisY > 0.09 && sourisY < 0.2))//Madagascar
			{
				return verifications(joueur,risk.listeTerritoires.get(12),mode);
			}
			else if((sourisX > 0.51 && sourisX < 0.58) && (sourisY > 0.48 && sourisY < 0.51))//Moyen Orient
			{
				return verifications(joueur,risk.listeTerritoires.get(13),mode);
			}
			else if((sourisX > 0.62 && sourisX < 0.66) && (sourisY > 0.47 && sourisY < 0.49))//Inde
			{
				return verifications(joueur,risk.listeTerritoires.get(14),mode);
			}
			else if((sourisX > 0.56 && sourisX < 0.63) && (sourisY > 0.61 && sourisY < 0.63))//Afganistan
			{
				return verifications(joueur,risk.listeTerritoires.get(15),mode);
			}
			else if((sourisX > 0.59 && sourisX < 0.62) && (sourisY > 0.72 && sourisY < 0.75))//Oural
			{
				return verifications(joueur,risk.listeTerritoires.get(16),mode);
			}
			else if((sourisX > 0.63 && sourisX < 0.68) && (sourisY > 0.80 && sourisY < 0.82))//Sibï¿½rie
			{
				return verifications(joueur,risk.listeTerritoires.get(17),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.73) && (sourisY > 0.43 && sourisY < 0.45))//Siam
			{
				return verifications(joueur,risk.listeTerritoires.get(18),mode);
			}
			else if((sourisX > 0.67 && sourisX < 0.72) && (sourisY > 0.54 && sourisY < 0.56))//Chine
			{
				return verifications(joueur,risk.listeTerritoires.get(19),mode);
			}
			else if((sourisX > 0.70 && sourisX < 0.75) && (sourisY > 0.836 && sourisY < 0.859))//Yakouti
			{
				return verifications(joueur,risk.listeTerritoires.get(20),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.75) && (sourisY > 0.63 && sourisY < 0.65))//Mongolie
			{
				return verifications(joueur,risk.listeTerritoires.get(21),mode);
			}
			else if((sourisX > 0.8 && sourisX < 0.84) && (sourisY > 0.61 && sourisY < 0.64))//Japon
			{
				return verifications(joueur,risk.listeTerritoires.get(22),mode);
			}
			else if((sourisX > 0.77 && sourisX < 0.83) && (sourisY > 0.83 && sourisY < 0.86))//Kamachatka
			{
				return verifications(joueur,risk.listeTerritoires.get(23),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.74) && (sourisY > 0.72 && sourisY < 0.74))//Irkutsk
			{
				return verifications(joueur,risk.listeTerritoires.get(24),mode);
			}
			else if((sourisX > 0.025 && sourisX < 0.07) && (sourisY > 0.8 && sourisY < 0.82))//Alaska
			{
				return verifications(joueur,risk.listeTerritoires.get(25),mode);
			}
			else if((sourisX > 0.07 && sourisX < 0.2) && (sourisY > 0.8 && sourisY < 0.83))//Territoires du Nord
			{
				return verifications(joueur,risk.listeTerritoires.get(26),mode);
			}
			else if((sourisX > 0.1 && sourisX < 0.15) && (sourisY > 0.73 && sourisY < 0.75))//Alberta
			{
				return verifications(joueur,risk.listeTerritoires.get(27),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.20) && (sourisY > 0.71 && sourisY < 0.73))//Ontario
			{
				return verifications(joueur,risk.listeTerritoires.get(28),mode);
			}
			else if((sourisX > 0.26 && sourisX < 0.33) && (sourisY > 0.85 && sourisY < 0.88))//Groenland
			{
				return verifications(joueur,risk.listeTerritoires.get(29),mode);
			}
			else if((sourisX > 0.22 && sourisX < 0.26) && (sourisY > 0.71 && sourisY < 0.73))//Quebec
			{
				return verifications(joueur,risk.listeTerritoires.get(30),mode);
			}
			else if((sourisX > 0.09 && sourisX < 0.16) && (sourisY > 0.63 && sourisY < 0.67))//Etats de l'Ouest
			{
				return verifications(joueur,risk.listeTerritoires.get(31),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.22) && (sourisY > 0.58 && sourisY < 0.62))//Etats de l'Est
			{
				return verifications(joueur,risk.listeTerritoires.get(32),mode);
			}
			else if((sourisX > 0.11 && sourisX < 0.17) && (sourisY > 0.45 && sourisY < 0.57))//Amï¿½rique Centrale
			{
				return verifications(joueur,risk.listeTerritoires.get(33),mode);
			}
			else if((sourisX > 0.16 && sourisX < 0.23) && (sourisY > 0.42 && sourisY < 0.45))//Venezuela
			{
				return verifications(joueur,risk.listeTerritoires.get(34),mode);
			}
			else if((sourisX > 0.23 && sourisX < 0.29) && (sourisY > 0.34 && sourisY < 0.37))//Brï¿½sil
			{
				return verifications(joueur,risk.listeTerritoires.get(35),mode);
			}
			else if((sourisX > 0.2 && sourisX < 0.23) && (sourisY > 0.30 && sourisY < 0.32))//Pï¿½rou
			{
				return verifications(joueur,risk.listeTerritoires.get(36),mode);
			}
			else if((sourisX > 0.19 && sourisX < 0.25) && (sourisY > 0.21 && sourisY < 0.23))//Argentine
			{
				return verifications(joueur,risk.listeTerritoires.get(37),mode);
			}
			else if((sourisX > 0.68 && sourisX < 0.77) && (sourisY > 0.26 && sourisY < 0.32))//Indonï¿½sie
			{
				return verifications(joueur,risk.listeTerritoires.get(38),mode);
			}
			else if((sourisX > 0.78 && sourisX < 0.83) && (sourisY > 0.30 && sourisY < 0.35))//Nouvelle Guinnï¿½e
			{
				return verifications(joueur,risk.listeTerritoires.get(39),mode);
			}
			else if((sourisX > 0.73 && sourisX < 0.81) && (sourisY > 0.13 && sourisY < 0.18))//Australie de l'Ouest
			{
				return verifications(joueur,risk.listeTerritoires.get(40),mode);
			}
			else if((sourisX > 0.79 && sourisX < 0.86) && (sourisY > 0.17 && sourisY < 0.21))//Australie de l'Est
			{
				return verifications(joueur,risk.listeTerritoires.get(41),mode);
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

		if(risk.nombreTroupesATT == 0)
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
		else if((sourisX > 0.56 && sourisX < 0.67) && (sourisY > 0.32 && sourisY < 0.61) && territoire1.getNombreSoldats() > 0)//Soldat
		{
			risk.uniteeATT.add(risk.ajouterUnite(0, 0, risk.uniteeATT, territoire1));
			risk.nombreTroupesATT--;
			territoire1.ajouterSoldats(-1);

			infosHaut(11);

			return false;
		}
		else if((sourisX > 0.70 && sourisX < 0.81) && (sourisY > 0.32 && sourisY < 0.61) && territoire1.getNombreCavaliers() > 0)//Cavalier
		{
			risk.uniteeATT.add(risk.ajouterUnite(1, 0, risk.uniteeATT, territoire1));
			risk.nombreTroupesATT--;
			territoire1.ajouterCavaliers(-1);

			infosHaut(12);

			return false;
		}
		else if((sourisX > 0.83 && sourisX < 0.95) && (sourisY > 0.32 && sourisY < 0.61) && territoire1.getNombreCanons() > 0)//Canon
		{
			risk.uniteeATT.add(risk.ajouterUnite(2, 0, risk.uniteeATT, territoire1));
			risk.nombreTroupesATT--;
			territoire1.ajouterCanons(-1);

			infosHaut(13);

			return false;
		}

		else if((sourisX > 0.24 && sourisX < 0.44) && (sourisY > 0.19 && sourisY < 0.25))//Confirmer
		{
			if(risk.uniteeATT.size() == 0)
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
	 * @return true si on a cliquï¿½ sur un des deux boutons false sinon
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

			risk.attaque(territoire1, territoire2);

			infosHaut(1);
			infosBas(5000);

			StdDraw.pause(2000);//On attend un petit peu avant d'afficher le rï¿½sultat du combat

			infosHaut(3);

			StdDraw.pause(3000);

			reset(2);//Croix

			infosHaut(2);
			infosBas(0);

			risk.uniteeATT.clear();
			risk.uniteeDEF.clear();

			territoire1.resetTroupes();

			if(territoire2.estConquis())
			{
				coucheDeplacement();
				risk.listeJoueurs.get(risk.tour).ajouterTerritoireCapture();
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


		if((sourisX > 0.304 && sourisX < 0.425) && (sourisY > 0.227 && sourisY < 0.523) && territoire1.getNombreSoldats() > 0 && territoire1.peutAttaquer())//Soldat
		{
			Unitee deplacement = territoire1.uniteDeplacement(0, 0);

			if(deplacement == null)
			{
				infosBas(10);
				return false;
			}
			else
			{
				territoire1.supprimerUnite(deplacement,0);
				deplacement.setNombreDeplacement(deplacement.getNombreDeplacement() + 1);
				territoire2.ajouterUnite(deplacement);

				territoire1.ajouterTroupe(-1);
				territoire1.ajouterSoldats(-1);
				territoire2.ajouterTroupe(1);
				territoire2.ajouterSoldats(1);

				reset(2);

				couche = 5;
				mode = 3;

				return false;
			}

		}
		else if((sourisX > 0.445 && sourisX < 0.56) && (sourisY > 0.227 && sourisY < 0.523) && territoire1.getNombreCavaliers() > 0 && territoire1.peutAttaquer())//Cavalier
		{
			Unitee deplacement = territoire1.uniteDeplacement(1, 0);

			if(deplacement == null)
			{
				infosBas(10);
				return false;
			}
			else
			{
				territoire1.supprimerUnite(deplacement, 0);
				deplacement.setNombreDeplacement(deplacement.getNombreDeplacement() + 1);
				territoire2.ajouterUnite(deplacement);

				territoire1.ajouterTroupe(-3);
				territoire1.ajouterCavaliers(-1);
				territoire2.ajouterTroupe(3);
				territoire2.ajouterCavaliers(1);

				reset(2);

				couche = 5;
				mode = 3;

				return false;
			}

		}
		else if((sourisX > 0.58 && sourisX < 0.69) && (sourisY > 0.227 && sourisY < 0.523) && territoire1.getNombreCanons() > 0 && territoire1.peutAttaquer())//Canon
		{
			Unitee deplacement = territoire1.uniteDeplacement(2, 0);

			if(deplacement == null)
			{
				infosBas(10);
				return false;
			}
			else
			{
				territoire1.supprimerUnite(deplacement,0);
				deplacement.setNombreDeplacement(deplacement.getNombreDeplacement() + 1);
				territoire2.ajouterUnite(deplacement);

				territoire1.ajouterTroupe(-7);
				territoire1.ajouterCanons(-1);
				territoire2.ajouterTroupe(7);
				territoire2.ajouterCanons(1);

				reset(2);

				couche = 5;
				mode = 3;

				return false;
			}

		}
		else if((sourisX > 0.4 && sourisX < 0.59) && (sourisY > 0.16 && sourisY < 0.20))
		{

			couche = 5;
			mode = 3;

			reset(2);
			infosBas(0);

			return false;
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

			reset(3);//Mission intitulï¿½
			return false;
		}
		else
		{
			return true;
		}
	}
	//COUCHES


	//DIVERS
	/**
	 * Mï¿½thodes regroupant tous les types de verifications nescessaires au bon fonctionnement du jeu
	 * @param joueur Joueur le joueur sur lequel on fait la vï¿½rification
	 * @param territoire Territoire le territoire sur lequel on fait la vï¿½rification
	 * @param mode indice de la verification qu'on veut faire
	 * @return true ou false selon ce qu'on vï¿½rifie, il n'y a pas de sortie "type"
	 */
	public boolean verifications(Joueur joueur, Territoire territoire, int mode)
	{
		//Ces deux lignes sont pour corriger le bug du StdDraw.isMousePressed()
		sourisX = 0;
		sourisY = 0;

		switch(mode)
		{
		case 0:	//Deploiment


			if(territoire.appartientA(joueur) && (risk.nombreSoldats > 0 || risk.nombreCavaliers > 0 || risk.nombreCanons > 0))
			{
				risk.deploiement(territoire);


				if(risk.nombreSoldats == 0 && risk.nombreCavaliers == 0 && risk.nombreCanons == 0 && debut)
				{
					risk.tour(risk.tour);
					risk.nombreTroupesDeploiement = risk.combienTroupe(risk.listeJoueurs.get(risk.tour),debut);


					if(debut)
					{
						couche = 9;
						reset(4);
					}
					else
					{
						couche = 3;//Deploiement
						this.mode = 0;
						reset(0);
					}


					return false;
				}
				else if(risk.nombreSoldats == 0 && risk.nombreCavaliers == 0 && risk.nombreCanons == 0)
				{
					couche = 4;//Choix

					reset(1);//Choix

					return false;
				}
				else
				{
					reset(-1);//Vide
				}

				infosDeploiement();

				return false;
			}
			else
			{
				infosBas(6);
				return true;
			}


		case 1://Selection du territoire duquel le joueur attaque

			if(territoire.appartientA(joueur) && territoire.peutAttaquer())
			{
				territoire1 = territoire;
				this.mode = 2;//Attaque etape 2

				infosHaut(0);
				infosBas(1);
				return false;
			}
			else
			{
				infosBas(2);
				return true;
			}

		case 2://Selection du territoire qu'il attaque

			if(territoire.appartientA(joueur) == false && territoire.estAdjacentA(territoire1))
			{
				territoire2 = territoire;
				couche = 6;

				risk.nombreTroupesATT = territoire1.nombreDesATT();

				infosHaut(10);
				confirmationAttaque();

				return false;
			}
			else if(territoire.estAdjacentA(territoire1) != true)
			{
				infosBas(4);
				return true;
			}
			else
			{
				infosBas(3);
				return true;
			}

		case 3://Selection du territoire duquel le joueur fait le dï¿½placement

			if(territoire.appartientA(joueur) && territoire.peutAttaquer())
			{
				territoire1 = territoire;
				this.mode = 4;//Deplacement etape 2

				infosHaut(0);
				infosBas(0);
				return false;
			}
			else
			{
				infosBas(5);
				return true;
			}

		case 4://Selection du territoire sur lequel on fait le dï¿½placement

			if(territoire.appartientA(joueur) && territoire.estAdjacentA(territoire1))
			{
				territoire2 = territoire;
				couche = 8;

				coucheDeplacement();
				return false;
			}
			else
			{
				infosBas(6);
				return true;
			}

		default:
			return false;
	}

}
	//DIVERS

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


	//Getters Setters
	public int getxMax() {
		return xMax;
	}

	public void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public int getyMax() {
		return yMax;
	}

	public void setyMax(int yMax) {
		this.yMax = yMax;
	}


	public int getCouche() {
		return couche;
	}


	public void setCouche(int couche) {
		this.couche = couche;
	}

	public void setDebut(boolean debut)
	{
		this.debut = debut;
	}

}
