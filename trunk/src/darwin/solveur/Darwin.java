package darwin.solveur;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IDarwin;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelectionNaturelle;

public class Darwin extends UnicastRemoteObject implements IDarwin, Serializable {

	/**
	 * Un identifiant pour être Marchable sur le réseau 
	 */
	private static final long serialVersionUID = -629791890658867547L;

	// VARIABLES D'INSTANCE
	/**
	 * La selection naturelle utilisée pour les itération
	 */
	protected ISelectionNaturelle selectionNaturelle;
	
	// VARIABLES D'INSTANCES D'AFFICHAGE
	protected boolean afficherChaqueGeneration, afficherChaqueScore, afficherIterations;
	
	/**
	 * La condition d'arrêt
	 */
	protected IConditionArret conditionArret;
	
	public Darwin(ISelectionNaturelle selectionNaturelle, IConditionArret conditionArret)
		throws RemoteException{
		this.selectionNaturelle = selectionNaturelle;
		this.setConditionArret(conditionArret);
		this.afficherChaqueGeneration(false);
		this.afficherChaqueScore(false);
		this.afficherIterations(true);
	}
	
	/**
	 * Constructeur Vide
	 */
	public Darwin() throws RemoteException {
		this.afficherChaqueGeneration(false);
		this.afficherChaqueScore(false);
		this.afficherIterations(true);
	}
	
	@Override
	public void setConditionArret(IConditionArret condition) {
		this.conditionArret = condition;
	}
	
	@Override
	public IConditionArret getConditionArret() {
		return this.conditionArret;
	}

	@Override
	public ISelectionNaturelle getSelectionNaturelle() {
		return this.selectionNaturelle;
	}


	@Override
	public IPopulation solve() {

		/* Affichage de la population initiale */
		System.out.println("-----------------------------------------------------");
		System.out.println("*****     DARWIN Genetic Solver version 1.0     *****");
		System.out.println("*****             By Momo and Dim'              *****");
		System.out.println("*****          Left-Handed power team           *****");
		System.out.println("-----------------------------------------------------" + "\n" + "\n");

		System.out.println("-----------------------------------------------------");
		System.out.println("*****          Population initiale :            *****");
		System.out.println("-----------------------------------------------------" + "\n");
		
		System.out.println(this.getSelectionNaturelle().getPopulation()+ "\n" + "Score : " +
					this.getSelectionNaturelle().getPopulation().evaluerPopulation() + "\n" + "\n");
		
		System.out.println("-----------------------------------------------------");
		System.out.println("*****   Demarrage de l'algorithme génétique     *****");
		System.out.println("-----------------------------------------------------" + "\n");

		int iterations = 0;
		
		while(this.getConditionArret() != null){
			
			/* Nouvelle génération */
			this.getSelectionNaturelle().nextGeneration();
			
			/* Verification de la condition d'arrêt */
			if(this.getConditionArret().isSatisfied(this.getSelectionNaturelle().getPopulation())){
				
				/* Application du nouvel environnement si besoin */
				if(this.getConditionArret().nextEnvironnement() != null){
					this.getSelectionNaturelle().getPopulation().setEnvironnement(this.getConditionArret().nextEnvironnement());
				}
				iterations = this.getConditionArret().getNombreIteration();
				this.setConditionArret(this.getConditionArret().nextConditionArret());
			}
			
			/* Affichages */
			if(this.afficherChaqueGeneration){
				System.out.println(this.getSelectionNaturelle().getPopulation());
			}
			if(this.afficherChaqueScore){
				System.out.println("Score : " + this.getSelectionNaturelle().getPopulation().evaluerPopulation());
			}
//			if(this.afficherIterations){
//				System.out.println("Itérations :" + iterations);
//			}
//			iterations ++;
		}
		
		System.out.println("-----------------------------------------------------");
		System.out.println("*****           Resolution terminée             *****");
		System.out.println("*****           Population finale :             *****");
		System.out.println("-----------------------------------------------------" + "\n");
		
		IPopulation popFinale = this.getSelectionNaturelle().getPopulation();
		
		System.out.println(popFinale + "\n" + "Score : " + popFinale.evaluerPopulation());
		if(this.afficherIterations){
			System.out.println("Après : " + iterations + " générations");
		}
		return popFinale;
	}
	
	public void afficherChaqueGeneration(boolean b){
		this.afficherChaqueGeneration = b;
	}
	
	public void afficherChaqueScore(boolean b){
		this.afficherChaqueScore = b;
	}
	
	public void afficherIterations(boolean b){
		this.afficherIterations = b;
	}

	@Override
	public void setSelectionNaturelle(ISelectionNaturelle newSelectionNaturelle) {
		this.selectionNaturelle = newSelectionNaturelle;		
	}
}
