package darwin.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Momo && Dim
 *	Repr�sente un ensemble d'individu (de taille d�finie) plong�s dans un environnement particulier.
 *	Les individus tout comme la population dont ils font partie peuvent �tre �valu�s relativement �
 *	cet environnement.
 */
public interface IPopulation extends Serializable {
	
	/**
	 * 
	 * @return Le nombre d'individu que la population doit en th�orie contenir.
	 */
	int getTailleSouhaitee();
	
	/**
	 * 
	 * @return Le nombre d'individu que la population contient en r�alit�, ce
	 * 			nombre ne doit diff�rer du nombre th�orique que temporairement.
	 */
	int getTailleEffective();
	
	/**
	 * 
	 * @return L'ensemble des individus de la population sous forme de liste.
	 */
	List<IIndividu> getListIndividus();
	
	/**
	 * 
	 * @param individus L'ensemble des individus � placer dans la population.
	 */
	void setListIndividus(List<IIndividu> individus);
	
	/**
	 * 
	 * @param i L'index de l'individu � retourner
	 * @return L'individu � l'index i dans la population
	 */
	IIndividu getIndividu(int i);
	
	/**
	 * Ajoute un individu � une population d�j� existante
	 * @param individu L'individu � ajouter
	 */
	void ajouterIndividu(IIndividu individu);
	
	/**
	 * 
	 * @return L'environnement auquel est li�e la population
	 */
	IEnvironnement getEnvironnement();
	
	/**
	 * Associe un environnement � la population
	 * @param environnement L'environnement � associer � la population
	 */
	void setEnvironnement(IEnvironnement environnement);
	
	/**
	 * 
	 * @param individu L'individu � evaluer
	 * @return Un double correspondant � l'�valuation de l'individu dans l'environnement auquel est il est soumis
	 * @throws Exception Si l'individu n'est pas �valuable dans l'environnement actuel
	 */
	double evaluerIndividu(IIndividu individu) throws Exception;
	
	/**
	 * 
	 * @param index L'index de l'individu � �valuer
	 * @return L'�valuation de l'individu plac� � l'index donn� dans la population
	 * @throws Exception Si l'individu n'est pas �valuable dans l'environnement actuel
	 */
	double evaluerIndividu(int index) throws Exception;
	
	/**
	 * 
	 * @return L'�valuation de la population, qui en g�n�ral est �gale au cumul des �valuations des individus de la population
	 */
	double evaluerPopulation();
	
	/**
	 * 
	 * @return L'individu de la population qui a la meilleure �valuation
	 */
	IIndividu getBestIndividu();
	
	/**
	 * G�n�re une population au hasard
	 */
	void generer();
	
}
