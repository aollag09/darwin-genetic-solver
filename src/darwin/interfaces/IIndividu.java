package darwin.interfaces;

import java.util.List;

/**
 * @author Dim && Momo
 *	Représente un individu, composé de différentes caractéristiques, tout comme une caractéristique,
 *	un individu est clonable.
 */
public interface IIndividu extends Cloneable{

	/**
	 * 
	 * @return Un Integer caractérisant le type de l'individu
	 */
	Integer getType();
	
	/**
	 * 
	 * @return Le nom de l'individu
	 */
	String getName();
	
	/**
	 * Permet de changer le nom d'un individu
	 * @param name
	 */
	void setName(String name);
	
	/**
	 * 
	 * @return L'ensemble des caractéristiques propres à l'individu
 	 */
	List<ICaracteristique> getListCaracteristique();
	
	/**
	 * 
	 * @param index
	 * @return La caractéristique placée à l'index donnée en paramètre
	 */
	ICaracteristique getCaracteristique(int index);
	
	/**
	 * 
	 * @return Le nombre de caractéristiques définissant l'individu
 	 */
	int getNombreCaracteristiques();
	
	/**
	 * 
	 * @return Une copie de l'individu (et non une réference)
	 */
	IIndividu clone();

	/**
	 * 
	 * @param o
	 * @return true si les deux individus ont le même code génétique (ie les mêmes caractéristiques)
	 */
	//boolean equals(Object o);
}
