package darwin.modele;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;

/**
 * @author Dim && Momo
 * Implémentation partielle et abstraite de IConditionArret
 */
public abstract class ConditionArret implements IConditionArret {

	// VARIABLES D'INSTANCE
	/**
	 * Le nombre d'itérations effectuée
	 */
	protected int iterations;
	
	/**
	 * Constructeur à appeler lorsque la condition est la première utilisée
	 */
	protected ConditionArret(){
		this.iterations = 0;
	}
	
	/**
	 * Constructeur à appeler lorsque la condition suit une autre (on transmet le nombre d'itérations)
	 * @param iterations
	 */
	protected ConditionArret(int iterations){
		this.iterations = iterations;
	}
	
	@Override
	public abstract boolean isSatisfied(IPopulation population);

	@Override
	public abstract IConditionArret nextConditionArret();

	@Override
	public abstract IEnvironnement nextEnvironnement();
	
	@Override
	public int getNombreIteration(){
		return this.iterations;	
	}

}
