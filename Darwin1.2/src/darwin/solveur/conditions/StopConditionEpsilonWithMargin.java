package darwin.solveur.conditions;

import darwin.interfaces.IStopCondition;
import darwin.interfaces.IEnvironment;
import darwin.interfaces.IPopulation;
import darwin.model.StopCondition;

/**
 * An epsilon stop condition If the evaluation of the population n minus the
 * evaluation of the population n-1 is less than epsilon for a certain number of
 * times (the margin) the condition is satisfied
 * 
 * @author Dim & Momo
 * 
 */
public class StopConditionEpsilonWithMargin extends StopCondition {

	/**
	 * the number of operations accepted with a small evolution (less than
	 * epsilon)
	 */
	private int margin;

	/** the old score */
	private double oldScore = 0;

	/** the epsilon value */
	private double epsilon = 0.05;

	/**
	 * basic constructor
	 * 
	 * @param epsilon
	 * @param marge
	 */
	public StopConditionEpsilonWithMargin(double epsilon, int marge) {
		this.epsilon = epsilon;
		this.margin = marge;
	}

	@Override
	public boolean isSatisfied(IPopulation population) {
		this.iterations++;
		double d = this.oldScore - population.evaluatePopulation();
		this.oldScore = population.evaluatePopulation();
		return (Math.abs(d) < this.epsilon);
	}

	@Override
	public IStopCondition nextStopCondition() {
		if (margin > 0) {
			margin--;
			return this;
		} else {
			margin = 0;
			return null;
		}
	}

	@Override
	public IEnvironment nextEnvironnement() {
		return null;
	}

	public int getMarge() {
		return margin;
	}

	public void setMarge(int marge) {
		this.margin = marge;
	}

	public double getAncienScore() {
		return oldScore;
	}

	public void setAncienScore(double ancienScore) {
		this.oldScore = ancienScore;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
}
