package darwin.modele;

import java.util.List;

import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;

/** 
 * @author Dim && Momo
 *	Implémentation partielle et abstraite de ISelection
 */
public abstract class Selection implements ISelection {

	// VARIABLES D'INSTANCE
	/**
	 * Le nombre d'individus à garder lors d'une selection (>=1)
	 */
	protected int nbIndivus;
	
	/**
	 * Constructeur basique
	 * @param nbIndividus Le nombre d'individus à selectionner
	 * @throws Exception Si ce nombre < 1
	 */
	protected Selection(int nbIndividus) throws Exception{
		if(nbIndividus < 1){
			System.out.println("Nombre d'individus à selectionner < 1");
			throw new Exception();
		}
		else{
			this.nbIndivus = nbIndividus;
		}	
	}
	
	@Override
	public boolean selectionPossible(IPopulation population) {
		return (this.nbIndivus <= population.getTailleEffective());
	}

	@Override
	public abstract List<IIndividu> selectionner(IPopulation population);

}
