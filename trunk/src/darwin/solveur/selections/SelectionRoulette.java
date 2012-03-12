package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.modele.Selection;

/**
 * Permet de selectionner un nombre défini d'individus qui ont chacun une probabilité d'être sélectionné
 * qui dépend de leur évaluation au sein de l'environnement où ils sont plongés.
 * @author Dim
 *
 */
public class SelectionRoulette extends Selection{

	public SelectionRoulette(int nbIndividus) throws Exception {
		super(nbIndividus);
	}

	@Override
	public List<IIndividu> selectionner(IPopulation population) {
		/* On s'assure qu'il est possible d'effectuer la selection (si ce n'est pas le cas on renvoie la population inchangée */
		if(this.selectionPossible(population)){
			
			/* On récupère le score cumulé de toute la population afin d'établir les probabilités */
			double scorePop = population.evaluerPopulation();
			
			/* On attribue une probabilité à chaque individu de la population en fonction de son score*/
			double[] probs = new double[population.getTailleEffective()];
			for(int i=0; i<probs.length; i++){
				try {
					probs[i] = population.evaluerIndividu(population.getIndividu(i))/scorePop;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/* Initialisation de la liste des individus selectionnés */
			List<IIndividu> selectionnes = new ArrayList<IIndividu>();
			for(int i=0; i<this.nbIndivus; i++){
				IIndividu individu;
				do{
					individu = this.selectionUnique(population, probs);
				}while(selectionnes.contains(individu));		
				selectionnes.add(individu);
			}
			return selectionnes;
		}
		else{
			System.out.println("Selection impossible, plus d'individus à selectionner que d'individus présents dans la population");
			return population.getListIndividus();
		}
	}

	/**
	 * 
	 * @param population
	 * @param probs
	 * @return Un indivu choisi à la roulette dans la population
	 */
	protected IIndividu selectionUnique(IPopulation population, double[] probs){
		IIndividu heureuxElu = null;
		int i = 0;
		do{
			double d = Math.random();
			boolean retour = (d<probs[i])?true:false;
			if(retour){
				heureuxElu = population.getIndividu(i);
			}
			i += 1;
			if(i == population.getTailleEffective()){
				i = 0;
			}
		}while(heureuxElu == null);
		return heureuxElu;
	}
}
