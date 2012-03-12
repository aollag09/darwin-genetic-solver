package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.modele.Selection;

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
	public List<IIndividu> selectionner(IPopulation population) {
		/* On s'assure qu'il est possible d'effectuer la selection (si ce n'est pas le cas on renvoie la population inchang�e */
		if(this.selectionPossible(population)){
			
			/* On r�cup�re le score cumul� de toute la population afin d'�tablir les probabilit�s */
			double scorePop = population.evaluerPopulation();
			
			/* On attribue une probabilit� � chaque individu de la population en fonction de son score*/
			double[] probs = new double[population.getTailleEffective()];
			for(int i=0; i<probs.length; i++){
				try {
					probs[i] = population.evaluerIndividu(population.getIndividu(i))/scorePop;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/* Initialisation de la liste des individus selectionn�s */
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
			System.out.println("Selection impossible, plus d'individus � selectionner que d'individus pr�sents dans la population");
			return population.getListIndividus();
		}
	}

	/**
	 * 
	 * @param population
	 * @param probs
	 * @return Un indivu choisi � la roulette dans la population
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
