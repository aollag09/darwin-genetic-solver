package darwin.modele;

import java.util.List;

import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;
import darwin.interfaces.IPopulation;

/** 
 * @author Dim && Momo
 *	Implémentation partielle et abstraite de IPopulation
 */
public abstract class Population implements IPopulation {

	// VARIABLES D'INSTANCES 

	private static final long serialVersionUID = 4152819040013584461L;

	/** La liste de tous les individus */
	protected List<IIndividu> individus;

	/** Le nombre d'individus souhaités */
	protected int nombreIndividusSouhaite;

	/** L'environement dans lequel évolue la population */
	protected IEnvironnement environnement;
	
	/**
	 * Constructeur par défaut
	 */
	protected Population(){
	}
	
	/**
	 * Constructeur basique
	 * @param nombreIndividusSouhaites
	 * @param environnement
	 */
	protected Population(int nombreIndividusSouhaites, IEnvironnement environnement){
		this.nombreIndividusSouhaite = nombreIndividusSouhaites;
		this.environnement = environnement;
		this.generer();
	}
	
	@Override
	public void ajouterIndividu(IIndividu individu) {
		this.getListIndividus().add(individu);
	}

	@Override
	public double evaluerIndividu(IIndividu individu) throws Exception {
		double retour = this.getEnvironnement().evaluerIndividu(individu);
		return retour;
	}

	@Override
	public double evaluerIndividu(int index) throws Exception {
		return this.evaluerIndividu(this.getIndividu(index));
	}

	@Override
	public double evaluerPopulation() {
		double retour = 0;
		for(IIndividu i: this.getListIndividus()){
			try {
				retour += this.evaluerIndividu(i);
			} catch (Exception e) {
				System.out.println("L'individu " + i + "n'est pas évaluable");
				e.printStackTrace();
			}
		}
		return retour;
	}

	@Override
	public abstract void generer();

	@Override
	public IEnvironnement getEnvironnement() {
		return this.environnement;
	}

	@Override
	public IIndividu getIndividu(int i) {
		return this.getListIndividus().get(i);
	}

	@Override
	public List<IIndividu> getListIndividus() {
		return this.individus;
	}

	@Override
	public int getTailleEffective() {
		return this.getListIndividus().size();
	}

	@Override
	public int getTailleSouhaitee() {
		return this.nombreIndividusSouhaite;
	}

	@Override
	public void setEnvironnement(IEnvironnement environnement) {
		this.environnement = environnement;
	}

	@Override
	public void setListIndividus(List<IIndividu> individus) {
		this.individus = individus;
	}
	
	@Override
	public IIndividu getBestIndividu(){
		IIndividu retour = this.getIndividu(0);
		for(int i=1; i<this.getTailleEffective(); i++){
			try {
				if(this.evaluerIndividu(retour) < this.evaluerIndividu(this.getIndividu(i))){
					retour = this.getIndividu(i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retour;
	}
	
	public String toString() {
		return "Population [individus=" + individus + ", environnement="
				+ environnement + "]";
	}

}
