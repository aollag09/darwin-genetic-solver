package darwin.solveur.conditions;

import darwin.interfaces.IStopCondition;
import darwin.interfaces.IEnvironment;
import darwin.interfaces.IPopulation;
import darwin.model.StopCondition;

/**
 * An epsilon stop condition If the evaluation of the population n minus the
 * evaluation of the population n-1 is less than epsilon, the condition is
 * satisfied
 * 
 * @author Dim & Momo
 * 
 */
public class StopConditionEpsilon extends StopCondition {

	/** the epsilon value */
	private double epsilon;

	/** the old evaluation value */
	private double oldEvaluation;

	/**
	 * basic constructor
	 * 
	 * @param epsilon
	 */
	public StopConditionEpsilon(double epsilon) {
		this.epsilon = epsilon;
		this.oldEvaluation = Double.MIN_VALUE;

	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	@Override
	public boolean isSatisfied(IPopulation population) {
		iterations++;
		if (oldEvaluation == Double.MIN_VALUE) {
			/* the old evaluation isn't still instantiated */
			oldEvaluation = population.evaluatePopulation();
			return false;
		} else {
			double evaluationCourante = population.evaluatePopulation();
			if (Math.abs(oldEvaluation - evaluationCourante) > this.epsilon) {
				/* the stop condition isn't satisfied */
				oldEvaluation = evaluationCourante;
				return false;
			} else {
				oldEvaluation = evaluationCourante;
				return true;
			}
		}

	}

	@Override
	public IStopCondition nextStopCondition() {
		return null;
	}

	@Override
	public IEnvironment nextEnvironnement() {
		return null;
	}

	public double getOldEvaluation() {
		return oldEvaluation;
	}

	public void setOldEvaluation(double oldEvaluation) {
		this.oldEvaluation = oldEvaluation;
	}

}
