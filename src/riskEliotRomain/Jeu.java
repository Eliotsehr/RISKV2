package riskEliotRomain;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.introcs.StdDraw;

public class Jeu {
	
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
	public int couche = 0;

	/*Modes
	 * 0 - Deploiement
	 * 1 - Mode choix du territoire pour attaquer
	 * 2 - Mode choix du territoire a attaquer
	 * 3 - Mode deplacement choix du territoire de depart
	 * 4 - Mode deplacement choix du territoire d'arrive
	 */
	public int mode = 0;

	public double sourisX;//Coordonnee en X de la souris lors d'un clique
	public double sourisY;//Coordonnee en Y de la souris lors d'un clique
	
	public ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
	public ArrayList<Territoire> listeTerritoires = new ArrayList<Territoire>();
	public ArrayList<Continent> listeContinents = new ArrayList<Continent>();
	
	public int tour = 0;
	public int map = 0;
	public int nombreJoueurs;
	public int indexFichierEnCache = 0;
	
	public boolean ia = false;
	public boolean debutPartie = true;
	public boolean saisieTexte = false;
	
	public Joueur joueurEnCours;//Joueur dont c'est le tour
	
	public ArrayList<Unite> unitesDeDeplacement = new ArrayList<Unite>();//Liste contenant les unités qui vont être déplacées lors d'un déplacement
	
	public ArrayList<Territoire> listeGagnants = new ArrayList<Territoire>();//Liste contenant les territoires ayant remporté un combat
	public ArrayList<Joueur> listeElimines = new ArrayList<Joueur>();//Liste des joueurs ayant été éliminés
	
	public Territoire territoire1;//Variable pour alleger les lignes de code, elle contient le "territoire de depart" (pour une attaque ou un déplacement)
	public Territoire territoire2;//Idem mais pour le territoire d'arrivé
	
	
	
	/**
	 * Constructeur du jeu, initialise les territoires, continent et unités
	 */
	public Jeu() {
		
		effacerCache();
	}
	
	
	//INITIALISATION
	/**
	 * Vider le cache du jeu
	 */
	public void effacerCache()
	{
		for(File file: new java.io.File("img\\cache").listFiles()) 
	    if (!file.isDirectory() && !file.getName().equals("git.txt")) 
	    {
	    	System.out.println(file.getName());
	    	file.delete();
	    }
	        
	}
	
	/**
	 * Initialisation des territories en fonction de la carte selectionee
	 */
	public void creerTerritoires()
	{
		if(this.map == 0)
		{
			//Territoires adjacents
			String[] listeIS = {"Scandinavie","Grande-Bretagne","Groenland"};
			String[] listeSC = {"Island","Grande-Bretagne","Ukraine","Europe du Nord"};
			String[] listeGB = {"Island","Scandinavie","Europe de l'Ouest","Europe du Nord"};
			String[] listeEO = {"Grande-Bretagne","Europe du Nord","Europe du Sud"};
			String[] listeES = {"Europe de l'Ouest","Europe du Nord","Ukraine","Egypte","Afrique du Nord"};
			String[] listeEN = {"Grande-Bretagne","Ukraine","Europe de l'Ouest","Europe du Sud","Scandinavie"};
			String[] listeUK = {"Scandinavie","Europe du Nord","Europe du Sud","Oural","Afganistan","Moyen-Orient"};
			String[] listeEG = {"Afrique de l'Est","Afrique du Nord","Moyen-Orient","Europe du Sud"};
			String[] listeAN = {"Europe de l'Ouest","Europe du Sud","Egypte","Brésil","Congo","Afrique de l'Est"};
			String[] listeAE = {"Egypte","Moyen-Orient","Afrique du Nord","Congo","Afrique du Sud","Madagascar"};
			String[] listeCO = {"Egypte","Afrique du Nord","Afrique du Sud","Afrique de l'Est"};
			String[] listeMA = {"Afrique de l'Est","Afrique du Sud"};
			String[] listeAS = {"Congo","Afrique de l'Est","Madagascar"};
			String[] listeMO = {"Egypte","Afrique de l'Est","Ukraine","Europe du Sud","Afganistan","Inde"};
			String[] listeIN = {"Moyen-Orient","Afganistan","Chine","Siam"};
			String[] listeAF = {"Ukraine","Moyen-Orient","Inde","Oural","Chine"};
			String[] listeOU = {"Chine","Afganistan","Ukraine","Sibérie"};
			String[] listeSIB = {"Mongolie","Chine","Oural","Irkutsk","Yakouti"};
			String[] listeSI = {"Inde","Chine","Indonésie"};
			String[] listeCH = {"Sibérie","Inde","Afganistan","Oural","Siam","Mongolie"};
			String[] listeYA = {"Kamchatka","Irkutsk","Sibérie"};
			String[] listeMON = {"Sibérie","Chine","Irkutsk","Kamchatka","Japon"};
			String[] listeJA = {"Kamchatka","Mongolie"};
			String[] listeKA = {"Irkutsk","Yakouti","Mongolie","Japon","Alaska"};
			String[] listeIR = {"Mongolie","Kamchatka","Yakouti","Sibérie"};
			String[] listeAL = {"Kamchatka","Alberta","Territoires du Nord"};
			String[] listeTN = {"Ontario","Alberta","Alaska","Groenland"};
			String[] listeALB = {"Alaska","Territoires du Nord","Ontario","Etats de L'Ouest"};
			String[] listeON = {"Territoires du Nord","Alberta","Etats de L'Ouest","Etats de L'Est","Quebec","Groenland"};
			String[] listeGR = {"Ontario","Territoires du Nord","Quebec","Island"};
			String[] listeQU = {"Etats de L'Est","Ontario","Groenland"};
			String[] listeEDO = {"Etats de L'Est","Amerique Centrale","Ontario","Alberta"};
			String[] listeEDE = {"Etats de L'Ouest","Amerique Centrale","Ontario","Quebec"};
			String[] listeAC = {"Venezuela","Etats de L'Est","Etats de L'Ouest"};
			String[] listeVE = {"Amerique Centrale","Pérou","Brésil"};
			String[] listeBR = {"Argentine","Afrique du Nord","Pérou","Venezuela"};
			String[] listePE = {"Argentine","Brésil","Venezuela"};
			String[] listeAR = {"Pérou","Brésil"};
			String[] listeIND = {"Nouvelle Guinée","Siam","Australie de l'Ouest"};
			String[] listeNG = {"Indonésie","Australie de l'Ouest","Australie de l'Est"};
			String[] listeADO = {"Australie de l'Est","Nouvelle Guinée","Indonésie"};
			String[] listeADE = {"Australie de l'Ouest","Nouvelle Guinée"};
			
			//Territoires	
			
			
			this.listeTerritoires.add(new Territoire("Island",listeIS));
			this.listeTerritoires.add(new Territoire("Scandinavie",listeSC));
			this.listeTerritoires.add(new Territoire("Grande-Bretagne",listeGB));
			this.listeTerritoires.add(new Territoire("Europe de l'Ouest",listeEO));
			this.listeTerritoires.add(new Territoire("Europe du Sud",listeES));
			this.listeTerritoires.add(new Territoire("Europe du Nord",listeEN));
			this.listeTerritoires.add(new Territoire("Ukraine",listeUK));
			this.listeTerritoires.add(new Territoire("Egypte",listeEG));
			this.listeTerritoires.add(new Territoire("Afrique du Nord",listeAN));
			this.listeTerritoires.add(new Territoire("Afrique de l'Est",listeAE));
			this.listeTerritoires.add(new Territoire("Congo",listeCO));
			this.listeTerritoires.add(new Territoire("Afrique du Sud",listeAS));
			this.listeTerritoires.add(new Territoire("Madagascar",listeMA));
			this.listeTerritoires.add(new Territoire("Moyen-Orient",listeMO));
			this.listeTerritoires.add(new Territoire("Inde",listeIN));
			this.listeTerritoires.add(new Territoire("Afganistan",listeAF));
			this.listeTerritoires.add(new Territoire("Oural",listeOU));
			this.listeTerritoires.add(new Territoire("Sibérie",listeSIB));
			this.listeTerritoires.add(new Territoire("Siam",listeSI));
			this.listeTerritoires.add(new Territoire("Chine",listeCH));
			this.listeTerritoires.add(new Territoire("Yakouti",listeYA));
			this.listeTerritoires.add(new Territoire("Mongolie",listeMON));
			this.listeTerritoires.add(new Territoire("Japon",listeJA));
			this.listeTerritoires.add(new Territoire("Kamchatka",listeKA));
			this.listeTerritoires.add(new Territoire("Irkutsk",listeIR));
			this.listeTerritoires.add(new Territoire("Alaska",listeAL));
			this.listeTerritoires.add(new Territoire("Territoires du Nord",listeTN));
			this.listeTerritoires.add(new Territoire("Alberta",listeALB));
			this.listeTerritoires.add(new Territoire("Ontario",listeON));
			this.listeTerritoires.add(new Territoire("Groenland",listeGR));
			this.listeTerritoires.add(new Territoire("Quebec",listeQU));
			this.listeTerritoires.add(new Territoire("Etats de L'Ouest",listeEDO));
			this.listeTerritoires.add(new Territoire("Etats de L'Est",listeEDE));
			this.listeTerritoires.add(new Territoire("Amerique Centrale",listeAC));
			this.listeTerritoires.add(new Territoire("Venezuela",listeVE));
			this.listeTerritoires.add(new Territoire("Brésil",listeBR));
			this.listeTerritoires.add(new Territoire("Pérou",listePE));
			this.listeTerritoires.add(new Territoire("Argentine",listeAR));
			this.listeTerritoires.add(new Territoire("Indonésie",listeIND));
			this.listeTerritoires.add(new Territoire("Nouvelle Guinée",listeNG));
			this.listeTerritoires.add(new Territoire("Australie de l'Ouest",listeADO));
			this.listeTerritoires.add(new Territoire("Australie de l'Est",listeADE));

			
			for(int i = 0; i < this.listeTerritoires.size();i++)
			{
				this.listeTerritoires.get(i).ajouterUniteTerritoire(new Unite(0,1,6,2,1,2));
			}
			
			
			//Continents
			ArrayList<Territoire> c1 = new ArrayList<Territoire>();
			for(int i = 0;i<7;i++)
			{
				c1.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c2 = new ArrayList<Territoire>();
			for(int i = 7;i<13;i++)
			{
				c2.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c3 = new ArrayList<Territoire>();
			for(int i = 13;i<25;i++)
			{
				c3.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c4 = new ArrayList<Territoire>();
			for(int i = 25;i<34;i++)
			{
				c4.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c5 = new ArrayList<Territoire>();
			for(int i = 34;i<38;i++)
			{
				c5.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c6 = new ArrayList<Territoire>();
			for(int i = 38;i<42;i++)
			{
				c6.add(this.listeTerritoires.get(i));
			}
			

			this.listeContinents.add(new Continent(0,c1));//Europe
			this.listeContinents.add(new Continent(1,c2));//Afrique
			this.listeContinents.add(new Continent(2,c3));//Asie
			this.listeContinents.add(new Continent(3,c4));//Amerique du Nord
			this.listeContinents.add(new Continent(4,c5));//Amerique du Sud
			this.listeContinents.add(new Continent(5,c6));//Océanie
		}
		else if(this.map == 1)
		{
			//Territoires adjacents
			
			String[] listeTW = {"Skagos","The Grev Cliffs","Wolfswood"};
			String[] listeSK = {"The Wall","The Grev Cliffs"};
			String[] listeTGC = {"The Wall","Skagos","Wolfswood","WMain.affichageell"};
			String[] listeWO = {"The Wall","The Grev Cliffs","WMain.affichageell","The Neck","The Rills"};
			String[] listeWI = {"The Grev Cliffs","Wolfswood","The Neck","The Flint Chiff"};
			String[] listeTR = {"Wolfswood","The Neck"};
			String[] listeTN = {"Wolfswood","The Rills","WMain.affichageell","The Flint Chiff","The Vale"};
			String[] listeTFC = {"The Neck","The Vale","Jron Islands","Riverlands"};
			String[] listeTV = {"The Flint Chiff","Riverlands","The Neck","Crownlands"};
			String[] listeJI = {"The Flint Chiff","Riverlands"};
			String[] listeRL = {"Jron Islands","The Flint Chiff","The Vale","Crownlands","Westerlands","Realms Of Jhogrvin"};
			String[] listeCL = {"The Vale","Riverlands","Westerlands","The Reach","Stormlands","Andalos"};
			String[] listeWL = {"Riverlands","Crownlands","The Reach","Shield Lands"};
			String[] listeSL = {"The Reach","Westerlands","Whispering Sound"};
			String[] listeTRE = {"Shield Lands","Westerlands","Crownlands","Stormlands","Red Mountains","Whispering Sound"};
			String[] listeSTRM = {"Crownlands","The Reach","Red Mountains","Andalos"};
			String[] listeWS = {"Shield Lands","The Reach","Red Mountains"};
			String[] listeRM = {"Whispering Sound","The Reach","Dorne","Stormlands"};
			String[] listeDR = {"Red Mountains","The Disputed Lands"};
			String[] listeBC = {"Andalos", "Fills of Norvos"};
			String[] listeAN = {"Braavosian Coastland", "Fills of Norvos", "The Golden Fields",  "Crownlands", "Stormlands"};
			String[] listeFON = {"Braavosian Coastland","Andalos", "The Golden Fields", "Qhoyne Lands"};
			String[] listeQHL = {"Fills of Norvos", "Forrest Of Dohor" , "The Golden Fields" , "Rhoynian Veld" };
			String[] listeFOD = {"Sarnor", "Parched Fields" , "Rhoynian Veld", "Qhoyne Lands"};
			String[] listeTGF = {"Andalos", "Fills of Norvos", "Qhoyne Lands","Sar Mell"};
			String[] listeTDL = {"Dorne", "Sar Mell","The Golden Fields"};
			String[] listeRHV = {"Sar Mell","Western Waste","Parched Fields","Forrest Of Dohor","Qhoyne Lands", "The Golden Fields"};
			String[] listeSM = {"The Disputed Lands", "The Golden Fields","Rhoynian Veld", "Western Waste", "Sea of Sighs" };
			String[] listeWW = {"Sea of Sighs","Sar Mell","Rhoynian Veld","Parched Fields","Painted Mountains"};
			String[] listeSOS = {"Sar Mell","Western Waste", "Elyria","Valyria"};
			String[] listeEL = {"Sea of Sighs","Painted Mountains", "Valyria"};
			String[] listeVA = {"Sea of Sighs", "Valyria"};
			String[] listePM = {"Elyria", "Western Waste", "Parched Fields", "Western Grass Sea" ,"Lhazar", "Slayer's Bay"};
			String[] listeSB = {"Ghiscar", "Red Waste", "Lhazar", "Painted Mountains"};
			String[] listeLH = {"Slayer's Bay", "Painted Mountains", "Western Grass Sea", "Eastern Grass Sea", "Samyrian Fills","Bayasabhad", "Red Waste"};
			String[] listeSA = {"Bayasabhad", "Lhazar", "Eastern Grass Sea", "Vaes Dothrak"};
			String[] listeGH = {"Slayer's Bay", "Red Waste"};
			String[] listeRW = {"Ghiscar", "Slayer's Bay", "Lhazar", "Bayasabhad", "Qarth"};
			String[] listeBA = {"Qarth", "Red Waste", "Lhazar" ,"Samyrian Fills"};
			String[] listeQA = {"Red Waste" ,"Bayasabhad"};
			String[] listeSAR = {"Forrest Of Dohor" ,"Parched Fields", "Abandoned Lands"};
			String[] listeAL = {"Sarnor" ,"Parched Fields","Western Grass Sea", "Kingdoms of the Jfqevron"};
			String[] listeKOTJ = {"Abandoned Lands" ,"Western Grass Sea" ,"Eastern Grass Sea", "Vaes Dothrak" ,"The Footprint"};
			String[] listeTF = {"Kingdoms of the Jfqevron", "Vaes Dothrak", "Realms Of Jhogrvin", "Jbben"};
			String[] listeJB = {"The Footprint"};
			String[] listeROJ = {"Vaes Dothrak" ,"The Footprint","Riverlands"};
			String[] listeVD = {"Realms Of Jhogrvin", "The Footprint", "Kingdoms of the Jfqevron" ,"Eastern Grass Sea" , "Samyrian Fills"};
			String[] listePF = {"Painted Mountains","Western Waste" ,"Rhoynian Veld" ,"Forrest Of Dohor" ,"Sarnor" ,"Abandoned Lands" , "Western Grass Sea"};
			String[] listeWGS = {"Painted Mountains" ,"Parched Fields","Abandoned Lands","Kingdoms of the Jfqevron","Eastern Grass Sea", "Lhazar"};
			String[] listeEGS = {"Western Grass Sea","Kingdoms of the Jfqevron", "Vaes Dothrak" ,"Samyrian Fills","Lhazar"};
			
			//Territoires
			this.listeTerritoires.add(new Territoire("The Wall",listeTW));
			this.listeTerritoires.add(new Territoire("Skagos",listeSK));
			this.listeTerritoires.add(new Territoire("The Grev Cliffs",listeTGC));
			this.listeTerritoires.add(new Territoire("Wolfswood",listeWO));
			this.listeTerritoires.add(new Territoire("WMain.affichageell",listeWI));
			this.listeTerritoires.add(new Territoire("The Rills",listeTR));
			this.listeTerritoires.add(new Territoire("The Flint Chiff",listeTFC));
			this.listeTerritoires.add(new Territoire("The Neck",listeTN));
			this.listeTerritoires.add(new Territoire("The Vale",listeTV));
			this.listeTerritoires.add(new Territoire("Jron Islands",listeJI));
			this.listeTerritoires.add(new Territoire("Riverlands",listeRL));
			this.listeTerritoires.add(new Territoire("Crownlands",listeCL));
			this.listeTerritoires.add(new Territoire("Westerlands",listeWL));
			this.listeTerritoires.add(new Territoire("Shield Lands",listeSL));
			this.listeTerritoires.add(new Territoire("The Reach",listeTRE));
			this.listeTerritoires.add(new Territoire("Stormlands",listeSTRM));
			this.listeTerritoires.add(new Territoire("Whispering Sound",listeWS));
			this.listeTerritoires.add(new Territoire("Red Mountains",listeRM));
			this.listeTerritoires.add(new Territoire("Dorne",listeDR));
			this.listeTerritoires.add(new Territoire("Braavosian Coastland",listeBC));
			this.listeTerritoires.add(new Territoire("Andalos",listeAN));
			this.listeTerritoires.add(new Territoire("Fills of Norvos",listeFON));
			this.listeTerritoires.add(new Territoire("Qhoyne Lands",listeQHL));
			this.listeTerritoires.add(new Territoire("Forrest Of Dohor",listeFOD));
			this.listeTerritoires.add(new Territoire("The Golden Fields",listeTGF));
			this.listeTerritoires.add(new Territoire("The Disputed Lands",listeTDL));
			this.listeTerritoires.add(new Territoire("Rhoynian Veld",listeRHV));
			this.listeTerritoires.add(new Territoire("Sar Mell",listeSM));
			this.listeTerritoires.add(new Territoire("Western Waste",listeWW));
			this.listeTerritoires.add(new Territoire("Sea of Sighs",listeSOS));
			this.listeTerritoires.add(new Territoire("Elyria",listeEL));
			this.listeTerritoires.add(new Territoire("Valyria",listeVA));
			this.listeTerritoires.add(new Territoire("Painted Mountains",listePM));
			this.listeTerritoires.add(new Territoire("Slayer's Bay",listeSB));
			this.listeTerritoires.add(new Territoire("Lhazar",listeLH));
			this.listeTerritoires.add(new Territoire("Samyrian Fills",listeSA));
			this.listeTerritoires.add(new Territoire("Ghiscar",listeGH));
			this.listeTerritoires.add(new Territoire("Red Waste",listeRW));
			this.listeTerritoires.add(new Territoire("Bayasabhad",listeBA));
			this.listeTerritoires.add(new Territoire("Qarth",listeQA));
			this.listeTerritoires.add(new Territoire("Sarnor",listeSAR));
			this.listeTerritoires.add(new Territoire("Abandoned Lands",listeAL));
			this.listeTerritoires.add(new Territoire("Kingdoms of the Jfqevron",listeKOTJ));
			this.listeTerritoires.add(new Territoire("The Footprint",listeTF));
			this.listeTerritoires.add(new Territoire("Jbben",listeJB));
			this.listeTerritoires.add(new Territoire("Realms Of Jhogrvin",listeROJ));
			this.listeTerritoires.add(new Territoire("Vaes Dothrak",listeVD));
			this.listeTerritoires.add(new Territoire("Parched Fields",listePF));
			this.listeTerritoires.add(new Territoire("Western Grass Sea",listeWGS));
			this.listeTerritoires.add(new Territoire("Eastern Grass Sea",listeEGS));
		
		
			for(int i = 0; i < this.listeTerritoires.size();i++)
			{
				ArrayList<Unite> liste = new ArrayList<Unite>();
						
				this.listeTerritoires.get(i).setListeUnitees(liste);
				this.listeTerritoires.get(i).ajouterUniteTerritoire(new Unite(0,1,6,2,1,2));
			}
			
			//Continents
			ArrayList<Territoire> c1 = new ArrayList<Territoire>();
			for(int i = 0;i<8;i++)
			{
				c1.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c2 = new ArrayList<Territoire>();
			for(int i = 8;i<13;i++)
			{
				c2.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c6 = new ArrayList<Territoire>();
			for(int i = 13;i<19;i++)
			{
				c6.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c4 = new ArrayList<Territoire>();
			for(int i = 19;i<26;i++)
			{
				c4.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c5 = new ArrayList<Territoire>();
			for(int i = 26;i<32;i++)
			{
				c5.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c3 = new ArrayList<Territoire>();
			for(int i = 32;i<42;i++)
			{
				c3.add(this.listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c7 = new ArrayList<Territoire>();
			for(int i = 42;i<50;i++)
			{
				c7.add(this.listeTerritoires.get(i));
			}
			

			this.listeContinents.add(new Continent(0,c1));//Europe
			this.listeContinents.add(new Continent(1,c2));//Afrique
			this.listeContinents.add(new Continent(2,c3));//Asie
			this.listeContinents.add(new Continent(3,c4));//Amerique du Nord
			this.listeContinents.add(new Continent(4,c5));//Amerique du Sud
			this.listeContinents.add(new Continent(5,c6));//Océanie
			this.listeContinents.add(new Continent(6,c7));
		}
	}

	/**
	 * Distribue les territoires entre les joueurs
	 */
	public void distribuerTerritores()
	{
		//Cette partie permet de créer une liste mélangée des territoires
		ArrayList<Integer> listeRandomTerritoires = new ArrayList<Integer>();
		
	    for (int i=0; i<this.listeTerritoires.size(); i++)
	    {
	    	listeRandomTerritoires.add(new Integer(i));
        }
	    
	    Collections.shuffle(listeRandomTerritoires);
	    //fin
	    
	  
		int indexJoueur = 0;
		
		for(int i = 0;i < this.listeTerritoires.size();i++)//On attribue dans l'ordre de la liste mélangée les territoires aux joueurs
		{
			this.listeTerritoires.get(listeRandomTerritoires.get(i)).setProprietaire(this.listeJoueurs.get(indexJoueur));//On définit le propriétaire
			this.listeJoueurs.get(indexJoueur).ajouterTerritoire(1);//On ajoute une troupe au joueur
			this.listeJoueurs.get(indexJoueur).ajouterTerritoireControle(this.listeTerritoires.get(listeRandomTerritoires.get(i)));
			indexJoueur++;
			
			if(indexJoueur>=this.listeJoueurs.size())
			{
				indexJoueur = 0;
			}
		}	
		
			
	}

	/**
	 * Attribu un mission à un joueur
	 */
	public void distribuerMissions()
	{
		//Cette partie permet de créer une liste mélangée des territoires
		ArrayList<Integer> listeRandomMissions = new ArrayList<Integer>();
		for (int i=0; i<this.listeJoueurs.size()+2 && i<7; i++)
		{
			listeRandomMissions.add(new Integer(i));
		}
			    
		Collections.shuffle(listeRandomMissions);
		//fin
		
		for(int i = 0;i<this.listeJoueurs.size();i++)
		{
			
			this.listeJoueurs.get(i).definirMission(mission(listeRandomMissions.get(i)));
		}
	}

	/**
	 * Renvoit une mission en fonction de l'index
	 * @param int index l'index de la mission
	 * @return la mission
	 */
	public Mission mission(int index)
	{
		switch(index)
		{
		case 0:
			return new Mission("Contrôler 3 régions et au moins 18 territoires",0);
		case 1:
			return new Mission("Contrôler la plus grosse région + 1 autre région",1);
		case 2:
			return new Mission("Conquérir tous les territoires",2);
		case 3:
			return new Mission("Contrôler 30 territoires",3);
		case 4:
			int random = (int) (Math.random() * this.listeJoueurs.size() - 0) + 0;
			Mission mission = new Mission("Détruire le joueur "+listeJoueurs.get(random).getNom()+" !",4);	
			mission.definirJoueurAEliminer(this.listeJoueurs.get(random));
			
			return mission;
		case 5:
			return new Mission("Contrôler 18 territoires avec au moins 2 armées",5);
		case 6:
			return new Mission("Contrôler 24 territoires",6);
		case 7:
			return new Mission("Contrôler 21 territoires",7);
		default:
			return null;
		}
	}
	//INITIALISATION
	
	
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
		this.sourisX = 0;
		this.sourisY = 0;

		switch(couche)
		{

		case 0:

			while(cliqueMenu(this.sourisX, this.sourisY))
			{
				if(clique())
				{
					this.sourisX = sourisX();
					this.sourisY = sourisY();

					attendre();
				}
				
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}

			break;

		case 1:
			
			this.saisieTexte = true;

			String nombreJoueur = new String();

			while(saisieTexte)
			{
				if(StdDraw.hasNextKeyTyped())
				{
					char touche = StdDraw.nextKeyTyped();
					double curseur = 0.5;
					if(touche == '!')
					{
						this.saisieTexte = false;
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
			
			this.nombreJoueurs = Integer.parseInt(nombreJoueur);
			
			if((this.nombreJoueurs > 6 || this.nombreJoueurs < 2) && this.ia == false)
			{
				this.nombreJoueurs = 2;
			}
			this.couche = 2;//Nom joueurs
			Main.affichage.afficherEcranSaisieNomJoueurs();

			break;

		case 2:

			for(int i = 1;i<=this.nombreJoueurs;i++)
			{
				this.saisieTexte = true;
				String nomJoueur = new String();
				double curseur = 0.3;

				StdDraw.text(0.4, 0.7, ""+i);

				while(this.saisieTexte)
				{
					if(StdDraw.hasNextKeyTyped())
					{
						char touche = StdDraw.nextKeyTyped();
						if(touche == '!')
						{
							this.saisieTexte = false;
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

				this.listeJoueurs.add(new Joueur(nomJoueur,i-1));
				
				
				//listeJoueurs.add(new IA(nomJoueur,i-1));
				Main.affichage.afficherEcranSaisieNomJoueurs();
			}
			
			if(this.ia)
			{
				this.nombreJoueurs++;
				this.listeJoueurs.add(new IA("Patricia", listeJoueurs.size()));
			}

			this.couche= 9;//Mission

			creerTerritoires();
			distribuerTerritores();
			distribuerMissions();
			
			this.joueurEnCours = this.listeJoueurs.get(this.tour);
			this.joueurEnCours.calculerNombreTroupesDeploiement(this.debutPartie);

			StdDraw.setCanvasSize(Main.affichage.xMax, Main.affichage.yMax);
			
			Main.affichage.afficherPlateau();
			Main.affichage.afficherJetons();

			sauvegarderPlateauEnCache();
			
			Main.affichage.resetAffichage(4);//Mission lettre
			this.joueurEnCours = this.listeJoueurs.get(this.tour);
			this.joueurEnCours.calculerNombreTroupesDeploiement(this.debutPartie);
			
			break;

		case 3:

			while(cliqueDeploiement(this.sourisX,this.sourisY))
			{
				if(clique())
				{
					this.sourisX = this.sourisX();
					this.sourisY = this.sourisY();

					attendre();
				}
				
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
				
			}

			break;

		case 4:

			while(cliqueChoix(this.sourisX, this.sourisY))
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

			while(cliqueTerritoires(this.sourisX, this.sourisY,mode))
			{
				if(clique())
				{
					this.sourisX = sourisX();
					this.sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}



			break;

		case 6:

			while(cliqueChoixTroupes(this.sourisX,this.sourisY))
			{
				if(clique())
				{
					this.sourisX = sourisX();
					this.sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}
			

			break;

		/*case 7:

			while(cliqueConfirmationAttaque(this.sourisX,this.sourisY))
			{
				if(clique())
				{
					this.sourisX = sourisX();
					this.sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}

			break;*/

		case 8:

			while(cliqueDeplacement(this.sourisX,this.sourisY))
			{
				if(clique())
				{
					this.sourisX = sourisX();
					this.sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}

			break;

		case 9:
			
			while(cliqueMissionLettre(this.sourisX,this.sourisY))
			{
				if(clique())
				{
					this.sourisX = sourisX();
					this.sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}

			break;

		case 10:

			while(cliqueMission(this.sourisX,this.sourisY))
			{
				if(clique())
				{
					this.sourisX = sourisX();
					this.sourisY = sourisY();

					attendre();
				}

				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}
			
			break;
			
		case 11:
			
			while(cliqueCarte(this.sourisX,this.sourisY))
			{
				if(clique())
				{
					this.sourisX = sourisX();
					this.sourisY = sourisY();

					attendre();
				}
				//System.out.println("x: "+sourisX()+" y: "+sourisY());
			}
			
			break;
			
		case 12:
			
			//IA IA = (IA) listeJoueurs.get(listeJoueurs.size()-1);
			IA IA = (IA) this.joueurEnCours;
			
			IA.echangerTroupes();
			
			IA.deployer();
			
			resetDeplacement(IA);

			while(IA.peutAttaquerOuDeplacer() && Main.jeu)
			{

				this.territoire1 = IA.choisirTerritoireQuiAttaque(0);
				this.territoire2 = IA.choisirTerritoireQuiDefend(this.territoire1, 0);
				
				IA.choisirUnitesCombat(this.territoire1);
				

				this.territoire1.attaquer(this.territoire2);
				
				Main.affichage.afficherInfosHaut(1);
				Main.affichage.afficherInfosBas(-1);

				StdDraw.pause(2000);//On attend un petit peu avant d'afficher le resultat du combat
				
				Main.affichage.afficherInfosHaut(3);

				if(this.territoire2.estConquis())
				{
					IA.ajouterTerritoireCapture();
					IA.deplacer(this.territoire1, this.territoire2);
				}
				
				StdDraw.pause(2000);
				
				this.territoire1.uniteCombat.clear();
				this.territoire2.uniteCombat.clear();

				this.territoire1.resetTroupes();
					
				Main.affichage.afficherInfosHaut(2);
			
				StdDraw.pause(2000);
				
				Main.affichage.majAffichageTerritoire(this.territoire1);
				Main.affichage.majAffichageTerritoire(this.territoire2);
				
				defaiteJoueur();
				finPartie();
			}
				
			IA.deplacerUniteBloquee();
			IA.aComplteSaMission();
			
			if(Main.jeu)
			{
				
				this.couche = 3;//Deploiement
				this.mode = 0;//Deploiement
				
				//this.couche = 12;//Deploiement
				//mode = 0;//Deploiement

				resetDeplacement(this.joueurEnCours);
				passerAuTourSuivant();
				
				this.joueurEnCours = this.listeJoueurs.get(this.tour);
				this.joueurEnCours.calculerNombreTroupesDeploiement(debutPartie);
				
				if(this.joueurEnCours.getNombreTroupesDeploiement() == 0)
				{
					this.joueurEnCours.setNombreTroupesDeploiement(1);
				}
				this.joueurEnCours.resetTerritoireCapture();
				
				Main.affichage.resetAffichage(0);//Deploiement
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
			this.couche = 1;//Nombres joueurs
			Main.affichage.afficherEcranChoixNombreJoueurs();
			return false;
		}
		else if((sourisX > 0.18 && sourisX < 0.82) && (sourisY > 0.39 && sourisY < 0.46))//Cartes
		{
			this.couche = 11;//Cartes
			
			Main.affichage.afficherEcranChoixMap();
			return false;
		}
		else if((sourisX > 0.18 && sourisX < 0.82) && (sourisY > 0.24 && sourisY < 0.31))//IA
		{
			this.couche = 1;//Nombre joueurs
			ia = true;
			
			Main.affichage.afficherEcranChoixNombreJoueurs();
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
			this.couche = 0;//Menu
			
			this.map = 0;
			
			Main.affichage.afficherEcranMenu();
			return false;
		}
		else if((sourisX > 0.53 && sourisX < 0.79) && (sourisY > 0.17 && sourisY < 0.33))//Map GOT
		{
			this.couche = 0;//Menu
			
			this.map = 1;
			
			Main.affichage.afficherEcranMenu();
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
	 * @return false si on a clique sur un element pertinent de la fenetre de l'Main.affichageace true sinon
	 */
	public boolean cliqueDeploiement(double sourisX,double sourisY)
	{
		this.sourisX = 0;
		this.sourisY = 0;
		
		//System.out.println(joueurEnCours.nombreTroupesDeploiement);

		if(joueurEnCours.getNombreTroupesDeploiement()== 0)
		{
			this.mode = 0;//Deploiement
			this.couche = 5;//Modes

			chargerPlateauEnCache();
			
			Main.affichage.afficherInfosDeploiement();

			return false;
		}
		if((sourisX > 0.304 && sourisX < 0.425) && (sourisY > 0.32 && sourisY < 0.61))//Soldat
		{
			this.joueurEnCours.setNombreTroupesDeploiement(this.joueurEnCours.getNombreTroupesDeploiement() - 1);
			this.joueurEnCours.setNombreSoldatsDeploiement(this.joueurEnCours.getNombreSoldatsDeploiement() + 1);

			Main.affichage.afficherCadreChoixTroupesDeploiement();

			return false;
		}
		else if((sourisX > 0.445 && sourisX < 0.56) && (sourisY > 0.32 && sourisY < 0.61))//Cavalier
		{
			if(this.joueurEnCours.getNombreTroupesDeploiement() > 2)
			{
				this.joueurEnCours.setNombreTroupesDeploiement(this.joueurEnCours.getNombreTroupesDeploiement() - 3);
				this.joueurEnCours.setNombreCavaliersDeploiement(this.joueurEnCours.getNombreCavaliersDeploiement() + 1);

				Main.affichage.afficherCadreChoixTroupesDeploiement();

				return false;
			}
			else
			{
				Main.affichage.afficherInfosBas(8);
				return true;
			}
		}
		else if((sourisX > 0.58 && sourisX < 0.69) && (sourisY > 0.32 && sourisY < 0.61))//Canon
		{

			if(this.joueurEnCours.getNombreTroupesDeploiement() > 6)
			{
				this.joueurEnCours.setNombreTroupesDeploiement(this.joueurEnCours.getNombreTroupesDeploiement() - 7);
				this.joueurEnCours.setNombreCanonsDeploiement(this.joueurEnCours.getNombreCanonsDeploiement() + 1);

				Main.affichage.afficherCadreChoixTroupesDeploiement();

				return false;
			}
			else
			{
				Main.affichage.afficherInfosBas(8);
				return true;
			}
		}
		else
		{
			return true;
		}
	}

	/**
	 * Hitbox de l'Main.affichageace de choix
	 * @param sourisX valeur en x de la souris
	 * @param sourisY valeur en y de la souris
	 * @return false si on a clique sur un element pertinent de la fenetre de l'Main.affichageace true sinon
	 */
	public boolean cliqueChoix(double sourisX, double sourisY)
	{
		if((sourisX > 0.22 && sourisX < 0.35) && (sourisY > 0.06 && sourisY < 0.11))//Attaquer
		{
			this.mode = 1;//Attaque 1
			this.couche = 5;//Modes

			Main.affichage.resetAffichage(2);//Croix
			Main.affichage.afficherInfosBas(0);

			return false;
		}
		else if((sourisX > 0.44 && sourisX < 0.57) && (sourisY > 0.06 && sourisY < 0.11))//Deplacer
		{
			this.mode = 3;//Deplacement 1
			this.couche = 5;//Modes

			Main.affichage.resetAffichage(2);//Croix
			Main.affichage.afficherInfosBas(0);

			return false;
		}
		else if((sourisX > 0.65 && sourisX < 0.80) && (sourisY > 0.06 && sourisY < 0.11))//Fin de tour
		{
			
			this.joueurEnCours.aComplteSaMission();
			resetDeplacement(this.joueurEnCours);
			defaiteJoueur();
			finPartie();
			passerAuTourSuivant();
			this.joueurEnCours = this.listeJoueurs.get(this.tour);
			this.joueurEnCours.calculerNombreTroupesDeploiement(this.debutPartie);
			
			if(this.joueurEnCours.getNombreTroupesDeploiement() == 0)
			{
				this.joueurEnCours.setNombreTroupesDeploiement(1);
			}
			this.joueurEnCours.resetTerritoireCapture();
			
			
			if(this.ia && this.tour == this.listeJoueurs.size()-1)
			{
				this.couche = 12;//IA
				chargerPlateauEnCache();
			}
			else
			{
				this.couche = 3;//Deploiement
				this.mode = 0;//Deploiement
				Main.affichage.resetAffichage(0);//Choix

			}
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
	 * @return false si on a clique sur un element pertinent de la fenetre de l'Main.affichageace qui rempli les conditions du mode, true sinon
	 */
	public boolean cliqueTerritoires(double sourisX, double sourisY, int mode)
	{
		if(this.map == 0)
		{
			if((sourisX > 0.34 && sourisX < 0.40) && (sourisY > 0.74 && sourisY < 0.79))//Island
			{
				return verifier(this.listeTerritoires.get(0),mode);
			}
			else if((sourisX > 0.4 && sourisX < 0.47) && (sourisY > 0.72 && sourisY < 0.85))//Scandinavie
			{
				return verifier(this.listeTerritoires.get(1),mode);
			}
			else if((sourisX > 0.3 && sourisX < 0.38) && (sourisY > 0.65 && sourisY < 0.68))//Grande-Bretagne
			{
				return verifier(this.listeTerritoires.get(2),mode);
			}
			else if((sourisX > 0.34 && sourisX < 0.40) && (sourisY > 0.48 && sourisY < 0.61))//Europe de l'Ouest
			{
				return verifier(this.listeTerritoires.get(3),mode);
			}
			else if((sourisX > 0.41 && sourisX < 0.47) && (sourisY > 0.55 && sourisY < 0.59))//Europe du Sud
			{
				return verifier(this.listeTerritoires.get(4),mode);
			}
			else if((sourisX > 0.41 && sourisX < 0.46) && (sourisY > 0.62 && sourisY < 0.66))//Europe du Nord
			{
				return verifier(this.listeTerritoires.get(5),mode);
			}
			else if((sourisX > 0.49 && sourisX < 0.55) && (sourisY > 0.72 && sourisY < 0.74))//Ukraine
			{
				return verifier(this.listeTerritoires.get(6),mode);
			}
			else if((sourisX > 0.45 && sourisX < 0.49) && (sourisY > 0.41 && sourisY < 0.44))//Egypte
			{
				return verifier(this.listeTerritoires.get(7),mode);
			}
			else if((sourisX > 0.36 && sourisX < 0.45) && (sourisY > 0.36 && sourisY < 0.38))//Afrique du Nord
			{
				return verifier(this.listeTerritoires.get(8),mode);
			}
			else if((sourisX > 0.49 && sourisX < 0.54) && (sourisY > 0.30 && sourisY < 0.34))//Afrique de l'Est
			{
				return verifier(this.listeTerritoires.get(9),mode);
			}
			else if((sourisX > 0.44 && sourisX < 0.49) && (sourisY > 0.255 && sourisY < 0.28))//Congo
			{
				return verifier(this.listeTerritoires.get(10),mode);
			}
			else if((sourisX > 0.45 && sourisX < 0.49) && (sourisY > 0.13 && sourisY < 0.17))//Afrique du Sud
			{
				return verifier(this.listeTerritoires.get(11),mode);
			}
			else if((sourisX > 0.54 && sourisX < 0.59) && (sourisY > 0.09 && sourisY < 0.2))//Madagascar
			{
				return verifier(this.listeTerritoires.get(12),mode);
			}
			else if((sourisX > 0.51 && sourisX < 0.58) && (sourisY > 0.48 && sourisY < 0.51))//Moyen Orient
			{
				return verifier(this.listeTerritoires.get(13),mode);
			}
			else if((sourisX > 0.62 && sourisX < 0.66) && (sourisY > 0.47 && sourisY < 0.49))//Inde
			{
				return verifier(this.listeTerritoires.get(14),mode);
			}
			else if((sourisX > 0.56 && sourisX < 0.63) && (sourisY > 0.61 && sourisY < 0.63))//Afganistan
			{
				return verifier(this.listeTerritoires.get(15),mode);
			}
			else if((sourisX > 0.59 && sourisX < 0.62) && (sourisY > 0.72 && sourisY < 0.75))//Oural
			{
				return verifier(this.listeTerritoires.get(16),mode);
			}
			else if((sourisX > 0.63 && sourisX < 0.68) && (sourisY > 0.80 && sourisY < 0.82))//Siberie
			{
				return verifier(this.listeTerritoires.get(17),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.73) && (sourisY > 0.43 && sourisY < 0.45))//Siam
			{
				return verifier(this.listeTerritoires.get(18),mode);
			}
			else if((sourisX > 0.67 && sourisX < 0.72) && (sourisY > 0.54 && sourisY < 0.56))//Chine
			{
				return verifier(this.listeTerritoires.get(19),mode);
			}
			else if((sourisX > 0.70 && sourisX < 0.75) && (sourisY > 0.836 && sourisY < 0.859))//Yakouti
			{
				return verifier(this.listeTerritoires.get(20),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.75) && (sourisY > 0.63 && sourisY < 0.65))//Mongolie
			{
				return verifier(this.listeTerritoires.get(21),mode);
			}
			else if((sourisX > 0.8 && sourisX < 0.84) && (sourisY > 0.61 && sourisY < 0.64))//Japon
			{
				return verifier(this.listeTerritoires.get(22),mode);
			}
			else if((sourisX > 0.77 && sourisX < 0.83) && (sourisY > 0.83 && sourisY < 0.86))//Kamachatka
			{
				return verifier(this.listeTerritoires.get(23),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.74) && (sourisY > 0.72 && sourisY < 0.74))//Irkutsk
			{
				return verifier(this.listeTerritoires.get(24),mode);
			}
			else if((sourisX > 0.025 && sourisX < 0.07) && (sourisY > 0.8 && sourisY < 0.82))//Alaska
			{
				return verifier(this.listeTerritoires.get(25),mode);
			}
			else if((sourisX > 0.07 && sourisX < 0.2) && (sourisY > 0.8 && sourisY < 0.83))//Territoires du Nord
			{
				return verifier(this.listeTerritoires.get(26),mode);
			}
			else if((sourisX > 0.1 && sourisX < 0.15) && (sourisY > 0.73 && sourisY < 0.75))//Alberta
			{
				return verifier(this.listeTerritoires.get(27),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.20) && (sourisY > 0.71 && sourisY < 0.73))//Ontario
			{
				return verifier(this.listeTerritoires.get(28),mode);
			}
			else if((sourisX > 0.26 && sourisX < 0.33) && (sourisY > 0.85 && sourisY < 0.88))//Groenland
			{
				return verifier(this.listeTerritoires.get(29),mode);
			}
			else if((sourisX > 0.22 && sourisX < 0.26) && (sourisY > 0.71 && sourisY < 0.73))//Quebec
			{
				return verifier(this.listeTerritoires.get(30),mode);
			}
			else if((sourisX > 0.09 && sourisX < 0.16) && (sourisY > 0.63 && sourisY < 0.67))//Etats de l'Ouest
			{
				return verifier(this.listeTerritoires.get(31),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.22) && (sourisY > 0.58 && sourisY < 0.62))//Etats de l'Est
			{
				return verifier(this.listeTerritoires.get(32),mode);
			}
			else if((sourisX > 0.11 && sourisX < 0.17) && (sourisY > 0.45 && sourisY < 0.57))//Amerique Centrale
			{
				return verifier(this.listeTerritoires.get(33),mode);
			}
			else if((sourisX > 0.16 && sourisX < 0.23) && (sourisY > 0.42 && sourisY < 0.45))//Venezuela
			{
				return verifier(this.listeTerritoires.get(34),mode);
			}
			else if((sourisX > 0.23 && sourisX < 0.29) && (sourisY > 0.34 && sourisY < 0.37))//Bresil
			{
				return verifier(this.listeTerritoires.get(35),mode);
			}
			else if((sourisX > 0.2 && sourisX < 0.23) && (sourisY > 0.30 && sourisY < 0.32))//Perou
			{
				return verifier(this.listeTerritoires.get(36),mode);
			}
			else if((sourisX > 0.19 && sourisX < 0.25) && (sourisY > 0.21 && sourisY < 0.23))//Argentine
			{
				return verifier(this.listeTerritoires.get(37),mode);
			}
			else if((sourisX > 0.68 && sourisX < 0.77) && (sourisY > 0.26 && sourisY < 0.32))//Indonesie
			{
				return verifier(this.listeTerritoires.get(38),mode);
			}
			else if((sourisX > 0.78 && sourisX < 0.83) && (sourisY > 0.30 && sourisY < 0.35))//Nouvelle Guinnee
			{
				return verifier(this.listeTerritoires.get(39),mode);
			}
			else if((sourisX > 0.73 && sourisX < 0.81) && (sourisY > 0.13 && sourisY < 0.18))//Australie de l'Ouest
			{
				return verifier(this.listeTerritoires.get(40),mode);
			}
			else if((sourisX > 0.79 && sourisX < 0.86) && (sourisY > 0.17 && sourisY < 0.21))//Australie de l'Est
			{
				return verifier(this.listeTerritoires.get(41),mode);
			}
			else if((sourisX > 0.89 && sourisX < 0.94) && (sourisY > 0.03 && sourisY < 0.15))//Annuler
			{
				sauvegarderPlateauEnCache();
				
				Main.affichage.resetAffichage(1);//Choix

				this.couche = 4;//Choix

				return false;
			}
			else
			{
				return true;
			}
		}
		else if(this.map == 1)
		{
			if((sourisX > 0.18 && sourisX < 0.21) && (sourisY > 0.83 && sourisY < 0.85))//The Wall
			{
				return verifier(this.listeTerritoires.get(0),mode);
			}
			else if((sourisX > 0.25 && sourisX < 0.28) && (sourisY > 0.83 && sourisY < 0.88))//Skagos
			{
				return verifier(this.listeTerritoires.get(1),mode);
			}
			else if((sourisX > 0.2 && sourisX < 0.28) && (sourisY > 0.77 && sourisY < 0.81))//The Grev Cliffs
			{
				return verifier(this.listeTerritoires.get(2),mode);
			}
			else if((sourisX > 0.08 && sourisX < 0.15) && (sourisY > 0.74 && sourisY < 0.79))//Wolfswood
			{
				return verifier(this.listeTerritoires.get(3),mode);
			}
			else if((sourisX > 0.17 && sourisX < 0.23) && (sourisY > 0.71 && sourisY < 0.76))//WMain.affichageell
			{
				return verifier(this.listeTerritoires.get(4),mode);
			}
			else if((sourisX > 0.06 && sourisX < 0.13) && (sourisY > 0.67 && sourisY < 0.73))//The Rills
			{
				return verifier(this.listeTerritoires.get(5),mode);
			}
			else if((sourisX > 0.09 && sourisX < 0.15) && (sourisY > 0.61 && sourisY < 0.65))//The Flint Cliff
			{
				return verifier(this.listeTerritoires.get(6),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.18) && (sourisY > 0.66 && sourisY < 0.69))//The Neck
			{
				return verifier(this.listeTerritoires.get(7),mode);
			}
			else if((sourisX > 0.19 && sourisX < 0.25) && (sourisY > 0.51 && sourisY < 0.60))//The Vale
			{
				return verifier(this.listeTerritoires.get(8),mode);
			}
			else if((sourisX > 0.06 && sourisX < 0.1) && (sourisY > 0.54 && sourisY < 0.58))//Jron Islands
			{
				return verifier(this.listeTerritoires.get(9),mode);
			}
			else if((sourisX > 0.1 && sourisX < 0.15) && (sourisY > 0.52 && sourisY < 0.55))//Riverlands
			{
				return verifier(this.listeTerritoires.get(10),mode);
			}
			else if((sourisX > 0.17 && sourisX < 0.24) && (sourisY > 0.45 && sourisY < 0.50))//Crownlands
			{
				return verifier(this.listeTerritoires.get(11),mode);
			}
			else if((sourisX > 0.08 && sourisX < 0.16) && (sourisY > 0.44 && sourisY < 0.5))//Westerlands
			{
				return verifier(this.listeTerritoires.get(12),mode);
			}
			else if((sourisX > 0.08 && sourisX < 0.14) && (sourisY > 0.38 && sourisY < 0.41))//Shield Lands
			{
				return verifier(this.listeTerritoires.get(13),mode);
			}
			else if((sourisX > 0.15 && sourisX < 0.2) && (sourisY > 0.39 && sourisY < 0.44))//The Reach
			{
				return verifier(this.listeTerritoires.get(14),mode);
			}
			else if((sourisX > 0.2 && sourisX < 0.25) && (sourisY > 0.36 && sourisY < 0.41))//Stromlands
			{
				return verifier(this.listeTerritoires.get(15),mode);
			}
			else if((sourisX > 0.06 && sourisX < 0.12) && (sourisY > 0.29 && sourisY < 0.35))//Whispering Sounds
			{
				return verifier(this.listeTerritoires.get(16),mode);
			}
			else if((sourisX > 0.14 && sourisX < 0.18) && (sourisY > 0.3 && sourisY < 0.33))//Red Mountains
			{
				return verifier(this.listeTerritoires.get(17),mode);
			}
			else if((sourisX > 0.18 && sourisX < 0.28) && (sourisY > 0.25 && sourisY < 0.29))//Dorne
			{
				return verifier(this.listeTerritoires.get(18),mode);
			}
			else if((sourisX > 0.34 && sourisX < 0.38) && (sourisY > 0.54 && sourisY < 0.6))//Braavosian Coastland
			{
				return verifier(this.listeTerritoires.get(19),mode);
			}
			else if((sourisX > 0.33 && sourisX < 0.38) && (sourisY > 0.45 && sourisY < 0.49))//Andalos
			{
				return verifier(this.listeTerritoires.get(20),mode);
			}
			else if((sourisX > 0.38 && sourisX < 0.42) && (sourisY > 0.48 && sourisY < 0.54))//Fills Of Norvos
			{
				return verifier(this.listeTerritoires.get(21),mode);
			}
			else if((sourisX > 0.43 && sourisX < 0.46) && (sourisY > 0.43 && sourisY < 0.52))//Qhoyne Lands
			{
				return verifier(this.listeTerritoires.get(22),mode);
			}
			else if((sourisX > 0.48 && sourisX < 0.54) && (sourisY > 0.46 && sourisY < 0.50))//Forrest Of Dohor
			{
				return verifier(this.listeTerritoires.get(23),mode);
			}
			else if((sourisX > 0.38 && sourisX < 0.44) && (sourisY > 0.36 && sourisY < 0.41))//The Golden Fields
			{
				return verifier(this.listeTerritoires.get(24),mode);
			}
			else if((sourisX > 0.34 && sourisX < 0.43) && (sourisY > 0.28 && sourisY < 0.32))//The Disputed Lands
			{
				return verifier(this.listeTerritoires.get(25),mode);
			}
			else if((sourisX > 0.46 && sourisX < 0.52) && (sourisY > 0.38 && sourisY < 0.44))//Rhoynian Veld
			{
				return verifier(this.listeTerritoires.get(26),mode);
			}
			else if((sourisX > 0.45 && sourisX < 0.50) && (sourisY > 0.29 && sourisY < 0.33))//Sar Mell
			{
				return verifier(this.listeTerritoires.get(27),mode);
			}
			else if((sourisX > 0.51 && sourisX < 0.57) && (sourisY > 0.33 && sourisY < 0.37))//Western Waste
			{
				return verifier(this.listeTerritoires.get(28),mode);
			}
			else if((sourisX > 0.53 && sourisX < 0.56) && (sourisY > 0.21 && sourisY < 0.31))//Sea Of Sight
			{
				return verifier(this.listeTerritoires.get(29),mode);
			}
			else if((sourisX > 0.56 && sourisX < 0.59) && (sourisY > 0.19 && sourisY < 0.25))//Elyria
			{
				return verifier(this.listeTerritoires.get(30),mode);
			}
			else if((sourisX > 0.53 && sourisX < 0.57) && (sourisY > 0.1 && sourisY < 0.17))//Valyria
			{
				return verifier(this.listeTerritoires.get(31),mode);
			}
			else if((sourisX > 0.61 && sourisX < 0.69) && (sourisY > 0.32 && sourisY < 0.35))//Painted Mountains
			{
				return verifier(this.listeTerritoires.get(32),mode);
			}
			else if((sourisX > 0.69 && sourisX < 0.73) && (sourisY > 0.26 && sourisY < 0.30))//Slaver's Bay
			{
				return verifier(this.listeTerritoires.get(33),mode);
			}
			else if((sourisX > 0.74 && sourisX < 0.81) && (sourisY > 0.30 && sourisY < 0.34))//Lhazar
			{
				return verifier(this.listeTerritoires.get(34),mode);
			}
			else if((sourisX > 0.83 && sourisX < 0.90) && (sourisY > 0.36 && sourisY < 0.4))//Samyrian Fills
			{
				return verifier(this.listeTerritoires.get(35),mode);
			}
			else if((sourisX > 0.68 && sourisX < 0.75) && (sourisY > 0.16 && sourisY < 0.21))//Ghiscar
			{
				return verifier(this.listeTerritoires.get(36),mode);
			}
			else if((sourisX > 0.75 && sourisX < 0.83) && (sourisY > 0.21 && sourisY < 0.27))//Red Waste
			{
				return verifier(this.listeTerritoires.get(37),mode);
			}
			else if((sourisX > 0.84 && sourisX < 0.9) && (sourisY > 0.27 && sourisY < 0.32))//Bayasabhad
			{
				return verifier(this.listeTerritoires.get(38),mode);
			}
			else if((sourisX > 0.84 && sourisX < 0.91) && (sourisY > 0.19 && sourisY < 0.25))//Qarth
			{
				return verifier(this.listeTerritoires.get(39),mode);
			}
			else if((sourisX > 0.55 && sourisX < 0.61) && (sourisY > 0.5 && sourisY < 0.59))//Sarnor
			{
				return verifier(this.listeTerritoires.get(40),mode);
			}
			else if((sourisX > 0.62 && sourisX < 0.69) && (sourisY > 0.46 && sourisY < 0.55))//Abandoned Lands
			{
				return verifier(this.listeTerritoires.get(41),mode);
			}
			else if((sourisX > 0.70 && sourisX < 0.78) && (sourisY > 0.45 && sourisY < 0.53))//Kingdoms Of The Jfeqevron
			{
				return verifier(this.listeTerritoires.get(42),mode);
			}
			else if((sourisX > 0.75 && sourisX < 0.87) && (sourisY > 0.54 && sourisY < 0.61))//The Footprint
			{
				return verifier(this.listeTerritoires.get(43),mode);
			}
			else if((sourisX > 0.79 && sourisX < 0.87) && (sourisY > 0.70 && sourisY < 0.80))//Jbben
			{
				return verifier(this.listeTerritoires.get(44),mode);
			}
			else if((sourisX > 0.89 && sourisX < 0.96) && (sourisY > 0.49 && sourisY < 0.61))//Realms Of Jhogrvin
			{
				return verifier(this.listeTerritoires.get(45),mode);
			}
			else if((sourisX > 0.8 && sourisX < 0.87) && (sourisY > 0.48 && sourisY < 0.52))//Vaes Dothrak
			{
				return verifier(this.listeTerritoires.get(46),mode);
			}
			else if((sourisX > 0.57 && sourisX < 0.64) && (sourisY > 0.37 && sourisY < 0.44))//Parched Fields
			{
				return verifier(this.listeTerritoires.get(47),mode);
			}
			else if((sourisX > 0.66 && sourisX < 0.75) && (sourisY > 0.36 && sourisY < 0.41))//Western Grass Sea
			{
				return verifier(this.listeTerritoires.get(48),mode);
			}
			else if((sourisX > 0.76 && sourisX < 0.84) && (sourisY > 0.40 && sourisY < 0.44))//Easter Grass Sea
			{
				return verifier(this.listeTerritoires.get(49),mode);
			}
			else if((sourisX > 0.89 && sourisX < 0.94) && (sourisY > 0.03 && sourisY < 0.15))//Annuler
			{
				Main.affichage.resetAffichage(1);//Choix

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


		if((sourisX > 0.03 && sourisX < 0.23) && (sourisY > 0.19 && sourisY < 0.25))//Anuler
		{
			mode = 1;//Attaque etape 1
			couche = 5;//Choix territoire
			territoire1.resetTroupes();
			

			Main.affichage.resetAffichageTroupesSelectionees();
			Main.affichage.resetAffichage(2);
			Main.affichage.afficherInfosBas(0);

			return false;
		}
		else if((sourisX > 0.56 && sourisX < 0.67) && (sourisY > 0.33 && sourisY < 0.63) && territoire1.getNombreSoldats() > 0  && territoire1.unitePeutAttaquer(0) && territoire1.getNombreTroupesATT() > 0)//Soldat
		{
			territoire1.ajouterUniteCombat(0, 0);
			territoire1.ajouterSoldats(-1);
			territoire1.setNombreTroupesATT(territoire1.getNombreTroupesATT() - 1);
			
			Main.affichage.affichageSelectionSoldat++;
			Main.affichage.afficherInfosHaut(11);
			Main.affichage.affichrNombreTroupesTerritoire();

			return false;
		}
		else if((sourisX > 0.70 && sourisX < 0.81) && (sourisY > 0.33 && sourisY < 0.63) && territoire1.getNombreCavaliers() > 0  && territoire1.unitePeutAttaquer(1) && territoire1.getNombreTroupesATT() > 0)//Cavalier
		{
			territoire1.ajouterUniteCombat(1, 0);
			territoire1.ajouterCavaliers(-1);
			territoire1.setNombreTroupesATT(territoire1.getNombreTroupesATT() - 1);
			
			Main.affichage.affichageSelectionCavalier++;
			Main.affichage.afficherInfosHaut(12);
			Main.affichage.affichrNombreTroupesTerritoire();

			return false;
		}
		else if((sourisX > 0.83 && sourisX < 0.95) && (sourisY > 0.33 && sourisY < 0.63) && territoire1.getNombreCanons() > 0 && territoire1.unitePeutAttaquer(2) && territoire1.getNombreTroupesATT() > 0)//Canon
		{
			territoire1.ajouterUniteCombat(2, 0);
			territoire1.ajouterCanons(-1);
			territoire1.setNombreTroupesATT(territoire1.getNombreTroupesATT() - 1);
			
			Main.affichage.affichageSelectionCanon++;
			Main.affichage.affichrNombreTroupesTerritoire();
			Main.affichage.afficherInfosHaut(13);

			return false;
		}

		else if((sourisX > 0.24 && sourisX < 0.44) && (sourisY > 0.19 && sourisY < 0.25))//Confirmer
		{
			if(territoire1.uniteCombat.size() == 0)
			{
				Main.affichage.afficherInfosBas(9);
				return true;
			}
			else
			{
				Main.affichage.resetAffichageTroupesSelectionees();
				territoire1.attaquer(territoire2);

				Main.affichage.afficherInfosHaut(1);
				Main.affichage.afficherInfosBas(-1);

				StdDraw.pause(2000);//On attend un petit peu avant d'afficher le resultat du combat

				Main.affichage.afficherInfosHaut(3);

				StdDraw.pause(3000);

				territoire1.uniteCombat.clear();
				territoire2.uniteCombat.clear();

				territoire1.resetTroupes();
					
				Main.affichage.resetAffichage(6);
				
				sauvegarderPlateauEnCache();

				Main.affichage.afficherInfosHaut(2);
				Main.affichage.afficherInfosBas(0);

				if(territoire2.estConquis())
				{
					
					Main.affichage.afficherCadreDeplacement();
					joueurEnCours.ajouterTerritoireCapture();
					
					this.sourisX = 0;
					this.sourisY = 0;
					
					while(cliqueDeplacement(this.sourisX,this.sourisY))
					{
						if(clique())
						{
							this.sourisX = sourisX();
							this.sourisY = sourisY();

							attendre();
						}
					}
					
					this.sauvegarderPlateauEnCache();
					Main.affichage.afficherCroix();
					
				}
				
				mode = 1;//Attaque etape 1
				couche = 5;//Choix territoire

				return false;
				
			}
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
				Main.affichage.afficherInfosBas(10);
			}
			else
			{
				Main.affichage.affichageSelectionSoldat++;
				Main.affichage.afficherCadreDeplacement();
				Main.affichage.afficherInfosHaut(11);
			}
		
			
		}
		else if((sourisX > 0.445 && sourisX < 0.56) && (sourisY > 0.227 && sourisY < 0.523) && territoire1.getNombreCavaliers() > 0 && territoire1.peutAttaquerOuDeplacer())//Cavalier
		{
			unitesDeDeplacement.add(territoire1.uniteDeplacement(1, 0));
			if(unitesDeDeplacement.get(unitesDeDeplacement.size()-1) == null)
			{
				unitesDeDeplacement.remove(unitesDeDeplacement.get((unitesDeDeplacement.size()-1)));
				Main.affichage.afficherInfosBas(10);
			}
			else
			{
				Main.affichage.affichageSelectionCavalier++;
				Main.affichage.afficherCadreDeplacement();
				Main.affichage.afficherInfosHaut(12);
			}
		}
		else if((sourisX > 0.58 && sourisX < 0.69) && (sourisY > 0.227 && sourisY < 0.523) && territoire1.getNombreCanons() > 0 && territoire1.peutAttaquerOuDeplacer())//Canon
		{
			unitesDeDeplacement.add(territoire1.uniteDeplacement(2, 0));
			if(unitesDeDeplacement.get(unitesDeDeplacement.size()-1) == null)
			{
				unitesDeDeplacement.remove(unitesDeDeplacement.get((unitesDeDeplacement.size()-1)));
				Main.affichage.afficherInfosBas(10);
			}
			else
			{
				Main.affichage.affichageSelectionCanon++;
				Main.affichage.afficherCadreDeplacement();
				Main.affichage.afficherInfosHaut(13);
				
			}
			
		}
		
		
		if((sourisX > 0.30 && sourisX < 0.49) && (sourisY > 0.15 && sourisY < 0.21))//Anuler
		{

			if(territoire2.listeUnitees.size() == 0)
			{
				return true;
			}
			else
			{
				couche = 5;
				mode = 3;

				Main.affichage.resetAffichage(2);
				Main.affichage.afficherInfosBas(0);
				
				for(int i = 0; i < unitesDeDeplacement.size(); i++)
				{
					territoire1.ajouterUniteTerritoire(unitesDeDeplacement.get(i));
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

				Main.affichage.resetAffichageTroupesSelectionees();
				unitesDeDeplacement.clear();
				
				return false;
			}
		}
		else if((sourisX > 0.51 && sourisX < 0.69) && (sourisY > 0.15 && sourisY < 0.21))//Confirmer
		{
			if(unitesDeDeplacement.size() == 0)
			{
				Main.affichage.afficherInfosBas(10);
				return false;
			}
			else
			{
				for(int i = 0;i < unitesDeDeplacement.size(); i ++)
				{
					territoire1.deplacement(territoire2, unitesDeDeplacement.get(i));
				}
				Main.affichage.resetAffichage(6);

				couche = 5;
				mode = 3;

				Main.affichage.resetAffichageTroupesSelectionees();
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

			Main.affichage.resetAffichage(0);
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

			Main.affichage.resetAffichage(3);//Mission intitule
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Sauvegarde en cache l'etat du plateau
	 */
	public void sauvegarderPlateauEnCache()
	{
		Main.affichage.afficherInfosHaut(-1);
		Main.affichage.afficherInfosBas(-1);
		Main.affichage.afficherCouleurJoueurEnCours(this.tour);
		String nom = "img/cache/plateau"+this.indexFichierEnCache+".png";
		StdDraw.save(nom);
		this.indexFichierEnCache++;
	}
	
	/**
	 * Charge la dernière sauvegarde en cache du plateau
	 */
	public void chargerPlateauEnCache()
	{
		String nom = "img/cache/plateau"+(this.indexFichierEnCache-1)+".png";
		StdDraw.picture(0.5, 0.5, nom);
		Main.affichage.afficherCouleurJoueurEnCours(this.tour);
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
	
	
	
	//DIVERS
	/**
	 * Definit le tour auquel on est
	 * @param tour
	 */
	public void passerAuTourSuivant()
	{
		tour++;
		
		if(tour >= listeJoueurs.size())
		{
			tour = 0;
		}
		
		
		while(listeJoueurs.get(tour) == null)
		{
			tour++;
			if(tour >= listeJoueurs.size())
			{
				tour = 0;
			}
		}
		
		
		if(tour == 0 && debutPartie == true)
		{
			debutPartie = false;
		}
	}

	/**
	 * Check si un joueur a perdu
	 */
	public void defaiteJoueur()
	{
		for(int i = 0; i < listeJoueurs.size();i++)
		{
			if(listeJoueurs.get(i) != null)
			{
				if(listeJoueurs.get(i).aPerdu())
				{
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledRectangle(0.5, 1, 0.3, 0.05);
					StdDraw.setPenColor(StdDraw.BLACK);
					
					listeElimines.add(listeJoueurs.get(i));
					StdDraw.text(0.5, 0.97, listeJoueurs.get(i).getNom()+" a perdu !");
					listeJoueurs.set(i,null);
					StdDraw.pause(3000);
				}
			}
		}
	}

	/**
	 * Permet de savoir si un joueur a gagné
	 */
	public void finPartie()
	{
		if(listeElimines.size() == listeJoueurs.size()-1)
		{
			Main.jeu = false;
		}
	}

	/**
	 * Methodes regroupant tous les types de verifications nescessaires au bon fonctionnement du jeu
	 * @param joueur Joueur le joueur sur lequel on fait la verification
	 * @param territoire Territoire le territoire sur lequel on fait la verification
	 * @param mode indice de la verification qu'on veut faire
	 * @return true ou false selon ce qu'on verifie, il n'y a pas de sortie "type"
	 */
	public boolean verifier(Territoire territoire, int mode)
	{
		//Ces deux lignes sont pour corriger le bug du StdDraw.isMousePressed()
		sourisX = 0;
		sourisY = 0;

		switch(mode)
		{
		case 0:	//Deploiment

			if(territoire.appartientA(this.joueurEnCours) && (this.joueurEnCours.getNombreSoldatsDeploiement() > 0 || this.joueurEnCours.getNombreCavaliersDeploiement() > 0 || this.joueurEnCours.getNombreCanonsDeploiement() > 0 ))
			{
				this.territoire1 = territoire;
				territoire.deployerUnite(this.joueurEnCours);

				if(this.joueurEnCours.getNombreSoldatsDeploiement() == 0 && this.joueurEnCours.getNombreCavaliersDeploiement() == 0 && this.joueurEnCours.getNombreCanonsDeploiement() == 0  && debutPartie)
				{
					Main.affichage.resetAffichage(5);
					
					sauvegarderPlateauEnCache();
					passerAuTourSuivant();
					this.joueurEnCours = listeJoueurs.get(tour);
					
					this.joueurEnCours.calculerNombreTroupesDeploiement(debutPartie);
					
					


					if(debutPartie && ia && tour == listeJoueurs.size()-1)
					{
						IA ia = (IA) listeJoueurs.get(tour);
						
						ia.echangerTroupes();
						
						ia.deployer();
						
						passerAuTourSuivant();
						
						debutPartie = false;
						this.joueurEnCours = listeJoueurs.get(tour);
						
						this.joueurEnCours.calculerNombreTroupesDeploiement(debutPartie);
						this.couche = 3;
						Main.affichage.resetAffichage(0);
					}
					else if(debutPartie)
					{
						this.couche = 9;
						Main.affichage.resetAffichage(4);
					}
					else if(tour == listeJoueurs.size()-1)
					{
						this.couche = 12;
						Main.affichage.resetAffichage(-1);
					}
					else
					{
						this.couche = 3;//Deploiement
						this.mode = 0;
						Main.affichage.resetAffichage(0);
					}

					
					
					return false;
				}
				else if( this.joueurEnCours.getNombreSoldatsDeploiement() == 0 && this.joueurEnCours.getNombreCavaliersDeploiement() == 0 && this.joueurEnCours.getNombreCanonsDeploiement() == 0 )
				{
					Main.affichage.resetAffichage(5);
					
					sauvegarderPlateauEnCache();
					
					this.couche = 4;//Choix

					Main.affichage.resetAffichage(1);

					return false;
				}
				else
				{
					Main.affichage.resetAffichage(5);
				}

				Main.affichage.afficherInfosDeploiement();

				return false;
			}
			else
			{
				Main.affichage.afficherInfosBas(6);
				return true;
			}


		case 1://Selection du territoire duquel le joueur attaque

			if(territoire.appartientA(this.joueurEnCours) && territoire.peutAttaquerOuDeplacer())
			{
				territoire1 = territoire;
				this.mode = 2;//Attaque etape 2

				Main.affichage.afficherInfosHaut(0);
				Main.affichage.afficherInfosBas(1);
				return false;
			}
			else
			{
				Main.affichage.afficherInfosBas(2);
				return true;
			}

		case 2://Selection du territoire qu'il attaque

			if(territoire.appartientA(joueurEnCours) == false && territoire.estAdjacentA(territoire1))
			{
				territoire2 = territoire;
				couche = 6;


				territoire1.setNombreTroupesATT(territoire1.calculerNombreDesATT());

				Main.affichage.afficherInfosHaut(10);
				Main.affichage.affichrNombreTroupesTerritoire();

				return false;
			}
			else if(territoire.estAdjacentA(territoire1) != true)
			{
				Main.affichage.afficherInfosBas(4);
				return true;
			}
			else
			{
				Main.affichage.afficherInfosBas(3);
				return true;
			}

		case 3://Selection du territoire duquel le joueur fait le deplacement

			if(territoire.appartientA(joueurEnCours) && territoire.peutAttaquerOuDeplacer())
			{
				territoire1 = territoire;
				this.mode = 4;//Deplacement etape 2

				Main.affichage.afficherInfosHaut(0);
				Main.affichage.afficherInfosBas(0);
				return false;
			}
			else
			{
				Main.affichage.afficherInfosBas(5);
				return true;
			}

		case 4://Selection du territoire sur lequel on fait le deplacement

			if(territoire.appartientA(joueurEnCours) && territoire.estAdjacentA(territoire1))
			{
				territoire2 = territoire;
				couche = 8;
				
				this.sauvegarderPlateauEnCache();
				
				Main.affichage.afficherCadreDeplacement();
				return false;
			}
			else
			{
				Main.affichage.afficherInfosBas(6);
				return true;
			}

		default:
			return false;
	}

}

	/**
	 * Main.affichage.reset les déplacements d'un joueur
	 * @param joueur
	 */
	public void resetDeplacement(Joueur joueur)
	{
		for(int i = 0;i<listeTerritoires.size();i++)
		{
			if(listeTerritoires.get(i).getProprietaire().equals(joueur))
			{
				listeTerritoires.get(i).resetDeplacements();
			}
		}
	}
	
	/**
	 * Permet de retrouver un territoire via son nom
	 * @param nom le nom du territoire
	 * @return le territoire
	 */
	public Territoire retrouverAvecNom(String nom)
	{
		for(int i = 0;i < listeTerritoires.size();i++)
		{
			if(listeTerritoires.get(i).getNom() == nom)
			{
				return listeTerritoires.get(i);
			}
		}
		
		return null;
	}
	//DIVERS

}