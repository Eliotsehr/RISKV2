
public class Des {

	private int scoreMin;
	private int scoreMax;
	
	
	/**
	 * Constructueur de l'objet Des
	 * @param scoreMin score minimum du de
	 * @param scoreMax score maximum du de
	 */
	public Des(int scoreMin, int scoreMax) {
		this.scoreMin = scoreMin;
		this.scoreMax = scoreMax;
	}
	
	/**
	 * Lance un dé
	 * @return renvoit la valeur du dé
	 */
	public int lanceDes()
	{
		int random = (int) (Math.random() * (this.scoreMax - this.scoreMin)+1 - 0) + this.scoreMin;
		return random;
	}

	
	
	//Getter Setters
	public int getScoreMin() {
		return scoreMin;
	}

	public void setScoreMin(int scoreMin) {
		this.scoreMin = scoreMin;
	}

	public int getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}

}
