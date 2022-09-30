package memento;

import mobility.Point;

/** Class that get location of the animal
 *  @author - Leah Brodsky
 *   @author - Ziv Gimani
 */

public class Memento {
	private Point location;

	/**
	 * constructor
	 * @param location-location of the animal
	 */
	public Memento(Point location) {
		this.location = location;
	}

	/**
	 * get location of the animal
	 * @return location
	 */
	public Point getLocation() {
		return location;
	}
}
