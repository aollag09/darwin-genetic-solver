package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IBeing;
import darwin.interfaces.IPopulation;
import darwin.model.Selection;

/**
 * Elitist Selection, which selects being regarding their evaluation
 * we just take the best ones
 * 
 * @author Dim & Momo
 * 
 */
public class SelectionElitist extends Selection {

	public SelectionElitist(int nbIndividus) throws Exception {
		super(nbIndividus);
	}

	@Override
	public List<IBeing> select(IPopulation population) {
		if (this.isSelectionPossible(population)) {
			List<IBeing> toReturn = new ArrayList<IBeing>();
			for (int i = 0; i < this.numberBeings; i++) {
				int k = 0;
				IBeing being = population.getBeing(k);
				while (toReturn.contains(being)) {
					k++;
					being = population.getBeing(k);
				}
				for (int j = 0; j < population.getEffectiveSize(); j++) {
					if ((j != k)
							&& !(toReturn.contains(population.getBeing(j)))) {
						try {
							if (population.evaluateBeing(being) > population
									.evaluateBeing(j)) {
								being = population.getBeing(j);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				toReturn.add(being);
			}
			return toReturn;
		} else {
			System.err
					.println("Ipossible Selection, more beings to select that beings in the population");
			return population.getBeingsList();
		}
	}

	// OLD VERSION, TOO MUCH COMPLEXITY
	// @Override
	// public List<IIndividu> selectionner(IPopulation population) {
	// /* On s'assure qu'il est possible d'effectuer la selection (si ce n'est
	// pas le cas on renvoie la population inchang�e */
	// if(this.selectionPossible(population) || !(this.nbIndivus ==
	// population.getTailleSouhaitee())){
	// /* Initialisation de la liste des individus selectionn�s
	// * On garde ce tableau de taille fixe en mettant � jour � chaque fois les
	// * this.nbIndivi meilleurs individus en complexit� donc direment de O(n)
	// */
	// List<IIndividu> selectionnes = new ArrayList<IIndividu>();
	// for(int i = 0; i<this.nbIndivus; i++){
	// selectionnes.add(null);
	// }
	// /* On traite les tous les autres �l�ments */
	// for(int i = 0; i < population.getTailleEffective(); i++){
	// try{
	// double evaluationCourante =
	// population.evaluerIndividu(population.getIndividu(i));
	// int indexBest = 0;
	// while( indexBest < this.nbIndivus){
	// if(selectionnes.get(indexBest) == null ||
	// evaluationCourante >
	// population.evaluerIndividu(selectionnes.get(indexBest))){
	// /* On ajoute l'individu courant � la liste des selectionn�s au bon
	// index*/
	// /* 2 �tapes :*/
	// /* 1�) D�caler les indices sup�rieurs d'un rang de 1 car on ins�re un
	// nouvel �l�ment*/
	// if(indexBest < this.nbIndivus - 1){
	// /* Si celui d�j� selectionn� n'est pas le dernier �l�ment */
	// for(int j = this.nbIndivus - 2; j>= indexBest; j--){
	// selectionnes.set(j+1, selectionnes.get(j));
	// }
	// }
	// /* 2�) Ajouter le nouvelle individu au bon rang des meilleurs */
	// selectionnes.set(indexBest, population.getIndividu(i));
	// /* Toutes les modifications �tant apport�es, on sort de la boucle */
	// indexBest = this.nbIndivus + 1;
	// }else
	// indexBest ++;
	// }
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// }
	// return selectionnes;
	// }else{
	// System.out.println("Selection impossible, plus d'individus � selectionner que d'individus pr�sents dans la population");
	// return population.getListIndividus();
	// }
	//
	// }

}
