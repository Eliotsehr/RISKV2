public class Unitee {
	
	private int type;
	private int pMin;
	private int pMax;
	private int pATT;
	private int pDEF;
	private int mvtTOUR;
	private int scoreDES;//Score des d�s de l'unit� lors d'un combat
	private int nombreDeplacement;//Nombre de d�placement de l'unit� lors du tour en cours
	
	/**
	 * Contructeur de l'objet unit�
	 * @param type index du type d'unit� 
	 * @param pMin puissance d'attaque minimale de la troupe
	 * @param pMax puissance d'attaque maximale de la troupe
	 * @param pATT priorit� d'attaque de la troupe
	 * @param pDEF priorit� de d�fense de la troupe
	 * @param mvtTOUR nombre de d�placement maximal que peut faire une troupe
	 */
	public Unitee(int type,int pMin,int pMax,int pATT, int pDEF,int mvtTOUR) {
		this.type = type;
		this.pMin = pMin;
		this.pMax = pMax;
		this.pATT = pATT;
		this.pDEF = pDEF;
		this.mvtTOUR = mvtTOUR;
	}

	
	/**
	 * Verifie si une unit� peut se d�placer
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
