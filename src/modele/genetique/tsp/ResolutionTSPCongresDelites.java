package modele.genetique.tsp;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelectionNaturelle;
import darwin.solveur.Darwin;
import darwin.solveur.conditions.ConditionArretEpsilonAvecMarge;
import darwin.solveur.crossovers.CrossOverChemin;
import darwin.solveur.mutations.MutationChemin;
import darwin.solveur.selections.SelectionElitiste;
import darwin.solveur.selections.SelectionTournoi;

/**
 * Méthode de résolution du TSP : On crée d'abord une population 
 * d'élites, qu'on va essayer de faire évoluer vers une meilleure
 * solution.
 * @author dim
 *
 */
public class ResolutionTSPCongresDelites {

	/**
	 * La taille de la population d'élites qu'on va générer
	 */
	public int taillePopulationElite;
	
	public ResolutionTSPCongresDelites(int taillePop){
		this.taillePopulationElite = taillePop;
	}
	
	public void solve(int inter){
		IPopulation fin = null;
		try {
			IPopulation p = new PopulationTSP(this.taillePopulationElite, new EnvironnementTSP());
			p.setListIndividus(this.genererElites(inter));
			System.out.println(p.getTailleEffective());
			ISelectionNaturelle s = new SelectionNaturelleTSP(new SelectionTournoi((int)this.taillePopulationElite*2/3), new SelectionTournoi(this.taillePopulationElite),
					new CrossOverChemin(0.7), new MutationChemin(0.2), p, 40, 0.2);
			Darwin d = new Darwin(s, new ConditionArretEpsilonAvecMarge(10, 0));
			d.afficherChaqueScore(true);
			fin = d.solve();
			System.out.println("Meilleur chemin : " + "\n" + fin.getBestIndividu() + "\n" + "score : " + fin.evaluerIndividu(fin.getBestIndividu()));
			Chemin c = (Chemin) fin.getBestIndividu();
			JFrame f = new JFrame();
			Dimension dim = new Dimension(800,500);
			f.setSize(dim);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setContentPane(((PopulationTSP) fin).afficherGraphiquementIndividu(c, dim));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<IIndividu> genererElites(int taillePopIntermediaires){
		List<IIndividu> elites = new ArrayList<IIndividu>();
		try {
			IPopulation p = new PopulationTSP(taillePopIntermediaires, new EnvironnementTSP());
			ISelectionNaturelle s = new SelectionNaturelleTSP(new SelectionTournoi((int)taillePopIntermediaires*2/3), new SelectionTournoi(taillePopIntermediaires),
					new CrossOverChemin(0.7), new MutationChemin(0.2), p,(int)this.taillePopulationElite*2/6 , 0.2);
			Darwin d = new Darwin(s, new ConditionArretEpsilonAvecMarge(10, 0));
			d.afficherChaqueScore(true);
			for(int i=0; i<this.taillePopulationElite; i++){
				IPopulation pop = d.solve();
				elites.add(pop.getBestIndividu());
				p.generer();
				d.setConditionArret(new ConditionArretEpsilonAvecMarge(0.1, 10));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elites;
	}
	
	public static void main(String[] args){
		ResolutionTSPCongresDelites r = new ResolutionTSPCongresDelites(300);
		r.solve(200);
	}
}
 