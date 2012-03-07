package darwin.interfaces;

/**
 * @author Momo && Dim
 * Solveur de l'algorithme génétique
 */
public interface IDarwin {

	/**
	 * Fixe la condition d'arret
	 * @param condition
	 */
	void setConditionArret(IConditionArret condition);
	
	/**
	 * Fait tourner l'algorithme génétique jusqu'à ce que la condition d'arrêt finale soit remplie
	 * @return
	 */
	IPopulation solve();
	
	/**
	 * 
	 * @return La condition d'arret actuellement utilisée
	 */
	IConditionArret getConditionArret();
	
	/**
	 * 
	 * @return La selection naturelle sur laquelle on itere
	 */
	ISelectionNaturelle getSelectionNaturelle();
}
