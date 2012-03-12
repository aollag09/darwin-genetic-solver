package modele.genetique.tsp;

import java.awt.Dimension;
import java.rmi.RemoteException;

import javax.swing.JFrame;

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
			PopulationTSP population = new PopulationTSP(100, env);
			SelectionNaturelleTSP stsp = new SelectionNaturelleTSP(new SelectionTournoi(50), 
									new SelectionElitiste(100), new CrossOverChemin(0.7), new MutationSimple(2, 0),population);
			Darwin d = new Darwin(stsp, new ConditionArretEpsilonAvecMarge(0.001, 10000));
			//d.afficherChaqueGeneration(true);
			d.afficherChaqueScore(true);
			d.solve();
			Chemin c = (Chemin) population.getBestIndividu();
			JFrame f = new JFrame();
			Dimension dim = new Dimension(800,500);
			f.setSize(dim);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setContentPane(population.afficherGraphiquementIndividu(c, dim));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) throws RemoteException {
		new ResolutionTSP();
	}
}
