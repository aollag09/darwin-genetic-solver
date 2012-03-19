package parallelisation.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
public class Requete implements Runnable{


	// VARIABLES D'INSTANCES :
	private int identifiantServeur;
	private MaitreTSP maitre;
	private Thread thread;
	private ISelectionNaturelle selectionNaturelle;
	private IConditionArret conditionDArret;
	
	
	// CONSTRUCTEUR :
	public Requete(int identifiantServeur, MaitreTSP maitre,
			ISelectionNaturelle selectionNaturelle,
			IConditionArret conditionDArret) {
		super();
		this.identifiantServeur = identifiantServeur;
		this.maitre = maitre;
		this.selectionNaturelle = selectionNaturelle;
		this.conditionDArret = conditionDArret;
		
		this.thread = new Thread(this);
	}


	@Override
	public void run() {
		try {
			System.out.println("");
			String adresse = MaitreTSP.CHEMIN_RESEAU+this.identifiantServeur;
			System.out.print("Serveur "+this.identifiantServeur+" : Lancement de la requête...");
			IDarwin darwin = (IDarwin)Naming.lookup(adresse);
			darwin.setSelectionNaturelle(selectionNaturelle);
			darwin.setConditionArret(conditionDArret);
			IPopulation p = darwin.solve();
			System.out.println("Le serveur "+this.identifiantServeur+" a terminé le traitement de se requête " +
					"avec pour meilleur évaluation : " +p.evaluerIndividu(p.getBestIndividu()));
			
			
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

	public int getIdentifiantServeur() {
		return identifiantServeur;
	}


	public void setIdentifiantServeur(int identifiantServeur) {
		this.identifiantServeur = identifiantServeur;
	}


	public MaitreTSP getMaitre() {
		return maitre;
	}


	public void setMaitre(MaitreTSP maitre) {
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
