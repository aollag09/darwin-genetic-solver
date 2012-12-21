package darwin.model;

import darwin.interfaces.IEnvironment;
import darwin.interfaces.IBeing;

/**
 * @author Momo && Dim
 *	Impl�mentation partielle et abstraite de IEnvironnement
 */
public abstract class Environment implements IEnvironment{
	
	// VARIABLES D'INSTANCES :
		
	/** Le nom de l'environement */
	protected String name;
	
	/**
	 * Constructeur par d�faut
	 */
	protected Environment(){
	}
	
	/**
	 * Constructeur basique
	 * @param name
	 */
	protected Environment(String name){
		this.name = name;
	}
	
	public abstract double evaluateBeing(IBeing being) throws Exception;
	
	public abstract boolean isValid(IBeing being);
	
	public String toString() {
		return "Environnement [name=" + name + "]";
	}
}
