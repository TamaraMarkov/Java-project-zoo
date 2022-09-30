package mobility;

/**
 * @author Leah Brodsky
 * @author Ziv Gimani
 * This class creates and check the coordinates of the point
 */
public class Point {
	static final private int X_LOW_BOUNDARY = 0;
	static final private int Y_LOW_BOUNDARY = 0;
	static final private int X_HIGH_BOUNDARY = 800;
	static final private int Y_HIGH_BOUNDARY = 600;
    private int x,y;

	/**
	 *  Point construction
	 * @param x - coordinate
	 * @param y - coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Copy constructor
	 * @param p - point
	 */
	public Point(Point p) {
		x = p.getX();
		y = p.getY();
	}

	/**
	 * Function that checks if the x and y coordinates of the point are in the boundaries
	 * @param x - coordinate
	 * @param y - coordinate
	 * @return boolean
	 */
	static public boolean checkBoundaries(int x, int y) {
		return (x >= X_LOW_BOUNDARY) && (x <= X_HIGH_BOUNDARY) &&
				(y >= Y_LOW_BOUNDARY) && (y <= Y_HIGH_BOUNDARY);
	}

	/**
	 *
	 * @return x coordinates
	 */
	public int getX() {
		return x;
	}
	/**
	 *
	 * @return y coordinates
	 */
	public int getY() {
		return y;
	}
	@Override
	/**
	 * @return x,y in format (x,y)
	 */
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
