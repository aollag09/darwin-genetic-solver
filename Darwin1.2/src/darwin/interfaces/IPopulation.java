package darwin.interfaces;

import java.util.List;

/**
 * @author Momo && Dim A population is a set of being with e define size. All
 *         the beings evolve in an environment
 */
public interface IPopulation {

	/**
	 * 
	 * @return the wished number of being in the population.
	 */
	int getWishedSize();

	/**
	 * 
	 * @return the real number of being in the population
	 */
	int getEffectiveSize();

	/**
	 * 
	 * @return all the beings of the population
	 */
	List<IBeing> getBeingsList();

	/**
	 * 
	 * @param beings
	 *            L'ensemble des individus � placer dans la population.
	 */
	void setBeingsList(List<IBeing> beings);

	/**
	 * 
	 * @param i
	 *            the index
	 * @return the being i
	 */
	IBeing getBeing(int i);

	/**
	 * @param being
	 *            add the being to the population
	 */
	void addBeing(IBeing being);

	/**
	 * @return the environment where the population evolves
	 */
	IEnvironment getEnvironment();

	/**
	 * @param environment
	 *            set the environment of the population
	 */
	void setEnvironment(IEnvironment environment);

	/**
	 * 
	 * @param being
	 * @return the evaluation of the being in is environment
	 * @throws Exception
	 *             if the being cannot be evaluated in this environment
	 */
	double evaluateBeing(IBeing being) throws Exception;

	/**
	 * 
	 * @param index
	 *            of the being to evaluate
	 * @return the evaluation of the being in is environment
	 * @throws Exception
	 *             if the being cannot be evaluated in this environment
	 */
	double evaluateBeing(int index) throws Exception;

	/**
	 * 
	 * @return the evaluation of all the population. Generally the sum of the
	 *         evaluation of all the beings
	 */
	double evaluatePopulation();

	/**
	 * 
	 * @return the best being in the environment
	 */
	IBeing getBestIndividu();

	/**
	 * generate randomly a new population
	 */
	void generate();

}
