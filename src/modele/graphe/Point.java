package modele.graphe;


import java.awt.geom.Point2D;
import java.io.Serializable;

public class Point extends Point2D implements Serializable {


	private static final long serialVersionUID = 6288030958282483768L;

	private double x,y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point() {
		super();
		x = 0;
		y = 0;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;

	}



}
