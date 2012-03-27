package parallelisation.interfaces;

import java.io.Serializable;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.ISelectionNaturelle;

public interface IRequete extends Runnable,Serializable {

	/** M�thode pour lancer l'interface */
	public void lancerRequete();

	/** M�thode pour r�cup�rer le maitre qui lance toutes les requ�tes */
	public IMaitre getMaitre();

	public ISelectionNaturelle getSelectionNaturelle();

	public IConditionArret getConditionDArret();

}
