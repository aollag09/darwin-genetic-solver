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
		double t1 = System.currentTimeMillis();
		ArrayList<Number> s = new ArrayList<Number>();
		ArrayList<IIndividu> bests = new ArrayList<IIndividu>();
		for(int i=0;i<1;i++){
	
			IPopulation p = new PopulationTuples(300, 10, new int[] {1,2,4,0,15,20,2,5,18,17});
			
			try {
				ISelectionNaturelle selec = new SelectionNaturelleSimple(p, 200, 1, 0.7, 1, 0.1, 150, 0.1);
				
				IConditionArret c = /*new ConditionArretEpsilon(0.05);*/new ConditionArret() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					int c = 40;
					double ancienScore = 0;
					double epsilon = 0.05;
					
					@Override
					public IEnvironnement nextEnvironnement() {
						return null;
					}
					
					@Override
					public IConditionArret nextConditionArret() {
						if(c>0 && ancienScore<600){
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
						//return (population.evaluerPopulation() > 100 || this.iterations>1000);
					}
				};
				
				Darwin d = new Darwin(selec, c);
				d.afficherChaqueScore(true);
				IPopulation p1 = d.solve();
				s.add(p1.evaluerIndividu(p1.getBestIndividu()));
				bests.add(p1.getBestIndividu());
				System.out.println("Meilleur : " + p1.getBestIndividu() + " (" + p1.evaluerIndividu(p1.getBestIndividu()) + ")");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
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
