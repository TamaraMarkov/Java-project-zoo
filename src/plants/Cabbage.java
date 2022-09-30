package plants;

import utilities.MessageUtility;

/** Class that creates cabbage object
 *
 *  @author - Leah Brodsky
 *   @author - Ziv Gimani
 */

public class Cabbage extends Plant {
	private static Cabbage cabbage;
	/**
	 * if there is cabbage object return it, else create it and return
	 * @return meat object
	 */
	public static Cabbage getCabbage() {
		if (cabbage == null)
			cabbage = new Cabbage();
		return cabbage;
	}
	/**
	 * Constructor
	 * @see MessageUtility#logConstractor(String className, String name) 
	 */
	private Cabbage() {
		MessageUtility.logConstractor("Cabbage", "Cabbage");
	}

	protected String getImageName() {
		return "cabbage.png";
	}
}
