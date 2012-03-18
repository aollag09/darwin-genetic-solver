package modele.genetique.tsp;

import java.util.BitSet;

/**
 * Toutes les méthodes de calcul utile dans tout le modèle !
 * 
 * @author Amaury
 *
 */
public class Utilitaire {

        /** 
         * @param max
         * @return Le nombre minimum de bit nécessaire pour stocker en mémoire au maximum le nombre max
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

        public static int intFromBitSet(BitSet bitSet){
                int retour = 0;
                for(int i=bitSet.nextSetBit(0); i>=0; i = bitSet.nextSetBit(i+1)){
                        retour += Math.pow(2, i);
                }
                return retour;
        }
}