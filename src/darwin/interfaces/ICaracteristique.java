/**
 * 
 */
package darwin.interfaces;

import java.util.BitSet;

/**
 * @author Momo && Dim
 *	Réprésente une caractéristique codée par un unique gène, lors d'une instanciation 
 *	avec un état particulier, on obtient un allèle de ce gène. Une caractéristique
 *	est clonable afin de pouvoir la modifier sans perdre l'originale.
 */
public interface ICaracteristique extends Cloneable {

	/**
	 * 
	 * @return le nom de la caractéristique
	 */
	String getName();
	
	/**
	 * 
	 * @return Le Bitset codant la caractéristique
	 */
	BitSet getBitSet(); 
	
	/**
	 * 
	 * @return le nombre de bits sur lesquels peut être codée la caractéristique
	 */
	int getTailleBitSet();
	
	/**
	 * Met à jour la caractéristique lors d'une modification du gène
	 */
	void update();
	
	/**
	 * 
	 * @return Une copie de la caractéristique (et non une réference)
	 */
	ICaracteristique clone();
	
	/**
	 * 
	 * @param o
	 * @return true si les deux caracteristiques ont le même bitSet
	 */
	//boolean equals(Object o);
}
