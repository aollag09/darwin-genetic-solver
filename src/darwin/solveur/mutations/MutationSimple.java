package darwin.solveur.mutations;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IIndividu;
import darwin.modele.Mutation;

/**
 * Permet de réaliser une mutation simple : Pour toutes les caractéristiques de l'individu, 
 * on change l'état d'un nombre défini de bits du bitSet avec une probabilité p.
 * @author Dim
 *
 */
public class MutationSimple extends Mutation{

	// VARIABLES D'INSTANCE
	/**
	 * le nombre de bits à muter dans une caractéristique
	 */
	protected int nbBitAMuter;
	
	
	// CONSTANTES
	/**
	 * Le nombre de bits à muter par défaut
	 */
	protected static final int NOMBRE_BIT_A_MUTER_DEFAUT = 1;
	
	/**
	 * La probabilité de mutation par défaut
	 */
	protected static final double PROBABILITE_DEFAUT = 0.1;
	
	/**
	 * Constructeur par défaut
	 * @throws Exception
	 */
	public MutationSimple() throws Exception{
		super(PROBABILITE_DEFAUT);
		this.nbBitAMuter = NOMBRE_BIT_A_MUTER_DEFAUT;
	}
	
	/**
	 * Constructeur hérité
	 * @param nbBitAMuter
	 * @param prob
	 * @throws Exception
	 */
	public MutationSimple(int nbBitAMuter, double prob) throws Exception {
		super(prob);
		this.nbBitAMuter = nbBitAMuter;
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
				return nIndividu;
		}
		else{
			return nIndividu;
		}
	}
	
	/**
	 * 
	 * @param caracteristique
	 * @return true si la caractéristique a été mutée
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

	public boolean mutationCaracteristiquePossible(ICaracteristique caracteristique){
		return (caracteristique.getTailleBitSet() > nbBitAMuter);
	}

}
