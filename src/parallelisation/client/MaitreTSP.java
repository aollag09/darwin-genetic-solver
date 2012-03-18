package parallelisation.client;

import java.rmi.Naming;
import java.util.ArrayList;

import modele.genetique.tsp.EnvironnementTSP;
import modele.genetique.tsp.PopulationTSP;
import modele.genetique.tsp.SelectionNaturelleTSP;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IDarwin;
import darwin.interfaces.IIndividu;
import darwin.solveur.conditions.ConditionArretEpsilonAvecMarge;
import darwin.solveur.crossovers.CrossOverChemin;
import darwin.solveur.mutations.MutationChemin;
import darwin.solveur.selections.SelectionElitiste;


/**
 * La classe Maitre TSP est la classe maitre de la parallélisation du problème du TSP.
 * C'est lui qui va :
 * 	> Initialiser les requêtes à envoyer sur tous les Serveurs disponnibles
 * 	> Envoyez ces requêtes (une pour chaque serveur) avec ça liste de requêtes sur des threads différents
 * 	> Récupérer les résulats des traitements des serveurs
 * 	> Analyser ces résultats 
 * @author Amaury
 *
 */
public class MaitreTSP {
	
	public static void main(String[] args) {
		MaitreTSP m = new MaitreTSP();
		m.lancerRequetes();
	}
	
	// CONSTANTES :
	public static final String CHEMIN_RESEAU = "rmi://localhost:1099/Serveur";
	
	//VARIABLES D'INSTANCES : 
	/** L'ensemble des meilleurs individus récupérés avec les générations sur les serveurs */
	@SuppressWarnings("unused")
	private ArrayList<IIndividu> bestIndividus;
	/** Le nombre de serveurs disponnibles */
	private int nombreServeurs;

	public MaitreTSP() {
		nombreServeurs = getNombreServeurs();
		bestIndividus = new ArrayList<IIndividu>();
		System.out.println(nombreServeurs);
	}
	
	public void lancerRequetes(){
		/* On crée d'abord l'environement toujours indentique */
		EnvironnementTSP environnement = new EnvironnementTSP();
		/* Pour chacun des serveurs, on envoie une requête */
		for(int i = 1; i <= nombreServeurs; i++){
			try{
			PopulationTSP population = new PopulationTSP(10, environnement);
			SelectionNaturelleTSP stsp = new SelectionNaturelleTSP(new SelectionElitiste(10), 
					new SelectionElitiste(10), new CrossOverChemin(0.8), new MutationChemin(0.9),population,10,1);
			IConditionArret condition = new ConditionArretEpsilonAvecMarge(0.01, 500);
			Requete r = new Requete(i, this, stsp, condition);
			r.lancerRequete();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param ind, l'individu à récupérer après traitement de la requête
	 */
	public void recupererIndividu(IIndividu ind){
		
	}
	
	public static int getNombreServeurs(){
		boolean erreur = false;
		int indexServeur = 0;
		while( ! erreur ){
			indexServeur ++;
			try { 
				String adresse = CHEMIN_RESEAU+indexServeur;
				@SuppressWarnings("unused")
				IDarwin darwin = (IDarwin)Naming.lookup(adresse); 
			}
			catch (Exception e) { 
				erreur = true;
			} 
		}
		return indexServeur-1;
	}
	
	

}
