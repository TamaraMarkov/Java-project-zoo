package mobility;

/**
 * @authors Leah Brodsky
 * @author Ziv Gimani
 * Interface for getting and setting location
 */
public interface Ilocatable {
    public Point getLocation();
    public boolean setLocation(Point location);
}
