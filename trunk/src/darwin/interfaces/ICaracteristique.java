/**
 * 
 */
package darwin.interfaces;

import java.util.BitSet;

/**
 * @author Momo && Dim
 *	R�pr�sente une caract�ristique cod�e par un unique g�ne, lors d'une instanciation 
 *	avec un �tat particulier, on obtient un all�le de ce g�ne. Une caract�ristique
 *	est clonable afin de pouvoir la modifier sans perdre l'originale.
 */
public interface ICaracteristique extends Cloneable {

	/**
	 * 
	 * @return le nom de la caract�ristique
	 */
	String getName();
	
	/**
	 * 
	 * @return Le Bitset codant la caract�ristique
	 */
	BitSet getBitSet(); 
	
	/**
	 * 
	 * @return le nombre de bits sur lesquels peut �tre cod�e la caract�ristique
	 */
	int getTailleBitSet();
	
	/**
	 * Met � jour la caract�ristique lors d'une modification du g�ne
	 */
	void update();
	
	/**
	 * 
	 * @return Une copie de la caract�ristique (et non une r�ference)
	 */
	ICaracteristique clone();
	
	/**
	 * 
	 * @param o
	 * @return true si les deux caracteristiques ont le m�me bitSet
	 */
	//boolean equals(Object o);
}
