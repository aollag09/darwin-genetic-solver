package modele.genetique.tsp;

import java.util.List;

import darwin.interfaces.ICaracteristique;
import darwin.modele.Individu;



/**
 * Pour le problème du TSP le chemin est un Individu possible du modèle génétique avec une certaine valeur
 * dans un certain environement
 * 
 * @author Amaury
 *
 */
public class Chemin extends Individu{

	
	public Chemin(Individu i) {
		super(i);
	}

	
	public Chemin(String name, List<ICaracteristique> caracteristiques) {
		super(name, caracteristiques);
	}



	@Override
	public Individu clone() {
		// TODO Auto-generated method stub
		return null;
	}



	
	

}
