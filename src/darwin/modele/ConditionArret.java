package darwin.modele;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;

/**
 * @author Dim && Momo
 * Impl�mentation partielle et abstraite de IConditionArret
 */
public abstract class ConditionArret implements IConditionArret {

	// VARIABLES D'INSTANCE
	/**
	 * Le nombre d'it�rations effectu�e
	 */
	protected int iterations;
	
	/**
	 * Constructeur � appeler lorsque la condition est la premi�re utilis�e
	 */
	protected ConditionArret(){
		this.iterations = 0;
	}
	
	/**
	 * Constructeur � appeler lorsque la condition suit une autre (on transmet le nombre d'it�rations)
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
