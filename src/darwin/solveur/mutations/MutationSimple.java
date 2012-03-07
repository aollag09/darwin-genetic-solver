package darwin.solveur.mutations;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IIndividu;
import darwin.modele.Mutation;

/**
 * Permet de r�aliser une mutation simple : Pour toutes les caract�ristiques de l'individu, 
 * on change l'�tat d'un nombre d�fini de bits du bitSet avec une probabilit� p.
 * @author Dim
 *
 */
public class MutationSimple extends Mutation{

	// CONSTANTES
	/**
	 * Le nombre de bits � muter par d�faut
	 */
	protected static final int NOMBRE_BIT_A_MUTER_DEFAUT = 1;
	
	/**
	 * La probabilit� de mutation par d�faut
	 */
	protected static final double PROBABILITE_DEFAUT = 0.1;
	
	/**
	 * Constructeur par d�faut
	 * @throws Exception
	 */
	public MutationSimple() throws Exception{
		super(NOMBRE_BIT_A_MUTER_DEFAUT, PROBABILITE_DEFAUT);
	}
	
	/**
	 * Constructeur h�rit�
	 * @param nbBitAMuter
	 * @param prob
	 * @throws Exception
	 */
	public MutationSimple(int nbBitAMuter, double prob) throws Exception {
		super(nbBitAMuter, prob);
	}

	@Override
	public IIndividu muter(IIndividu individu) {
		boolean mutant = false;
		IIndividu nIndividu = individu.clone();
		if(this.mutationIndividuPossible(nIndividu)){
			for(ICaracteristique c : nIndividu.getListCaracteristique()){
				boolean b = this.muterCaracteristique(c);
				mutant = mutant || b;
			}
		}
		if(mutant){
				nIndividu.setName(nIndividu.getName() + "-mutant");
		}
		return nIndividu;
	}
	
	/**
	 * 
	 * @param caracteristique
	 * @return true si la caract�ristique a �t� mut�e
	 */
	protected boolean muterCaracteristique(ICaracteristique caracteristique){
		boolean retour = false;
		if(this.mutationCaracteristiquePossible(caracteristique) && this.doMutation()){
			for(int i=0; i<this.nbBitAMuter; i++){
				int bit = (int) (Math.random()*caracteristique.getTailleBitSet());
				caracteristique.getBitSet().flip(bit);
				caracteristique.update();
			}
			retour = true;
			
		}
		return retour;
	}

}
