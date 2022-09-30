package animals;

import diet.Carnivore;
import diet.Herbivore;
import diet.IDiet;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;
/**
 * This class contains functions that define an elephant
 * @author Leah Brodsky
 * @author Ziv Gimani
 * @see  food.IEdible
 * @see  Animal
 * @see mobility.Mobile
 * @see mobility.Point
 * @see utilities.MessageUtility
 *
 */
public class Elephant extends Chew {
	/**
	 * Length of the trunk
	 */
	private double trunkLength = 1;
	public Elephant() {
		super("", new Point(50, 90));
		MessageUtility.logConstractor("Elephant",getName());
		setWeight(500);
		setTrunkLength(5);
		setDiet(new Herbivore());
	}
	/**
	 * Constructor
	 * @param name
	 * @param trunkLength
	 * @see #getName()
	 * @see #setDiet(IDiet diet)
	 * @see #setTrunkLength(double trunkLength)
	 * @see #setName(String name)
	 * @see #setWeight(double weight)
	 * @see MessageUtility#logConstractor(String className, String name)
	 *
	 */
	public Elephant(String name,double trunkLength) {
		super(name, new Point(50, 90));
		setName(name);
		MessageUtility.logConstractor("Elephant",getName());
		setWeight(500);
		setTrunkLength(trunkLength);
		setDiet(new Herbivore());
	}
	/**
	 * @param name
	 * @param location
	 * @see #setLocation(Point)
	 */
	public Elephant(String name, Point location) {
		this(name, 1);
		setLocation(location);
	}
	/**
	 * Which sound elephant makes
	 * @see Chew
	 */
	public void makeSound() {
		chew("Trumpets with joy while flapping its ears, then chews");
	}

	/**
	 * @see MessageUtility#logGetter(String name, String funcName, Object trunkLength)
	 * @see Animal#getName()
	 * @return trunkLength
	 */

	public double getTrunkLength() {
		MessageUtility.logGetter(getName(),"getTrunkLength",trunkLength);
		return trunkLength;
	}
	/**
	 *
	 * @param trunkLength
	 * @see Animal#getName()
	 * @see utilities.MessageUtility#logSetter(String name, String funcName, Object trunkLength, boolean)
	 * @return boolean
	 */
	public boolean setTrunkLength(double trunkLength) {
		if ((trunkLength >= 0.5) && (trunkLength <= 3)) {
			this.trunkLength = trunkLength;
			MessageUtility.logSetter(getName(),"setTrunkLength",trunkLength,true);
			return true;
		}
		MessageUtility.logSetter(getName(),"setTrunkLength",trunkLength,false);
		return false;
	}
	/**
	 * choose image of the right color elephant turning to the right side
	 * @return images
	 */

	protected String getRightMoveImageName() {
		String color = getColor();
		if (color == null)
			return "elf_n_1.png";
		if (color.equals("Red"))
			return "elf_r_1.png";
		if (color.equals("Blue"))
			return "elf_b_1.png";
		return "elf_n_1.png";
	}
	/**
	 * choose image of the right color elephant turning to the left side
	 * @return images
	 */
	protected String getLeftMoveImageName() {
		String color = getColor();
		if (color == null)
			return "elf_n_2.png";
		if (color.equals("Red"))
			return "elf_r_2.png";
		if (color.equals("Blue"))
			return "elf_b_2.png";
		return "elf_n_2.png";
	}
	public void setWeightAccordingToSize() {
		setWeight(getSize() * 10);
	}
}
