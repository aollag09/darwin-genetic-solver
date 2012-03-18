package parallelisation.serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import parallelisation.client.MaitreTSP;


import darwin.interfaces.IDarwin;
import darwin.solveur.Darwin;

/**
 * 
 * Cette classe permet de créer un serveur sur lequel on peut donner différentes commandes concernant 
 * l'algorithme génétique du TSP.
 * 
 * 
 * 
 * @author Amaury
 *
 */
public class ServeurTSP {

	public ServeurTSP(){
		boolean erreur = false;
		String adresse = "";
		/* Chaque serveur doit créer un objet Darwin sur lequel il va fair tourner toutes les mutations ! */
		IDarwin darwin;
		try {
			darwin = new Darwin();
			
			
			/* On cherche à déposer cet objet sur le réseau, en trouvant le dernier disponnible */
			int bonIndex = 0;
			int index = 1;
			while(bonIndex == 0){
				try { 
					@SuppressWarnings("unused")
					IDarwin d = (IDarwin)Naming.lookup(
									MaitreTSP.CHEMIN_RESEAU+index); 
				}catch (Exception e) {
					bonIndex = index;
				}
				index ++;
			}
			
			/* On dépose alors sur le serveur l'objet Darwin avec la bonne adresse ! */
			adresse = MaitreTSP.CHEMIN_RESEAU+bonIndex;
			
			
			Naming.rebind(adresse, darwin);
			
		} catch (RemoteException e1) {
			erreur = true;
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			erreur = true;
		}
		
	
		if( ! erreur){
			System.out.println("Serveur lancé sur : "+adresse);
			System.out.println("En attente de requête ...");
		}else
			System.out.println("Assurez vous d'avoir bien lancé le rmiregistry dans le dossier bin de Darwin avant de créer un Serveur");
		/* Le serveur est près à traiter vos requêtes */
	}
	
	public static void main(String[] args) {
		new ServeurTSP();
	}
}
