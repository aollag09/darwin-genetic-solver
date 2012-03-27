package tuples;

import java.util.ArrayList;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelectionNaturelle;
import darwin.modele.ConditionArret;
import darwin.solveur.Darwin;
import darwin.solveur.SelectionNaturelleSimple;

public class ResolutionTuples {

	public static void main(String[] args){
		
		//Pour mesurer le temps de recherche
		double t1 = System.currentTimeMillis();
		
		//Pour stocker les meilleurs score des individus obtenu sur plusieurs tests
		ArrayList<Number> s = new ArrayList<Number>();
		
		//Pour stocker les meilleurs individus obtenus sur plusieurs tests
		ArrayList<IIndividu> bests = new ArrayList<IIndividu>();
		
		//Boucle de tests
		for(int i=0;i<1;i++){
	
			//Initialisation de la population :
			//		taille de tuple : 10 
			//		taille de population : 300
			//		tuple objectif : {1,2,4,0,15,20,2,5,18,17}
			IPopulation p = new PopulationTuples(300, 10, new int[] {1,2,4,0,15,20,2,5,18,17});
			
			try {
				//Selection Naturelle Simple construite par défaut
				ISelectionNaturelle selec = new SelectionNaturelleSimple(p, 200, 1, 0.7, 1, 0.1, 150, 0.1);
				
				//Condition d'arrêt anonyme
				IConditionArret c = new ConditionArret() {
					
					private static final long serialVersionUID = 1L;
					
					/**
					 * Le nombre de fois où l'on retente une génération si
					 * on a atteint un extremum local
					 */
					int c = 40;
					
					/**
					 * Le score obtenu à la génération suivante
					 */
					double ancienScore = 0;
					
					/**
					 * L'epsilon qui nous indique si l'on a atteint un extremum
					 */
					double epsilon = 0.05;
					
					@Override
					public IEnvironnement nextEnvironnement() {
						return null;
					}
					
					@Override
					public IConditionArret nextConditionArret() {
						if(c>0){
							c--;
							return this;
						}
						else{
							return null;
						}
					}
					
					@Override
					public boolean isSatisfied(IPopulation population) {
						this.iterations++;
						double d = 	this.ancienScore - population.evaluerPopulation();
						this.ancienScore = population.evaluerPopulation();
						return (Math.abs(d)<this.epsilon);
					}
				};
				
				//Construction de l'objet Darwin
				Darwin d = new Darwin(selec, c);
				
				//Options d'affichage
				d.afficherChaqueScore(true);
				
				//Résolution
				IPopulation p1 = d.solve();
				
				//Stockage de la solution trouvée
				s.add(p1.evaluerIndividu(p1.getBestIndividu()));
				bests.add(p1.getBestIndividu());
				System.out.println("Meilleur : " + p1.getBestIndividu() + " (" + p1.evaluerIndividu(p1.getBestIndividu()) + ")");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//Affichage des résultats après la boucle de test
		double moyenne = 0;
		System.out.println("\n" + "\n" + " Meilleurs individus obtenus sur " + s.size() + " test :");
		for(int i = 0; i<s.size(); i++){
			moyenne += s.get(i).doubleValue();
			System.out.println(bests.get(i) + " Score : " + s.get(i));
		}
		System.out.println("\n" + "Score moyen : " + moyenne/bests.size());
		System.out.println("Temps de recherche : " + (System.currentTimeMillis() - t1)/1000);
	}
}
