package animals;

import food.EFoodType;
import food.IEdible;
import mobility.Point;
import utilities.MessageUtility;

/**
 * Abstract class which define the sound that make animals
 * @see Animal
 */

public abstract class Roar extends Animal {
	/**
	 * @see Animal#Animal(String name, Point location)
	 * @param name
	 * @param location
	 */

	public Roar(String name, Point location) {
		super(name, location);
	}

	/**
	 * Message what sound the animal will make
	 * @param message
	 */
	protected void roar(String message) {
		MessageUtility.logSound(getName(), message);
	}
}
