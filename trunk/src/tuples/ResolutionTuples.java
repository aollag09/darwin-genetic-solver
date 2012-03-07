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
		
		IPopulation p = new PopulationTuples(20, 10, new int[] {1,2,3,4,5,6,7,8,9,10});
		
		try {
			ISelectionNaturelle selec = new SelectionNaturelleSimple(p, 10, 2, 0.7, 2, 0.1, 5);
			
			IConditionArret c = new ConditionArret() {
				
				@Override
				public IEnvironnement nextEnvironnement() {
					return null;
				}
				
				@Override
				public IConditionArret nextConditionArret() {
					return null;
				}
				
				@Override
				public boolean isSatisfied(IPopulation population) {
					this.iterations++;
					return (population.evaluerPopulation()> 350 || this.iterations>1000);
				}
			};
			
			Darwin d = new Darwin(selec, c);
			d.afficherChaqueScore(true);
			d.solve();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
