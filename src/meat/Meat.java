package meat;

import java.awt.Graphics;

import food.EFoodType;
import food.Food;
import mobility.Point;

/** Class that creates meat object
 *
 *  @author - Leah Brodsky
 *   @author - Ziv Gimani
 */

public class Meat extends Food {
	private static Meat meat;

	/**
	 * if there is meat object return it, else create it and return
	 * @return meat object
	 */
	public static Meat getMeat() {
		if (meat == null)
			meat = new Meat();
		return meat;
	}
	//class constructor
	private Meat() {
	}
	@Override
	public EFoodType getFoodtype() {
		return EFoodType.MEAT;
	}

	protected String getImageName() {
		return "meat.gif";
	}
}
