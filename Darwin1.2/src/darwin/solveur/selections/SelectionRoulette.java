package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IBeing;
import darwin.interfaces.IPopulation;
import darwin.model.Selection;

/**
 * Permet de selectionner un nombre d�fini d'individus qui ont chacun une probabilit� d'�tre s�lectionn�
 * qui d�pend de leur �valuation au sein de l'environnement o� ils sont plong�s.
 * @author Dim
 *
 */
public class SelectionRoulette extends Selection{

	public SelectionRoulette(int nbIndividus) throws Exception {
		super(nbIndividus);
	}

	@Override
	public List<IBeing> select(IPopulation population) {
		/* On s'assure qu'il est possible d'effectuer la selection (si ce n'est pas le cas on renvoie la population inchang�e */
		if(this.isSelectionPossible(population)){
			
			/* On r�cup�re le score cumul� de toute la population afin d'�tablir les probabilit�s */
			double scorePop = population.evaluatePopulation();
			
			/* On attribue une probabilit� � chaque individu de la population en fonction de son score*/
			double[] probs = new double[population.getEffectiveSize()];
			for(int i=0; i<probs.length; i++){
				try {
					probs[i] = (scorePop-population.evaluateBeing(population.getBeing(i)))/scorePop;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/* Initialisation de la liste des individus selectionn�s */
			List<IBeing> selectionnes = new ArrayList<IBeing>();
			for(int i=0; i<this.numberBeings; i++){
				IBeing being;
				do{
					being = this.selectionUnique(population, probs);
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
	 * 
	 * @param population
	 * @param probs
	 * @return Un indivu choisi � la roulette dans la population
	 */
	protected IBeing selectionUnique(IPopulation population, double[] probs){
		IBeing heureuxElu = null;
		int i = 0;
		do{
			double d = Math.random();
			boolean retour = (d<probs[i])?true:false;
			if(retour){
				heureuxElu = population.getBeing(i);
			}
			i += 1;
			if(i == population.getEffectiveSize()){
				i = 0;
			}
		}while(heureuxElu == null);
		return heureuxElu;
	}
}
