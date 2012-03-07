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
import darwin.solveur.selections.SelectionRoulette;
import darwin.solveur.selections.SelectionTirageAleatoire;

/**
 * Selection naturelle standard : On selectionne m(<=taille de la population) individus, qu'on croise puis mute,
 * on ins�re les nouveaux individus obtenus au sein de la population et on r�effectue une selection afin de se 
 * ramener � la taille de population souhait�e.
 * @author Dim
 *
 */
public class SelectionNaturelleSimple extends SelectionNaturelle{

	// VARIABLES D'INSTANCE
	
	/**
	 * Le nombre de couple sur lesquel effectuer un crossOver d'une g�n�ration � l'autre
	 * Ce nombre peut �tre 
	 */
	protected int nombreCouples;
	
	/**
	 * Constructeur avec types de selection, crossOver et Mutation pr�d�finis
	 * @param pop La population sur laquelle effectuer la selection Naturelle
	 * @param nbIndividusSelInit Le nombre d'individus � choisir lors de la premiere selection
	 * @param nbCaracCross Le de caract�res � interchanger lors du crossOver
	 * @param pCross La probabilit� de crossOver
	 * @param nbBitMut Le nombre de bits � muter lors de la mutation 
	 * @param pMut La probabilit� de mutation
	 * @param nbCouples
	 * @throws Exception
	 */
	public SelectionNaturelleSimple(IPopulation pop, int nbIndividusSelInit,
			int nbCaracCross, double pCross, int nbBitMut, double pMut, int nbCouples) throws Exception{
		super(new SelectionRoulette(nbIndividusSelInit), new SelectionElitiste(pop.getTailleSouhaitee()),
				new CrossOverSimple(nbCaracCross, pCross), new MutationSimple(nbBitMut, pMut), pop);
		if(nbCouples < 0){
			System.out.println("Nombre de couples indiqu� < 0");
			throw new Exception();
		}
		else{
			this.nombreCouples = nbCouples;
		}	
	}
	
	/**
	 * Constructeur o� on d�finit manuellement toutes les �tapes de la selection, et le nombre de couples pour le crossOver
	 * @param selInit La selection initiale
	 * @param selFin La selection finale
	 * @param cross Le crossOver
	 * @param mut La mutation
	 * @param pop La population
	 * @param nbCouples Le nombre de couples
	 */
	public SelectionNaturelleSimple(ISelection selInit, ISelection selFin,
			ICrossOver cross, IMutation mut, IPopulation pop, int nbCouples) {
		super(selInit, selFin, cross, mut, pop);
		this.nombreCouples = nbCouples;
	}

	public int getNombreCouples() {
		return nombreCouples;
	}
	
	@Override
	public void nextGeneration() {
		
		/* SELECTION */
		List<IIndividu> selectionnes = new ArrayList<IIndividu>(this.getSelectionInitiale().selectionner(this.getPopulation()));
		List<IIndividu> nouveaux = new ArrayList<IIndividu>();
		
		/* CROSSOVER */
		if(this.crossOverPossible()){

			ISelection selectionCrossOver;
			try {
				selectionCrossOver = new SelectionTirageAleatoire(2);
				
				/* On effectue autant de crossOver qu'il y a de couple (on peut avoir plusieurs fois les m�mes couples, sachant qu'ils ne
				  donneront pas forc�ment les m�me individus enfants */
				for(int i = 0; i<this.getNombreCouples(); i++){
					
					/* Selection des individu pour le crossOver */
					List<IIndividu> selCross =selectionCrossOver.selectionner(this.getPopulation());
					List<IIndividu> croises = this.getCrossOver().crossOver(selCross.get(0), selCross.get(1));
					
					/* Ajout aux individus selectionn�s */
					nouveaux.addAll(croises);
				}
			} catch (Exception e) {
				System.out.println("Erreur dans l'initialisation de la selection des couples pour le crossOver");
				e.printStackTrace();
			}
			
		}
		/* MUTATION (Tous les selectionn�s y sont soumis)*/
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
