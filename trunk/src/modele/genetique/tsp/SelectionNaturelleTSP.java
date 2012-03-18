package modele.genetique.tsp;

import java.io.Serializable;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IMutation;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;
import darwin.solveur.SelectionNaturelleSimple;

public class SelectionNaturelleTSP extends SelectionNaturelleSimple implements Serializable {

	/**
	 * For Marshalling on the network
	 */
	private static final long serialVersionUID = -4411491316704509061L;

	public SelectionNaturelleTSP(ISelection selection,ISelection selection2, ICrossOver crossOver,
			IMutation mutation, IPopulation population, int nombreCouples, double propabilite) throws Exception {
		super(selection, selection2, crossOver, mutation, population, nombreCouples, propabilite);
	}

}