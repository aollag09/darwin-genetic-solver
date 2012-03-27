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
			Registry registre = null;
			
			/* On r�cup�re l'adresse IP */
			String ipAdresse = null;
			try {
				ipAdresse = InetAddress.getLocalHost().getHostAddress().toString();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/* R�cup�ration ou cr�ation du registre sur le local */
			try{
				registre = LocateRegistry.createRegistry(Integer.parseInt(Maitre.PORT));
			}catch (Exception e) {
				/* Si on ne peut pas cr�er le registrery, c'est qu'il existe d�j� */
				/* On cherche donc � le r�cup�rer */
				registre = LocateRegistry.getRegistry(ipAdresse,Integer.parseInt(Maitre.PORT));
			}


			/* On d�pose alors sur le serveur l'objet Darwin avec la bonne adresse ! */
			adresse ="rmi//"+ipAdresse+"//Serveur"+ (registre.list().length+1);
			/* Ajout de l'objet sur le r�seau */
			Naming.rebind(adresse, darwin);


			/* On ajoute le serveur ajout� � la liste des serveurs disponnibles */
			Registry registreServeur = null;
			try{
				
			}catch (Exception e) {
				// TODO: handle exception
			}

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
		Serveur s = new Serveur();
		s.lancer();
	}
}
