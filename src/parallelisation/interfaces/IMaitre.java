package parallelisation.interfaces;

import java.io.Serializable;
import java.util.List;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;

public interface IMaitre extends Serializable {

	
	/**
	 * Lance chacune des requêtes
	 */
	void lancerRequetes();
	
	/** 
	 * Initialisation des requêtes
	 */
	void initialiserRequetes();
	
	/**
	 * @param bestIndividu, un client appelle cette méthode pour donner
	 * au maitre un de ces individus
	 */
	void recupererIndividu(IIndividu bestIndividu);	
	
	void setConditionArret(IConditionArret condition);
	
	IConditionArret getConditionArret();
	
	IPopulation getBestIndividus();
	
	void setBestIndividus(IPopulation newBest);
	
	/**
	 * @param requete, ajoute la requete en paramètre à la liste des requêtes
	 */
	void ajouterRequete(IRequete requete);

	/**
	 * 
	 * @return la listes de toutes les requêtes sur chacun des serveur envoyées par le maitre
	 */
	List<IRequete> getListRequete();
	
	/**
	 * Méthode appelée une fois que le maitre à satisfait sa condition 
	 * d'arrêt
	 */
	void operationFinale();
	
	
}
