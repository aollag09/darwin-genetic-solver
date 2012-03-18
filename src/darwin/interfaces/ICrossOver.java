package darwin.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dim && Momo
 *	Permet de croiser deux individus
 */
public interface ICrossOver extends Serializable{

	/**
	 * 
	 * @param individu1
	 * @param individu2
	 * @return effectue un crossOver � partir des deux individus pass�s en param�tre et renvoie les individus
	 * 	obtenus sous forme de liste
	 */
	List<IIndividu> crossOver(IIndividu individu1, IIndividu individu2);
}
