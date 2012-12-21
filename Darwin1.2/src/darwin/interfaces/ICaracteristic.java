/**
 * 
 */
package darwin.interfaces;

import java.util.BitSet;

/**
 * Object to define a characteristics code for a unique Gene. This
 * Gene/Characteristics can have different Allele/Instantiations The
 * characteristics is defined by a BitSet This object can be cloned
 * 
 * @author Momo && Dim
 */
public interface ICaracteristic extends Cloneable {

	/**
	 * 
	 * @return the name of the characteristic
	 */
	String getName();

	/**
	 * 
	 * @return the BitSet that codes the characteristic
	 */
	BitSet getBitSet();

	/**
	 * 
	 * @return the number of bits
	 */
	int getBitSetSize();

	/**
	 * Update the characteristic when the gene is modified
	 */
	void update();

	/**
	 * 
	 * @return the object deeply cloned
	 */
	ICaracteristic clone();

}
