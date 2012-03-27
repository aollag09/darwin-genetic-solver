package parallelisation.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import parallelisation.interfaces.IConditionArretMaitre;
import parallelisation.interfaces.IListeServeur;
import parallelisation.interfaces.IMaitre;
import parallelisation.interfaces.IRequete;
import parallelisation.registry.ListeServeur;

public abstract class Maitre implements IMaitre {


	private static final long serialVersionUID = 1L;

	// CONSTANTES :
	
	
	public static String ADRESSE_IP = "172.17.2.13";
	public static String PORT = "1099";
	public static String CHEMIN_RESEAU = "rmi//"+ADRESSE_IP+":"+PORT+"//Serveur";
	public static String CHEMIN_RESEAU_REGISTRE = "rmi//"+ADRESSE_IP;
	public static String CHEMIN_RESEAU_REGISTRE_WITH_PORT = "rmi//"+ADRESSE_IP+":"+PORT;
	public static String CHEMIN_RESEAU_REGISTRE_SERVEURS = CHEMIN_RESEAU_REGISTRE_WITH_PORT + "//Liste";

	//VARIABLES D'INSTANCES : 
	
	/** L'ensemble des meilleurs individus récupérés avec les générations sur les serveurs */
	protected IPopulation bestIndividus;
	
	/** La liste des chemin réseau de tous les serveurs actuellement lancés */
	protected IListeServeur listServeurs;
	
	/** La condition d'arrêt à vérifier */
	protected IConditionArretMaitre conditonArret;
	
	/** La liste de toutes les requêtes */
	protected List<IRequete> listRequetes;
	
	/** Le registre sur le réseau */
	protected Registry registre;
	
	
	public Maitre() {
		
		try {

		registre = LocateRegistry.getRegistry(Maitre.ADRESSE_IP,Integer.parseInt(Maitre.PORT));
		/* On récupère la liste des serveurs sur le registre local */ 
		listServeurs = new ListeServeur();
		listRequetes = new ArrayList<IRequete>();
		conditonArret = new IConditionArretMaitre() {
			
			private static final long serialVersionUID = -7705378985512001228L;

			@Override
			public IEnvironnement nextEnvironnement() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public IConditionArretMaitre nextConditionArret() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isSatisfied() {
				try {
					return bestIndividus.getTailleSouhaitee() == listServeurs.size();
				} catch (RemoteException e) {
					e.printStackTrace();
					return false;
				}
			}
		};
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * @param ind, l'individu à récupérer après traitement de la requête
	 */
	public void recupererIndividu(IIndividu ind){
		this.bestIndividus.ajouterIndividu(ind);
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
	public IConditionArretMaitre getConditionArret() {
		return conditonArret;
	}
	
	@Override
	public void setConditionArret(IConditionArretMaitre condition) {
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

