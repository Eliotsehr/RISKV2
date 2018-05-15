import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.introcs.StdDraw;

public class Jeu {
	
	public ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
	public ArrayList<Territoire> listeTerritoires = new ArrayList<Territoire>();
	
	
	public ArrayList<Continent> listeContinents = new ArrayList<Continent>();
	
	public int tour = 0;
	public int map = 0;
	public boolean ia = false;
	public int nombreJoueurs;
	
	public boolean debut = true;
	public boolean continuer = false;
	
	public int nombreTroupesATT;
	
	
	public ArrayList<Territoire> listeGagnants = new ArrayList<Territoire>();
	public ArrayList<Joueur> listeElimines = new ArrayList<Joueur>();
	
	
	/**
	 * Constructeur du jeu, initialise les territoires, continent et unités
	 */
	public Jeu() {
	}
	
	public void creerTerritoires()
	{
		if(map == 0)
		{
			//Territoires adjacents
			String[] listeIS = {"Scandinavie","Grande-Bretagne","Groenland"};
			String[] listeSC = {"Island","Grande-Bretagne","Ukraine"};
			String[] listeGB = {"Island","Scandinavie","Europe de l'Ouest"};
			String[] listeEO = {"Grande-Bretagne","Europe du Nord","Europe du Sud"};
			String[] listeES = {"Europe de l'Ouest","Europe du Nord","Ukraine","Egypte","Afrique du Nord"};
			String[] listeEN = {"Grande-Bretagne","Ukraine","Europe de l'Ouest","Europe du Sud"};
			String[] listeUK = {"Scandinavie","Europe du Nord","Europe du Sud","Oural","Afganistan","Moyen-Orient"};
			String[] listeEG = {"Afrique de l'Est","Afrique du Nord","Moyen-Orient","Europe du Sud"};
			String[] listeAN = {"Europe de l'Ouest","Europe du Sud","Egypte","Brésil","Congo","Afrique de l'Est"};
			String[] listeAE = {"Egypte","Moyen-Orient","Afrique du Nord","Congo","Afrique du Sud","Madagascar"};
			String[] listeCO = {"Europe du Sud","Egypte","Afrique du Nord","Afrique du Sud","Afrique de l'Est"};
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
			
			
			listeTerritoires.add(new Territoire("Island",listeIS));
			listeTerritoires.add(new Territoire("Scandinavie",listeSC));
			listeTerritoires.add(new Territoire("Grande-Bretagne",listeGB));
			listeTerritoires.add(new Territoire("Europe de l'Ouest",listeEO));
			listeTerritoires.add(new Territoire("Europe du Sud",listeES));
			listeTerritoires.add(new Territoire("Europe du Nord",listeEN));
			listeTerritoires.add(new Territoire("Ukraine",listeUK));
			listeTerritoires.add(new Territoire("Egypte",listeEG));
			listeTerritoires.add(new Territoire("Afrique du Nord",listeAN));
			listeTerritoires.add(new Territoire("Afrique de l'Est",listeAE));
			listeTerritoires.add(new Territoire("Congo",listeCO));
			listeTerritoires.add(new Territoire("Afrique du Sud",listeAS));
			listeTerritoires.add(new Territoire("Madagascar",listeMA));
			listeTerritoires.add(new Territoire("Moyen-Orient",listeMO));
			listeTerritoires.add(new Territoire("Inde",listeIN));
			listeTerritoires.add(new Territoire("Afganistan",listeAF));
			listeTerritoires.add(new Territoire("Oural",listeOU));
			listeTerritoires.add(new Territoire("Sibérie",listeSIB));
			listeTerritoires.add(new Territoire("Siam",listeSI));
			listeTerritoires.add(new Territoire("Chine",listeCH));
			listeTerritoires.add(new Territoire("Yakouti",listeYA));
			listeTerritoires.add(new Territoire("Mongolie",listeMON));
			listeTerritoires.add(new Territoire("Japon",listeJA));
			listeTerritoires.add(new Territoire("Kamchatka",listeKA));
			listeTerritoires.add(new Territoire("Irkutsk",listeIR));
			listeTerritoires.add(new Territoire("Alaska",listeAL));
			listeTerritoires.add(new Territoire("Territoires du Nord",listeTN));
			listeTerritoires.add(new Territoire("Alberta",listeALB));
			listeTerritoires.add(new Territoire("Ontario",listeON));
			listeTerritoires.add(new Territoire("Groenland",listeGR));
			listeTerritoires.add(new Territoire("Quebec",listeQU));
			listeTerritoires.add(new Territoire("Etats de L'Ouest",listeEDO));
			listeTerritoires.add(new Territoire("Etats de L'Est",listeEDE));
			listeTerritoires.add(new Territoire("Amerique Centrale",listeAC));
			listeTerritoires.add(new Territoire("Venezuela",listeVE));
			listeTerritoires.add(new Territoire("Brésil",listeBR));
			listeTerritoires.add(new Territoire("Pérou",listePE));
			listeTerritoires.add(new Territoire("Argentine",listeAR));
			listeTerritoires.add(new Territoire("Indonésie",listeIND));
			listeTerritoires.add(new Territoire("Nouvelle Guinée",listeNG));
			listeTerritoires.add(new Territoire("Australie de l'Ouest",listeADO));
			listeTerritoires.add(new Territoire("Australie de l'Est",listeADE));

			
			for(int i = 0; i < listeTerritoires.size();i++)
			{
				listeTerritoires.get(i).ajouterUniteTerritoire(new Unitee(0,1,6,2,1,2));
			}
			
			
			//Continents
			ArrayList<Territoire> c1 = new ArrayList<Territoire>();
			for(int i = 0;i<7;i++)
			{
				c1.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c2 = new ArrayList<Territoire>();
			for(int i = 7;i<13;i++)
			{
				c2.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c3 = new ArrayList<Territoire>();
			for(int i = 13;i<25;i++)
			{
				c3.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c4 = new ArrayList<Territoire>();
			for(int i = 25;i<34;i++)
			{
				c4.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c5 = new ArrayList<Territoire>();
			for(int i = 34;i<38;i++)
			{
				c5.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c6 = new ArrayList<Territoire>();
			for(int i = 38;i<42;i++)
			{
				c6.add(listeTerritoires.get(i));
			}
			

			listeContinents.add(new Continent(0,c1));//Europe
			listeContinents.add(new Continent(1,c2));//Afrique
			listeContinents.add(new Continent(2,c3));//Asie
			listeContinents.add(new Continent(3,c4));//Amerique du Nord
			listeContinents.add(new Continent(4,c5));//Amerique du Sud
			listeContinents.add(new Continent(5,c6));//Océanie
		}
		else if(map == 1)
		{
			//Territoires adjacents
			
			String[] listeTW = {"Skagos","The Grev Cliffs","Wolfswood"};
			String[] listeSK = {"The Wall","The Grev Cliffs"};
			String[] listeTGC = {"The Wall","Skagos","Wolfswood","Winterfell"};
			String[] listeWO = {"The Wall","The Grev Cliffs","Winterfell","The Neck","The Rills"};
			String[] listeWI = {"The Grev Cliffs","Wolfswoo","The Neck","The Flint Chiff"};
			String[] listeTR = {"Wolfswood","The Neck"};
			String[] listeTN = {"Wolfswood","The Rills","Winterfell","The Flint Chiff","The Vale"};
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
			String[] listeQHL = {"Fills of Norvos", "Forrest of Dohor" , "The Golden Fields" , "Rhoynian Veld" };
			String[] listeFOD = {"Sarnor", "Parched Fields" , "Rhoynian Veld", "Qhoyne Lands"};
			String[] listeTGF = {"Andalos", "Fills of Norvos", "Qhoyne Lands","Sar Mell"};
			String[] listeTDL = {"Dorne", "Sar Mell","The Golden Fields"};
			String[] listeRHV = {"Sar Mell","Wester Waste","Parched Fields","Forrest of Dohor","Qhoyne Lands", "The Golden Fields"};
			String[] listeSM = {"The Disputed Lands", "The Golden Fields","Rhoynian Veld", "Western Waste", "Sea of Sighs" };
			String[] listeWW = {"Sea of Sighs","Sar Mell","Rhoynian Veld","Parched Fields","Painted Mountains"};
			String[] listeSOS = {"Sar Mell","Western Waste", "Elyria","Valyria"};
			String[] listeEL = {"Sea of Sighs","Painted Mountains", "Valyria"};
			String[] listeVA = {"Sea of Sighs", "Valyria"};
			String[] listePM = {"Elyria", "Western Waste", "Parched Fields", "Western Grass Sea" ,"Lhazar", "Slaver's Bay"};
			String[] listeSB = {"Ghiscar", "Red Waste", "Lhazar", "Painted Mountains"};
			String[] listeLH = {"Slaver's Bay", "Painted Mountains", "Western Grass Sea", "Eastern Grass Sea", "Samyrian Fills","Bayasabhad", "Red Waste"};
			String[] listeSA = {"Bayasabhad", "Lhazar", "Eastern Grass Sea", "Vaes Dothrak"};
			String[] listeGH = {"Slayer's Bay", "Red Waste"};
			String[] listeRW = {"Ghiscar", "Slayer's Bay", "Lhazar", "Bayasabhad", "Qarth"};
			String[] listeBA = {"Qarth", "Red Waste", "Lhazar" ,"Samyrian Fills"};
			String[] listeQA = {"Red Waste" ,"Bayasabhad"};
			String[] listeSAR = {"Forrest of Dohor" ,"Parched Fields", "Abandoned Lands"};
			String[] listeAL = {"Sarnor" ,"Parched Fields","Western Grass Sea", "Kingdoms of the Jfqevron"};
			String[] listeKOTJ = {"Abandoned Lands" ,"Western Grass Sea" ,"Eastern Grass Sea", "Vaes Dothrak" ,"The Footprint"};
			String[] listeTF = {"Kingdoms of the Jfqevron", "Vaes Dothrak", "Realms of Jhogrvin", "Jbben"};
			String[] listeJB = {"The Footprint"};
			String[] listeROJ = {"Vaes Dothrak" ,"The Footprint","Riverlands"};
			String[] listeVD = {"Realms of Jhogrvin", "The Footprint", "Kingdoms of the Jfqevron" ,"Eastern Grass Sea" , "Samyrian Fills"};
			String[] listePF = {"Painted Mountains","Western Waste" ,"Rhoynian Veld" ,"Forrest of Dohor" ,"Sarnor" ,"Abandoned Lands" , "Western Grass Sea"};
			String[] listeWGS = {"Painted Mountains" ,"Parched Fields","Abandoned Lands","Kingdoms of the Jfqevron","Eastern Grass Sea", "Lhazar"};
			String[] listeEGS = {"Wester Grass Sea","Kingdoms of the Jfqevron", "Vaes Dothrak" ,"Samyrian Fills","Lhazar"};
			
			//Territoires
			listeTerritoires.add(new Territoire("The Wall",listeTW));
			listeTerritoires.add(new Territoire("Skagos",listeSK));
			listeTerritoires.add(new Territoire("The Grev Cliffs",listeTGC));
			listeTerritoires.add(new Territoire("Wolfswood",listeWO));
			listeTerritoires.add(new Territoire("Winterfell",listeWI));
			listeTerritoires.add(new Territoire("The Rills",listeTR));
			listeTerritoires.add(new Territoire("The Flint Cliff",listeTFC));
			listeTerritoires.add(new Territoire("The Neck",listeTN));
			listeTerritoires.add(new Territoire("The Vale",listeTV));
			listeTerritoires.add(new Territoire("Jron Islands",listeJI));
			listeTerritoires.add(new Territoire("Riverlands",listeRL));
			listeTerritoires.add(new Territoire("Crownlands",listeCL));
			listeTerritoires.add(new Territoire("Westerlands",listeWL));
			listeTerritoires.add(new Territoire("Shield Lands",listeSL));
			listeTerritoires.add(new Territoire("The Reach",listeTRE));
			listeTerritoires.add(new Territoire("Stormlands",listeSTRM));
			listeTerritoires.add(new Territoire("Whispering Sounds",listeWS));
			listeTerritoires.add(new Territoire("Red Mountains",listeRM));
			listeTerritoires.add(new Territoire("Dorne",listeDR));
			listeTerritoires.add(new Territoire("Braavosian Coastland",listeBC));
			listeTerritoires.add(new Territoire("Andalos",listeAN));
			listeTerritoires.add(new Territoire("Fills of Norvos",listeFON));
			listeTerritoires.add(new Territoire("Qhoyne Lands",listeQHL));
			listeTerritoires.add(new Territoire("Forrest Of Dohor",listeFOD));
			listeTerritoires.add(new Territoire("The Golden Fields",listeTGF));
			listeTerritoires.add(new Territoire("The Disputed Lands",listeTDL));
			listeTerritoires.add(new Territoire("Rhoynian Veld",listeRHV));
			listeTerritoires.add(new Territoire("Sar Mell",listeSM));
			listeTerritoires.add(new Territoire("Western Waste",listeWW));
			listeTerritoires.add(new Territoire("Sea Of Sight",listeSOS));
			listeTerritoires.add(new Territoire("Elyria",listeEL));
			listeTerritoires.add(new Territoire("Valyria",listeVA));
			listeTerritoires.add(new Territoire("Painted Mountains",listePM));
			listeTerritoires.add(new Territoire("Slaver's Bay",listeSB));
			listeTerritoires.add(new Territoire("Lhazar",listeLH));
			listeTerritoires.add(new Territoire("Samyrian Fills",listeSA));
			listeTerritoires.add(new Territoire("Ghiscar",listeGH));
			listeTerritoires.add(new Territoire("Red Waste",listeRW));
			listeTerritoires.add(new Territoire("Bayasabhad",listeBA));
			listeTerritoires.add(new Territoire("Qarth",listeQA));
			listeTerritoires.add(new Territoire("Sarnor",listeSAR));
			listeTerritoires.add(new Territoire("Abandoned Lands",listeAL));
			listeTerritoires.add(new Territoire("Kingdoms Of The Jfeqevron",listeKOTJ));
			listeTerritoires.add(new Territoire("The Footprint",listeTF));
			listeTerritoires.add(new Territoire("Jbben",listeJB));
			listeTerritoires.add(new Territoire("Realms Of Jhogrvin",listeROJ));
			listeTerritoires.add(new Territoire("Vaes Dothrak",listeVD));
			listeTerritoires.add(new Territoire("Parched Fields",listePF));
			listeTerritoires.add(new Territoire("Western Grass Sea",listeWGS));
			listeTerritoires.add(new Territoire("Easter Grass Sea",listeEGS));
		
		
			for(int i = 0; i < listeTerritoires.size();i++)
			{
				ArrayList<Unitee> liste = new ArrayList<Unitee>();
						
				listeTerritoires.get(i).setListeUnitees(liste);
				listeTerritoires.get(i).ajouterUniteTerritoire(new Unitee(0,1,6,2,1,2));
			}
			
			//Continents
			ArrayList<Territoire> c1 = new ArrayList<Territoire>();
			for(int i = 0;i<8;i++)
			{
				c1.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c2 = new ArrayList<Territoire>();
			for(int i = 8;i<13;i++)
			{
				c2.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c6 = new ArrayList<Territoire>();
			for(int i = 13;i<19;i++)
			{
				c6.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c4 = new ArrayList<Territoire>();
			for(int i = 19;i<26;i++)
			{
				c4.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c5 = new ArrayList<Territoire>();
			for(int i = 26;i<32;i++)
			{
				c5.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c3 = new ArrayList<Territoire>();
			for(int i = 32;i<42;i++)
			{
				c3.add(listeTerritoires.get(i));
			}
			
			ArrayList<Territoire> c7 = new ArrayList<Territoire>();
			for(int i = 42;i<50;i++)
			{
				c7.add(listeTerritoires.get(i));
			}
			

			listeContinents.add(new Continent(0,c1));//Europe
			listeContinents.add(new Continent(1,c2));//Afrique
			listeContinents.add(new Continent(2,c3));//Asie
			listeContinents.add(new Continent(3,c4));//Amerique du Nord
			listeContinents.add(new Continent(4,c5));//Amerique du Sud
			listeContinents.add(new Continent(5,c6));//Océanie
			listeContinents.add(new Continent(6,c7));
		}
	}

	
	//DIVERS
	/**
	 * Distribue les territoires entre les joueurs
	 */
	public void attributionTerritoire()
	{
		//Cette partie permet de créer une liste mélangée des territoires
		ArrayList<Integer> listeRandomTerritoires = new ArrayList<Integer>();
	    for (int i=0; i<listeTerritoires.size(); i++)
	    {
	    	listeRandomTerritoires.add(new Integer(i));
        }
	    
	    Collections.shuffle(listeRandomTerritoires);
	    //fin
	    
	  
		int indexJoueur = 0;
		
		for(int i = 0;i < listeTerritoires.size();i++)//On attribue dans l'ordre de la liste mélangée les territoires aux joueurs
		{
			listeTerritoires.get(listeRandomTerritoires.get(i)).setProprietaire(listeJoueurs.get(indexJoueur));//On définit le propriétaire
			listeJoueurs.get(indexJoueur).ajouterTerritoire(1);//On ajoute une troupe au joueur
			listeJoueurs.get(indexJoueur).ajouterTerritoireControle(listeTerritoires.get(listeRandomTerritoires.get(i)));
			indexJoueur++;
			
			if(indexJoueur>=listeJoueurs.size())
			{
				indexJoueur = 0;
			}
		}	
			
	}

	
	/**
	 * Attribu un mission à un joueur
	 */
	public void attributionMission()
	{
		//Cette partie permet de créer une liste mélangée des territoires
		ArrayList<Integer> listeRandomMissions = new ArrayList<Integer>();
		for (int i=0; i<listeJoueurs.size()+2 && i<7; i++)
		{
			listeRandomMissions.add(new Integer(i));
		}
			    
		Collections.shuffle(listeRandomMissions);
		//fin
		
		for(int i = 0;i<listeJoueurs.size();i++)
		{
			
			listeJoueurs.get(i).definirMission(mission(listeRandomMissions.get(i)));
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
			int random = (int) (Math.random() * listeJoueurs.size() - 0) + 0;
			Mission mission = new Mission("Détruire le joueur "+listeJoueurs.get(random).getNom()+" !",4);	
			mission.definirJoueurAEliminer(listeJoueurs.get(random));
			
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
	
	

	
	
	/**
	 * Definit le tour auquel on est
	 * @param tour
	 */
	public void tour(int tour)
	{
		this.tour++;
		
		if(this.tour == listeJoueurs.size())
		{
			this.tour = 0;
		}
		
		if(this.tour == 0)
		{
			this.debut = false;
		}
	}

	
	/**
	 * Check si un joueur a perdu
	 */
	public void defaiteJoueur()
	{
		for(int i = 0; i < listeJoueurs.size();i++)
		{
			if(listeJoueurs.get(i).aPerdu())
			{
				listeElimines.add(listeJoueurs.get(i));
				StdDraw.text(0.5, 0.97, listeJoueurs.get(i).getNom()+" a perdu !");
				listeJoueurs.remove(i);
				StdDraw.pause(3000);
			}
		}
	}


	/**
	 * Permet de savoir si un joueur a gagné
	 */
	public void finPartie()
	{
		if(listeJoueurs.size() == 1)
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
	public boolean verifications(Interface interf, Territoire territoire, int mode)
	{
		//Ces deux lignes sont pour corriger le bug du StdDraw.isMousePressed()
		interf.sourisX = 0;
		interf.sourisY = 0;

		switch(mode)
		{
		case 0:	//Deploiment

			if(territoire.appartientA(interf.joueurEnCours) && (interf.joueurEnCours.getNombreSoldatsDeploiement() > 0 || interf.joueurEnCours.getNombreCavaliersDeploiement() > 0 || interf.joueurEnCours.getNombreCanonsDeploiement() > 0 ))
			{
				territoire.deploiement(interf.joueurEnCours);

				if(interf.joueurEnCours.getNombreSoldatsDeploiement() == 0 && interf.joueurEnCours.getNombreCavaliersDeploiement() == 0 && interf.joueurEnCours.getNombreCanonsDeploiement() == 0  && debut)
				{
					tour(tour);
					interf.joueurEnCours = listeJoueurs.get(tour);
					
					interf.joueurEnCours.combienTroupe(debut);


					if(debut && ia && tour == listeJoueurs.size()-1)
					{
						IA ia = (IA) listeJoueurs.get(tour);
						
						ia.echange();
						
						ia.deploiement();
						
						tour(tour);
						
						debut = false;
						interf.joueurEnCours = listeJoueurs.get(tour);
						
						interf.joueurEnCours.combienTroupe(debut);
						interf.couche = 3;
						interf.reset(0);
					}
					else if(debut)
					{
						interf.couche = 9;
						interf.reset(4);
					}
					else if(tour == listeJoueurs.size()-1)
					{
						interf.couche = 12;
						interf.reset(-1);
					}
					else
					{
						interf.couche = 3;//Deploiement
						interf.mode = 0;
						interf.reset(0);
					}


					return false;
				}
				else if( interf.joueurEnCours.getNombreSoldatsDeploiement() == 0 && interf.joueurEnCours.getNombreCavaliersDeploiement() == 0 && interf.joueurEnCours.getNombreCanonsDeploiement() == 0 )
				{
					interf.couche = 4;//Choix

					interf.reset(1);//Choix

					return false;
				}
				else
				{
					interf.reset(-1);//Vide
				}

				interf.infosDeploiement();

				return false;
			}
			else
			{
				interf.infosBas(6);
				return true;
			}


		case 1://Selection du territoire duquel le interf.joueurEnCours attaque

			if(territoire.appartientA(interf.joueurEnCours) && territoire.peutAttaquerOuDeplacer())
			{
				interf.territoire1 = territoire;
				interf.mode = 2;//Attaque etape 2

				interf.infosHaut(0);
				interf.infosBas(1);
				return false;
			}
			else
			{
				interf.infosBas(2);
				return true;
			}

		case 2://Selection du territoire qu'il attaque

			if(territoire.appartientA(interf.joueurEnCours) == false && territoire.estAdjacentA(interf.territoire1))
			{
				interf.territoire2 = territoire;
				interf.couche = 6;

				nombreTroupesATT = interf.territoire1.nombreDesATT();

				interf.infosHaut(10);
				interf.confirmationAttaque();

				return false;
			}
			else if(territoire.estAdjacentA(interf.territoire1) != true)
			{
				interf.infosBas(4);
				return true;
			}
			else
			{
				interf.infosBas(3);
				return true;
			}

		case 3://Selection du territoire duquel le interf.joueurEnCours fait le deplacement

			if(territoire.appartientA(interf.joueurEnCours) && territoire.peutAttaquerOuDeplacer())
			{
				interf.territoire1 = territoire;
				interf.mode = 4;//Deplacement etape 2

				interf.infosHaut(0);
				interf.infosBas(0);
				return false;
			}
			else
			{
				interf.infosBas(5);
				return true;
			}

		case 4://Selection du territoire sur lequel on fait le deplacement

			if(territoire.appartientA(interf.joueurEnCours) && territoire.estAdjacentA(interf.territoire1))
			{
				interf.territoire2 = territoire;
				interf.couche = 8;

				interf.coucheDeplacement();
				return false;
			}
			else
			{
				interf.infosBas(6);
				return true;
			}

		default:
			return false;
	}

}

	
	
	
	/**
	 * Reset les déplacements d'un joueur
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
	//DIVERS

}
