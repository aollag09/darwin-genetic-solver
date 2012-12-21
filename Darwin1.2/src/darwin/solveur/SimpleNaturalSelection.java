package darwin.solveur;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IBeing;
import darwin.interfaces.IMutation;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;
import darwin.model.NaturalSelection;
import darwin.solveur.crossovers.CrossOverSimple;
import darwin.solveur.mutations.MutationSimple;
import darwin.solveur.selections.SelectionElitist;
import darwin.solveur.selections.SelectionRandom;
import darwin.solveur.selections.SelectionTournament;

/**
 * Selection naturelle standard : On selectionne m(<=taille de la population) individus, qu'on croise puis mute,
 * on ins�re les nouveaux individus obtenus au sein de la population et on r�effectue une selection afin de se 
 * ramener � la taille de population souhait�e.
 * @author Dim
 *
 */
public class SimpleNaturalSelection extends NaturalSelection{

	// VARIABLES D'INSTANCE

	/**
	 * Le nombre de couple sur lesquel effectuer un crossOver d'une g�n�ration � l'autre
	 * Ce nombre peut �tre 
	 */
	protected int nombreCouples;
	
	/**
	 * La probabit� d'apparition d'individus completement nouveaux, doit �tre comprise entre 0 et 1
	 */
	protected double probImmigration;
	
	/**
	 * Constructeur avec types de selection, crossOver et Mutation pr�d�finis
	 * @param pop La population sur laquelle effectuer la selection Naturelle
	 * @param nbIndividusSelInit Le nombre d'individus � choisir lors de la premiere selection
	 * @param nbCaracCross Le de caract�res � interchanger lors du crossOver
	 * @param pCross La probabilit� de crossOver
	 * @param nbBitMut Le nombre de bits � muter lors de la mutation 
	 * @param pMut La probabilit� de mutation
	 * @param nbCouples Le nombre de couples � former lors du crossOver
	 * @param probIm La probabilit� d'immigration
	 * @throws Exception
	 */
	public SimpleNaturalSelection(IPopulation pop, int nbIndividusSelInit,
			int nbCaracCross, double pCross, int nbBitMut, double pMut, int nbCouples, double probIm) throws Exception{
		this(new SelectionTournament(nbIndividusSelInit), new SelectionElitist(pop.getWishedSize()),
				new CrossOverSimple(nbCaracCross, pCross), new MutationSimple(nbBitMut, pMut), pop, nbCouples, probIm);
	}
	
	/**
	 * Constructeur o� on d�finit manuellement toutes les �tapes de la selection, et le nombre de couples pour le crossOver
	 * @param selInit La selection initiale
	 * @param selFin La selection finale
	 * @param cross Le crossOver
	 * @param mut La mutation
	 * @param pop La population
	 * @param nbCouples Le nombre de couples
	 * @param probImmigration La probabilit� d'immigration
	 */
	public SimpleNaturalSelection(ISelection selInit, ISelection selFin,
			ICrossOver cross, IMutation mut, IPopulation pop, int nbCouples, double probImmigration) throws Exception{
		super(selInit, selFin, cross, mut, pop);
		if(nbCouples < 0){
			System.out.println("Nombre de couples indiqu� < 0");
			throw new Exception();
		}
		else{
			this.nombreCouples = nbCouples;
		}	
		if(probImmigration<0 || probImmigration>1){
			System.out.println("Probabilit� d'immigration non comprise entre 0 et 1");
			throw new Exception();
		}
		else{
			this.probImmigration = probImmigration;
		}	
	}

	public int getNombreCouples() {
		return nombreCouples;
	}
	
	@Override
	public void nextGeneration() {
		
		/* IMMIGRATION */
		
		boolean doImmigration = false;
		if(this.probImmigration == 1){
			doImmigration = true;
		}
		else{
			double d = Math.random();
			if(d<probImmigration){
				doImmigration = true;
			}
		}
		
		if(doImmigration){
			List<IBeing> actuels = new ArrayList<IBeing>();
			for(IBeing i : this.getPopulation().getBeingsList()){
				actuels.add(i.clone());
			}
			this.getPopulation().generate();
			for(IBeing i : this.getPopulation().getBeingsList()){
				i.setName(i.getName() + "-Immigr�");
			}
			for(IBeing i : actuels){
				this.getPopulation().addBeing(i);
			}	
		}
		
		/* SELECTION */
		List<IBeing> selectionnes = new ArrayList<IBeing>(this.getFirstSelection().select(this.getPopulation()));
		List<IBeing> nouveaux = new ArrayList<IBeing>();
		
		/* CROSSOVER */
		if(this.crossOverPossible()){

			ISelection selectionCrossOver;
			try {
				selectionCrossOver = new SelectionRandom(2);
				
				/* On effectue autant de crossOver qu'il y a de couple (on peut avoir plusieurs fois les m�mes couples, sachant qu'ils ne
				  donneront pas forc�ment les m�me individus enfants */
				for(int i = 0; i<this.getNombreCouples(); i++){
					
					/* Selection des individu pour le crossOver */
					List<IBeing> selCross =selectionCrossOver.select(this.getPopulation());
					List<IBeing> croises = this.getCrossOver().crossOver(selCross.get(0), selCross.get(1));
					
					/* Ajout aux individus selectionn�s */
					nouveaux.addAll(croises);
				}
			} catch (Exception e) {
				System.out.println("Erreur dans l'initialisation de la selection des couples pour le crossOver");
				e.printStackTrace();
			}
			
		}
		/* MUTATION (Tous les selectionn�s y sont soumis)*/
		for(IBeing i : selectionnes){
			nouveaux.add(this.getMutation().mute(i));
		}
		
		/* AJOUT DANS LA POPULATION */
		
		for(IBeing i : nouveaux){
			if(!this.getPopulation().getBeingsList().contains(i)){
				this.getPopulation().addBeing(i);
			}
		}
		/* SELECTION */
		this.getPopulation().setBeingsList(this.finalSelection.select(this.getPopulation()));
	}

}
