package darwin.interfaces;

import java.util.List;

/**
 * @author Momo && Dim
 *	Permet de selectionner un �chantillon d'individu parmis une population, et selon
 *	des crit�res d�finis.
 */
public interface ISelection {

	/**
	 * 
	 * @param population
	 * @return renvoie un �chantillon d'individus selectionn�s parmis la population pass�e en param�tre
	 */
	List<IIndividu> selectionner(IPopulation population);
	
	/**
	 * 
	 * @param population
	 * @return true si il est possible d'effectuer une selection sur la population
	 */
	boolean selectionPossible(IPopulation population);
}
