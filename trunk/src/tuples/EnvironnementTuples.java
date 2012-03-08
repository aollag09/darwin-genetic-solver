package tuples;

import darwin.interfaces.IIndividu;
import darwin.modele.Environnement;

public class EnvironnementTuples extends Environnement{

	/**
	 * Tuple objectif
	 */
	private int[] objectif;
	
	public EnvironnementTuples(int[] objectif){
		this.objectif = objectif;
	}
	
	@Override
	public double evaluerIndividu(IIndividu individu) {
		double score = 0;
		if(isValid(individu)){
			Tuple t = (Tuple)individu;
			for(int i=0; i<t.getNbEntiers(); i++){
				Entier e = (Entier)t.getListCaracteristique().get(i);
				double d = e.getValeur();
				score += (d-objectif[i])*(d-objectif[i]);	
			}
			if(score == 0){
				score = 100;
			}
			else{
				score = 1/(Math.sqrt(score));
			}
		}
		return score;
	}

	@Override
	public boolean isValid(IIndividu individu) {
		return(individu instanceof Tuple && (individu.getNombreCaracteristiques() == this.objectif.length));
	}

}
