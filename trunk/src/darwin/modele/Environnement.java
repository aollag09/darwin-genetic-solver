package darwin.modele;

import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;

/**
 * @author Momo && Dim
 *	Implémentation partielle et abstraite de IEnvironnement
 */
public abstract class Environnement implements IEnvironnement{
	
	// VARIABLES D'INSTANCES :
	
	private static final long serialVersionUID = 3950886875103105136L;
	
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
	
	public abstract double evaluerIndividu(IIndividu individu) throws Exception;
	
	public abstract boolean isValid(IIndividu individu);
	
	public String toString() {
		return "Environnement [name=" + name + "]";
	}
}
