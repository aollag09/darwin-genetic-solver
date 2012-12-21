package darwin.interfaces;

/**
 * Object that allows the solver to do a natural selection over its being over
 * one generation
 * 
 * @author Dim && Momo
 */

public interface INaturalSelection {

	/**
	 * 
	 * @return the first selection used
	 */
	ISelection getFirstSelection();

	/**
	 * 
	 * @return the last selection used
	 */
	ISelection getLastSelection();

	/**
	 * 
	 * @return the mutation used
	 */
	IMutation getMutation();

	/**
	 * 
	 * @return the cross over used
	 */
	ICrossOver getCrossOver();

	/**
	 * 
	 * @return the current population that we're going to select
	 */
	IPopulation getPopulation();

	/**
	 * Perform the natural selection over the current population
	 */
	void nextGeneration();
}
