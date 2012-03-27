package parallelisation.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IListeServeur extends Remote,Serializable{
	
	void ajouterServeur(String cheminReseau) throws RemoteException;
	
	List<String> getServeurs() throws RemoteException;
	
	int size() throws RemoteException;

}
