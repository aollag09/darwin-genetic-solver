package tuples;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelectionNaturelle;
import darwin.modele.ConditionArret;
import darwin.solveur.Darwin;
import darwin.solveur.SelectionNaturelleSimple;

public class ResolutionTuples {

	public static void main(String[] args){
		
		IPopulation p = new PopulationTuples(20, 5, new int[] {1,2,4,0,15});
		
		try {
			ISelectionNaturelle selec = new SelectionNaturelleSimple(p, 15, 1, 0.7, 1, 0.3, 10, 0.2);
			
			IConditionArret c = /*new ConditionArretEpsilon(0.05);*/new ConditionArret() {
				
				int c = 10;
				double ancienScore = 0;
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
					//return (population.evaluerPopulation() > 100 || this.iterations>1000);
				}
			};
			
			Darwin d = new Darwin(selec, c);
			d.afficherChaqueScore(true);
			d.afficherChaqueGeneration(true);
			IPopulation p1 = d.solve();
			System.out.println("Meilleur : " + p1.getBestIndividu() + " (" + p1.evaluerIndividu(p1.getBestIndividu()) + ")");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
