package darwin.model;

import java.util.List;

import darwin.interfaces.IBeing;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;

/**
 * @author Dim && Momo Abstract and parial implementation of the ISelection
 *         object
 */
public abstract class Selection implements ISelection {

	/** the number of beings to keep during a selection */
	protected int numberBeings;

	/**
	 * Default constructor
	 */
	protected Selection() {
	}

	/**
	 * Basic constructor
	 * 
	 * @param numberBeings
	 * @throws Exception
	 *             if the number of beings in parameter is less than 1
	 */
	protected Selection(int numberBeings) throws Exception {
		if (numberBeings < 1) {
			System.err.println("The number of beings to select is < 1");
			throw new Exception();
		} else
			this.numberBeings = numberBeings;

	}

	@Override
	public boolean isSelectionPossible(IPopulation population) {
		return (this.numberBeings <= population.getEffectiveSize());
	}

	@Override
	public abstract List<IBeing> select(IPopulation population);

}
