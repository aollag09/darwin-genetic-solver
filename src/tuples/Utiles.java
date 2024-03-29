package tuples;

import java.util.BitSet;

public class Utiles {
	
	/** 
	 * @param max
	 * @return Le nombre minimum de bit n�cessaire pour stocker en m�moire au maximum le nombre max
	 */
	public static int nombreBitNecessairesPour(int max){
		int nombreBits = 0;
		int valeurCourante = 1;
		while(valeurCourante < max){
			valeurCourante *= 2;
			nombreBits ++;
		}
		return nombreBits;
	}


	/**
	 * 
	 * @param nombre
	 * @return Le BitSet correspondant � la valeur donn�e en param�tre
	 */
	public static BitSet convertInBitSet(int nombre){
		BitSet toReturn = new BitSet();
		int index = 0;
		while (nombre != 0L) {
			if (nombre % 2L != 0) {
				toReturn.set(index);
			}
			++index;
			nombre = nombre >>> 1;
		}
		return toReturn;
	}

	/**
	 * 
	 * @param bitSet
	 * @return La valeur correspondant au BitSet donn� en param�tre
	 */
	public static int intFromBitSet(BitSet bitSet){
		int retour = 0;
		for(int i=bitSet.nextSetBit(0); i>=0; i = bitSet.nextSetBit(i+1)){
			retour += Math.pow(2, i);
		}
		return retour;
	}
}
