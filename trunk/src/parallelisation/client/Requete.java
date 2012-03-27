package parallelisation.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import parallelisation.interfaces.IMaitre;
import parallelisation.interfaces.IRequete;

import darwin.interfaces.IConditionArret;
import darwin.interfaces.IDarwin;
import darwin.interfaces.IPopulation;
import darwin.interfaces.ISelectionNaturelle;


/**
 * La classe requete permet d'envoyer des requête spécifiques à un Serveur sur le réseau
 * 
 * @author Amaury
 *
 */
public class Requete implements IRequete{

	
	// VARIABLES D'INSTANCES :
	private static final long serialVersionUID = -7573569720186682869L;
	private IMaitre maitre;
	private Thread thread;
	private ISelectionNaturelle selectionNaturelle;
	private IConditionArret conditionDArret;
	private String cheminServeur;
	
	// CONSTRUCTEUR :
	public Requete( IMaitre maitre,
			ISelectionNaturelle selectionNaturelle,
			IConditionArret conditionDArret, String chemin) {
		super();
		this.maitre = maitre;
		this.selectionNaturelle = selectionNaturelle;
		this.conditionDArret = conditionDArret;
		this.thread = new Thread(this);
		this.cheminServeur = chemin;
	}


	@Override
	public void run() {
		try {
			String[] infos = cheminServeur.split("//");
			String ipAdress = infos[1];
			String ServeurName = infos[2];
			System.out.println(cheminServeur+" : Lancement de la requête...");
			
			/* Récupération de l'objet Darwin sur le Réseau */
			Registry registre = LocateRegistry.getRegistry(Maitre.ADRESSE_IP, Integer.parseInt(Maitre.PORT));
			IDarwin darwin = (IDarwin) registre.lookup(cheminServeur);
			
			/* Modification de l'objet */
			darwin.setSelectionNaturelle(selectionNaturelle);
			darwin.setConditionArret(conditionDArret);
			
			/* Lancement de la résolution */
			IPopulation p = darwin.solve();
			
			System.out.println("Le "+cheminServeur+" a terminé le traitement de se requête " +
					"avec pour meilleur évaluation : " +p.evaluerIndividu(p.getBestIndividu()));
			maitre.recupererIndividu(p.getBestIndividu());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void lancerRequete(){
		this.thread.start();
	}

	public IMaitre getMaitre() {
		return maitre;
	}


	public void setMaitre(IMaitre maitre) {
		this.maitre = maitre;
	}


	public Thread getThread() {
		return thread;
	}


	public void setThread(Thread thread) {
		this.thread = thread;
	}


	public ISelectionNaturelle getSelectionNaturelle() {
		return selectionNaturelle;
	}


	public void setSelectionNaturelle(ISelectionNaturelle selectionNaturelle) {
		this.selectionNaturelle = selectionNaturelle;
	}


	public IConditionArret getConditionDArret() {
		return conditionDArret;
	}


	public void setConditionDArret(IConditionArret conditionDArret) {
		this.conditionDArret = conditionDArret;
	}

	
	
	




}
