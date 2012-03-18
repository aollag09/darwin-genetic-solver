package parralelisation.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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

public class ClientTSP {

	
	public static void main(String[] args) {
		new ClientTSP();
	}
	
	public ClientTSP() {
		/* On donne des opérations à tous les serveurs sur le réseau */
		boolean erreur = false;
		int indexServeur = 1;
		
		/* On crée l'environement toujours indentique */
		EnvironnementTSP environnement = new EnvironnementTSP();
		
		while( ! erreur ){
			try { 
				String adresse = "rmi://localhost/Serveur"+indexServeur;
				System.out.println("Lancement des opération sur le Serveur "+indexServeur);
				IDarwin darwin = (IDarwin)
						Naming.lookup(
								adresse); 
				PopulationTSP population = new PopulationTSP(10, environnement);
				SelectionNaturelleTSP stsp = new SelectionNaturelleTSP(new SelectionElitiste(10), 
						new SelectionElitiste(10), new CrossOverChemin(0.8), new MutationChemin(0.9),population,10,1);
				IConditionArret condition = new ConditionArretEpsilonAvecMarge(0.01, 500);
				darwin.setSelectionNaturelle(stsp);
				darwin.setConditionArret(condition);
				darwin.solve();
				indexServeur ++;
			}
			catch (MalformedURLException murle) { 
				System.out.println(); 
				System.out.println(
						"MalformedURLException"); 
				System.out.println(murle);
				erreur = true;
			} 
			catch (RemoteException re) { 
				System.out.println(); 
				System.out.println(
						"RemoteException"); 
				System.out.println(re); 
				erreur = true;
			} 
			catch (NotBoundException nbe) { 
				System.out.println(); 
				System.out.println(
						"NotBoundException"); 
				System.out.println(nbe); 
				erreur = true;
			} 
			catch (Exception e) {
				erreur = true;
			}
		}

	}
	

}
