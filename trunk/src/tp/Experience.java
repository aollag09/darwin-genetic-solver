package tp;

import java.util.ArrayList;
import java.util.List;

import parallelisation.client.Maitre;

public class Experience {
	
	/** Le maitreTSP sur lequel on va effectuer l'expérience */
	private Maitre maitre;
	
	/** Le nom de l'exprience */
	private String name;
	
	/** La liste des meilleurs résultats */
	private List<Double> bestResults;
	
	/** La liste des temps de résolution */
	private List<Double> tempsResolutions;
	
	/**
	 * Constructeur pour initialiser les paramètres nécessaires
	 */
	public Experience(String nom, Maitre m) {
		super();
		setMaitre(m);
		name = nom;
		bestResults = new ArrayList<Double>();
		tempsResolutions = new ArrayList<Double>();
		
	}
	
	/**
	 * Constructor using fields
	 * @param maitre
	 * @param name
	 * @param bestResults
	 * @param tempsResolutions
	 */
	public Experience(Maitre maitre, String name, List<Double> bestResults,
			List<Double> tempsResolutions) {
		super();
		this.maitre = maitre;
		this.name = name;
		this.bestResults = bestResults;
		this.tempsResolutions = tempsResolutions;
	}



	/**
	 * @return la moyenne des meilleurs résulats de l'expérience courante
	 */
	public double getMoyenne(){
		double somme = 0;
		for(Double d : bestResults)
			somme += d;
		return somme / (bestResults.size() + 0.0);
	}
	
	/**
	 * @return le temps moyen d'execution
	 */
	public double getTempsMoyen(){
		double somme = 0;
		for(Double d : tempsResolutions)
			somme += d;
		return somme / (tempsResolutions.size() + 0.0);
	}
	
	public double getBest(){
		double currentBest = Double.MAX_VALUE;
		for(Double d : bestResults)
			if(d < currentBest)
				currentBest = d;
		return currentBest;
	}
	
	/**
	 * Pour ajouter un résultat à la liste des résultats
	 * @param evluation
	 * @param time
	 */
	public void ajouterResultat(double evluation, double time){
		this.bestResults.add(evluation);
		this.tempsResolutions.add(time);
	}
	
	public String printPretty(){
		String s = "";
		s+= name+";";
		s+= this.getBest()+";";
		s+= this.getMoyenne()+";";
		s+= this.getTempsMoyen()+";";
		for (int i = 0; i < bestResults.size(); i++) {
			s+= this.bestResults.get(i)+";";
		}		
		s.replaceAll(".", ",");
		return s;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getTempsResolutions() {
		return tempsResolutions;
	}

	public void setTempsResolutions(List<Double> tempsResolutions) {
		this.tempsResolutions = tempsResolutions;
	}

	public List<Double> getBestResults() {
		return bestResults;
	}

	public void setBestResults(List<Double> bestResults) {
		this.bestResults = bestResults;
	}

	public Maitre getMaitre() {
		return maitre;
	}

	public void setMaitre(Maitre maitre) {
		this.maitre = maitre;
	}
	
	
	
	

}
