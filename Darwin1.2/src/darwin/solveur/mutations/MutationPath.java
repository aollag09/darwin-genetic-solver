package darwin.solveur.mutations;

import java.io.Serializable;

import darwin.interfaces.ICaracteristic;
import darwin.interfaces.IBeing;
import darwin.model.Mutation;

public class MutationPath extends Mutation implements Serializable {

	private static final long serialVersionUID = -751605708947382909L;

	/**
	 * basic constructor
	 * @param probabilite 
	 */
	public MutationPath(double probabilite) {
		super.probability = probabilite;
	}
	
	@Override
	public IBeing mute(IBeing being) {
		/* Le nombre de carat�res que l'on d�cide du muter */
		int nombreCaractereAMuter = (int)(Math.random()*being.getCaracteristicsSize());
		IBeing nIndividu = being.clone();
		if(this.isMutable(nIndividu)){
			for(int i = 0; i< nombreCaractereAMuter; i++){
				/* On choisi al�atoirement le caract�re � muter */

				int caracAMuter = (int) (Math.random())* (being.getCaracteristicsSize() - 1);
				int newPosition = (int) (Math.random())* (being.getCaracteristicsSize() - 1);
				ICaracteristic c1 = being.getCaracteristics(caracAMuter);
				ICaracteristic c2 = being.getCaracteristics(newPosition);
				being.getCaracteristics().set(newPosition,c1);
				being.getCaracteristics().set(caracAMuter,c2);
			}
		}
		nIndividu.setName(nIndividu.getName() + "-mutant");
		return nIndividu;
	}

	@Override
	public boolean isCaracteristicMutable(
			ICaracteristic caracteristic) {
		return true;
	}

	@Override
	public boolean isMutable(IBeing being) {
		return being.getCaracteristicsSize()>1;
	}

}
