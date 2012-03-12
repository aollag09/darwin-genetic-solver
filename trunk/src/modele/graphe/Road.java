package modele.graphe;



public class Road {
	
	/** La distance de la route */
	private double distance;
	
	public Road() {
		distance = 0;
	}
	
	public Road(double val){
		distance = val;
	}
	
	public String toString(){
		return (int)(distance)+"";
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public static double getDistance(Ville t1, Ville t2){
		return Math.sqrt( (t1.getPosition().getX() - t2.getPosition().getX())*(t1.getPosition().getX() - t2.getPosition().getX()) +
				(t1.getPosition().getY() - t2.getPosition().getY())*(t1.getPosition().getY() - t2.getPosition().getY()));
	}
	

}
