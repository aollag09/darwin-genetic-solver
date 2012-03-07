package darwin.interfaces;

/**
 * @author Momo && Dim
 * 	Permet de muter une ou plusieurs caract�ristiques d'un individu en agissant directement sur le bitSet
 */
public interface IMutation {

	/**
	 * 
	 * @param individu
	 * @return mute un individu et le retourne
	 */
	IIndividu muter(IIndividu individu);
	
	/**
	 * 
	 * @param individu
	 * @return true si il est possible de muter l'individu pass� en param�tre
	 */
	boolean mutationIndividuPossible(IIndividu individu);
	
	/**
	 * 
	 * @param caracteristique
	 * @return true si il est possible de muter la caract�ristique pass�e en param�tre
	 */
	boolean mutationCaracteristiquePossible(ICaracteristique caracteristique);
}
