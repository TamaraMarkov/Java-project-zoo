package factories;

import animals.Animal;

/**
 * This abstract class get the animal
 * @author - Leah Brodsky
 *  @author - Ziv Gimani
 *
 */

public abstract class AbstractFactory {
	public abstract Animal getAnimal(String type);
}
