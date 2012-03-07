package darwin.modele;

import java.util.BitSet;

import darwin.interfaces.ICaracteristique;

/**
 * @author Dim && Momo
 *	Implémentation partielle et abstraite de ICaracteristique
 */
public abstract class Caracteristique implements ICaracteristique {

	// VARIABLES D'INSTANCES
	
	/** Le BitSet qui code la caractéristique de l'individu */
	protected BitSet bitSet;
	
	/** La taille de ce BitSet */
	protected int tailleBitSet;
		
	/** Le nom de cette caractéristique */
	protected String nom;
	
	/**
	 * Constructeur basique
	 * @param nom
	 * @param bitSet
	 * @param tailleBitSet
	 */
	protected Caracteristique(String nom, BitSet bitSet, int tailleBitSet){
		this.tailleBitSet = tailleBitSet;
		this.bitSet = bitSet;
		this.nom = nom;
	}
	
	@Override
	public BitSet getBitSet() {
		return this.bitSet;
	}

	@Override
	public String getName() {
		return this.nom;
	}

	@Override
	public int getTailleBitSet() {
		return this.tailleBitSet;
	}

	@Override
	public abstract void update();
	
	public abstract Caracteristique clone();
	
	public boolean equals(Object o){
		boolean retour = false;
		if(o instanceof Caracteristique){
			Caracteristique c = (Caracteristique) o;
			retour = this.getBitSet().equals(c.getBitSet());
		}
		return retour;
	}
	
	/**
	 * Représentation sous forme de chaine de caractères
	 */
	public String toString(){
		return "Caracteristique " + nom + " [bitSet = " + bitSet + "]";
	}
}
