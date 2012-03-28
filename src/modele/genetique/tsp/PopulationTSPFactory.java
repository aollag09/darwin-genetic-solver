package modele.genetique.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import modele.graphe.Ville;

import org.apache.commons.collections15.ListUtils;

import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IEnvironnement;
import darwin.interfaces.IIndividu;


/**
 * Classe permettant � l'utilisateur de cr�er des population initales respectants certains 
 * crit�res notamment pour cr�er des popluations avec des crit�res diff�rents
 * 
 * @author Amaury
 *
 */
public class PopulationTSPFactory {

	/** Les individus avec lesquels nous allons travailler */
	ArrayList<IIndividu> individus;

	/** L'environement */
	EnvironnementTSP environement;

	public PopulationTSPFactory(ArrayList<IIndividu> individus,
			EnvironnementTSP environement) {
		super();
		this.individus = individus;
		this.environement = environement;
	}
	
	public PopulationTSPFactory(IEnvironnement env){
		environement = (EnvironnementTSP) env;
	}
	
	/**
	 * @param String name, le nom de l'individu
	 * @return un individu al�atoire
	 */
	public IIndividu genererAleatoirementIndividu(String name){
		List<ICaracteristique> villes = new ArrayList<ICaracteristique>();
		villes.addAll(environement.getGraphe().getVertices());
		Collections.shuffle(villes);
		Chemin chemin = new Chemin(name,villes);
		return chemin;
	}
	

	/** G�n�rer al�atoirement une population d'une taille donn�e */
	public ArrayList<IIndividu> genererAleatoirementPopulation(int tailleSouhaitee){
		ArrayList<IIndividu> ind = new ArrayList<IIndividu>();
		for( int i = 0; i< tailleSouhaitee; i++){
			ind.add(this.genererAleatoirementIndividu("Le chemin" + i));
		}               
		return ind;
	}

	/**
	 * 
	 * @param pourcentageAccepte, le pourcetage de similitude moyen accept� entre tous les individus
	 * @param tailleSouhaitee, la taille souhait�e pour la population
	 * @return Une population dont les individus ont un pourcentage de similitude entre eux inf�rieur ou �gal � celui
	 * rentr� en param�tre
	 */
	public ArrayList<IIndividu> genererPopulationSimilitudeRestrainte(double pourcentageAccepte, int tailleSouhaitee){
		ArrayList<IIndividu> individus = genererAleatoirementPopulation(tailleSouhaitee);
		HashMap<IIndividu, Double> distances = distanceIndividusPopulation(individus);
		double evaluationMoyenne = evaluerMoyenne(distances);
		/* Tant que l'�valuation moyenne des ditances entre tous les individus on retire le pire individu et on en random un autre*/
		while(evaluationMoyenne > pourcentageAccepte){
			/* On trouve le pire individu */
			IIndividu pire = null;
			double max = Double.MIN_VALUE;
			for(IIndividu index : distances.keySet()){
				if(distances.get(index) > max){
					max = distances.get(index);
					pire = index;
				}
			}
			/* On cr�e un nouvel individu pour remplacer le pire */
			IIndividu nouveau = genererAleatoirementIndividu(pire.getName());
			double evalutationDistanceNouveau = 0;
			for(IIndividu index : distances.keySet()){
				evalutationDistanceNouveau += distanceEntreIndividus(nouveau, index);
			}
			/* On met � jour l'�vluation de tous les autres */
			for(IIndividu index : distances.keySet()){
				distances.put(index, distances.get(index) +
							( ( distanceEntreIndividus(nouveau, index) - distanceEntreIndividus(pire, index)) 
									/ distances.size()));
			}
			
			/* On ajoute le nouvel individu dans la HashMap */
			distances.put(nouveau, evalutationDistanceNouveau / distances.size());
			
			/* On le retire de la liste des individus */
			individus.remove(pire);
			individus.add(nouveau);
			
			/* Mise � jour de l'�valuation moyenne */
			evaluationMoyenne = evaluerMoyenne(distances);
		}
		
		return individus;
	}
	
	  /**
     * La distance entre deux individus est simplement d�fini comme le pourcentage 
     * d'edges en commun par rapport au nombre d'edges au total
     * @param i1
     * @param i2
     * @return la distance entre i1 et i2
     */
    public double distanceEntreIndividus(IIndividu i1, IIndividu i2){
    	ArrayList<Vector<Ville>> pairesI1 = new ArrayList<Vector<Ville>>();
    	ArrayList<Vector<Ville>> pairesI2 = new ArrayList<Vector<Ville>>();
    	for(int i = 0; i<i1.getNombreCaracteristiques() - 1 ; i++){
    		Vector<Ville> v1 = new Vector<Ville>();
    		v1.add( (Ville) (i1.getCaracteristique(i)) );
    		v1.add( (Ville) (i1.getCaracteristique(i + 1)) );
    		
    		Vector<Ville> v2 = new Vector<Ville>();
    		v2.add( (Ville) (i2.getCaracteristique(i)) );
    		v2.add( (Ville) (i2.getCaracteristique(i + 1)) );
    		
    		pairesI1.add(v1);
    		pairesI2.add(v2);
    	}
    	return (ListUtils.intersection(pairesI1, pairesI2).size()+0.0) / i1.getNombreCaracteristiques();
    }
    
 
    /**
     * 
     * @param population, la population � evaluer
     * @return une HashMap, qui indique la distance d'un individu par rapport � tous les autres
     */
    public HashMap<IIndividu, Double>distanceIndividusPopulation( ArrayList<IIndividu> individus ){
    	HashMap<IIndividu, Double> distances = new HashMap<IIndividu, Double>();
    	/* Initialisation de la HashMap  */
    	for(int i = 0; i < individus.size(); i++){
    		distances.put(individus.get(i), 0.0);
    	}
    	for(int i = 0; i < individus.size(); i++){
    		/* L'individu qu'on �value courament */
    		Chemin chemin = (Chemin) individus.get(i);
    		for(int j = i+1; j < individus.size(); j++){
    			Chemin cheminCourant = (Chemin) individus.get(j);
    			double distanceCourante = distanceEntreIndividus(chemin, cheminCourant);
    			
    			distances.put(chemin, distanceCourante + distances.get(chemin));
    			distances.put(cheminCourant, distanceCourante + distances.get(cheminCourant));
    		}
    	}
    	/* On met les r�sulats sous forme de fraction pour avoir des probabilit�es */
    	for(int i = 0; i < individus.size(); i++){
    		distances.put(individus.get(i), distances.get(individus.get(i)) / individus.size() );
    	}
    	
    	return distances;
    }
	
    /**
     * 
     * @param individus
     * @return La moyenne des �valuations des individus
     */
    public double evaluerMoyenne(HashMap<IIndividu, Double> individus){
    	double somme = 0;
    	for(IIndividu i : individus.keySet()){
    		somme += individus.get(i);
    	}
    	return somme / individus.size();
    }
}
