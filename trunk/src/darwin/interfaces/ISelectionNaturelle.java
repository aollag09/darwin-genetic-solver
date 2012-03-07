package darwin.interfaces;

/**
 * @author Dim && Momo
 *	Permet d'effectuer une selection naturelle sur une génération
 */
public interface ISelectionNaturelle {
	
	/**
	 * 
	 * @return le type de selection utilisé en début de sélection naturelle
	 */
	ISelection getSelectionInitiale();
	
	/**
	 * 
	 * @return le type de selection utilisé en fin de selection naturelle
	 */
	ISelection getSelectionFinale();
	
	/**
	 * 
	 * @return le type de mutation utilisée
	 */
	IMutation getMutation();
	
	/**
	 * 
	 * @return le type de crossOver utilisé
	 */
	ICrossOver getCrossOver();
	
	/**
	 * 
	 * @return la population sur laquelle on effectue la selection
	 */
	IPopulation getPopulation();
	
	/**
	 * Met à jour la population grâce à une selection "naturelle"
	 */
	void nextGeneration();
}
