package darwin.interfaces;

/**
 * @author Dim && Momo
 *	Représente un environnement, capable d'évaluer un individu. Il est essentiel de bien définir
 *	l'environnement associé à un problème afin de pouvoir le résoudre au mieux.
 */
public interface IEnvironnement {

	/**
	 * 
	 * @param individu
	 * @return un double correspondant à l'évaluation de l'individu
	 * @throws Exception Si l'individu ne peut pas être évalué
	 */
	double evaluerIndividu(IIndividu individu) throws Exception;
	
	/**
	 * 
	 * @param individu
	 * @return true si l'individu est valide et évaluable dans cet environnement
	 */
	boolean isValid(IIndividu individu);
	
}
