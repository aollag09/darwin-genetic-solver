package darwin.solveur.conditions;

import darwin.interfaces.IStopCondition;
import darwin.interfaces.IEnvironment;
import darwin.interfaces.IPopulation;
import darwin.model.StopCondition;

public class StopConditionIterations extends StopCondition{
	
	protected int limiteIterations;
	
	public StopConditionIterations(int limite){
		this.limiteIterations = limite;
	}
	
	@Override
	public boolean isSatisfied(IPopulation population) {
		this.iterations ++;
		return this.getIterationsNumber()>= this.limiteIterations;
	}

	@Override
	public IStopCondition nextStopCondition() {
		return null;
	}

	@Override
	public IEnvironment nextEnvironnement() {
		return null;
	}

}
