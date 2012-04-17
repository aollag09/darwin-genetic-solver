package darwin.solveur.conditions;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;
import darwin.modele.ConditionArret;

/**
 * Condition d'arrêt adaptée lorsque la selection finale est un tournoi
 * On définit un seuil, si d'une génération à l'autre, la variation de score
 * entre l'ancien et le nouveau meilleur individu ne dépasse pas ce seuil,
 * on incrémente un compteur, lorsque ce compteur atteint un chiffre prédeterminé,
 * on s'arrête. Si entre temps le seuil est dépassé, on réinitialise le compteur.
 * @author Dim
 *
 */
public class ConditionArretTournoi extends ConditionArret{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Le seuil qui caractérise une amélioration significative du score
	 * du meilleur individu de la population
	 */
	private double seuil;
	
	/**
	 * L'ancien meilleur score
	 */
	private double ancienScore;
	
	/**
	 * Le compteur
	 */
	private int compteur;
	
	/**
	 * La limite du compteur
	 */
	private int stop;
	
	public ConditionArretTournoi(double seuil, int stop){
		super();
		this.seuil = seuil;
		this.stop = stop;
		this.compteur = 0;
		this.ancienScore = Math.PI;
	}
	
	@Override
	public boolean isSatisfied(IPopulation population) {
		boolean retour = false;
		double best = 0;
		try {
			best = population.evaluerIndividu(population.getBestIndividu());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.ancienScore == Math.PI){
			this.ancienScore = best;
		}
		else{
			if((best - this.ancienScore)<this.seuil){
				this.compteur ++;
			}
			else{
				this.compteur = 0;
			}
			if(this.compteur >= this.stop){
				retour = true;
			}
			this.ancienScore = best;
		}
		System.out.println("Compteur " + this.compteur);

		return retour;
	}

	@Override
	public IConditionArret nextConditionArret() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEnvironnement nextEnvironnement() {
		// TODO Auto-generated method stub
		return null;
	}

}
