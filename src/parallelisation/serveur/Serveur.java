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
 * Cette classe permet de cr�er un serveur sur lequel on peut donner diff�rentes commandes concernant 
 * l'algorithme g�n�tique du TSP.
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
			System.out.println("Erreur dans la r�cup�ration de l'adresse r�seau locale");
			e2.printStackTrace();
		}

		boolean erreur = false;
		String adresse = "";
		/* Chaque serveur doit cr�er un objet Darwin sur lequel il va fair tourner toutes les mutations ! */
		IDarwin darwin;
		try {
			darwin = new Darwin();			
			
			/* On cherche � d�poser cet objet sur le r�seau, en trouvant le dernier disponnible */
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
			
			/* On d�pose alors sur le serveur l'objet Darwin avec la bonne adresse ! */
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
			System.out.println("Serveur lanc� sur : "+adresse);
			System.out.println("En attente de requ�te ...");
		}else{
			System.out.println("Assurez vous d'avoir bien lanc� le rmiregistry dans le dossier bin de Darwin avant de cr�er un Serveur");
			System.out.println("Sinon le probl�me vient du fait que l'adresse sur laquelle vous souhaitez lancer la JVM n'est pas disponnible");	
		}
		/* Le serveur est pr�s � traiter vos requ�tes */
	}
	
	public static void main(String[] args) {
		Serveur s = new Serveur();
		s.lancer();
	}
}
