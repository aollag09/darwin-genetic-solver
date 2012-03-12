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
	 * @throws Exception Si l'individu ne peut pas �tre �valu�
	 */
	double evaluerIndividu(IIndividu individu) throws Exception;
	
	/**
	 * 
	 * @param individu
	 * @return true si l'individu est valide et �valuable dans cet environnement
	 */
	boolean isValid(IIndividu individu);
	
}
