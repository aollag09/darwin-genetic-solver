package modele.genetique.tsp;

import java.awt.Dimension;

import javax.swing.JFrame;

import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelectionNaturelle;
import darwin.solveur.Darwin;
import darwin.solveur.conditions.ConditionArretEpsilonAvecMarge;
import darwin.solveur.conditions.ConditionArretScoreMinimum;
import darwin.solveur.crossovers.CrossOverChemin;
import darwin.solveur.mutations.MutationChemin;
import darwin.solveur.selections.SelectionElitiste;
import darwin.solveur.selections.SelectionTournoi;

public class ResolutionTSPMemoireScore {

        /**
         * Le meilleur score obtenu
         */
        public double meilleurConnu;
        
        public ResolutionTSPMemoireScore(){
                this.meilleurConnu = -10000;
                //this.initialisation(200, 100, 80);
        }
        
        public void initialisation(int taillePop, int nbSelect, int nbEnfants){
                try {
                        IPopulation pop = new PopulationTSP(taillePop, new EnvironnementTSP());
                        ISelectionNaturelle selec = new SelectionNaturelleTSP(new SelectionTournoi(nbSelect), new SelectionElitiste(taillePop),
                                        new CrossOverChemin(0.7), new MutationChemin(0.2), pop, nbEnfants, 0.3);
                        Darwin d = new Darwin(selec, new ConditionArretEpsilonAvecMarge(0.1, 30));
                        d.afficherChaqueScore(true);
                        IPopulation fin = d.solve();
                        this.meilleurConnu = fin.evaluerIndividu(fin.getBestIndividu());
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        
        public IPopulation resolution(int taillePop, int nbSelect, int nbEnfants,int iterations, double delta){
                IPopulation retour = null;
                IPopulation pop;
                try {
                        pop = new PopulationTSP(taillePop, new EnvironnementTSP());
                        ISelectionNaturelle selec = new SelectionNaturelleTSP(new SelectionTournoi(nbSelect), new SelectionTournoi(taillePop),
                                        new CrossOverChemin(0.7), new MutationChemin(0.2), pop, nbEnfants, 0.3);
                        Darwin d = new Darwin(selec, new ConditionArretScoreMinimum(this.meilleurConnu, delta));
                        d.afficherChaqueScore(true);
                        for(int i=0; i<iterations; i++){
                                pop.generer();
                                retour = d.solve();
                                if(this.meilleurConnu < retour.evaluerIndividu(pop.getBestIndividu())){
                                        this.meilleurConnu = retour.evaluerIndividu(pop.getBestIndividu());
                                }
                                d.setConditionArret(new ConditionArretScoreMinimum(this.meilleurConnu, delta));
                        }
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return retour;
        }
        
        public static void main(String[] args){
                ResolutionTSPMemoireScore r = new ResolutionTSPMemoireScore();
                IPopulation p = r.resolution(500, 300, 300, 10, 200);
                try {
                        System.out.println("Best chemin : " + p.getBestIndividu() + "\n" + "Score : " + r.meilleurConnu);
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
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
        }
}