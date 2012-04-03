package parallelisation.registry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import parallelisation.client.Maitre;


/**
 * Registre qui va garde en mémoire le chemin réseau de tous les serveurs connectés sur le réseau
 * C'est la première étape
 * 
 * @author Amaury
 *
 */
public class RegistreServeur {
	
	public static void main(String[] args) {
		RegistreServeur r = new RegistreServeur();
		r.lancer();
		System.out.println("Le registre est bien lancé en local du Maitre au port :"+Maitre.PORT);
		System.out.println("Vous pouvez maintenant ajouter des Serveurs !");
	}
	
	/** La liste de tous les serveurs disponnibles */
	private ListeServeur serveurs;
	
	public RegistreServeur() {
		try {
			serveurs = new ListeServeur();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void lancer(){
		
		try {
			/* On crée un registre sur le réseau */
			LocateRegistry.createRegistry(Integer.parseInt(Maitre.PORT));
			
			/* Ajout de l'objet liste des serveurs sur le réseau */
			Naming.rebind(Maitre.CHEMIN_RESEAU_REGISTRE_SERVEURS, serveurs);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
