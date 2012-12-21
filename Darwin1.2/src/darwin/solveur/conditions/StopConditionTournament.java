package darwin.solveur.conditions;

import darwin.interfaces.IEnvironment;
import darwin.interfaces.IPopulation;
import darwin.interfaces.IStopCondition;
import darwin.model.StopCondition;

/**
 * Special stop condition when the final selection of the natural selection is a tournament
 * We define a minimumVariation, if between two generation, the change of the score
 * isn't greater than this mnimumVariation we increment a counter.
 * If not, we set the counter back to value 0.
 * @author Dim & Momo
 *
 */
public class StopConditionTournament extends StopCondition{

	/** the minimum variation */
	private double minimumVariation;
	
	/** the old score */
	private double oldScore;
	
	/** the counter */
	private int counter;
	
	/** the counter limit */
	private int counterLimit;
	
	/**
	 * basic constructor
	 * @param minimumVariation
	 * @param scounterLimit
	 */
	public StopConditionTournament(double minimumVariation, int counterLimit){
		super();
		this.minimumVariation = minimumVariation;
		this.counterLimit = counterLimit;
		this.counter = 0;
		this.oldScore = Math.PI;
	}
	
	@Override
	public boolean isSatisfied(IPopulation population) {
		boolean retour = false;
		double best = 0;
		try {
			best = population.evaluateBeing(population.getBestIndividu());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(Math.abs(this.oldScore - best)<this.minimumVariation){
			this.counter ++;
		}
		else{
			this.counter = 0;
		}
		if(this.counter >= this.counterLimit || best == 0){
			retour = true;
		}
		this.oldScore = best;
		return retour;
	}

	@Override
	public IStopCondition nextStopCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEnvironment nextEnvironnement() {
		// TODO Auto-generated method stub
		return null;
	}

}
