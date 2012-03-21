package parallelisation.interfaces;

import java.io.Serializable;


/**
 * 
 * Interface de cr�er un serveur sur lequel on peut donner diff�rentes commandes concernant 
 * l'algorithme g�n�tique du TSP.
 * 
 * @author Amaury
 *
 */
public interface IServeur extends Serializable {
	
	
	public void lancer();
	
}
