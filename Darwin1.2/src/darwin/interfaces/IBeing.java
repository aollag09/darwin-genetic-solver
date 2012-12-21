package darwin.interfaces;

import java.util.List;

/**
 * A Being is an element define by a list of characteristics and that can evolve
 * in an environment
 * 
 * @author Dim && Momo
 */
public interface IBeing extends Cloneable {

	/**
	 * 
	 * @return an integer which is the type of the being
	 */
	Integer getType();

	/**
	 * 
	 * @return the name of the being
	 */
	String getName();

	/**
	 * @param name
	 */
	void setName(String name);

	/**
	 * 
	 * @return all the characteristics of the being
	 */
	List<ICaracteristic> getCaracteristics();

	/**
	 * Define all the characteristics of the current being
	 */
	void setCaracteristics(List<ICaracteristic> caracs);

	/**
	 * 
	 * @param index
	 * @return the characteristics i
	 */
	ICaracteristic getCaracteristics(int index);

	/**
	 * 
	 * @return the number of characteristics
	 */
	int getCaracteristicsSize();

	/**
	 * 
	 * @return a deep copy of the being
	 */
	IBeing clone();

}
