package parallelisation.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import modele.genetique.tsp.EnvironnementTSP;
import modele.genetique.tsp.PopulationTSP;
import modele.genetique.tsp.SelectionNaturelleTSP;

import darwin.interfaces.IConditionArret;
import darwin.solveur.conditions.ConditionArretEpsilonAvecMarge;
import darwin.solveur.crossovers.CrossOverChemin;
import darwin.solveur.mutations.MutationChemin;
import darwin.solveur.selections.SelectionElitiste;
import darwin.solveur.selections.SelectionRoulette;
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
		try {
			for(String chemin : listServeurs.getServeurs()){
				try{
				PopulationTSP population = new PopulationTSP(100, environnement);
				SelectionNaturelleTSP stsp = new SelectionNaturelleTSP(new SelectionTournoi(70), 
						new SelectionRoulette(100), new CrossOverChemin(0.8), new MutationChemin(0.7),population,10,1);
				IConditionArret condition = new ConditionArretEpsilonAvecMarge(0.01, 500);
				Requete r = new Requete( this, stsp, condition, chemin);
				System.out.println("Population pour "+chemin+" intialis�e");
				this.listRequetes.add(r);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	
	
	public String toString(){
		String toReturn = "";
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1108);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.rmi.registry.Registry r;
		try {
			r = LocateRegistry.getRegistry(Maitre.ADRESSE_IP, 1099);
			toReturn += "La liste des objets sur le serveur"+"\n";
			String[] name = r.list();
			for(String s : name)
				toReturn += s +"\n";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}
	

}
