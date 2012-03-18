package darwin.solveur.mutations;

import java.io.Serializable;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IIndividu;
import darwin.modele.Mutation;

public class MutationChemin extends Mutation implements Serializable {

	private static final long serialVersionUID = -751605708947382909L;

	/**
	 * Constructeur Simple
	 * @param probabilite d'effectuer la mutation
	 */
	public MutationChemin(double probabilite) {
		super.probabilite = probabilite;
	}
	
	@Override
	public IIndividu muter(IIndividu individu) {
		/* Le nombre de caratères que l'on décide du muter */
		int nombreCaractereAMuter = (int)(Math.random()*individu.getNombreCaracteristiques());
		IIndividu nIndividu = individu.clone();
		if(this.mutationIndividuPossible(nIndividu)){
			for(int i = 0; i< nombreCaractereAMuter; i++){
				/* On choisi aléatoirement le caractère à muter */

				int caracAMuter = (int) (Math.random())* (individu.getNombreCaracteristiques() - 1);
				int newPosition = (int) (Math.random())* (individu.getNombreCaracteristiques() - 1);
				ICaracteristique c1 = individu.getCaracteristique(caracAMuter);
				ICaracteristique c2 = individu.getCaracteristique(newPosition);
				individu.getListCaracteristique().set(newPosition,c1);
				individu.getListCaracteristique().set(caracAMuter,c2);
			}
		}
		nIndividu.setName(nIndividu.getName() + "-mutant");
		return nIndividu;
	}

	@Override
	public boolean mutationCaracteristiquePossible(
			ICaracteristique caracteristique) {
		return true;
	}

	@Override
	public boolean mutationIndividuPossible(IIndividu individu) {
		return individu.getNombreCaracteristiques()>1;
	}

}
