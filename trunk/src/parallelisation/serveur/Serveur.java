package parallelisation.serveur;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import parallelisation.client.MaitreTSP;
import parallelisation.interfaces.IServeur;


import darwin.interfaces.IDarwin;
import darwin.solveur.Darwin;

/**
 * 
 * Cette classe permet de créer un serveur sur lequel on peut donner différentes commandes concernant 
 * l'algorithme génétique du TSP.
 * 
 * @author Amaury
 *
 */
public class Serveur implements IServeur{

	private static final long serialVersionUID = 7210480090025579137L;
	private static InetAddress address;
	
	public static String CHEMIN_RESEAU;

	public Serveur(){};
	
	public void lancer(){
		
		try {
			address = InetAddress.getLocalHost();
			CHEMIN_RESEAU = "rmi//"+address.getHostAddress()+"//Serveur";
		} catch (UnknownHostException e2) {
			System.out.println("Erreur dans la récupération de l'adresse réseau locale");
			e2.printStackTrace();
		}

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
									CHEMIN_RESEAU+index); 
				}catch (Exception e) {
					bonIndex = index;
				}
				index ++;
			}
			
			/* On dépose alors sur le serveur l'objet Darwin avec la bonne adresse ! */
			adresse = CHEMIN_RESEAU+bonIndex;
			
			
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
		}else{
			System.out.println("Assurez vous d'avoir bien lancé le rmiregistry dans le dossier bin de Darwin avant de créer un Serveur");
			System.out.println("Sinon le problème vient du fait que l'adresse sur laquelle vous souhaitez lancer la JVM n'est pas disponnible");	
		}
		/* Le serveur est près à traiter vos requêtes */
	}
	
	public static void main(String[] args) {
		Serveur s = new Serveur();
		s.lancer();
	}
}
