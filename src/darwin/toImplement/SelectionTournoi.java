package darwin.toImplement;

import java.util.List;

import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.modele.Selection;

/* A IMPLEMENTER ET PLACER DANS LE PACKAGE SELECTIONS */
public class SelectionTournoi extends Selection{

	public SelectionTournoi(int nbIndividus) throws Exception {
		super(nbIndividus);
	}

	@Override
	public List<IIndividu> selectionner(IPopulation population) {
		// TODO Auto-generated method stub
		return null;
	}

}
