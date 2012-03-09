package darwin.modele;

import java.util.ArrayList;
import java.util.List;
import darwin.interfaces.ICaracteristique;
import darwin.interfaces.IIndividu;

/**
 * @author Momo && Dim
 *	Implémentation partielle et abstraite de IIndividu
 */
public abstract class Individu implements IIndividu {

	// VARIABLES D'INSTANCES :
	
	/** L'identifiant correspondant au type de l'individu */
	protected int type;
	
	/** Le nom de l'individu */
	protected String name;

	/** La liste des caractéristiques de l'individu */
	protected List<ICaracteristique> caracteristiques;
	
	
	/**
	 * Constructeur par défaut
	 */
	protected Individu(){
	}

	/**
	 * Construteur basique 
	 * @param name
	 * @param caracteristiques
	 */
	protected Individu(String name, List<ICaracteristique> caracteristiques){
		this.type = -1;
		this.name = name;
		this.caracteristiques = caracteristiques;
	}
	
	/**
	 * Constructeur par recopie (pour le clone() ).
	 * @param i
	 */
	protected Individu(Individu i){
		this.type = i.getType();
		this.name = new String(i.getName());
		this.caracteristiques = new ArrayList<ICaracteristique>();
		for(ICaracteristique c : i.getListCaracteristique()){
			this.caracteristiques.add(c.clone());
		}
	}
	
	@Override
	public List<ICaracteristique> getListCaracteristique() {
		return this.caracteristiques;
	}
	@Override
	public ICaracteristique getCaracteristique(int index){
		return this.getListCaracteristique().get(index);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getNombreCaracteristiques() {
		return this.caracteristiques.size();
	}

	@Override
	public Integer getType() {
		return this.type;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public abstract Individu clone();
	
	public boolean equals(Object o){
		boolean retour = false;
		if(o instanceof Individu){
			retour = true;
			Individu i = (Individu) o;
			for(int j=0; j<this.getNombreCaracteristiques(); j++){
				retour = retour && this.getCaracteristique(j).equals(i.getCaracteristique(j));
			}
		}
		return retour;
	}
	
	public String toString() {
		String retour = "Individu " + name + ", caracteristiques = ";
		for(ICaracteristique c : this.getListCaracteristique()){
			retour += "\n" + "     " + c;
		}
		return retour;
	}
}
