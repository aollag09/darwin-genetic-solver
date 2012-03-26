package parallelisation.interfaces;

import java.io.Serializable;

import darwin.interfaces.IEnvironnement;

public interface IConditionArretMaitre extends Serializable {

	
	/**

	 * @return true si la condition d'arr�t est satisfaite
	 */
	boolean isSatisfied();
	
	/**
	 * 
	 * @return La condition d'arr�t suivante ou null si il n'y en a pas
	 */
	IConditionArretMaitre nextConditionArret();
	
	/**
	 * 
	 * @return Le nouvel environnement � appliquer, ou null si on garde le m�me
	 */
	IEnvironnement nextEnvironnement();

}
