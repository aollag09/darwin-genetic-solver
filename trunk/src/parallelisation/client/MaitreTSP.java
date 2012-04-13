package parallelisation.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import tp.TP;

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
	
	/** Le TP auquel appartient le Maitre */
	private TP tp;


	public MaitreTSP(TP currentTP) {
		super(); 
		setTp(currentTP);
		try {	
			this.bestIndividus = new PopulationTSP();

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


	public MaitreTSP(int ns, TP currentTP) {
		super(ns); 
		setTp(currentTP);
		try {	
			this.bestIndividus = new PopulationTSP();

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void operationFinale() {
		tp.nextExp();
	}

	

	@Override
	public void initialiserRequetes() {
		System.out.println();
		/* On crée d'abord l'environement toujours indentique */
		EnvironnementTSP environnement = new EnvironnementTSP();
		/* Pour chacun des serveurs, on envoie une requête */
		try {
			for(String chemin : listServeurs.getServeurs()){
				try{
					PopulationTSP population = new PopulationTSP(100, environnement,0.015);
					SelectionNaturelleTSP stsp = new SelectionNaturelleTSP(new SelectionTournoi(70), 
							new SelectionElitiste(population.getTailleSouhaitee()), new CrossOverChemin(0.8), new MutationChemin(0.7),population,50,0.5);
					IConditionArret condition = new ConditionArretEpsilonAvecMarge(0.01, 20);
					Requete r = new Requete( this, stsp, condition, chemin);
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



	public TP getTp() {
		return tp;
	}



	public void setTp(TP tp) {
		this.tp = tp;
	}


	@Override
	public void ajouterResultatExperience(double d, long tempsTotal) {
		tp.getTodoList().get(0).ajouterResultat(d, tempsTotal);
	}


}
