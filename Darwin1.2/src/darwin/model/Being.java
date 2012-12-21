package darwin.model;

import java.util.ArrayList;
import java.util.List;
import darwin.interfaces.ICaracteristic;
import darwin.interfaces.IBeing;

/**
 * @author Momo && Dim
 *	Partial and abstract implementation of the being
 */
public abstract class Being implements IBeing {

	// VARIABLES D'INSTANCES :
	
	/** the id of the being */
	protected int type;
	
	/** the name of the being */
	protected String name;

	/** all the characteristics of the being */
	protected List<ICaracteristic> caracteristics;
	
	
	/**
	 * Default constructor
	 */
	protected Being(){
	}

	/**
	 * basic constructor
	 * @param name
	 * @param caracteristics
	 */
	protected Being(String name, List<ICaracteristic> caracteristics){
		this.type = -1;
		this.name = name;
		this.caracteristics = caracteristics;
	}
	
	/**
	 * Deep copy constructor
	 * @param i
	 */
	protected Being(Being i){
		this.type = i.getType();
		this.name = new String(i.getName());
		this.caracteristics = new ArrayList<ICaracteristic>();
		if(i.getCaracteristics() != null){
			for(ICaracteristic c : i.getCaracteristics()){
				this.caracteristics.add(c.clone());
			}
		}
	}
	
	@Override
	public List<ICaracteristic> getCaracteristics() {
		return this.caracteristics;
	}
	
	@Override
	public void setCaracteristics(List<ICaracteristic> caracs){
		this.caracteristics = caracs;
	}
	
	@Override
	public ICaracteristic getCaracteristics(int index){
		return this.getCaracteristics().get(index);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getCaracteristicsSize() {
		return this.caracteristics.size();
	}

	@Override
	public Integer getType() {
		return this.type;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public abstract Being clone();
	
	
	public String toString() {
		String retour = "Individu " + name + ", caracteristiques = ";
		for(ICaracteristic c : this.getCaracteristics()){
			retour += "\n" + "     " + c;
		}
		return retour;
	}
}
