package modele.graphe;

import tuples.Utiles;
import darwin.modele.Caracteristique;

/**
 * Tous les individus "Chemin" sont composés du plusieurs Caractéristiques qui sont ici les Villes !
 * 
 * @author Amaury
 *
 */
public class Ville extends Caracteristique {
	
	
	// VARIABLES D'INSTANCES
	
	/** Le numéro de la ville */
	private int id;

	/**  La position sur la carte */
	private Point position;

	
	// CONSTRUCTEUR
	
	public Ville(int id, Point p, String name) throws Exception {
		super();
		this.id = id;
		position = p;
	}

	public Ville() {
		super();
		setPosition(new Point());
		id = 0;
	}

	public Ville(int id, Point p) throws Exception{
		this.id = id;
		setPosition(p);
		this.bitSet = Utiles.convertInBitSet(id);
		this.tailleBitSet = Utiles.nombreBitNecessairesPour(id);
	}
	
	
	// AUTRES METHODES

	public Ville(Ville ville) {
		this.id 			= ville.id;
		this.position 		= ville.position;
		this.bitSet 		= ville.bitSet;
		this.tailleBitSet 	= ville.tailleBitSet;
		this.nom 			= ville.nom;
	}

	public String toString(){
		return id+"";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if (id != other.id)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	
	/**
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public void update() {
		int value = Utiles.intFromBitSet(this.getBitSet());
		this.id = value;	
	}

	@Override
	public Caracteristique clone() {
		return new Ville(this);
	}
	
	
}
