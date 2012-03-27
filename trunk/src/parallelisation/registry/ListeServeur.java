package parallelisation.registry;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import parallelisation.interfaces.IListeServeur;

public class ListeServeur extends UnicastRemoteObject implements IListeServeur{

	private static final long serialVersionUID = -2432081193133304766L;
	private ArrayList<String> serveurs;

	public ListeServeur() throws RemoteException{
		serveurs = new ArrayList<String>();
	}

	public ListeServeur(ArrayList<String> serveurs) throws RemoteException{
		this.serveurs = serveurs;
	}

	@Override
	public void ajouterServeur(String cheminReseau) {
		serveurs.add(cheminReseau);
		System.out.println("Serveur ajouté :"+cheminReseau);
	}

	@Override
	public List<String> getServeurs() {
		return serveurs;
	}

	@Override
	public int size() {
		return this.serveurs.size();
	}

}
