package darwin.modele;

import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;

/**
 * @author Momo && Dim
 *	Implémentation partielle et abstraite de IEnvironnement
 */
public abstract class Environnement implements IEnvironnement{
	
	// VARIABLES D'INSTANCES :
	
	/** Le nom de l'environement */
	protected String name;
	
	/**
	 * Constructeur par défaut
	 */
	protected Environnement(){
	}
	
	/**
	 * Constructeur basique
	 * @param name
	 */
	protected Environnement(String name){
		this.name = name;
	}
	
	public abstract double evaluerIndividu(IIndividu individu);
	
	public abstract boolean isValid(IIndividu individu);
	
	public String toString() {
		return "Environnement [name=" + name + "]";
	}
}
