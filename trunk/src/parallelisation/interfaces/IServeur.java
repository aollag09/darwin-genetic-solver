package parallelisation.interfaces;

import java.io.Serializable;


/**
 * 
 * Interface de créer un serveur sur lequel on peut donner différentes commandes concernant 
 * l'algorithme génétique du TSP.
 * 
 * @author Amaury
 *
 */
public interface IServeur extends Serializable {
	
	
	public void lancer();
	
}
