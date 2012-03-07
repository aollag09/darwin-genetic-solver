package darwin.modele;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IMutation;

/** 
 * @author Dim && Momo
 *	Implémentation partielle et abstraite de IMutation
 */
public abstract class Mutation implements IMutation{
	
	// VARIABLES D'INSTANCE
	
	/**
	 * le nombre de bits à muter dans une caractéristique
	 */
	protected int nbBitAMuter;
	
	/**
	 * La probablité que la mutation ait lieu (comprit entre 0 et 1)
	 */
	protected double probabilite;
	
	protected Mutation(int nbBitAMuter, double prob) throws Exception{
		this.nbBitAMuter = nbBitAMuter;
		if(prob<0 || prob>1){
			System.out.println("Probabilité de mutation non comprise entre 0 et 1");
			throw new Exception();
		}
		else{
			this.probabilite = prob;			
		}
	}
	
	public abstract IIndividu muter(IIndividu individu);

	public boolean mutationIndividuPossible(IIndividu individu){
		return (individu.getNombreCaracteristiques() != 0);
	}
	
	/**
	 * 
	 * @param caracteristique
	 * @return true si la caractéristique contient plus de bits que de bits à muter
	 */
	public boolean mutationCaracteristiquePossible(ICaracteristique caracteristique){
		return (caracteristique.getTailleBitSet() > nbBitAMuter);
	}
	
	/**
	 * 
	 * @return true avec une probabilité p
	 */
	protected boolean doMutation(){
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
