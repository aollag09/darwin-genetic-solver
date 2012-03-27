package parallelisation.registry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import parallelisation.client.Maitre;


/**
 * Registre qui va garde en m�moire le chemin r�seau de tous les serveurs connect�s sur le r�seau
 * C'est la premi�re �tape
 * 
 * @author Amaury
 *
 */
public class RegistreServeur {
	
	public static void main(String[] args) {
		RegistreServeur r = new RegistreServeur();
		r.lancer();
		System.out.println("Le registre est bien lanc� en local du Maitre au port :"+Maitre.PORT);
		System.out.println("Vous pouvez maintenant ajouter des Serveurs !");
	}
	
	/** La liste de tous les serveurs disponnibles */
	private ListeServeur serveurs;
	
	public RegistreServeur() {
		try {
			serveurs = new ListeServeur();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void lancer(){
		
		try {
			/* On cr�e un registre sur le r�seau */
			Registry registre;
			registre = LocateRegistry.createRegistry(Integer.parseInt(Maitre.PORT));
			
			/* Ajout de l'objet liste des serveurs sur le r�seau */
			Naming.rebind(Maitre.CHEMIN_RESEAU_REGISTRE_SERVEURS, serveurs);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
