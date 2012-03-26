package parallelisation.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import darwin.interfaces.IDarwin;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;
import parallelisation.interfaces.IConditionArretMaitre;
import parallelisation.interfaces.IMaitre;
import parallelisation.interfaces.IRequete;

public abstract class Maitre implements IMaitre {


	private static final long serialVersionUID = 1L;
	private static InetAddress address;

	// CONSTANTES :
	public static String CHEMIN_RESEAU = "rmi//localhost//Serveur";

	//VARIABLES D'INSTANCES : 
	
	/** L'ensemble des meilleurs individus récupérés avec les générations sur les serveurs */
	protected IPopulation bestIndividus;
	
	/** Le nombre de serveurs disponnibles */
	protected int nombreServeurs;
	
	/** La condition d'arrêt à vérifier */
	protected IConditionArretMaitre conditonArret;
	
	/** La liste de toutes les requêtes */
	protected List<IRequete> listRequetes;
	
	
	public Maitre() {
		
		try {
			address = InetAddress.getLocalHost();
			CHEMIN_RESEAU = "rmi//"+address.getHostAddress()+"//Serveur";
		} catch (UnknownHostException e2) {
			System.out.println("Erreur dans la récupération de l'adresse réseau locale");
			e2.printStackTrace();
		}

		
		nombreServeurs = getNombreServeurs();
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
				return bestIndividus.getTailleSouhaitee() == nombreServeurs;
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

