package parallelisation.interfaces;

import java.io.Serializable;
import java.util.List;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;

public interface IMaitre extends Serializable {

	
	/**
	 * Lance chacune des requ�tes
	 */
	void lancerRequetes();
	
	/** 
	 * Initialisation des requ�tes
	 */
	void initialiserRequetes();
	
	/**
	 * @param bestIndividu, un client appelle cette m�thode pour donner
	 * au maitre un de ces individus
	 */
	void recupererIndividu(IIndividu bestIndividu);	
	
	void setConditionArret(IConditionArret condition);
	
	IConditionArret getConditionArret();
	
	IPopulation getBestIndividus();
	
	void setBestIndividus(IPopulation newBest);
	
	/**
	 * @param requete, ajoute la requete en param�tre � la liste des requ�tes
	 */
	void ajouterRequete(IRequete requete);

	/**
	 * 
	 * @return la listes de toutes les requ�tes sur chacun des serveur envoy�es par le maitre
	 */
	List<IRequete> getListRequete();
	
	/**
	 * M�thode appel�e une fois que le maitre � satisfait sa condition 
	 * d'arr�t
	 */
	void operationFinale();
	
	
}
