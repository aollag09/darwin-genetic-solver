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
				if(objectif[i] == d){
					score += 2;
				}
				else{
					score += 1/(Math.abs(objectif[i]-d));
				}	
			}
		}
		return score;
	}

	@Override
	public boolean isValid(IIndividu individu) {
		return(individu instanceof Tuple && (individu.getNombreCaracteristiques() == this.objectif.length));
	}

}
