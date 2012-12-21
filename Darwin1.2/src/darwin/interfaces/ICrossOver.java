package darwin.interfaces;

import java.util.List;

/**
 * Object that allows the user to cross different being
 * 
 * @author Dim && Momo
 */
public interface ICrossOver {

	/**
	 * 
	 * @param being1
	 * @param being2
	 * @return return the list of child from the cross between being1 and being2
	 * 
	 */
	List<IBeing> crossOver(IBeing being1, IBeing being2);
}
