package darwin.solveur.crossovers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import darwin.interfaces.ICaracteristic;
import darwin.interfaces.IBeing;
import darwin.model.CrossOver;

/**
 * Simple cross over with a probability p to perform n cross over
 * @author Dim
 *
 */
public class CrossOverSimple extends CrossOver{
		
	/** the number of bit to change */
	protected int nbCharacters;
	
	/**
	 * basic constructor
	 * @param n
	 * @param p
	 * @throws Exception
	 */
	public CrossOverSimple(int n, double p) throws Exception{
		super(p);
		this.nbCharacters = n;
	}
	
	@Override
	public List<IBeing> crossOver(IBeing individu1, IBeing individu2) {
		
		//On initialise la liste � retourner avec les individus inchang�s
		ArrayList<IBeing> retour = 
				new ArrayList<IBeing>(Arrays.asList(new IBeing[] {individu1, individu2}));
		
		//Si le hasard en d�cide ainsi (et que les individus sont du m�me type, on effectue le crossOver
		if(this.doCrossOver() && individu1.getType().equals(individu2.getType())){
					
			int nbTotalCaracteres = (individu1.getCaracteristicsSize()<individu2.getCaracteristicsSize())?
					individu1.getCaracteristicsSize():individu2.getCaracteristicsSize();
			/* On s'assure que les individus contiennent au moins n+1 caract�res */
			int nbCaracteresEffectif = (this.nbCharacters < nbTotalCaracteres)?
												this.nbCharacters:nbTotalCaracteres;
			IBeing n1 = individu1.clone();
			IBeing n2 = individu2.clone();
			
			/* On choisi au hasard les caract�res � interchanger et on effectue le crossOver*/
			for(int i=0; i< nbCaracteresEffectif; i++){
				int j = (int)(Math.random()*nbTotalCaracteres);
				ICaracteristic c1 = n1.getCaracteristics().get(j).clone();
				ICaracteristic c2 = n2.getCaracteristics().get(j).clone();
				n1.getCaracteristics().remove(j);
				n1.getCaracteristics().add(j,c2);
				n2.getCaracteristics().remove(j);
				n2.getCaracteristics().add(j, c1);
			}
			n1.setName(n1.getName()+"-j");
			n2.setName(n2.getName()+"-j");
			retour = new ArrayList<IBeing>(Arrays.asList(new IBeing[] {n1, n2}));
		}
		return retour;
	}
}
