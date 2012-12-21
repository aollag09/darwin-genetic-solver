package darwin.model;

import darwin.interfaces.ICaracteristic;
import darwin.interfaces.IBeing;
import darwin.interfaces.IMutation;

/**
 * Abstract and Partial implementation of the IMutation
 * 
 * @author Dim && Momo
 */
public abstract class Mutation implements IMutation {

	// VARIABLES D'INSTANCE

	/**
	 * The probability that the mutation occurs (between 0 and 1)
	 */
	protected double probability;

	/**
	 * Default constructor
	 */
	protected Mutation() {
		this.probability = 0.01;
	}

	/**
	 * Basic constructor
	 * 
	 * @param prob
	 * @throws Exception
	 *             if the probability is not between 0 and 1
	 */
	protected Mutation(double prob) throws Exception {
		if (prob < 0 || prob > 1) {
			System.err
					.println("The mutation probability has to be between 0 and 1");
			throw new Exception();
		} else {
			this.probability = prob;
		}
	}

	public abstract IBeing mute(IBeing being);

	public abstract boolean isMutable(IBeing being);

	/**
	 * @param caracteristic
	 * @return true if the characteristic contains a larger number of bits that
	 *         the number of bit to mute
	 */
	public abstract boolean isCaracteristicMutable(ICaracteristic caracteristic);

	/**
	 * 
	 * @return true with a probability p
	 */
	protected boolean doMutation() {
		if (this.probability == 1d)
			return true;
		else {
			double d = Math.random();
			boolean retour = (d < this.probability) ? true : false;
			return retour;
		}
	}
}
