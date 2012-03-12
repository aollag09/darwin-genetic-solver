package modele.genetique.tsp;

import java.awt.Dimension;

import javax.swing.JFrame;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IMutation;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;
import darwin.solveur.SelectionNaturelleSimple;

public class SelectionNaturelleTSP extends SelectionNaturelleSimple {

	public SelectionNaturelleTSP(ISelection selection,ISelection selection2, ICrossOver crossOver,
			IMutation mutation, IPopulation population) throws Exception {
		super(selection, selection2, crossOver, mutation, population, 2, 0.5);
	}
	
}
