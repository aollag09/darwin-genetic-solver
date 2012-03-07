package darwin.modele;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IMutation;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;
import darwin.interfaces.ISelectionNaturelle;

/** 
 * @author Momo && Dim
 *	Implémentation partielle et abstraite de ISelectionNaturelle
 */
public abstract class SelectionNaturelle implements ISelectionNaturelle {

	// VARIABLES D'INSTANCES

	/**
	 * La selection qui selectionne les individus à croiser et à muter
	 */
	protected ISelection selectionInitiale;
	
	/**
	 * La selection qui selectionne les individus à garder dans la population d'une génération à l'autre
	 */
	protected ISelection selectionFinale;

	/** L'objet CrossOver qui permet de croiser certains individus */
	protected ICrossOver crossOver;

	/** L'objet de Mutation qui permet de générer de tout nouveaux individus en modifiant leur code génétique */
	protected IMutation mutation;
	
	/** La population sur laquelle la Selection Naturelle va s'effectuer */
	protected IPopulation population;
	
	protected SelectionNaturelle(ISelection selInit, ISelection selFin, ICrossOver cross, IMutation mut, IPopulation pop){
		this.selectionInitiale = selInit;
		this.selectionFinale = selFin;
		this.crossOver = cross;
		this.mutation = mut;
		this.population = pop;
	}
	
	@Override
	public ICrossOver getCrossOver() {
		return this.crossOver;
	}

	@Override
	public IMutation getMutation() {
		return this.mutation;
	}

	@Override
	public IPopulation getPopulation() {
		return this.population;
	}

	@Override
	public ISelection getSelectionFinale() {
		return this.selectionFinale;
	}

	@Override
	public ISelection getSelectionInitiale() {
		return this.selectionInitiale;
	}

	@Override
	public abstract void nextGeneration();
	
	/**
	 * 
	 * @return true si il y a au moins deux individus dans la population, ie que le
	 * crossOver peut se faire
	 */
	protected boolean crossOverPossible(){
		return this.getPopulation().getTailleEffective()>=2;
	}
}
