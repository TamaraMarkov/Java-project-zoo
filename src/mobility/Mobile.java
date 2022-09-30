package mobility;

import memento.Memento;

/**
 * @authors Leah Brodsky
 * @author Ziv Gimani
 * Class that deals with the movement and distance the animal makes
 */
public abstract class Mobile implements Ilocatable{

    private Point location;
    private double totalDistance;

    /**
     * Constrctor
     * @param location
     */
    public Mobile(Point location){
    	this.location = location;
    	totalDistance = 0;
    }

    /**
     * Function add the new distance to total distance
     * @param td
     */
    public void addTotalDistance(double td) {
    	totalDistance += td;
    }

    /**
     * Computes distance between two points
     * @param p
     * @return distance
     */
    public double calcDistance(Point p) {
    	return Math.sqrt(Math.pow(p.getX() - location.getX(), 2) + Math.pow(p.getY() - location.getY(), 2));
    }

    /**
     * Get point checks boundaries of its cordinates and computes the distance
     * @param p
     * @see Point#checkBoundaries(int x, int y)
     * @return distance
     */
    public double move(Point p) {
    	if (! Point.checkBoundaries(p.getX(), p.getY()))
    		return 0;
    	double distance = calcDistance(p);
    	addTotalDistance(distance);
    	location = new Point(p);
    	return distance;
    }

    @Override
    /**
     * @return location
     */
    public Point getLocation() {
        return location;
    }

    @Override
    /**
     * Set location
     * @see Point#checkBoundaries(int x, int y)
     * @return boolean
     */
    public boolean setLocation(Point location) {
    	if (! Point.checkBoundaries(location.getX(), location.getY()))
    		return false;
    	this.location = new Point(location);
        return true;
    }

    /**
     *
     * @return total distance
     */
	public double getTotalDistance() {
		return totalDistance;
	}
	public Memento createMemento() {
		return new Memento(location);
	}
	public void setMemento(Memento memento) {
		setLocation(memento.getLocation());
	}
}
