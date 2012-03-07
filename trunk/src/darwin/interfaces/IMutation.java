package darwin.interfaces;

/**
 * @author Momo && Dim
 * 	Permet de muter une ou plusieurs caractéristiques d'un individu en agissant directement sur le bitSet
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
	 * @return true si il est possible de muter l'individu passé en paramètre
	 */
	boolean mutationIndividuPossible(IIndividu individu);
	
	/**
	 * 
	 * @param caracteristique
	 * @return true si il est possible de muter la caractéristique passée en paramètre
	 */
	boolean mutationCaracteristiquePossible(ICaracteristique caracteristique);
}
