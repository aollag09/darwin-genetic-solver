package tuples;

import java.util.ArrayList;

import darwin.interfaces.IIndividu;
import darwin.modele.Population;

public class PopulationTuples extends Population{

	
	private static final long serialVersionUID = -3783372827023763346L;
	
	private int tailleTuples;
	
	public PopulationTuples(int nbIndividusSouhaites, int tailleTuples, int[] objectif){
		super(nbIndividusSouhaites,new EnvironnementTuples(objectif));
		this.tailleTuples = tailleTuples;
		this.generer();
	}
	
	@Override
	public void generer() {
		this.individus = new ArrayList<IIndividu>();
		for(int i=0;i<this.getTailleSouhaitee(); i++){
			int[] t = new int[this.tailleTuples];
			for(int j=0; j<t.length; j++){
				t[j] = (int) (Math.random()*(Entier.MAX_ENTIER+1));
			}
			try {
				this.individus.add(new Tuple(this.tailleTuples,t, i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String toString(){
		String retour = "";
		for(IIndividu indiv : this.getListIndividus()){
			try {
				retour += indiv + " Score : " + this.evaluerIndividu(indiv) + "\n";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retour;
	}
	
}
