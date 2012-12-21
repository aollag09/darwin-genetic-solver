package darwin.model;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IMutation;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;
import darwin.interfaces.INaturalSelection;

/**
 * 
 * Abstract an Partial implementation of the object ISelectionNaturelle
 * 
 * @author Momo && Dim
 */
public abstract class NaturalSelection implements INaturalSelection {

	/** the starting selection */
	protected ISelection startingSelection;

	/** the final selection */
	protected ISelection finalSelection;

	/** the cross over */
	protected ICrossOver crossOver;

	/** the mutation */
	protected IMutation mutation;

	/** the population */
	protected IPopulation population;

	/**
	 * basic constructor
	 * 
	 * @param selInit
	 * @param selFin
	 * @param cross
	 * @param mut
	 * @param pop
	 */
	protected NaturalSelection(ISelection selInit, ISelection selFin,
			ICrossOver cross, IMutation mut, IPopulation pop) {
		this.startingSelection = selInit;
		this.finalSelection = selFin;
		this.crossOver = cross;
		this.mutation = mut;
		this.population = pop;
	}

	@Override
	public ICrossOver getCrossOver() {
		return this.crossOver;
	}

	@Override
	public IMutation getMutation() {
		return this.mutation;
	}

	@Override
	public IPopulation getPopulation() {
		return this.population;
	}

	@Override
	public ISelection getLastSelection() {
		return this.finalSelection;
	}

	@Override
	public ISelection getFirstSelection() {
		return this.startingSelection;
	}

	@Override
	public abstract void nextGeneration();

	/**
	 * 
	 * @return true if there is at least to being in the population
	 */
	protected boolean crossOverPossible() {
		return this.getPopulation().getEffectiveSize() >= 2;
	}
}
