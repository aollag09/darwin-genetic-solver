package darwin.solveur.crossovers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import darwin.interfaces.ICaracteristic;
import darwin.interfaces.IBeing;
import darwin.model.CrossOver;

/**
 * Cross Over with one or several junction points
 * 
 * @author Dim & Momo
 *
 */
public class CrossOverJunctionPoint extends CrossOver{

	public CrossOverJunctionPoint(double probabilite) throws Exception {
		super(probabilite);
	}

	@Override
	public List<IBeing> crossOver(IBeing individu1, IBeing individu2) {
		/* the list to return */
		ArrayList<IBeing> retour = 
				new ArrayList<IBeing>(Arrays.asList(new IBeing[] {individu1, individu2}));
		/* we perform the cross over regarding a certain probability*/
		if(this.doCrossOver() && individu1.getType().equals(individu2.getType())){
			int nbTotalCaracteres = (individu1.getCaracteristicsSize()<individu2.getCaracteristicsSize())?
					individu1.getCaracteristicsSize():individu2.getCaracteristicsSize();
			IBeing n1 = individu1.clone();
			IBeing n2 = individu2.clone();
			
			int pointDeCroisement = (int) (Math.random()*nbTotalCaracteres);
			for(int i = pointDeCroisement; i< nbTotalCaracteres; i++){
				ICaracteristic c1 = n1.getCaracteristics().get(i).clone();
				ICaracteristic c2 = n2.getCaracteristics().get(i).clone();
				n1.getCaracteristics().remove(i);
				n1.getCaracteristics().add(i, c2);
				n2.getCaracteristics().remove(i);
				n2.getCaracteristics().add(i,c1);
			}
			n1.setName(n1.getName()+"-j");
			n2.setName(n2.getName()+"-j");
			retour = new ArrayList<IBeing>(Arrays.asList(new IBeing[] {n1, n2}));
		}
		return retour;
	}


}
