package darwin.model;

import java.util.List;

import darwin.interfaces.IEnvironment;
import darwin.interfaces.IBeing;
import darwin.interfaces.IPopulation;

/** 
 * @author Dim && Momo
 *	Abstract and partial implementation of the IPopulaiton */
public abstract class Population implements IPopulation {

	// VARIABLES D'INSTANCES 

	/** the list of all the beings */
	protected List<IBeing> beings;

	/** the wished number of beings */
	protected int whishedNumberBeings;

	/** the environment where the population evolves */
	protected IEnvironment environment;
	
	/**
	 * default constructor
	 */
	protected Population(){
	}
	
	/**
	 * basic constructor
	 * @param numberBeings
	 * @param environment
	 */
	protected Population(int numberBeings, IEnvironment environment){
		this.whishedNumberBeings = numberBeings;
		this.environment = environment;
		this.generate();
	}
	
	@Override
	public void addBeing(IBeing being) {
		this.getBeingsList().add(being);
	}

	@Override
	public double evaluateBeing(IBeing being) throws Exception {
		double retour = this.getEnvironment().evaluateBeing(being);
		return retour;
	}

	@Override
	public double evaluateBeing(int index) throws Exception {
		return this.evaluateBeing(this.getBeing(index));
	}

	@Override
	public double evaluatePopulation() {
		double retour = 0;
		for(IBeing i: this.getBeingsList()){
			try {
				retour += this.evaluateBeing(i);
			} catch (Exception e) {
				System.err.println("The being " + i + " cannot be evaluated");
				e.printStackTrace();
			}
		}
		return retour;
	}

	@Override
	public abstract void generate();

	@Override
	public IEnvironment getEnvironment() {
		return this.environment;
	}

	@Override
	public IBeing getBeing(int i) {
		return this.getBeingsList().get(i);
	}

	@Override
	public List<IBeing> getBeingsList() {
		return this.beings;
	}

	@Override
	public int getEffectiveSize() {
		return this.getBeingsList().size();
	}

	@Override
	public int getWishedSize() {
		return this.whishedNumberBeings;
	}

	@Override
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	@Override
	public void setBeingsList(List<IBeing> beings) {
		this.beings = beings;
	}
	
	@Override
	public IBeing getBestIndividu(){
		IBeing retour = this.getBeing(0);
		for(int i=1; i<this.getEffectiveSize(); i++){
			try {
				if(this.evaluateBeing(retour) > this.evaluateBeing(this.getBeing(i))){
					retour = this.getBeing(i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retour;
	}
	
	public String toString() {
		return "Population [individus=" + beings + ", environnement="
				+ environment + "]";
	}

}
