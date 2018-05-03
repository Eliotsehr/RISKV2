import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.introcs.StdDraw;

public class Jeu {
	
	public ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
	
	public ArrayList<Territoire> listeTerritoires = new ArrayList<Territoire>();
	public ArrayList<Territoire> listeGagnants = new ArrayList<Territoire>();
	
	public ArrayList<Continent> listeContinents = new ArrayList<Continent>();
	
	public int tour = 0;
	public int map = 0;
	public int nombreJoueurs;
	public int nombreTroupesDeploiement;
	
	public int nombreSoldats;
	public int nombreCavaliers;
	public int nombreCanons;
	
	public int nombreTroupesATT;
	
	public ArrayList<Integer> desATT = new ArrayList<Integer>();
	public ArrayList<Integer> desDEF = new ArrayList<Integer>();
	
	public ArrayList<Unitee> uniteeATT = new ArrayList<Unitee>();
	public ArrayList<Unitee> uniteeDEF = new ArrayList<Unitee>();
	
	public ArrayList<Joueur> listeElimines = new ArrayList<Joueur>();
	
	
	/**
	 * Constructeur du jeu, initialise les territoires, continent et unit�s
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
			String[] listeAN = {"Europe de l'Ouest","Europe du Sud","Egypte","Br�sil","Congo","Afrique de l'Est"};
			String[] listeAE = {"Egypte","Moyen-Orient","Afrique du Nord","Congo","Afrique du Sud","Madagascar"};
			String[] listeCO = {"Europe du Sud","Egypte","Afrique du Nord","Afrique du Sud","Afrique de l'Est"};
			String[] listeMA = {"Afrique de l'Est","Afrique du Sud"};
			String[] listeAS = {"Congo","Afrique de l'Est","Madagascar"};
			String[] listeMO = {"Egypte","Afrique de l'Est","Ukraine","Europe du Sud","Afganistan","Inde"};
			String[] listeIN = {"Moyen-Orient","Afganistan","Chine","Siam"};
			String[] listeAF = {"Ukraine","Moyen-Orient","Inde","Oural","Chine"};
			String[] listeOU = {"Chine","Afganistan","Ukraine","Sib�rie"};
			String[] listeSIB = {"Mongolie","Chine","Oural","Irkutsk","Yakouti"};
			String[] listeSI = {"Inde","Chine","Indon�sie"};
			String[] listeCH = {"Sib�rie","Inde","Afganistan","Oural","Siam","Mongolie"};
			String[] listeYA = {"Kamchatka","Irkutsk","Sib�rie"};
			String[] listeMON = {"Sib�rie","Chine","Irkutsk","Kamchatka","Japon"};
			String[] listeJA = {"Kamchatka","Mongolie"};
			String[] listeKA = {"Irkutsk","Yakouti","Mongolie","Japon","Alaska"};
			String[] listeIR = {"Mongolie","Kamchatka","Yakouti","Sib�rie"};
			String[] listeAL = {"Kamchatka","Alberta","Territoires du Nord"};
			String[] listeTN = {"Ontario","Alberta","Alaska","Groenland"};
			String[] listeALB = {"Alaska","Territoires du Nord","Ontario","Etats de l'Ouest"};
			String[] listeON = {"Territoires du Nord","Alberta","Etats de l'Ouest","Etats de L'Est","Quebec","Groenland"};
			String[] listeGR = {"Ontario","Territoires du Nord","Quebec","Island"};
			String[] listeQU = {"Etats de l'Est","Ontario","Groenland"};
			String[] listeEDO = {"Etats de l'Est","Amerique Centrale","Ontario","Alberta"};
			String[] listeEDE = {"Etats de l'Ouest","Amerique Centrale","Ontario","Quebec"};
			String[] listeAC = {"Venezuela","Etats de l'Est","Etats de l'Ouest"};
			String[] listeVE = {"Amerique Centrale","P�rou","Br�sil"};
			String[] listeBR = {"Argentine","Afrique du Nord","P�rou","Venezuela"};
			String[] listePE = {"Argentine","Br�sil","Venezuela"};
			String[] listeAR = {"P�rou","Br�sil"};
			String[] listeIND = {"Nouvelle Guin�e","Siam","Australie de l'Ouest"};
			String[] listeNG = {"Indon�sie","Australie de l'Ouest","Australie de l'Est"};
			String[] listeADO = {"Australie de l'Est","Nouvelle Guin�e","Indon�sie"};
			String[] listeADE = {"Australie de l'Ouest","Nouvelle Guinn�e"};
			
			//Territoires			
			listeTerritoires.add(new Territoire("Island",null,1,listeIS,1,0,0,null));
			listeTerritoires.add(new Territoire("Scandinavie",null,1,listeSC,1,0,0,null));
			listeTerritoires.add(new Territoire("Grande-Bretagne",null,1,listeGB,1,0,0,null));
			listeTerritoires.add(new Territoire("Europe de l'Ouest",null,1,listeEO,1,0,0,null));
			listeTerritoires.add(new Territoire("Europe du Sud",null,1,listeES,1,0,0,null));
			listeTerritoires.add(new Territoire("Europe du Nord",null,1,listeEN,1,0,0,null));
			listeTerritoires.add(new Territoire("Ukraine",null,1,listeUK,1,0,0,null));
			listeTerritoires.add(new Territoire("Egypte",null,1,listeEG,1,0,0,null));
			listeTerritoires.add(new Territoire("Afrique du Nord",null,1,listeAN,1,0,0,null));
			listeTerritoires.add(new Territoire("Afrique de l'Est",null,1,listeAE,1,0,0,null));
			listeTerritoires.add(new Territoire("Congo",null,1,listeCO,1,0,0,null));
			listeTerritoires.add(new Territoire("Afrique du Sud",null,1,listeAS,1,0,0,null));
			listeTerritoires.add(new Territoire("Madagascar",null,1,listeMA,1,0,0,null));
			listeTerritoires.add(new Territoire("Moyen-Orient",null,1,listeMO,1,0,0,null));
			listeTerritoires.add(new Territoire("Inde",null,1,listeIN,1,0,0,null));
			listeTerritoires.add(new Territoire("Afganistan",null,1,listeAF,1,0,0,null));
			listeTerritoires.add(new Territoire("Oural",null,1,listeOU,1,0,0,null));
			listeTerritoires.add(new Territoire("Sib�rie",null,1,listeSIB,1,0,0,null));
			listeTerritoires.add(new Territoire("Siam",null,1,listeSI,1,0,0,null));
			listeTerritoires.add(new Territoire("Chine",null,1,listeCH,1,0,0,null));
			listeTerritoires.add(new Territoire("Yakouti",null,1,listeYA,1,0,0,null));
			listeTerritoires.add(new Territoire("Mongolie",null,1,listeMON,1,0,0,null));
			listeTerritoires.add(new Territoire("Japon",null,1,listeJA,1,0,0,null));
			listeTerritoires.add(new Territoire("Kamchatka",null,1,listeKA,1,0,0,null));
			listeTerritoires.add(new Territoire("Irkutsk",null,1,listeIR,1,0,0,null));
			listeTerritoires.add(new Territoire("Alaska",null,1,listeAL,1,0,0,null));
			listeTerritoires.add(new Territoire("Territoires du Nord",null,1,listeTN,1,0,0,null));
			listeTerritoires.add(new Territoire("Alberta",null,1,listeALB,1,0,0,null));
			listeTerritoires.add(new Territoire("Ontario",null,1,listeON,1,0,0,null));
			listeTerritoires.add(new Territoire("Groenland",null,1,listeGR,1,0,0,null));
			listeTerritoires.add(new Territoire("Quebec",null,1,listeQU,1,0,0,null));
			listeTerritoires.add(new Territoire("Etats de l'Ouest",null,1,listeEDO,1,0,0,null));
			listeTerritoires.add(new Territoire("Etats de l'Est",null,1,listeEDE,1,0,0,null));
			listeTerritoires.add(new Territoire("Amerique Centrale",null,1,listeAC,1,0,0,null));
			listeTerritoires.add(new Territoire("Venezuela",null,1,listeVE,1,0,0,null));
			listeTerritoires.add(new Territoire("Br�sil",null,1,listeBR,1,0,0,null));
			listeTerritoires.add(new Territoire("P�rou",null,1,listePE,1,0,0,null));
			listeTerritoires.add(new Territoire("Argentine",null,1,listeAR,1,0,0,null));
			listeTerritoires.add(new Territoire("Indon�sie",null,1,listeIND,1,0,0,null));
			listeTerritoires.add(new Territoire("Nouvelle Guin�e",null,1,listeNG,1,0,0,null));
			listeTerritoires.add(new Territoire("Australie de l'Ouest",null,1,listeADO,1,0,0,null));
			listeTerritoires.add(new Territoire("Australie de l'Est",null,1,listeADE,1,0,0,null));

			
			for(int i = 0; i < listeTerritoires.size();i++)
			{
				ArrayList<Unitee> liste = new ArrayList<Unitee>();
						
				listeTerritoires.get(i).setListeUnitees(liste);
				listeTerritoires.get(i).ajouterUnite(new Unitee(0,1,6,2,1,2,0,0));
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
			listeContinents.add(new Continent(5,c6));//Oc�anie
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
			listeTerritoires.add(new Territoire("The Wall",null,1,listeTW,1,0,0,null));
			listeTerritoires.add(new Territoire("Skagos",null,1,listeSK,1,0,0,null));
			listeTerritoires.add(new Territoire("The Grev Cliffs",null,1,listeTGC,1,0,0,null));
			listeTerritoires.add(new Territoire("Wolfswood",null,1,listeWO,1,0,0,null));
			listeTerritoires.add(new Territoire("Winterfell",null,1,listeWI,1,0,0,null));
			listeTerritoires.add(new Territoire("The Rills",null,1,listeTR,1,0,0,null));
			listeTerritoires.add(new Territoire("The Neck",null,1,listeTN,1,0,0,null));
			listeTerritoires.add(new Territoire("The Flint Cliff",null,1,listeTFC,1,0,0,null));
			listeTerritoires.add(new Territoire("The Vale",null,1,listeTV,1,0,0,null));
			listeTerritoires.add(new Territoire("Jron Islands",null,1,listeJI,1,0,0,null));
			listeTerritoires.add(new Territoire("Riverlands",null,1,listeRL,1,0,0,null));
			listeTerritoires.add(new Territoire("Crownlands",null,1,listeCL,1,0,0,null));
			listeTerritoires.add(new Territoire("Westerlands",null,1,listeWL,1,0,0,null));
			listeTerritoires.add(new Territoire("Shield Lands",null,1,listeSL,1,0,0,null));
			listeTerritoires.add(new Territoire("The Reach",null,1,listeTRE,1,0,0,null));
			listeTerritoires.add(new Territoire("Stromlands",null,1,listeSTRM,1,0,0,null));
			listeTerritoires.add(new Territoire("Whispering Sounds",null,1,listeWS,1,0,0,null));
			listeTerritoires.add(new Territoire("Red Mountains",null,1,listeRM,1,0,0,null));
			listeTerritoires.add(new Territoire("Dorne",null,1,listeDR,1,0,0,null));
			listeTerritoires.add(new Territoire("Braavosian Coastland",null,1,listeBC,1,0,0,null));
			listeTerritoires.add(new Territoire("Andalos",null,1,listeAN,1,0,0,null));
			listeTerritoires.add(new Territoire("Fills Of Norvos",null,1,listeFON,1,0,0,null));
			listeTerritoires.add(new Territoire("Qhoyne Lands",null,1,listeQHL,1,0,0,null));
			listeTerritoires.add(new Territoire("Forrest Of Dohor",null,1,listeFOD,1,0,0,null));
			listeTerritoires.add(new Territoire("The Golden Fields",null,1,listeTGF,1,0,0,null));
			listeTerritoires.add(new Territoire("The Disputed Lands",null,1,listeTDL,1,0,0,null));
			listeTerritoires.add(new Territoire("Rhoynian Veld",null,1,listeRHV,1,0,0,null));
			listeTerritoires.add(new Territoire("Sar Mell",null,1,listeSM,1,0,0,null));
			listeTerritoires.add(new Territoire("Western Waste",null,1,listeWW,1,0,0,null));
			listeTerritoires.add(new Territoire("Sea Of Sight",null,1,listeSOS,1,0,0,null));
			listeTerritoires.add(new Territoire("Elyria",null,1,listeEL,1,0,0,null));
			listeTerritoires.add(new Territoire("Valyria",null,1,listeVA,1,0,0,null));
			listeTerritoires.add(new Territoire("Painted Mountains",null,1,listePM,1,0,0,null));
			listeTerritoires.add(new Territoire("Slaver's Bay",null,1,listeSB,1,0,0,null));
			listeTerritoires.add(new Territoire("Lhazar",null,1,listeLH,1,0,0,null));
			listeTerritoires.add(new Territoire("Samyrian Fills",null,1,listeSA,1,0,0,null));
			listeTerritoires.add(new Territoire("Ghiscar",null,1,listeGH,1,0,0,null));
			listeTerritoires.add(new Territoire("Red Waste",null,1,listeRW,1,0,0,null));
			listeTerritoires.add(new Territoire("Bayasabhad",null,1,listeBA,1,0,0,null));
			listeTerritoires.add(new Territoire("Qarth",null,1,listeQA,1,0,0,null));
			listeTerritoires.add(new Territoire("Sarnor",null,1,listeSAR,1,0,0,null));
			listeTerritoires.add(new Territoire("Abandoned Lands",null,1,listeAL,1,0,0,null));
			listeTerritoires.add(new Territoire("Kingdoms Of The Jfeqevron",null,1,listeKOTJ,1,0,0,null));
			listeTerritoires.add(new Territoire("The Footprint",null,1,listeTF,1,0,0,null));
			listeTerritoires.add(new Territoire("Jbben",null,1,listeJB,1,0,0,null));
			listeTerritoires.add(new Territoire("Realms Of Jhogrvin",null,1,listeROJ,1,0,0,null));
			listeTerritoires.add(new Territoire("Vaes Dothrak",null,1,listeVD,1,0,0,null));
			listeTerritoires.add(new Territoire("Parched Fields",null,1,listePF,1,0,0,null));
			listeTerritoires.add(new Territoire("Western Grass Sea",null,1,listeWGS,1,0,0,null));
			listeTerritoires.add(new Territoire("Easter Grass Sea",null,1,listeEGS,1,0,0,null));
		
		
			for(int i = 0; i < listeTerritoires.size();i++)
			{
				ArrayList<Unitee> liste = new ArrayList<Unitee>();
						
				listeTerritoires.get(i).setListeUnitees(liste);
				listeTerritoires.get(i).ajouterUnite(new Unitee(0,1,6,2,1,2,0,0));
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
			listeContinents.add(new Continent(5,c6));//Oc�anie
			listeContinents.add(new Continent(6,c7));
		}
	}
	
	
	//TROUPES
	/**
	 * Renvoit le nombre de troupes que le joueur doit recevoir
	 * @param joueur joueur pour lequel on veut savoir le nombre de troupe qu'il obtient au d�but du tour
	 * @param type type d'apport, 0 d�but de partie 1 jeu
	 * @return nombre de troupes
	 */
	public int combienTroupe(Joueur joueur, boolean debut)
	{
		if(debut)//Debut de partie
		{
			if(listeJoueurs.size() == 2)
			{
				return 19;
			}
			else if(listeJoueurs.size() == 3)
			{
				return 21;
			}
			else if(listeJoueurs.size() == 4)
			{
				return 20;
			}
			else if(listeJoueurs.size() == 5)
			{
				return 17;
			}
			else
			{
				return 13;
			}
		}
		else
		{
			return (int) Math.floor(joueur.getNombreDeTerritoires()/3) + bonusContinent(listeJoueurs.get(tour)) + bonusCapture(listeJoueurs.get(tour));
		}
	}
	
	
	/**
	 * Donne un bonus de troupe en cas de controle d'un continent
	 * @param joueur le joueur auquel on donne le bonus
	 * @return le bonus (int)
	 */
	public int bonusContinent(Joueur joueur)
	{
		int bonus = 0;
		
		for(int i = 0; i < listeContinents.size();i++)
		{
			if(listeContinents.get(i).estControlePar(joueur, 0))
			{
				if(i == 0)
				{
					bonus = bonus + 4;
				}
				else if(i == 1)
				{
					bonus = bonus + 3;
				}
				else if(i == 2)
				{
					bonus = bonus + 6;
				}
				else if(i == 3)
				{
					bonus = bonus + 5;
				}
				else if(i == 4)
				{
					bonus = bonus + 2;
				}
				else
				{
					bonus = bonus + 2;
				}
				
			}
		}
		
		return bonus;
	}

	
	/**
	 * Bonus des territoires captur�s au tour pr�c�dent
	 * @param Joueur joueur
	 * @return le bonus
	 */
	public int bonusCapture(Joueur joueur)
	{
		int bonus = 0;
		
		for(int i = 0;i<joueur.getNombreTerritoiresCaptures();i++)
		{
			bonus = bonus + (int) (Math.random() * 2 - 0) + 0;
		}
		
		return bonus;
	}
	//TROUPES
	
	
	//ATTAQUE
	/**
	 * Lance une attaque d'un territoire sur un autre
	 * @param territoireDEF le territoire qui se d�fend
	 * @return une liste avec les territoires qui ont gagn� pour chaque lanc�
	 */
	public void attaque(Territoire territoireATT, Territoire territoireDEF)
	{

		int nombreDesDEF = territoireDEF.nombreDesDEF();
		
		uniteeATT = listeUniteeATT(uniteeATT);
		uniteeDEF = listeUniteeDEF(territoireDEF, nombreDesDEF);
		
		
		uniteeATT = triDes(lanceDes(uniteeATT));
		uniteeDEF = triDes(lanceDes(uniteeDEF));
		
		prioriteATT(uniteeATT);
		prioriteDEF(uniteeDEF);
		
		ArrayList<Territoire> listeGagnants = gagnant(uniteeATT,uniteeDEF,territoireATT,territoireDEF);
		
		
		miseAJourProprietaire(territoireATT, territoireDEF);
		
		
		this.listeGagnants = listeGagnants;
	}

	
	/**
	 * D�finit la liste des unit�s qui vont d�fendre
	 * @param territoireDEF Le territoire qui se d�fend
	 * @param nombreDesDEF nombre de des en d�fense
	 * @return la liste
	 */
	public ArrayList<Unitee> listeUniteeDEF(Territoire territoireDEF,int nombreDesDEF)
	{
		int soldats = territoireDEF.getNombreSoldats();
		int canons = territoireDEF.getNombreCanons();
		int cavaliers = territoireDEF.getNombreCavaliers();
		
		ArrayList<Unitee> listeUniteeDEF = new ArrayList<Unitee>();
		
		for(int i = 0;i<nombreDesDEF;i++)
		{
			if(soldats > 0)
			{
				listeUniteeDEF.add(ajouterUnite(0,0,listeUniteeDEF,territoireDEF));
				soldats--;
			}
			else if(canons > 0)
			{
				listeUniteeDEF.add(ajouterUnite(2,0,listeUniteeDEF,territoireDEF));
				canons--;
			}
			else if(cavaliers > 0)
			{
				listeUniteeDEF.add(ajouterUnite(1,0,listeUniteeDEF,territoireDEF));
				cavaliers--;
			}
		}
		
		return listeUniteeDEF;
	}

	
	/**
	 * Liste des unit�e qui attaque tri�e
	 * @param listeUniteeATT
	 * @return la liste
	 */
	public ArrayList<Unitee> listeUniteeATT(ArrayList<Unitee> listeUniteeATT)
	{
	
		for(int i = 0;i<listeUniteeATT.size();i++)
		{
			for(int j = 0;j<listeUniteeATT.size();j++)
			{
				if(listeUniteeATT.get(i).getpATT() < listeUniteeATT.get(j).getpATT())
				{
					Unitee pivot = listeUniteeATT.get(i);
					
					listeUniteeATT.set(i, listeUniteeATT.get(j));
					listeUniteeATT.set(j, pivot);
				}
			}
		}
		
		return listeUniteeATT;
	}
	
	
	/**
	 * Ajoute des unit�s dans la liste
	 * @param int type le type d'unit�
	 * @param int rang 0 par d�faut
	 * @param ArrayList<Unitee> listeUniteeDEF
	 * @param Territoire territoireDEF
	 * @return l'unit� � ajouter
	 */
	public Unitee ajouterUnite(int type,int rang, ArrayList<Unitee> listeUniteeDEF, Territoire territoireDEF)
	{
		if(listeUniteeDEF.size() == 0 && territoireDEF.getListeUnitees().get(rang).getType() == type)
		{
			return territoireDEF.getListeUnitees().get(rang);
		}
		else if(territoireDEF.getListeUnitees().get(rang).getType() == type && pasDansLaListe(territoireDEF.getListeUnitees().get(rang),listeUniteeDEF))
		{
			return territoireDEF.getListeUnitees().get(rang);
		}
		else
		{
			return ajouterUnite(type, rang+1 , listeUniteeDEF, territoireDEF);
		}
	}
		
	
	/**
	 * V�rifie qu'une unit� n'est pas d�j� dans la liste des unit�s qui d�fendent ou attaque
	 * @param Unitee unite
	 * @param ArrayList<Unitee> liste
	 * @return true ou false
	 */
	public boolean pasDansLaListe(Unitee unite, ArrayList<Unitee> liste)
	{
		for(int i = 0; i < liste.size();i++)
		{
			if(liste.get(i) == unite)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Permet de r�aliser n lancement de des
	 * @param nombreDes int le nombre de des qu'on veut lancer
	 * @return une liste avec les r�sultats
	 */
	public ArrayList<Unitee> lanceDes(ArrayList<Unitee> listeUnitees)
	{
		for(int i = 0;i<listeUnitees.size();i++)
		{
			int random = (int) (Math.random() * (listeUnitees.get(i).getpMax()-listeUnitees.get(i).getpMin())+1 - 0)+listeUnitees.get(i).getpMin();
			listeUnitees.get(i).setScoreDES(random);
		}
		return listeUnitees;
	}
	
	
	/**
	 * Tri une liste de d�s dans l'ordre d�croissant
	 * @param listeDes ArrayList<Integer>  la liste � trier
	 * @return la liste tri�e dans l'ordre croissant
	 */
	public ArrayList<Unitee> triDes(ArrayList<Unitee> listeUnitee)
	{
		for(int i = 0;i < listeUnitee.size();i++)
		{
			for(int j = 0;j < listeUnitee.size();j++)
			{
				if(listeUnitee.get(i).getScoreDES() > listeUnitee.get(j).getScoreDES())
				{
					Unitee pivot = listeUnitee.get(i);
					
					listeUnitee.set(i, listeUnitee.get(j));
					listeUnitee.set(j, pivot);
				}
			}
		}
		
		return listeUnitee;
	}

	
	/**
	 * Trie la liste des unit�s d'attaque en cas d'�galit� en fonction des priorit�s
	 * @param liste
	 */
	public void prioriteATT(ArrayList<Unitee> liste)
	{
		for(int i = 0; i < liste.size();i++)
		{
			for(int j = 0;j < liste.size();j++)
			{
				if(liste.get(i).getpATT() < liste.get(j).getpATT() && liste.get(i).getScoreDES() == liste.get(j).getScoreDES())
				{
					Unitee pivot = liste.get(i);
					
					liste.set(i, liste.get(j));
					liste.set(j, pivot);
				}
			}
		}
	}
	
	
	/**
	 * Trie la liste des unit�s d'attaque en cas d'�galit� en fonction des priorit�s
	 * @param liste
	 */
	public void prioriteDEF(ArrayList<Unitee> liste)
	{
		for(int i = 0; i < liste.size();i++)
		{
			for(int j = 0;j < liste.size();j++)
			{
				if(liste.get(i).getpDEF() < liste.get(j).getpDEF() && liste.get(i).getScoreDES() == liste.get(j).getScoreDES())
				{
					Unitee pivot = liste.get(i);
					
					liste.set(i, liste.get(j));
					liste.set(j, pivot);
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
	public ArrayList<Territoire> gagnant(ArrayList<Unitee> uniteeATT, ArrayList<Unitee> uniteeDEF,Territoire territoireATT,Territoire territoireDEF)
	{
		ArrayList<Territoire> listeGagnants = new ArrayList<Territoire>();
		
		int compare;
		
		if(uniteeATT.size() > uniteeDEF.size())
		{
			compare = uniteeDEF.size();
		}
		else
		{
			compare = uniteeATT.size();
		}
		
		for(int i = 0; i < compare;i++)
		{
			if(uniteeATT.get(i).getScoreDES() > uniteeDEF.get(i).getScoreDES())
			{
				listeGagnants.add(territoireATT);
				miseAJourUnitee(uniteeDEF.get(i),territoireDEF);
			}
			else
			{
				listeGagnants.add(territoireDEF);
				miseAJourUnitee(uniteeATT.get(i),territoireATT);
			}
		}
		
		return listeGagnants;
	}
	
	
	/**
	 * Met � jour l'affichage et le nombre d'unit�s
	 * @param Unitee unitee
	 * @param Territoire territoire
	 */
	public void miseAJourUnitee(Unitee unitee, Territoire territoire)
	{
		if(unitee.getType() == 0)
		{
			territoire.supprimerUniteAleat(0, 0);
			
			territoire.ajouterSoldats(-1);
			territoire.ajouterTroupe(-1);
		}
		else if(unitee.getType() == 1)
		{
			territoire.supprimerUniteAleat(1, 0);
			
			territoire.ajouterCavaliers(-1);
			territoire.ajouterTroupe(-3);
		}
		else if(unitee.getType() == 2)
		{
			territoire.supprimerUniteAleat(2, 0);
			
			territoire.ajouterCanons(-1);
			territoire.ajouterTroupe(-7);
		}
	}
	
	/**
	 * Met � jour le proprietaire d'un territoire
	 * @param territoireATT le territoire qui attaque
	 * @param territoireDEF territoire qui �tait attaqu�
	 */
	public void miseAJourProprietaire(Territoire territoireATT,Territoire territoireDEF)
	{	
		if(territoireDEF.estConquis())
		{
			territoireDEF.changeProprietaire(territoireATT);
			
		}
	}
	//ATTAQUE
	
	
	//DIVERS
	/**
	 * Distribue les territoires entre les joueurs
	 */
	public void attributionTerritoire()
	{
		//Cette partie permet de cr�er une liste m�lang�e des territoires
		ArrayList<Integer> listeRandomTerritoires = new ArrayList<Integer>();
	    for (int i=0; i<listeTerritoires.size(); i++)
	    {
	    	listeRandomTerritoires.add(new Integer(i));
        }
	    
	    Collections.shuffle(listeRandomTerritoires);
	    //fin
	    
	  
		int indexJoueur = 0;
		
		for(int i = 0;i < listeTerritoires.size();i++)//On attribue dans l'ordre de la liste m�lang�e les territoires aux joueurs
		{
			listeTerritoires.get(listeRandomTerritoires.get(i)).setProprietaire(listeJoueurs.get(indexJoueur));//On d�finit le propri�taire
			
			listeJoueurs.get(indexJoueur).ajouterTerritoire(1);//On ajoute une troupe au joueur
			
			indexJoueur++;
			
			if(indexJoueur>=listeJoueurs.size())
			{
				indexJoueur = 0;
			}
		}	
			
	}

	
	/**
	 * Attribu un mission � un joueur
	 */
	public void attributionMission()
	{
		//Cette partie permet de cr�er une liste m�lang�e des territoires
		ArrayList<Integer> listeRandomMissions = new ArrayList<Integer>();
		for (int i=0; i<listeJoueurs.size(); i++)
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
			return new Mission("Contr�ler 3 r�gions et au moins 18 territoires",0);
		case 1:
			return new Mission("Contr�ler la plus grosse r�gion + 1 autre r�gion",1);
		case 2:
			return new Mission("Conqu�rir tous les territoires",2);
		case 3:
			return new Mission("Contr�ler 30 territoires",3);
		case 4:
			int random = (int) (Math.random() * listeJoueurs.size() - 0) + 0;
			Mission mission = new Mission("D�truire le joueur "+listeJoueurs.get(random).getNom()+" !",4);	
			mission.definirJoueurAEliminer(listeJoueurs.get(random));
			
			return mission;
		case 5:
			return new Mission("Contr�ler 18 territoires avec au moins 2 arm�es",5);
		case 6:
			return new Mission("Contr�ler 24 territoires",6);
		case 7:
			return new Mission("Contr�ler 21 territoires",7);
		default:
			return null;
		}
	}
	
	
	/**
	 * Met fin � la partie si un joueur a termin� sa mission
	 * @param Joueur joueur
	 */
	public void missionComplete(Joueur joueur)
	{
		if(joueur.getMission().estComplete(joueur))
		{
			Main.jeu = false;
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
			Main.interf.setDebut(false);
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
	 * Permet de savoir si un joueur a gagn�
	 * @return true s'il ne reste qu'un joueur false sinon
	 */
	public void finPartie()
	{
		if(listeJoueurs.size() == 1)
		{
			Main.jeu = false;
		}
	}

	
	/**
	 * Deploie des toupes sur le territoire s�l�ctionn�
	 */
	public void deploiement(Territoire territoire)
	{
		if(nombreCanons > 0)
		{
			territoire.ajouterUnite(new Unitee(2,4,9,3,2,1,0,0));//Canon
			
			territoire.ajouterTroupe(7);
			territoire.ajouterCanons(1);
			nombreCanons--;
		}
		else if(nombreCavaliers > 0)
		{
			territoire.ajouterUnite(new Unitee(1,2,7,1,3,3,0,0));//Cavalier
			
			territoire.ajouterTroupe(3);
			territoire.ajouterCavaliers(1);
			nombreCavaliers--;
		}
		else
		{
			territoire.ajouterUnite(new Unitee(0,1,6,2,1,2,0,0));//Soldat
			
			territoire.ajouterTroupe(1);
			territoire.ajouterSoldats(1);
			nombreSoldats--;
		}
	}
	
	
	/**
	 * Reset les d�placements d'un joueur
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
