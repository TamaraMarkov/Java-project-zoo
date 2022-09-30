package factories;

import animals.Animal;
import animals.Lion;

/**
 * This class create factory of carnivore animals
 * @author - Leah Brodsky
 *  @author - Ziv Gimani
 */

public class CarnivoreFactory extends AbstractFactory {
	private static CarnivoreFactory carnivoreFactory;

	/**
	 * if there is object return it, else create and return
	 * @return - class object
	 */
	public static CarnivoreFactory getCarnivoreFactory() {
		if (carnivoreFactory == null)
			carnivoreFactory = new CarnivoreFactory();
		return carnivoreFactory;
	}

	/**
	 * if the animal is carnivore, create and return it, else return null
	 */
	private CarnivoreFactory() {}
	@Override
	public Animal getAnimal(String type) {
		if (type.equals("Lion"))
			return new Lion();
		return null;
	}

}
