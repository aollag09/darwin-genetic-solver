package darwin.interfaces;

/**
 * Object that allows the solver to mute one or several characteristics of a being
 * by randomly change their BitSet
 * 
 * @author Momo && Dim
 */
public interface IMutation {

	/**
	 * 
	 * @param being
	 * @return mute a being return the result
	 */
	IBeing mute(IBeing being);

	/**
	 * 
	 * @param being
	 * @return true, if the being can be mute
	 */
	boolean isMutable(IBeing being);

	/**
	 * 
	 * @param characteristic
	 * @return true if the characteristics is mutable
	 */
	boolean isCaracteristicMutable(ICaracteristic caracteristic);
}
