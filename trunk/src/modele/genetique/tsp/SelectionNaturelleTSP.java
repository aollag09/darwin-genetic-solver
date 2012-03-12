package modele.genetique.tsp;

import java.awt.Dimension;

import javax.swing.JFrame;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IMutation;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;
import darwin.solveur.SelectionNaturelleSimple;

public class SelectionNaturelleTSP extends SelectionNaturelleSimple {

	public SelectionNaturelleTSP(ISelection selection, ICrossOver crossOver,
			IMutation mutation, IPopulation population) throws Exception {
		super(selection, selection, crossOver, mutation, population, 2, 0.5);
		// TODO Auto-generated constructor stub
	}
	
//	public static void main(String[] args) {
//		SelectionRoulette select;
//		CrossOverSimple crossOver;
//		MutationSimple mutation;
//		SelectionNaturelleTSP sntsp;
//		EnvironnementTSP environement;
//		PopulationTSP population;
//		try{
//			environement = new EnvironnementTSP();
//			population = new PopulationTSP(10, environement, true);
//			select 		= new SelectionRoulette(6);
//			crossOver 	= new CrossOverSimple(1, 0.5);
//			mutation  	= new MutationSimple(1, 0.2);
//			sntsp = new SelectionNaturelleTSP(select, crossOver, mutation, population);
//			
//			System.out.println("************************************");
//			System.out.println("*  Population de tuples initiales  *");
//			System.out.println("************************************");
//			System.out.println(population);
//			System.out.println("Score total : " + population.evaluerPopulation());
//			
////			JFrame f = new JFrame("Population initiale");
////			f.setSize(800,600);
////			f.setContentPane((Chemin)(population.getIndividu(0)).);
////			f.setVisible(true);
////			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			int nbSelections = 0;
//			
//			while(nbSelections<300){
//				sntsp.nextGeneration();
//				System.out.println(population.evaluerPopulation());
//				nbSelections ++;
//			}
//			
//			System.out.println(population);
//			System.out.println("******************************************************");
//			System.out.println("*  Population après " + nbSelections + " générations *");
//			System.out.println("******************************************************");
//			System.out.println("Score total : " + population.evaluerPopulation());
//
//		
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//   }
	
}
