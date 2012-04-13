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

	/** L'ensemble des meilleurs individus r�cup�r�s avec les g�n�rations sur les serveurs */
	protected IPopulation bestIndividus;

	/** La liste des chemin r�seau de tous les serveurs actuellement lanc�s */
	protected IListeServeur listServeurs;

	/** La condition d'arr�t � v�rifier */
	protected IConditionArretMaitre conditonArret;

	/** La liste de toutes les requ�tes */
	protected List<IRequete> listRequetes;

	/** Le registre sur le r�seau */
	protected Registry registre;

	/** Le nombre de serveurs que l'ont souhaite appeler */
	private int nombreServeur;

	/** Nombre de serveurs effectu�s */
	private int nombreServeurCourantTermine;


	public Maitre() {

		try {
			registre = LocateRegistry.getRegistry(Maitre.ADRESSE_IP,Integer.parseInt(Maitre.PORT));
			/* On r�cup�re la liste des serveurs sur le registre local */ 
			listServeurs = (IListeServeur) registre.lookup(Maitre.CHEMIN_RESEAU_REGISTRE_SERVEURS);
			nombreServeur = listServeurs.size();
			listRequetes = new ArrayList<IRequete>();
			nombreServeurCourantTermine = 0;
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
						return bestIndividus.getTailleEffective() == listServeurs.size();
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
	 * @param nombreServeur, le nombre de serveurs utilis�s
	 */
	public Maitre(int nombreServeur) {

		try {
			registre = LocateRegistry.getRegistry(Maitre.ADRESSE_IP,Integer.parseInt(Maitre.PORT));
			/* On r�cup�re la liste des serveurs sur le registre local */ 
			listServeurs = (IListeServeur) registre.lookup(Maitre.CHEMIN_RESEAU_REGISTRE_SERVEURS);
			this.nombreServeur = nombreServeur;
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
						return bestIndividus.getTailleEffective() == listServeurs.size();
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
	 * @param ind, l'individu � r�cup�rer apr�s traitement de la requ�te
	 */
	public void recupererIndividu(IIndividu ind){
		this.nombreServeurCourantTermine ++;
		if(this.nombreServeurCourantTermine >= this.nombreServeur){
			this.operationFinale();
		}
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
		if(this.nombreServeur > this.listRequetes.size()){
			System.out.println("Seulement "+this.listRequetes.size()
						+" disponnibles, impossible de lancer une requ�te sur "+this.nombreServeur
						+" serveurs.");
		}else{
			for (int i = 0; i < this.nombreServeur; i++) {
				listRequetes.get(i).lancerRequete();
			}
		}
	}


}

