package darwin.model;

import darwin.interfaces.IStopCondition;
import darwin.interfaces.IEnvironment;
import darwin.interfaces.IPopulation;

/**
 * @author Dim && Momo
 * Abstract and partial implementation of the object IStopCondition
 */
public abstract class StopCondition implements IStopCondition {

	/** the number of iterations to proceed */
	protected int iterations;
	
	/**
	 * default constructor
	 */
	protected StopCondition(){
		this.iterations = 0;
	}
	
	/**
	 * basic constructor
	 */
	protected StopCondition(int iterations){
		this.iterations = iterations;
	}
	
	@Override
	public abstract boolean isSatisfied(IPopulation population);

	@Override
	public abstract IStopCondition nextStopCondition();

	@Override
	public abstract IEnvironment nextEnvironnement();
	
	@Override
	public int getIterationsNumber(){
		return this.iterations;	
	}

}
