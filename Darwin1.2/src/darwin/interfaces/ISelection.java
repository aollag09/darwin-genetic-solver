package darwin.interfaces;

import java.util.List;

/**
 * Object to select some being in a population regarding some define rules
 * 
 * @author Momo && Dim
 */
public interface ISelection {

	/**
	 * 
	 * @param population
	 * @return the list of the selected beings
	 */
	List<IBeing> select(IPopulation population);

	/**
	 * 
	 * @param population
	 * @return true if the population can be selected
	 */
	boolean isSelectionPossible(IPopulation population);
}
