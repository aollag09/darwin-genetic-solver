package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.modele.Selection;

/**
 * Selectionne un nombre d�fini d'individus complement au hasard
 * @author Dim
 *
 */
public class SelectionTirageAleatoire extends Selection{

	public SelectionTirageAleatoire(int nbIndividus) throws Exception {
		super(nbIndividus);
	}

	@Override
	public List<IIndividu> selectionner(IPopulation population) {
		/* On s'assure qu'il est possible d'effectuer la selection (si ce n'est pas le cas on renvoie la population inchang�e */
		if(this.selectionPossible(population)){
			
			/* Initialisation de la liste des individus selectionn�s */
			List<IIndividu> selectionnes = new ArrayList<IIndividu>();
			for(int i=0; i<this.nbIndivus; i++){
				IIndividu individu;
				do{
					individu = this.selectionUnique(population);
				}while(selectionnes.contains(individu));		
				selectionnes.add(individu);
			}
			return selectionnes;
		}
		else{
			System.out.println("Selection impossible, plus d'individus � selectionner que d'individus pr�sents dans la population");
			return population.getListIndividus();
		}
	}

	/**
	 * Selectionne un individu au hasard dans la population
	 * @param population
	 * @return
	 */
	protected IIndividu selectionUnique(IPopulation population){
		int i = (int)(Math.random()*population.getTailleEffective());
		return population.getIndividu(i);
	}
}
