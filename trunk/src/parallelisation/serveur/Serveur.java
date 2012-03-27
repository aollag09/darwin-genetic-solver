package parallelisation.serveur;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import parallelisation.client.Maitre;
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

	public Serveur(){};

	public void lancer(){
		String adresse = "";
		try{
			/* L'objet que l'on va mettre sur le r�seau */
			IDarwin darwin = new Darwin();

			/* On r�cup�re le registre sur le r�seau */
			Registry registre;
			registre = LocateRegistry.getRegistry(Maitre.ADRESSE_IP,Integer.parseInt(Maitre.PORT));

			/* On d�pose alors sur le serveur l'objet Darwin avec la bonne adresse ! */
			adresse = Maitre.CHEMIN_RESEAU+ (registre.list().length+1);
			
			/* Ajout de l'objet sur le r�seau */
			Naming.rebind(adresse, darwin);

		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		System.out.println("Serveur lanc� sur : "+adresse);
		System.out.println("En attente de requ�te ...");

		/* Le serveur est pr�s � traiter vos requ�tes */
	}

	public static void main(String[] args) {
		try {
			System.out.println(LocateRegistry.getRegistry());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Serveur s = new Serveur();
		//s.lancer();
	}
}
