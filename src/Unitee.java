
public class Unitee {
	
	private int type;
	private int pMin;
	private int pMax;
	private int pATT;
	private int pDEF;
	private int mvtTOUR;
	private int scoreDES;
	private int nombreDeplacement;
	
	/**
	 * Contructeur de l'objet unit�
	 * @param int type index du type d'unit� 
	 * @param int pMin puissance d'attaque minimale de la troupe
	 * @param int pMax puissance d'attaque maximale de la troupe
	 * @param int pATT priorit� d'attaque de la troupe
	 * @param int pDEF priorit� de d�fense de la troupe
	 * @param int mvtTOUR nombre de d�placement maximal que peut faire une troupe
	 * @param int scoreDES score de la troupe lors d'un combat
	 * @param int nombreDeplacement nombre de d�placement de la troupe lors du tour en cours
	 */
	public Unitee(int type,int pMin,int pMax,int pATT, int pDEF,int mvtTOUR,int scoreDES,int nombreDeplacement) {
		this.type = type;
		this.pMin = pMin;
		this.pMax = pMax;
		this.pATT = pATT;
		this.pDEF = pDEF;
		this.mvtTOUR = mvtTOUR;
		this.nombreDeplacement = nombreDeplacement;
	}

	
	public boolean peutDeplacer()
	{
		if(this.type == 0 && this.nombreDeplacement < 2)
		{
			return true;
		}
		else if(this.type == 1 && this.nombreDeplacement < 3)
		{
			return true;
		}
		else if(this.type == 2 && this.nombreDeplacement < 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	/**
	 * Remet le nombre de d�placement d'une troupe � 0 � la fin d'un tour
	 */
	public void resetDeplacement()
	{
		this.nombreDeplacement = 0;
	}
	
	
	//Getters Setters
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getpATT() {
		return pATT;
	}

	public void setpATT(int pATT) {
		this.pATT = pATT;
	}

	public int getpDEF() {
		return pDEF;
	}

	public void setpDEF(int pDEF) {
		this.pDEF = pDEF;
	}

	public int getMvtTOUR() {
		return mvtTOUR;
	}

	public void setMvtTOUR(int mvtTOUR) {
		this.mvtTOUR = mvtTOUR;
	}


	public int getpMin() {
		return pMin;
	}


	public void setpMin(int pMin) {
		this.pMin = pMin;
	}


	public int getpMax() {
		return pMax;
	}


	public void setpMax(int pMax) {
		this.pMax = pMax;
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
