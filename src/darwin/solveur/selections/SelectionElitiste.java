package darwin.solveur.selections;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.modele.Selection;

/**
 * Selection d'invidus sur le modèle élitiste simple !
 * On selectionne simplement un nom prédeterminé d'individu en fonction de leur rang
 * après évaluation par l'environement !
 * On ne prend donc ici que les meilleurs individus 
 * 
 * @author Amaury
 *
 */
public class SelectionElitiste extends Selection{

	public SelectionElitiste(int nbIndividus) throws Exception {
		super(nbIndividus);
	}

	@Override
	public List<IIndividu> selectionner(IPopulation population) {
		/* On s'assure qu'il est possible d'effectuer la selection (si ce n'est pas le cas on renvoie la population inchangée */
		if(this.selectionPossible(population) || !(this.nbIndivus == population.getTailleSouhaitee())){
			/* Initialisation de la liste des individus selectionnés
			 * On garde ce tableau de taille fixe en mettant à jour à chaque fois les 
			 * this.nbIndivi meilleurs individus en complexité donc direment de O(n) */
			List<IIndividu> selectionnes = new ArrayList<IIndividu>();
			for(int i = 0; i<this.nbIndivus; i++){
				selectionnes.add(null);
			}
			/* On traite les tous les autres éléments */
			for(int i = 0; i < population.getTailleEffective(); i++){
				try{
					double evaluationCourante = population.evaluerIndividu(population.getIndividu(i));
					int indexBest = 0;
					while( indexBest < this.nbIndivus){
						if(selectionnes.get(indexBest) == null ||
								evaluationCourante > population.evaluerIndividu(selectionnes.get(indexBest))){
							/* On ajoute l'individu courant à la liste des selectionnés au bon index*/
							/* 2 étapes :*/
							/* 1°) Décaler les indices supérieurs d'un rang de 1 car on insère un nouvel élément*/
							if(indexBest < this.nbIndivus - 1){
								/* Si celui déjà selectionné n'est pas le dernier élément */
								for(int j = this.nbIndivus - 2; j>= indexBest; j--){
									selectionnes.set(j+1, selectionnes.get(j));
								}
							}
							/* 2°) Ajouter le nouvelle individu au bon rang des meilleurs */
							selectionnes.set(indexBest, population.getIndividu(i));
							/* Toutes les modifications étant apportées, on sort de la boucle */
							indexBest = this.nbIndivus + 1;
						}else
							indexBest ++;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return selectionnes;
		}else{
			System.out.println("Selection impossible, plus d'individus à selectionner que d'individus présents dans la population");
			return population.getListIndividus();
		}

	}

}
