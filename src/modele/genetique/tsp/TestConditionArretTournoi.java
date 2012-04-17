package modele.genetique.tsp;

import java.awt.Dimension;

import javax.swing.JFrame;

import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelectionNaturelle;
import darwin.solveur.Darwin;
import darwin.solveur.conditions.ConditionArretTournoi;
import darwin.solveur.crossovers.CrossOverChemin;
import darwin.solveur.mutations.MutationChemin;
import darwin.solveur.selections.SelectionTournoi;

public class TestConditionArretTournoi {

	public static void main(String[] args){
		
		try {
			IPopulation p = new PopulationTSP(200, new EnvironnementTSP());
	        ISelectionNaturelle s;
			s = new SelectionNaturelleTSP(new SelectionTournoi(100), new SelectionTournoi(200),
			                new CrossOverChemin(0.7), new MutationChemin(0.2), p,70 , 0.2);
	        Darwin d = new Darwin(s, new ConditionArretTournoi(50, 2000));
	        d.solve();
			 
	        Chemin c = (Chemin) p.getBestIndividu();
	        JFrame f = new JFrame();
	        Dimension dim = new Dimension(800,500);
	        f.setSize(dim);
	        f.setVisible(true);
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setContentPane(((PopulationTSP) p).afficherGraphiquementIndividu(c, dim));
	        
	        System.out.println("====================== BEST PATH =========================");
	        System.out.println(c.toString());
	        try {
	                System.out.println("VALUE >>>>> "+p.evaluerIndividu(c));
	        } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	        }
	        System.out.println("BEST  >>>>> "+" 7542 (pour le problème de Berlin52)");
	        
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
