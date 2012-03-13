package darwin.solveur.conditions;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;
import darwin.modele.ConditionArret;

public class ConditionArretEpsilonAvecMarge extends ConditionArret {

	// VARAIBLES D'INSANTCES
	
	/** 
	 * La marge, le nombre d'opérations acceptées avec une diférence 
	 * d'évolution d'epsilon
	 */
	private int marge;
	
	/** 
	 * On garde en mémoire l'ancien score 
	 */
	private double ancienScore = 0;
	
	/** 
	 * Epsilon, la valeur de la condition d'arret epsilon
	 */
	private double epsilon = 0.05;
	
	
	/**
	 * Constructeur classique de la conditon d'arrêt 
	 * @param epsilon
	 * @param marge
	 */
	public ConditionArretEpsilonAvecMarge(double epsilon, int marge) {
		this.epsilon = epsilon;
		this.marge = marge;
	}
	
	@Override
	public boolean isSatisfied(IPopulation population) {
		this.iterations++;
		double d = 	this.ancienScore - population.evaluerPopulation();
		this.ancienScore = population.evaluerPopulation();
		return (Math.abs(d)<this.epsilon);
	}

	@Override
	public IConditionArret nextConditionArret() {
		if(marge>0){
			marge--;
			return this;
		}
		else{
			marge = 0;
			return null;
		}
	}

	@Override
	public IEnvironnement nextEnvironnement() {
		return null;
	}
	
	public int getMarge() {
		return marge;
	}
	public void setMarge(int marge) {
		this.marge = marge;
	}
	public double getAncienScore() {
		return ancienScore;
	}
	public void setAncienScore(double ancienScore) {
		this.ancienScore = ancienScore;
	}
	public double getEpsilon() {
		return epsilon;
	}
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
}
