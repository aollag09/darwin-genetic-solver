package darwin.interfaces;

/**
 * @author Momo && Dim
 *	D�fini une condition d'arr�t lors de la r�solution d'un probl�me avec l'algorithme g�n�tique
 */
public interface IConditionArret {

	/**
	 * 
	 * @param population
	 * @return true si la condition d'arr�t sur la population donn�e en param�tre est satisfaite
	 */
	boolean isSatisfied(IPopulation population);
	
	/**
	 * 
	 * @return La condition d'arr�t suivante ou null si il n'y en a pas
	 */
	IConditionArret nextConditionArret();
	
	/**
	 * 
	 * @return Le nouvel environnement � appliquer, ou null si on garde le m�me
	 */
	IEnvironnement nextEnvironnement();
	
	/**
	 * 
	 * @return Le nombre d'it�rations effectu�es (ie le nombre d'appels � isSatisfied
	 */
	int getNombreIteration();
}
