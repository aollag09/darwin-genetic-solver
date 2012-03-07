package darwin.modele;

import java.util.List;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IIndividu;

/** 
 * @author Momo && Dim
 *	Implémentation partielle et abstraite de ICrossOver
 */
public abstract class CrossOver implements ICrossOver {
	
	//VARIABLES D'INSTANCE
	/**
	 * La probabilité que le crossOver ait lieu (doit être comprise entre 0 et 1)
	 */
	protected double probabilite;
	
	/**
	 * Constructeur par défaut
	 */
	protected CrossOver(){
		probabilite = 0.7;
	}
	
	/**
	 * Constructeur basique
	 * @param nbCaracs Le nombre de caractères à interchanger
	 * @param probabilite La probabilité pour que le crossOver ait lieu (entre 0 et 1)
	 * @throws Exception si la probabilité est en dehors de [0,1]
	 */
	protected CrossOver(double probabilite) throws Exception{
		if(probabilite<0 || probabilite>1){
			System.out.println("Probabilité de crossOver non comprise entre 0 et 1");
			throw new Exception();
		}
		else{
			this.probabilite = probabilite;
		}	
	}
	
	@Override
	public abstract List<IIndividu> crossOver(IIndividu individu1, IIndividu individu2);
	
	/**
	 * 
	 * @return true avec une probabilité p
	 */
	protected boolean doCrossOver(){
		if(this.probabilite == 1d){
			return true;
		}
		else{
			double d = Math.random();
			boolean retour = (d<this.probabilite)?true:false;
			return retour;
		}
	}
}
