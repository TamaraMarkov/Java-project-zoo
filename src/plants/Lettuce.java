package plants;

import utilities.MessageUtility;

/** Class that creates lettuce object
 *
 *  @author - Leah Brodsky
 *   @author - Ziv Gimani
 */

public class Lettuce extends Plant {
	private static Lettuce lettuce;
	/**
	 * if there is lettuce object return it, else create it and return
	 * @return meat object
	 */
	public static Lettuce getLettuce()
	{
		if (lettuce == null)
			lettuce = new Lettuce();
		return lettuce;
	}
	private Lettuce() {
		MessageUtility.logConstractor("Lettuce", "Lettuce");
	}

	protected String getImageName() {
		return "lettuce.png";
	}
}
