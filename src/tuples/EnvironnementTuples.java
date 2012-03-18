package tuples;

import darwin.interfaces.IIndividu;
import darwin.modele.Environnement;

public class EnvironnementTuples extends Environnement{


	private static final long serialVersionUID = -6642289192818570639L;
	
	/**
	 * Tuple objectif
	 */
	private int[] objectif;
	
	/**
	 * Constructeur avec pour seul paramètre le tuple objectif à atteindre
	 * @param objectif
	 */
	public EnvironnementTuples(int[] objectif){
		this.objectif = objectif;
	}
	
	@Override
	/**
	 * On évalue un tuple à partir de sa distance quadratique par rapport
	 * à l'objectif. On veut que les individus les plus proches aient un 
	 * score plus élevé, donc on renverra l'inverse de la distance quadratique,
	 * si on a atteint le tuple objectif (ie distance = 0), on donne le score 2,
	 * sachant le plus haut score possible pour les tuples différents de l'objectif
	 * est 1.
	 */
	public double evaluerIndividu(IIndividu individu) throws Exception{
		double score = 0;
		if(isValid(individu)){
			Tuple t = (Tuple)individu;
			for(int i=0; i<t.getNbEntiers(); i++){
				Entier e = (Entier)t.getListCaracteristique().get(i);
				double d = e.getValeur();
				score += (d-objectif[i])*(d-objectif[i]);	
			}
			if(score == 0){
				score = 2;
			}
			else{
				score = 1/(Math.sqrt(score));
			}
		}
		else{
			System.out.println("Tentative d'évaluation d'un individu non évaluable");
			throw new Exception();
		}
		return score;
	}

	@Override
	public boolean isValid(IIndividu individu) {
		return(individu instanceof Tuple && (individu.getNombreCaracteristiques() == this.objectif.length));
	}

}
