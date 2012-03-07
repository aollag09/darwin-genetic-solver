package darwin.interfaces;

/**
 * @author Momo && Dim
 * Solveur de l'algorithme g�n�tique
 */
public interface IDarwin {

	/**
	 * Fixe la condition d'arret
	 * @param condition
	 */
	void setConditionArret(IConditionArret condition);
	
	/**
	 * Fait tourner l'algorithme g�n�tique jusqu'� ce que la condition d'arr�t finale soit remplie
	 * @return
	 */
	IPopulation solve();
	
	/**
	 * 
	 * @return La condition d'arret actuellement utilis�e
	 */
	IConditionArret getConditionArret();
	
	/**
	 * 
	 * @return La selection naturelle sur laquelle on itere
	 */
	ISelectionNaturelle getSelectionNaturelle();
}
