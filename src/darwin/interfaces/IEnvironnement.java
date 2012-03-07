package darwin.interfaces;

/**
 * @author Dim && Momo
 *	Repr�sente un environnement, capable d'�valuer un individu. Il est essentiel de bien d�finir
 *	l'environnement associ� � un probl�me afin de pouvoir le r�soudre au mieux.
 */
public interface IEnvironnement {

	/**
	 * 
	 * @param individu
	 * @return un double correspondant � l'�valuation de l'individu
	 */
	double evaluerIndividu(IIndividu individu);
	
	/**
	 * 
	 * @param individu
	 * @return true si l'individu est valide dans cet environnement
	 */
	boolean isValid(IIndividu individu);
	
}
