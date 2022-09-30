package factories;

import animals.Animal;
import animals.Bear;
/**
 * This class create factory of omnivore animals
 * @author - Leah Brodsky
 *  @author - Ziv Gimani
 */
public class OmnivoreFactory extends AbstractFactory {
	private static OmnivoreFactory omnivoreFactory;
	/**
	 * if there is object return it, else create and return
	 * @return - class object
	 */
	public static OmnivoreFactory getOmnivoreFactory() {
		if (omnivoreFactory == null)
			omnivoreFactory = new OmnivoreFactory();
		return omnivoreFactory;
	}
	/**
	 * if the animal is omnivore, create and return it, else return null
	 */
	private OmnivoreFactory() {}
	@Override
	public Animal getAnimal(String type) {
		if (type.equals("Bear"))
			return new Bear();
		return null;
	}

}
