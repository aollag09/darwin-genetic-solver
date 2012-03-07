package darwin.interfaces;

/**
 * @author Momo && Dim
 *	Défini une condition d'arrêt lors de la résolution d'un problème avec l'algorithme génétique
 */
public interface IConditionArret {

	/**
	 * 
	 * @param population
	 * @return true si la condition d'arrêt sur la population donnée en paramètre est satisfaite
	 */
	boolean isSatisfied(IPopulation population);
	
	/**
	 * 
	 * @return La condition d'arrêt suivante ou null si il n'y en a pas
	 */
	IConditionArret nextConditionArret();
	
	/**
	 * 
	 * @return Le nouvel environnement à appliquer, ou null si on garde le même
	 */
	IEnvironnement nextEnvironnement();
	
	/**
	 * 
	 * @return Le nombre d'itérations effectuées (ie le nombre d'appels à isSatisfied
	 */
	int getNombreIteration();
}
