package darwin.solveur.conditions;

import darwin.interfaces.IStopCondition;
import darwin.interfaces.IEnvironment;
import darwin.interfaces.IPopulation;
import darwin.model.StopCondition;

/**
 * The solver keeps turning until the evaluation of the population reaches a
 * certain value
 * 
 * @author Dim & Momo
 * 
 */
public class StopConditionMinimumScore extends StopCondition {

	/** the minimum score to reach
	 */
	private double minimumScore;

	/**
	 * basic constructor
	 * @param scroreMin
	 */
	public StopConditionMinimumScore(double scroreMin) {
		minimumScore = scroreMin;
	}

	@Override
	public boolean isSatisfied(IPopulation population) {
		iterations++;
		double d = 0;
		try {
			d = population.evaluateBeing(population.getBestIndividu());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (d < minimumScore );
	}

	@Override
	public IStopCondition nextStopCondition() {
		return null;
	}

	@Override
	public IEnvironment nextEnvironnement() {
		return null;
	}

	public double getScoreMinimum() {
		return minimumScore;
	}

	public void setScoreMinimum(double scoreMinimum) {
		this.minimumScore = scoreMinimum;
	}

}
