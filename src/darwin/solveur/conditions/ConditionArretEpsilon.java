 package darwin.solveur.conditions;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;
import darwin.modele.ConditionArret;

/**
 * Un condition d'arret Epsilon
 * On arr�te de boucler dans le cas ou la diff�rence entre l'�valuation de la population courante
 * et la derni�re �valu�e est inf�rieure � epsilon.* 
 * 
 * @author Amaury
 *
 */
public class ConditionArretEpsilon extends ConditionArret{

	// VARIALBES D'INSTANCES
	
	/**
	 * Epsilon, la valeur de la condition d'arret epsilon
	 */
	private double epsilon;
	
	/**
	 * La valeur de l'ancienne �valuation
	 */
	private double oldEvaluation;
	
	
	
	// CONSTRUCTEUR
	
	/**
	 * Constructeur basic de la condition d'arr�t
	 * @param epsilon : la valeur du esilon de la condition d'arr�t epsilon
	 */
	public ConditionArretEpsilon(double epsilon) {
		this.epsilon = epsilon;
		this.oldEvaluation = Double.MIN_VALUE;
		
	}
	
	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	@Override
	public boolean isSatisfied(IPopulation population) {
		iterations++;
		if(oldEvaluation == Double.MIN_VALUE){
			/* L'ancienne �valuation n'est pas encore instanci�e */
			oldEvaluation = population.evaluerPopulation();
			return false;
		}else{
			double evaluationCourante = population.evaluerPopulation();					
			if(Math.abs(oldEvaluation -  evaluationCourante) > this.epsilon){
				/* La condition d'arr�t n'est pas satisfaite */
				oldEvaluation = evaluationCourante;
				return false;
			}else{
				oldEvaluation = evaluationCourante;
				return true;
			}
		}

	}

	@Override
	public IConditionArret nextConditionArret() {
		return null;
	}

	@Override
	public IEnvironnement nextEnvironnement() {
		return null;
	}

	public double getOldEvaluation() {
		return oldEvaluation;
	}

	public void setOldEvaluation(double oldEvaluation) {
		this.oldEvaluation = oldEvaluation;
	}

}
