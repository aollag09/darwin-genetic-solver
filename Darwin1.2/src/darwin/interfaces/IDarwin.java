package darwin.interfaces;

/**
 * The Genetic Algorithm Solver
 * 
 * @author Momo && Dim
 */
public interface IDarwin {

	/**
	 * Fix the stop condition
	 * 
	 * @param condition
	 */
	void setStopCondition(IStopCondition condition);

	/**
	 * Set the natural selection
	 */
	void setNaturalSelection(INaturalSelection newSelectionNaturelle);

	/**
	 * Solve the problem by looping over the current population until the stop
	 * condition is satisfied
	 * 
	 * @return the final population
	 */
	IPopulation solve();

	/**
	 * 
	 * @return the current stop condition used
	 */
	IStopCondition getStopCondition();

	/**
	 * 
	 * @return the current natural selection
	 */
	INaturalSelection getNaturalSelection();

	/**
	 * Add a generation listener to the algorithm
	 */
	void addGenerationListener(IGenerationListener listener);
}
