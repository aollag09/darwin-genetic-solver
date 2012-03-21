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
 * La classe Maitre TSP est la classe maitre de la parall�lisation du probl�me du TSP.
 * C'est lui qui va :
 * 	> Initialiser les requ�tes � envoyer sur tous les Serveurs disponnibles
 * 	> Envoyez ces requ�tes (une pour chaque serveur) avec �a liste de requ�tes sur des threads diff�rents
 * 	> R�cup�rer les r�sulats des traitements des serveurs
 * 	> Analyser ces r�sultats 
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
		/* On cr�e d'abord l'environement toujours indentique */
		EnvironnementTSP environnement = new EnvironnementTSP();
		/* Pour chacun des serveurs, on envoie une requ�te */
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
