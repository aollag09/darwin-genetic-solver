package darwin.solveur.conditions;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;
import darwin.modele.ConditionArret;

public class ConditionArretSimpleIterations extends ConditionArret{

	protected int limiteIterations;
	
	public ConditionArretSimpleIterations(int limite){
		this.limiteIterations = limite;
	}
	
	@Override
	public boolean isSatisfied(IPopulation population) {
		this.iterations ++;
		return this.getNombreIteration()>= this.limiteIterations;
	}

	@Override
	public IConditionArret nextConditionArret() {
		return null;
	}

	@Override
	public IEnvironnement nextEnvironnement() {
		return null;
	}

}
