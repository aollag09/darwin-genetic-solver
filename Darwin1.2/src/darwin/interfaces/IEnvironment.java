package darwin.interfaces;

/**
 * 
 * Object to represent the environment where the being are going to evolve This
 * object can be seen as the objective function or the feasible function of a
 * define problem
 * 
 * @author Dim && Momo
 */
public interface IEnvironment {

	/**
	 * @param being
	 * @return the evaluation of the being
	 * @throws Exception
	 *             if the being cannot be evaluated
	 */
	double evaluateBeing(IBeing being) throws Exception;

	/**
	 * @param being
	 * @return true if the being is valid in this environment
	 */
	boolean isValid(IBeing being);

}
