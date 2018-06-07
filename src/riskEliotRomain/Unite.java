//Code Romain Burias

package riskEliotRomain;
import java.util.ArrayList;

public class Unite {
	
	private int type;
	private int pMin;
	private int pMax;
	private int pATT;
	private int pDEF;
	private int mvtTOUR;
	
	private int scoreDES;//Score des dés de l'unité lors d'un combat
	private int nombreDeplacement;//Nombre de déplacement de l'unité lors du tour en cours
	
	public ArrayList<Territoire> listeTerritoiresParcourus = new ArrayList<Territoire>();
	
	/**
//	 * Contructeur de l'objet unité
	 * @param type index du type d'unité 
	 * @param pMin puissance d'attaque minimale de la troupe
	 * @param pMax puissance d'attaque maximale de la troupe
	 * @param pATT priorité d'attaque de la troupe
	 * @param pDEF priorité de défense de la troupe
	 * @param mvtTOUR nombre de déplacement maximal que peut faire une troupe
	 */
	public Unite(int type,int pMin,int pMax,int pATT, int pDEF,int mvtTOUR) {
		this.type = type;
		this.pMin = pMin;
		this.pMax = pMax;
		this.pATT = pATT;
		this.pDEF = pDEF;
		this.mvtTOUR = mvtTOUR;
	}

	
	/**
	 * Verifie si une unité peut se déplacer
	 * @return true si elle peut false sinon
	 */
	public boolean peutDeplacer()
	{
		if(this.nombreDeplacement < this.mvtTOUR)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	/**
	 * Remet le nombre de déplacement d'une troupe à 0 à la fin d'un tour
	 */
	public void resetDeplacement()
	{
		this.nombreDeplacement = 0;
	}
	

	//Getters Setters
	public int getType() {
		return type;
	}

	public int getpATT() {
		return pATT;
	}

	public int getpDEF() {
		return pDEF;
	}

	public int getMvtTOUR() {
		return mvtTOUR;
	}

	public int getpMin() {
		return pMin;
	}

	public int getpMax() {
		return pMax;
	}

	public int getScoreDES() {
		return scoreDES;
	}

	public void setScoreDES(int scoreDES) {
		this.scoreDES = scoreDES;
	}

	public int getNombreDeplacement() {
		return nombreDeplacement;
	}

	public void setNombreDeplacement(int nombreDeplacement) {
		this.nombreDeplacement = nombreDeplacement;
	}

}