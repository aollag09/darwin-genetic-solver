package darwin.interfaces;

/**
 * Object that defines a stop condition in the loop resolution of the Darwin
 * object
 * 
 * @author Momo && Dim
 */
public interface IStopCondition {

	/**
	 * @param population
	 * @return true if the condition is satisfied regarding the population in
	 *         parameter
	 */
	boolean isSatisfied(IPopulation population);

	/**
	 * 
	 * @return the next stop condition or null
	 */
	IStopCondition nextStopCondition();

	/**
	 * 
	 * @return the next environment for the next stop condition or null to keep
	 *         the same
	 */
	IEnvironment nextEnvironnement();

	/**
	 * 
	 * @return the current number of iterations
	 */
	int getIterationsNumber();
}
