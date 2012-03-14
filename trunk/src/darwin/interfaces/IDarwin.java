package darwin.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Momo && Dim
 * Solveur de l'algorithme génétique
 */
public interface IDarwin extends Remote {

	/**
	 * Fixe la condition d'arret
	 * @param condition
	 */
	void setConditionArret(IConditionArret condition)  throws RemoteException;
	
	/**
	 * Modifie la selection Naturelle
	 */
	void setSelectionNaturelle(ISelectionNaturelle newSelectionNaturelle) throws RemoteException;
	/**
	 * Fait tourner l'algorithme génétique jusqu'à ce que la condition d'arrêt finale soit remplie
	 * @return La population finale
	 */
	IPopulation solve() throws RemoteException;
	
	/**
	 * 
	 * @return La condition d'arret actuellement utilisée
	 */
	IConditionArret getConditionArret() throws RemoteException;
	
	/**
	 * 
	 * @return La selection naturelle sur laquelle on itere
	 */
	ISelectionNaturelle getSelectionNaturelle() throws RemoteException;
}
