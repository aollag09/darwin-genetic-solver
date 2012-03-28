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



	private static final long serialVersionUID = 3767055213227100312L;

	
	public Chemin(Individu i) {
		super(i);
	}


	public Chemin(String name, List<ICaracteristique> caracteristiques) {
		super(name, caracteristiques);
	}



	@Override
	public Individu clone() {
		return new Chemin(this);
	}


	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i< this.getListCaracteristique().size(); i++){
			s+= this.getCaracteristique(i).toString()+",";
		}
		return s;
	}





}