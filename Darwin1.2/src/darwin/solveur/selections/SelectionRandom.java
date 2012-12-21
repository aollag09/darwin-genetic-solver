package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IBeing;
import darwin.interfaces.IPopulation;
import darwin.model.Selection;

/**
 * Random Selector
 * @author Dim
 *
 */
public class SelectionRandom extends Selection{

	public SelectionRandom(int nbIndividus) throws Exception {
		super(nbIndividus);
	}

	@Override
	public List<IBeing> select(IPopulation population) {
		/* On s'assure qu'il est possible d'effectuer la selection (si ce n'est pas le cas on renvoie la population inchang�e */
		if(this.isSelectionPossible(population)){
			
			/* Initialisation de la liste des individus selectionn�s */
			List<IBeing> selectionnes = new ArrayList<IBeing>();
			for(int i=0; i<this.numberBeings; i++){
				IBeing being;
				do{
					being = this.selectionUnique(population);
				}while(selectionnes.contains(being));		
				selectionnes.add(being);
			}
			return selectionnes;
		}
		else{
			System.out.println("Selection impossible, plus d'individus � selectionner que d'individus pr�sents dans la population");
			return population.getBeingsList();
		}
	}

	/**
	 * Selectionne un individu au hasard dans la population
	 * @param population
	 * @return IIndividu l'individu selectionn� au hasard
	 */
	protected IBeing selectionUnique(IPopulation population){
		int i = (int)(Math.random()*population.getEffectiveSize());
		return population.getBeing(i);
	}
}
