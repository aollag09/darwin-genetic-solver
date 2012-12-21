package darwin.model;

import java.util.BitSet;

import darwin.interfaces.ICaracteristic;

/**
 * Abstract and partial Implementation of the object Characteristic
 * 
 * @author Dim && Momo
 */
public abstract class Caracteristic implements ICaracteristic {

	// VARIABLES D'INSTANCES

	/** the BitSet of the characteristic */
	protected BitSet bitSet;

	/** the size of the BitSet */
	protected int tailleBitSet;

	/** the name of the characteristic */
	protected String nom;

	/**
	 * default constructor
	 */
	protected Caracteristic() {
	}

	/**
	 * basic constructor
	 * 
	 * @param name
	 * @param bitSet
	 * @param bitSetSize
	 */
	protected Caracteristic(String name, BitSet bitSet, int bitSetSize) {
		this.tailleBitSet = bitSetSize;
		this.bitSet = bitSet;
		this.nom = name;
	}

	/**
	 * Deep copy constructor
	 * 
	 * @param c
	 */
	protected Caracteristic(Caracteristic c) {
		this.nom = new String(c.getName());
		this.bitSet = (BitSet) c.getBitSet().clone();
		this.tailleBitSet = c.tailleBitSet;
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
	public int getBitSetSize() {
		return this.tailleBitSet;
	}

	@Override
	public abstract void update();

	public abstract Caracteristic clone();

	public String toString() {
		return "Characteristic " + nom + " [bitSet = " + bitSet + "]";
	}
}
