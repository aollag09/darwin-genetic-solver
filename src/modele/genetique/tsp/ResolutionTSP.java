package modele.genetique.tsp;

import java.rmi.RemoteException;

import darwin.interfaces.IPopulation;
import darwin.solveur.Darwin;
import darwin.solveur.conditions.ConditionArretEpsilonAvecMarge;
import darwin.solveur.crossovers.CrossOverChemin;
import darwin.solveur.mutations.MutationSimple;
import darwin.solveur.selections.SelectionElitiste;
import darwin.solveur.selections.SelectionTournoi;

public class ResolutionTSP {

	
	public ResolutionTSP() {
		try {
			EnvironnementTSP env = new EnvironnementTSP();
			PopulationTSP population = new PopulationTSP(30, env);
			SelectionNaturelleTSP stsp = new SelectionNaturelleTSP(new SelectionTournoi(20), 
									new SelectionElitiste(30), new CrossOverChemin(0.5), new MutationSimple(2, 0),population);
			Darwin d = new Darwin(stsp, new ConditionArretEpsilonAvecMarge(200, 20));
			d.solve();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) throws RemoteException {
		new ResolutionTSP();
	}
}
