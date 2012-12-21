package darwin.solveur;

import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IGenerationListener;
import darwin.interfaces.IStopCondition;
import darwin.interfaces.IDarwin;
import darwin.interfaces.IPopulation;
import darwin.interfaces.INaturalSelection;

public class Darwin implements IDarwin {

	/**
	 * The number of iterations
	 */
	protected int generations;

	// VARIABLES D'INSTANCE
	/**
	 * La selection naturelle utilisee pour les iterations
	 */
	protected INaturalSelection naturalSelection;

	/**
	 * The generation listeners
	 */
	protected List<IGenerationListener> listeners;

	// VARIABLES D'INSTANCES D'AFFICHAGE
	protected boolean afficherChaqueGeneration, afficherChaqueScoreTotal,
			afficherIterations, afficherPopulationInitiale,
			afficherPopulationFinale, afficherChaqueMeilleurScore;

	/**
	 * La condition d'arr�t
	 */
	protected IStopCondition stopCondition;

	public Darwin(INaturalSelection naturalSelection,
			IStopCondition stopCondition) {
		this.naturalSelection = naturalSelection;
		this.setStopCondition(stopCondition);
		this.showEachGeneration(false);
		this.showEachTotalScore(false);
		this.showIterations(true);
		this.showEachBestScore(true);
		this.listeners = new ArrayList<IGenerationListener>();
	}

	/**
	 * Constructeur Vide
	 */
	public Darwin() {
		this.showEachGeneration(false);
		this.showEachTotalScore(true);
		this.showIterations(true);
	}

	@Override
	public void setStopCondition(IStopCondition condition) {
		this.stopCondition = condition;
	}

	@Override
	public IStopCondition getStopCondition() {
		return this.stopCondition;
	}

	@Override
	public INaturalSelection getNaturalSelection() {
		return this.naturalSelection;
	}

	@Override
	public IPopulation solve() {

		/* Affichage de la population initiale */
		System.out
				.println("-----------------------------------------------------");
		System.out
				.println("*****     DARWIN Genetic Solver version 1.2     *****");
		System.out
				.println("*****             By Momo and Dim'              *****");
		System.out
				.println("*****          Left-Handed Development          *****");
		System.out
				.println("-----------------------------------------------------"
						+ "\n" + "\n");

		if (this.afficherPopulationInitiale) {
			System.out
					.println("-----------------------------------------------------");
			System.out
					.println("*****          Initial population :             *****");
			System.out
					.println("-----------------------------------------------------"
							+ "\n");

			System.out.println(this.getNaturalSelection().getPopulation()
					+ "\n"
					+ "Score : "
					+ this.getNaturalSelection().getPopulation()
							.evaluatePopulation() + "\n" + "\n");
		}

		double t1 = System.currentTimeMillis();

		System.out
				.println("-----------------------------------------------------");
		System.out
				.println("*****               Solving...                  *****");
		System.out
				.println("-----------------------------------------------------"
						+ "\n");

		this.generations = 0;

		while (this.getStopCondition() != null) {

			for (IGenerationListener listener : this.listeners) {
				listener.nextGenerationPerformed(this.getNaturalSelection()
						.getPopulation());
			}

			/* Nouvelle g�n�ration */
			this.getNaturalSelection().nextGeneration();

			/* Verification de la condition d'arr�t */
			if (this.getStopCondition().isSatisfied(
					this.getNaturalSelection().getPopulation())) {

				/* Application du nouvel environnement si besoin */
				if (this.getStopCondition().nextEnvironnement() != null) {
					this.getNaturalSelection()
							.getPopulation()
							.setEnvironment(
									this.getStopCondition().nextEnvironnement());
				}
				// iterations = this.getConditionArret().getNombreIteration();
				this.setStopCondition(this.getStopCondition()
						.nextStopCondition());
			}

			/* Affichages */
			if (this.afficherChaqueGeneration) {
				System.out.println(this.getNaturalSelection().getPopulation());
			}
			if (this.afficherChaqueScoreTotal) {
				System.out.println("Total score : "
						+ this.getNaturalSelection().getPopulation()
								.evaluatePopulation());
			}
			if (this.afficherChaqueMeilleurScore) {
				try {
					System.out.println("Best score : "
							+ this.getNaturalSelection()
									.getPopulation()
									.evaluateBeing(
											this.getNaturalSelection()
													.getPopulation()
													.getBestIndividu()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (this.afficherIterations) {
				System.out.println("Iterations :" + this.generations);
			}
			this.generations++;
		}

		System.out.println("\n"
				+ "-----------------------------------------------------");
		System.out
				.println("*****            Solving completed              *****");
		System.out
				.println("-----------------------------------------------------"
						+ "\n");
		System.out.println("***** After " + (System.currentTimeMillis() - t1)
				/ 1000 + " seconds and " + this.generations + " generations"
				+ " ****** " + "\n");

		IPopulation popFinale = this.getNaturalSelection().getPopulation();

		if (this.afficherPopulationFinale) {
			System.out
					.println("-----------------------------------------------------");
			System.out
					.println("*****           final Population  :             *****");
			System.out
					.println("-----------------------------------------------------");

			System.out.println(popFinale + "\n" + "Score : "
					+ popFinale.evaluatePopulation());
		}

		return popFinale;
	}

	public void showEachGeneration(boolean b) {
		this.afficherChaqueGeneration = b;
	}

	public void showEachTotalScore(boolean b) {
		this.afficherChaqueScoreTotal = b;
	}

	public void showEachBestScore(boolean b) {
		this.afficherChaqueMeilleurScore = b;
	}

	public void showIterations(boolean b) {
		this.afficherIterations = b;
	}

	public void showInitialPopulation(boolean b) {
		this.afficherPopulationInitiale = b;
	}

	public void showFinalPopulation(boolean b) {
		this.afficherPopulationFinale = b;
	}

	public int getGenerations() {
		return this.generations;
	}

	@Override
	public void setNaturalSelection(INaturalSelection newSelectionNaturelle) {
		this.naturalSelection = newSelectionNaturelle;
	}

	@Override
	public void addGenerationListener(IGenerationListener listener) {
		this.listeners.add(listener);
	}
}
