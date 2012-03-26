package parallelisation.interfaces;

import java.io.Serializable;

import darwin.interfaces.IEnvironnement;

public interface IConditionArretMaitre extends Serializable {

	
	/**

	 * @return true si la condition d'arrêt est satisfaite
	 */
	boolean isSatisfied();
	
	/**
	 * 
	 * @return La condition d'arrêt suivante ou null si il n'y en a pas
	 */
	IConditionArretMaitre nextConditionArret();
	
	/**
	 * 
	 * @return Le nouvel environnement à appliquer, ou null si on garde le même
	 */
	IEnvironnement nextEnvironnement();

}
