package parallelisation.client;

import java.rmi.RemoteException;
import modele.genetique.tsp.EnvironnementTSP;
import modele.genetique.tsp.PopulationTSP;
import modele.genetique.tsp.SelectionNaturelleTSP;

import darwin.interfaces.IConditionArret;
import darwin.solveur.conditions.ConditionArretEpsilonAvecMarge;
import darwin.solveur.crossovers.CrossOverChemin;
import darwin.solveur.mutations.MutationChemin;
import darwin.solveur.selections.SelectionElitiste;
import darwin.solveur.selections.SelectionTournoi;


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
public class MaitreTSP extends Maitre {
	
	private static final long serialVersionUID = -2672256610096662180L;


	public static void main(String[] args) {
		MaitreTSP m = new MaitreTSP();
		m.initialiserRequetes();
		m.lancerRequetes();
	}
	
	

	public MaitreTSP() {
		super();
		try {	
			this.bestIndividus = new PopulationTSP();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void operationFinale() {
		System.out.println(" OPERATION FINALE ");
		System.out.println(this.bestIndividus.getBestIndividu());
	}


	@Override
	public void initialiserRequetes() {
		/* On crée d'abord l'environement toujours indentique */
		EnvironnementTSP environnement = new EnvironnementTSP();
		/* Pour chacun des serveurs, on envoie une requête */
		for(int i = 1; i <= nombreServeurs; i++){
			try{
			PopulationTSP population = new PopulationTSP(500, environnement);
			SelectionNaturelleTSP stsp = new SelectionNaturelleTSP(new SelectionTournoi(300), 
					new SelectionElitiste(500), new CrossOverChemin(0.8), new MutationChemin(0.5),population,10,1);
			IConditionArret condition = new ConditionArretEpsilonAvecMarge(0.01, 500);
			Requete r = new Requete(i, this, stsp, condition);
			this.listRequetes.add(r);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	
	

}
