package parallelisation.serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import parallelisation.client.Maitre;
import parallelisation.interfaces.IListeServeur;
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
	
	public static final String ADRESSE_IP_SUR_RESEAU = Maitre.ADRESSE_IP;

	public Serveur(){};

	public void lancer(){
		String adresse = "";
		try{
			/* L'objet que l'on va mettre sur le réseau */
			IDarwin darwin = new Darwin();

			/* On récupère le registre sur le réseau */
			Registry registre = null;
			
			/* On récupére l'adresse IP */
			String ipAdresse = null;
			ipAdresse = ADRESSE_IP_SUR_RESEAU;
			
			/* Récupération ou création du registre sur le local */
			try{
				registre = LocateRegistry.createRegistry(Integer.parseInt(Maitre.PORT));
			}catch (Exception e) {
				/* Si on ne peut pas créer le registrery, c'est qu'il existe déjà */
				/* On cherche donc à le récupérer */
				registre = LocateRegistry.getRegistry(ipAdresse,Integer.parseInt(Maitre.PORT));
			}


			/* On dépose alors sur le serveur l'objet Darwin avec la bonne adresse ! */
			adresse ="rmi//"+ipAdresse+"//Serveur"+ (registre.list().length+1);
			/* Ajout de l'objet sur le réseau */
			Naming.rebind(adresse, darwin);


			/* On ajoute le serveur ajouté à la liste des serveurs disponnibles */
			Registry registreServeur = null;
			try{
				registreServeur = LocateRegistry.getRegistry(Maitre.ADRESSE_IP, Integer.parseInt(Maitre.PORT));
				IListeServeur list = (IListeServeur) registreServeur.lookup(Maitre.CHEMIN_RESEAU_REGISTRE_SERVEURS);
				list.ajouterServeur(adresse);
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur dans la récupération du registre de serveur du maitre");
			}
			

		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		System.out.println("Serveur lancé sur : "+adresse);
		System.out.println("En attente de requête ...");

		/* Le serveur est près à traiter vos requêtes */
	}

	public static void main(String[] args) {
		Serveur s = new Serveur();
		s.lancer();
	}
}
