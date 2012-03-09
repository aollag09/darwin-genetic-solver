package darwin.solveur;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.ICrossOver;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IMutation;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelection;
import darwin.modele.SelectionNaturelle;
import darwin.solveur.crossovers.CrossOverSimple;
import darwin.solveur.mutations.MutationSimple;
import darwin.solveur.selections.SelectionElitiste;
import darwin.solveur.selections.SelectionTirageAleatoire;
import darwin.solveur.selections.SelectionTournoi;

/**
 * Selection naturelle standard : On selectionne m(<=taille de la population) individus, qu'on croise puis mute,
 * on insère les nouveaux individus obtenus au sein de la population et on réeffectue une selection afin de se 
 * ramener à la taille de population souhaitée.
 * @author Dim
 *
 */
public class SelectionNaturelleSimple extends SelectionNaturelle{

	// VARIABLES D'INSTANCE
	
	/**
	 * Le nombre de couple sur lesquel effectuer un crossOver d'une génération à l'autre
	 * Ce nombre peut être 
	 */
	protected int nombreCouples;
	
	/**
	 * La probabité d'apparition d'individus completement nouveaux, doit être comprise entre 0 et 1
	 */
	protected double probImmigration;
	
	/**
	 * Constructeur avec types de selection, crossOver et Mutation prédéfinis
	 * @param pop La population sur laquelle effectuer la selection Naturelle
	 * @param nbIndividusSelInit Le nombre d'individus à choisir lors de la premiere selection
	 * @param nbCaracCross Le de caractères à interchanger lors du crossOver
	 * @param pCross La probabilité de crossOver
	 * @param nbBitMut Le nombre de bits à muter lors de la mutation 
	 * @param pMut La probabilité de mutation
	 * @param nbCouples Le nombre de couples à former lors du crossOver
	 * @param probIm La probabilité d'immigration
	 * @throws Exception
	 */
	public SelectionNaturelleSimple(IPopulation pop, int nbIndividusSelInit,
			int nbCaracCross, double pCross, int nbBitMut, double pMut, int nbCouples, double probIm) throws Exception{
		this(new SelectionTournoi(nbIndividusSelInit), new SelectionElitiste(pop.getTailleSouhaitee()),
				new CrossOverSimple(nbCaracCross, pCross), new MutationSimple(nbBitMut, pMut), pop, nbCouples, probIm);
	}
	
	/**
	 * Constructeur où on définit manuellement toutes les étapes de la selection, et le nombre de couples pour le crossOver
	 * @param selInit La selection initiale
	 * @param selFin La selection finale
	 * @param cross Le crossOver
	 * @param mut La mutation
	 * @param pop La population
	 * @param nbCouples Le nombre de couples
	 * @param probImmigration La probabilité d'immigration
	 */
	public SelectionNaturelleSimple(ISelection selInit, ISelection selFin,
			ICrossOver cross, IMutation mut, IPopulation pop, int nbCouples, double probImmigration) throws Exception{
		super(selInit, selFin, cross, mut, pop);
		if(nbCouples < 0){
			System.out.println("Nombre de couples indiqué < 0");
			throw new Exception();
		}
		else{
			this.nombreCouples = nbCouples;
		}	
		if(probImmigration<0 || probImmigration>1){
			System.out.println("Probabilité d'immigration non comprise entre 0 et 1");
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
			List<IIndividu> actuels = new ArrayList<IIndividu>();
			for(IIndividu i : this.getPopulation().getListIndividus()){
				actuels.add(i.clone());
			}
			this.getPopulation().generer();
			for(IIndividu i : this.getPopulation().getListIndividus()){
				i.setName(i.getName() + "-Immigré");
			}
			for(IIndividu i : actuels){
				this.getPopulation().ajouterIndividu(i);
			}	
		}
		
		/* SELECTION */
		List<IIndividu> selectionnes = new ArrayList<IIndividu>(this.getSelectionInitiale().selectionner(this.getPopulation()));
		List<IIndividu> nouveaux = new ArrayList<IIndividu>();
		
		/* CROSSOVER */
		if(this.crossOverPossible()){

			ISelection selectionCrossOver;
			try {
				selectionCrossOver = new SelectionTirageAleatoire(2);
				
				/* On effectue autant de crossOver qu'il y a de couple (on peut avoir plusieurs fois les mêmes couples, sachant qu'ils ne
				  donneront pas forcément les même individus enfants */
				for(int i = 0; i<this.getNombreCouples(); i++){
					
					/* Selection des individu pour le crossOver */
					List<IIndividu> selCross =selectionCrossOver.selectionner(this.getPopulation());
					List<IIndividu> croises = this.getCrossOver().crossOver(selCross.get(0), selCross.get(1));
					
					/* Ajout aux individus selectionnés */
					nouveaux.addAll(croises);
				}
			} catch (Exception e) {
				System.out.println("Erreur dans l'initialisation de la selection des couples pour le crossOver");
				e.printStackTrace();
			}
			
		}
		/* MUTATION (Tous les selectionnés y sont soumis)*/
		for(IIndividu i : selectionnes){
			nouveaux.add(this.getMutation().muter(i));
		}
		
		/* AJOUT DANS LA POPULATION */
		
		for(IIndividu i : nouveaux){
			if(!this.getPopulation().getListIndividus().contains(i)){
				this.getPopulation().ajouterIndividu(i);
			}
		}
		System.out.println();
		/* SELECTION */
		this.getPopulation().setListIndividus(this.selectionFinale.selectionner(this.getPopulation()));
	}

}
