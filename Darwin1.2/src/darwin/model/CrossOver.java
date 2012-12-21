package darwin.model;

import java.util.List;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IBeing;

/** 
 * @author Momo && Dim
 *	Impl�mentation partielle et abstraite de ICrossOver
 */
public abstract class CrossOver implements ICrossOver {	
	
	//VARIABLES D'INSTANCE
	/**
	 * La probabilit� que le crossOver ait lieu (doit �tre comprise entre 0 et 1)
	 */
	protected double probabilite;
	
	/**
	 * Constructeur par d�faut
	 */
	protected CrossOver(){
		probabilite = 0.7;
	}
	
	/**
	 * Constructeur basique
	 * @param probabilite La probabilit� pour que le crossOver ait lieu (entre 0 et 1)
	 * @throws Exception si la probabilit� est en dehors de [0,1]
	 */
	protected CrossOver(double probabilite) throws Exception{
		if(probabilite<0 || probabilite>1){
			System.out.println("Probabilit� de crossOver non comprise entre 0 et 1");
			throw new Exception();
		}
		else{
			this.probabilite = probabilite;
		}	
	}
	
	@Override
	public abstract List<IBeing> crossOver(IBeing individu1, IBeing individu2);
	
	/**
	 * 
	 * @return true avec une probabilit� p
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
