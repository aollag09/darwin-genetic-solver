package parallelisation.client;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IDarwin;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import parallelisation.interfaces.IMaitre;
import parallelisation.interfaces.IRequete;

public abstract class Maitre implements IMaitre {


	private static final long serialVersionUID = 1L;

	// CONSTANTES :
	public static final String CHEMIN_RESEAU = "rmi//localhost//Serveur";

	//VARIABLES D'INSTANCES : 
	
	/** L'ensemble des meilleurs individus récupérés avec les générations sur les serveurs */
	protected IPopulation bestIndividus;
	
	/** Le nombre de serveurs disponnibles */
	protected int nombreServeurs;
	
	/** La condition d'arrêt à vérifier */
	protected IConditionArret conditonArret;
	
	/** La liste de toutes les requêtes */
	protected List<IRequete> listRequetes;
	
	
	public Maitre() {
		nombreServeurs = getNombreServeurs();
		listRequetes = new ArrayList<IRequete>();
		conditonArret = new IConditionArret() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public IEnvironnement nextEnvironnement() {
				return null;
			}
			
			@Override
			public IConditionArret nextConditionArret() {
				return null;
			}
			
			@Override
			public boolean isSatisfied(IPopulation population) {
				return population.getListIndividus().size() == nombreServeurs;
			}
			
			@Override
			public int getNombreIteration() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}


	/**
	 * @param ind, l'individu à récupérer après traitement de la requête
	 */
	public void recupererIndividu(IIndividu ind){
		this.bestIndividus.ajouterIndividu(ind);
	}

	public static int getNombreServeurs(){
		boolean erreur = false;
		int indexServeur = 0;
		while( ! erreur ){
			indexServeur ++;
			try { 
				String adresse = CHEMIN_RESEAU+indexServeur;
				@SuppressWarnings("unused")
				IDarwin darwin = (IDarwin)Naming.lookup(adresse); 
			}
			catch (Exception e) { 
				erreur = true;
			} 
		}
		return indexServeur-1;
	}
	
	@Override
	public List<IRequete> getListRequete() {
		return this.listRequetes;
	}
	
	@Override
	public void ajouterRequete(IRequete requete) {
		this.listRequetes.add(requete);
	}

	@Override
	public IPopulation getBestIndividus() {
		return this.bestIndividus;
	}
	
	@Override
	public IConditionArret getConditionArret() {
		return conditonArret;
	}
	
	@Override
	public void setConditionArret(IConditionArret condition) {
		this.conditonArret = condition;
	}
	
	@Override
	public void setBestIndividus(IPopulation newBest) {
		bestIndividus = newBest;
	}
	
	@Override
	public void lancerRequetes() {
		for(IRequete r : listRequetes)
			r.lancerRequete();
	}

	
}

