package animals;

import diet.Herbivore;
import diet.IDiet;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;
/**
 * This class contains functions that define a Giraffe
 *
 * @author Leah Brodsky
 * @author Ziv Gimani
 *
 * @see  food.IEdible
 * @see  Animal
 * @see mobility.Mobile
 * @see mobility.Point
 * @see utilities.MessageUtility
 *
 */

public class Giraffe extends Chew {
	/**
	 * The length of the neck
	 */
	private double neckLength;
	public Giraffe() {
		super("", new Point(50, 0));
		MessageUtility.logConstractor("Giraffe",getName());
		setWeight(450);
		setNeckLength(2);
		setDiet(new Herbivore());
	}
	/**
	 * @param name
	 * @param location
	 * @see #setLocation(Point)
	 */

	public Giraffe(String name, Point location) {
		this(name, 1);
		setLocation(location);
	}
	/**
	 * Constructor
	 * @param name
	 * @param neckLength
	 * @see #getName()
	 * @see #setDiet(IDiet diet)
	 * @see #setNeckLength(double neckLength)
	 * @see #setName(String name)
	 * @see #setWeight(double weight)
	 * @see MessageUtility#logConstractor(String className, String name)
	 *
	 */
	public Giraffe(String name, double neckLength) {
		super(name, new Point(50, 0));
		setName(name);
		MessageUtility.logConstractor("Giraffe",getName());
		setWeight(450);
		setNeckLength(neckLength);
		setDiet(new Herbivore());
	}
	/**
	 * Which sound elephant makes
	 * @see Chew
	 */
	@Override
	public void makeSound() {
		chew("Bleats and Stomps its legs, then chews");
	}

	/**
	 * @see MessageUtility#logGetter(String name, String funcName, Object neckLength)
	 * @see Animal#getName()
	 * @return neckLength
	 */
	public double getNeckLength() {
		MessageUtility.logGetter(getName(),"getNeckLength",neckLength);
		return neckLength;
	}
	/**
	 *
	 * @param neckLength
	 * @see Animal#getName()
	 * @see utilities.MessageUtility#logSetter(String name, String funcName, Object neckLength, boolean)
	 * @return boolean
	 */
	public boolean setNeckLength(double neckLength) {
		if ((neckLength >= 0) && (neckLength <= 50)) {
			this.neckLength = neckLength;
			MessageUtility.logSetter(getName(),"setNeckLength",neckLength,true);
			return true;
		}
		MessageUtility.logSetter(getName(),"setNeckLength",neckLength,false);
		return false;
	}
	/**
	 * choose image of the right color giraffe turning to the right side
	 * @return images
	 */

	protected String getRightMoveImageName() {
		String color = getColor();
		if (color == null)
			return "grf_n_1.png";
		if (color.equals("Red"))
			return "grf_r_1.png";
		if (color.equals("Blue"))
			return "grf_b_1.png";
		return "grf_n_1.png";
	}

	/**
	 * choose image of the right color giraffe turning to the left side
	 * @return images
	 */
	protected String getLeftMoveImageName() {
		String color = getColor();
		if (color == null)
			return "grf_n_2.png";
		if (color.equals("Red"))
			return "grf_r_2.png";
		if (color.equals("Blue"))
			return "grf_b_2.png";
		return "grf_n_2.png";
	}
	public void setWeightAccordingToSize() {
		setWeight(getSize() * 2.2);
	}
}
