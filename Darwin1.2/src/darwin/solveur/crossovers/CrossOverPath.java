package darwin.solveur.crossovers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import darwin.interfaces.ICaracteristic;
import darwin.interfaces.IBeing;
import darwin.model.CrossOver;

/**
 * CrossOverPath for special problems as the TSP
 * @author Dim & Momo
 *
 */
public class CrossOverPath extends CrossOver{

	public CrossOverPath(double probablite) throws Exception{
		super(probablite);
	}
	
	@Override
	public List<IBeing> crossOver(IBeing individu1, IBeing individu2) {
		/* the list to return */
		ArrayList<IBeing> retour = 
				new ArrayList<IBeing>(Arrays.asList(new IBeing[] {individu1, individu2}));
		/* we perform the cross over regarding a certain probability */
		if(this.doCrossOver() && individu1.getType().equals(individu2.getType())){
			int nbVilles = individu1.getCaracteristicsSize();
			if(nbVilles >= 2){
				int ptCoupage = (int) ((nbVilles-1)*Math.random());
				IBeing n1 = individu1.clone();
				IBeing n2 = individu2.clone();
				List<ICaracteristic> newChemin1 = new ArrayList<ICaracteristic>();
				List<ICaracteristic> newChemin2 = new ArrayList<ICaracteristic>();
				for(int i = 0; i<ptCoupage; i++){
					newChemin1.add(individu1.getCaracteristics(i));
					newChemin2.add(individu2.getCaracteristics(i));
				}
				for(int j = 0; j<nbVilles; j++){
					if(!newChemin1.contains(individu2.getCaracteristics(j))){
						newChemin1.add(individu2.getCaracteristics(j));
					}
					if(!newChemin2.contains(individu1.getCaracteristics(j))){
						newChemin2.add(individu1.getCaracteristics(j));
					}
				}
				n1.setCaracteristics(newChemin1);
				n2.setCaracteristics(newChemin2);
				n1.setName(n1.getName()+"-j");
				n2.setName(n2.getName()+"-j");
				retour = new ArrayList<IBeing>(Arrays.asList(new IBeing[] {n1, n2}));
			}
		}
		return retour;
	}

}
