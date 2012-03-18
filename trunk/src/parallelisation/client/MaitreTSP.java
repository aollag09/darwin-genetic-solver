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
 * La classe Maitre TSP est la classe maitre de la parall�lisation du probl�me du TSP.
 * C'est lui qui va :
 * 	> Initialiser les requ�tes � envoyer sur tous les Serveurs disponnibles
 * 	> Envoyez ces requ�tes (une pour chaque serveur) avec �a liste de requ�tes sur des threads diff�rents
 * 	> R�cup�rer les r�sulats des traitements des serveurs
 * 	> Analyser ces r�sultats 
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
	/** L'ensemble des meilleurs individus r�cup�r�s avec les g�n�rations sur les serveurs */
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
		/* On cr�e d'abord l'environement toujours indentique */
		EnvironnementTSP environnement = new EnvironnementTSP();
		/* Pour chacun des serveurs, on envoie une requ�te */
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
	 * @param ind, l'individu � r�cup�rer apr�s traitement de la requ�te
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
