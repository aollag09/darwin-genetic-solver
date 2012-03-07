package darwin.interfaces;

/**
 * @author Dim && Momo
 *	Permet d'effectuer une selection naturelle sur une g�n�ration
 */
public interface ISelectionNaturelle {
	
	/**
	 * 
	 * @return le type de selection utilis� en d�but de s�lection naturelle
	 */
	ISelection getSelectionInitiale();
	
	/**
	 * 
	 * @return le type de selection utilis� en fin de selection naturelle
	 */
	ISelection getSelectionFinale();
	
	/**
	 * 
	 * @return le type de mutation utilis�e
	 */
	IMutation getMutation();
	
	/**
	 * 
	 * @return le type de crossOver utilis�
	 */
	ICrossOver getCrossOver();
	
	/**
	 * 
	 * @return la population sur laquelle on effectue la selection
	 */
	IPopulation getPopulation();
	
	/**
	 * Met � jour la population gr�ce � une selection "naturelle"
	 */
	void nextGeneration();
}
