 package darwin.solveur.conditions;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IPopulation;
import darwin.modele.ConditionArret;

/**
 * Un condition d'arret Epsilon
 * On arrête de boucler dans le cas ou la différence entre l'évaluation de la population courante
 * et la dernière évaluée est inférieure à epsilon.* 
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
	 * La valeur de l'ancienne évaluation
	 */
	private double oldEvaluation;
	
	
	
	// CONSTRUCTEUR
	
	/**
	 * Constructeur basic de la condition d'arrêt
	 * @param epsilon : la valeur du esilon de la condition d'arrêt epsilon
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
			/* L'ancienne évaluation n'est pas encore instanciée */
			oldEvaluation = population.evaluerPopulation();
			return false;
		}else{
			double evaluationCourante = population.evaluerPopulation();					
			if(Math.abs(oldEvaluation -  evaluationCourante) > this.epsilon){
				/* La condition d'arrêt n'est pas satisfaite */
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
