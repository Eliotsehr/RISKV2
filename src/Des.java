
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
	
	//DIVERS
	/**
	 * Lance un dé
	 * @return renvoit la valeur du dé
	 */
	public int lanceDes()
	{
		int random = (int) (Math.random() * (this.scoreMax - this.scoreMin)+1 - 0) + this.scoreMin;
		return random;
	}
	//DIVERS
	
}
