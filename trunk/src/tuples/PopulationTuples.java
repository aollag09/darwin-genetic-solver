package tuples;

import java.util.ArrayList;

import darwin.interfaces.IIndividu;
import darwin.modele.Population;

public class PopulationTuples extends Population{

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
			this.individus.add(new Tuple(this.tailleTuples,t, i));
		}
	}

	public String toString(){
		String retour = "";
		for(IIndividu indiv : this.getListIndividus()){
			retour += indiv + " Score : " + this.evaluerIndividu(indiv) + "\n";
		}
		return retour;
	}
	
}
