package darwin.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Momo && Dim
 *	Représente un ensemble d'individu (de taille définie) plongés dans un environnement particulier.
 *	Les individus tout comme la population dont ils font partie peuvent être évalués relativement à
 *	cet environnement.
 */
public interface IPopulation extends Serializable {
	
	/**
	 * 
	 * @return Le nombre d'individu que la population doit en théorie contenir.
	 */
	int getTailleSouhaitee();
	
	/**
	 * 
	 * @return Le nombre d'individu que la population contient en réalité, ce
	 * 			nombre ne doit différer du nombre théorique que temporairement.
	 */
	int getTailleEffective();
	
	/**
	 * 
	 * @return L'ensemble des individus de la population sous forme de liste.
	 */
	List<IIndividu> getListIndividus();
	
	/**
	 * 
	 * @param individus L'ensemble des individus à placer dans la population.
	 */
	void setListIndividus(List<IIndividu> individus);
	
	/**
	 * 
	 * @param i L'index de l'individu à retourner
	 * @return L'individu à l'index i dans la population
	 */
	IIndividu getIndividu(int i);
	
	/**
	 * Ajoute un individu à une population déjà existante
	 * @param individu L'individu à ajouter
	 */
	void ajouterIndividu(IIndividu individu);
	
	/**
	 * 
	 * @return L'environnement auquel est liée la population
	 */
	IEnvironnement getEnvironnement();
	
	/**
	 * Associe un environnement à la population
	 * @param environnement L'environnement à associer à la population
	 */
	void setEnvironnement(IEnvironnement environnement);
	
	/**
	 * 
	 * @param individu L'individu à evaluer
	 * @return Un double correspondant à l'évaluation de l'individu dans l'environnement auquel est il est soumis
	 * @throws Exception Si l'individu n'est pas évaluable dans l'environnement actuel
	 */
	double evaluerIndividu(IIndividu individu) throws Exception;
	
	/**
	 * 
	 * @param index L'index de l'individu à évaluer
	 * @return L'évaluation de l'individu placé à l'index donné dans la population
	 * @throws Exception Si l'individu n'est pas évaluable dans l'environnement actuel
	 */
	double evaluerIndividu(int index) throws Exception;
	
	/**
	 * 
	 * @return L'évaluation de la population, qui en général est égale au cumul des évaluations des individus de la population
	 */
	double evaluerPopulation();
	
	/**
	 * 
	 * @return L'individu de la population qui a la meilleure évaluation
	 */
	IIndividu getBestIndividu();
	
	/**
	 * Génère une population au hasard
	 */
	void generer();
	
}
