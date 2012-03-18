package darwin.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Momo && Dim
 *	Permet de selectionner un échantillon d'individu parmis une population, et selon
 *	des critères définis.
 */
public interface ISelection extends Serializable{

	/**
	 * 
	 * @param population
	 * @return renvoie un échantillon d'individus selectionnés parmis la population passée en paramètre
	 */
	List<IIndividu> selectionner(IPopulation population);
	
	/**
	 * 
	 * @param population
	 * @return true si il est possible d'effectuer une selection sur la population
	 */
	boolean selectionPossible(IPopulation population);
}
