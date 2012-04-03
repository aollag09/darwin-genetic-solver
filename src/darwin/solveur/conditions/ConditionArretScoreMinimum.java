package darwin.solveur.conditions;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;
import darwin.modele.ConditionArret;

/* A IMPLEMENTER ET PLACER DANS LE PACKAGE CONDITIONS */
public class ConditionArretScoreMinimum extends ConditionArret{

	private static final long serialVersionUID = -5378753912484344516L;

	/**
	 * La valeur du scrore minimum à atteindre
	 */
	private double scoreMinimum;
	
	private double delta;
	
	/**
	 * Constructeur simple de la condition d'arrêt
	 * @param scroreMin la score miminum à atteindre
	 */
	public ConditionArretScoreMinimum(double scroreMin, double delta) {
		scoreMinimum = scroreMin;
		this.delta = delta;
	}
	
	@Override
	public boolean isSatisfied(IPopulation population) {
		iterations++;
		double d = 0;
		try {
			d = population.evaluerIndividu(population.getBestIndividu());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (d > scoreMinimum+delta || iterations>199);
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
