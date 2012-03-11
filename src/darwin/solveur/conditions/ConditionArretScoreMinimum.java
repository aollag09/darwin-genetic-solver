package darwin.solveur.conditions;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;
import darwin.modele.ConditionArret;

/* A IMPLEMENTER ET PLACER DANS LE PACKAGE CONDITIONS */
public class ConditionArretScoreMinimum extends ConditionArret{

	/**
	 * La valeur du scrore minimum à atteindre
	 */
	private double scoreMinimum;
	
	/**
	 * Constructeur simple de la condition d'arrêt
	 * @param scroreMin la score miminum à atteindre
	 */
	public ConditionArretScoreMinimum(double scroreMin) {
		this.setScoreMinimum(scroreMin);
	}
	
	@Override
	public boolean isSatisfied(IPopulation population) {
		return (population.evaluerPopulation() > scoreMinimum);
	}

	@Override
	public IConditionArret nextConditionArret() {
		return null;
	}

	@Override
	public IEnvironnement nextEnvironnement() {
		return null;
	}

	public double getScoreMinimum() {
		return scoreMinimum;
	}

	public void setScoreMinimum(double scoreMinimum) {
		this.scoreMinimum = scoreMinimum;
	}

}
