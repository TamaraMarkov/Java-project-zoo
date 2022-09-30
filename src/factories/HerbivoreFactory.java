package factories;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;

/**
 * This class create factory of herbivore animals
 * @author - Leah Brodsky
 *  @author - Ziv Gimani
 */

public class HerbivoreFactory extends AbstractFactory {
	private static HerbivoreFactory herbivoreFactory;
	/**
	 * if there is object return it, else create and return
	 * @return - class object
	 */
	public static HerbivoreFactory getHerbivoreFactory() {
		if (herbivoreFactory == null)
			herbivoreFactory = new HerbivoreFactory();
		return herbivoreFactory;
	}
	/**
	 * if the animal is herbivore, create and return it, else return null
	 */
	private HerbivoreFactory() {}
	@Override
	public Animal getAnimal(String type) {
		if (type.equals("Elephant"))
			return new Elephant();
		if (type.equals("Giraffe"))
			return new Giraffe();
		return new Turtle();
	}

}
