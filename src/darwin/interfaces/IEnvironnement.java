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
	 */
	double evaluerIndividu(IIndividu individu);
	
	/**
	 * 
	 * @param individu
	 * @return true si l'individu est valide dans cet environnement
	 */
	boolean isValid(IIndividu individu);
	
}
