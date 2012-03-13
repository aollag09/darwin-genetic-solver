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
import darwin.solveur.selections.SelectionRoulette;
import darwin.solveur.selections.SelectionTournoi;

public class ResolutionTSP {

	
	public ResolutionTSP() {
		try {
			EnvironnementTSP env = new EnvironnementTSP();
			PopulationTSP population = new PopulationTSP(100, env);
			SelectionNaturelleTSP stsp = new SelectionNaturelleTSP(new SelectionRoulette(40), 
									new SelectionElitiste(50), new CrossOverChemin(0.7), new MutationSimple(2, 0),population,10,1);
			Darwin d = new Darwin(stsp, new ConditionArretEpsilonAvecMarge(0.001, 5000));
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
			
			System.out.println("====================== BEST PATH =========================");
			System.out.println(c.toString());
			System.out.println("VALUE >>>>> "+population.evaluerIndividu(c));
			System.out.println("BEST  >>>>> "+" 7542 (pour le problème de Berlin52)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) throws RemoteException {
		new ResolutionTSP();
	}
}
