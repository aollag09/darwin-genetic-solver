package darwin.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dim && Momo
 *	Repr�sente un individu, compos� de diff�rentes caract�ristiques, tout comme une caract�ristique,
 *	un individu est clonable.
 */
public interface IIndividu extends Cloneable,Serializable{

	/**
	 * 
	 * @return Un Integer caract�risant le type de l'individu
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
	 * @return L'ensemble des caract�ristiques propres � l'individu
 	 */
	List<ICaracteristique> getListCaracteristique();
	
	/**
	 * D�finit la liste de caract�ristique donn�e en param�tre comme �tant
	 * le g�nome de l'individu
	 */
	void setListCaracteristiques(List<ICaracteristique> caracs);
	
	/**
	 * 
	 * @param index
	 * @return La caract�ristique plac�e � l'index donn�e en param�tre
	 */
	ICaracteristique getCaracteristique(int index);
	
	/**
	 * 
	 * @return Le nombre de caract�ristiques d�finissant l'individu
 	 */
	int getNombreCaracteristiques();
	
	/**
	 * 
	 * @return Une copie de l'individu (et non une r�ference)
	 */
	IIndividu clone();

	/**
	 * 
	 * @param o
	 * @return true si les deux individus ont le m�me code g�n�tique (ie les m�mes caract�ristiques)
	 */
	//boolean equals(Object o);
}
