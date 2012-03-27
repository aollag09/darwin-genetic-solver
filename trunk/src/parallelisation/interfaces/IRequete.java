package parallelisation.interfaces;

import java.io.Serializable;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.ISelectionNaturelle;

public interface IRequete extends Runnable,Serializable {

	/** Méthode pour lancer l'interface */
	public void lancerRequete();

	/** Méthode pour récupérer le maitre qui lance toutes les requêtes */
	public IMaitre getMaitre();

	public ISelectionNaturelle getSelectionNaturelle();

	public IConditionArret getConditionDArret();

}
