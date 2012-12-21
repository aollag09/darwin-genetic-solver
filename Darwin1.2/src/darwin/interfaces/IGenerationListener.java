package darwin.interfaces;

/**
 * Listener which nextGenerationPerformed method will be called each time a new
 * generation is reached in the algorithm
 * 
 * @author Dim & Momo
 * 
 */
public interface IGenerationListener {

	/**
	 * Called each time a new generation is reached by the population
	 * 
	 * @param population
	 */
	void nextGenerationPerformed(IPopulation population);

}
