package darwin.solveur.mutations;

import darwin.interfaces.ICaracteristic;
import darwin.interfaces.IBeing;
import darwin.model.Mutation;

/**
 * Object that performs a simple mutation with a probability p
 *
 *@author Dim & Momo
 */
public class MutationSimple extends Mutation{

	/** the number of bit to mute  */
	protected int numberBitsToMute;
	
	/** the default bit number to mute */
	protected static final int DEFAULT_BIT_NUMBER_TO_MUTE = 1;
	
	/** the default probability to mute */
	protected static final double DEFAULT_PROBABILITY = 0.1;
	
	/**
	 * default constructor
	 * @throws Exception
	 */
	public MutationSimple() throws Exception{
		super(DEFAULT_PROBABILITY);
		this.numberBitsToMute = DEFAULT_BIT_NUMBER_TO_MUTE;
	}
	
	/**
	 * basic constructor
	 * @param nbBitAMuter
	 * @param prob
	 * @throws Exception
	 */
	public MutationSimple(int numberBitsToMute, double prob) throws Exception {
		super(prob);
		this.numberBitsToMute = numberBitsToMute;
	}

	@Override
	public IBeing mute(IBeing being) {
		boolean mutant = false;
		IBeing nIndividu = being.clone();
		if(this.isMutable(nIndividu)){
			for(ICaracteristic c : nIndividu.getCaracteristics()){
				boolean b = this.muterCaracteristique(c);
				mutant = mutant || b;
			}
		}
		if(mutant){
				nIndividu.setName(nIndividu.getName() + "-mutant");
				return nIndividu;
		}
		else{
			return being;
		}
	}
	
	/**
	 * @param caracteristic
	 * @return true if the characteristic has been muted
	 */
	protected boolean muterCaracteristique(ICaracteristic caracteristic){
		boolean retour = false;
		if(this.isCaracteristicMutable(caracteristic) && this.doMutation()){
			for(int i=0; i<this.numberBitsToMute; i++){
				int bit = (int) (Math.random()*caracteristic.getBitSetSize());
				caracteristic.getBitSet().flip(bit);
				caracteristic.update();
			}
			retour = true;
			
		}
		return retour;
	}

	public boolean isCaracteristicMutable(ICaracteristic caracteristic){
		return (caracteristic.getBitSetSize() > numberBitsToMute);
	}

	@Override
	public boolean isMutable(IBeing being) {
		return (being.getCaracteristicsSize() != 0);
	}

}
