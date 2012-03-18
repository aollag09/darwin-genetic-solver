package modele.genetique.tsp;

import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;
import darwin.modele.Population;

import modele.graphe.Graphe;
import modele.graphe.Road;
import modele.graphe.Ville;

public class PopulationTSP extends Population {

        
        // CONSTRUCTEUR 
        
	private static final long serialVersionUID = -4816987600805972375L;

		public PopulationTSP() throws RemoteException {
                super();
        }

        public PopulationTSP(ArrayList<IIndividu> individus, int nombreIndividu,
                        IEnvironnement environnement) throws RemoteException {
                super();
                this.individus = individus;
                this.nombreIndividusSouhaite = nombreIndividu;
                this.environnement = environnement;
        }

        public PopulationTSP(int nombreIndividus, IEnvironnement environnement) throws RemoteException {
                super();
                this.nombreIndividusSouhaite = nombreIndividus;
                this.environnement = environnement;
                this.individus = new ArrayList<IIndividu>();
                generer();
        }

        
        /** On crée notre population initiale au hasard sur l'environement : ici le graphe */
        @Override
        public void generer() {
                EnvironnementTSP env = (EnvironnementTSP) this.environnement;
                individus.removeAll(getListIndividus());
                for( int i = 0; i< getTailleSouhaitee(); i++){
                        List<ICaracteristique> villes = new ArrayList<ICaracteristique>();
                        villes.addAll(env.getGraphe().getVertices());
                        Collections.shuffle(villes);
                        Chemin chemin = new Chemin("Le chemin "+ i,villes);
                        individus.add(chemin);
                }               
        }
        
        
        /**
         * Méthode permettant d'afficher graphiquement un individu
         */
        public JPanel afficherGraphiquementIndividu(Chemin chemin, Dimension d){
                // Creation du graphe
                Graphe g = new Graphe();
                for(ICaracteristique v : chemin.getListCaracteristique()){
                        // On ajoute toutes les villes au graphe
                        Ville ville = (Ville) v;
                        g.addVertex(ville);
                }
                for(int i= 0; i< chemin.getListCaracteristique().size() - 1; i ++){
                        // On ajoute tous les chemins au graphe
                        Ville v1 = (Ville) chemin.getCaracteristique(i);
                        Ville v2 = (Ville) chemin.getCaracteristique(i+1);
                        Road r = new Road(Road.getDistance(v1, v2));
                        g.addEdge(r, v1, v2);
                }
                // Ajout de la route entre la première et oa dernière ville
                Ville v1 = (Ville) chemin.getCaracteristique(0);
                Ville v2 = (Ville) chemin.getCaracteristique(chemin.getListCaracteristique().size() - 1);
                Road r = new Road(Road.getDistance(v1, v2));
                g.addEdge(r, v1, v2);
                // Affichage du graphe
                return g.afficher(d);
        }

        @Override
        public String toString(){
                String s = "";
                for(IIndividu i : this.getListIndividus()){
                        try {
                                s+= "Evaluation : "+this.evaluerIndividu(i)+" "+ i + "\n";
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
                return s;
        }
}
