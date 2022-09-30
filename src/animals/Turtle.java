package animals;

import diet.Herbivore;
import diet.IDiet;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;
/**
 * This class contains functions that define a bear
 *
 * @authors Leah Brodsky
 * @author Ziv Gimani
 *
 * @see  food.IEdible
 * @see  Animal
 * @see mobility.Mobile
 * @see mobility.Point
 * @see utilities.MessageUtility
 *
 */

public class Turtle extends Chew {
	/**
	 * age of the turtle
	 */
	private int age;

	public Turtle() {
		super("", new Point(80, 0));
		MessageUtility.logConstractor("Turtle",getName());
		setWeight(500);
		setAge(300);
		setDiet(new Herbivore());
	}
	/**
	 * @see Animal#Animal(String name , Point location)
	 * @param name - name of the animal
	 * @param location - location of the animal
	 */

	public Turtle(String name, Point location) {
		this(name, 1);
		setLocation(location);
	}

	/**
	 * /**


	 *       @see #getName()
	 *       @see #setDiet(IDiet diet)
	 *       @see #setAge(int Age)
	 *       @see #setName(String name)
	 *       @see #setWeight(double weight)
	 *       @see MessageUtility#logConstractor(String className, String name)
	 *      @param name - name of the turtle
	 *      @param Age - age of the turtle

	 *      */

	public Turtle(String name, int Age) {
		super(name, new Point(80, 0));
		setName(name);
		MessageUtility.logConstractor("Turtle",getName());
		setWeight(500);
		setAge(age);
		setDiet(new Herbivore());
	}

	/**
	 * @see Chew
	 */
	@Override
	public void makeSound() {
		chew("Retracts its head in then eats quietly");
	}

	/**
	 *
	 * @return - age
	 */
	public int getAge() {
		MessageUtility.logGetter(getName(),"getAge",age);
		return age;
	}

	/**
	 *
	 * @param age
	 * @see utilities.MessageUtility#logSetter(String name, String funcName, Object age, boolean)
	 * @return - boolean
	 */
	public boolean setAge(int age) {
		if ((age >= 0) && (age <= 500)) {
			age = age;
			MessageUtility.logSetter(getName(),"setAge",age,true);

			return true;
		}
		MessageUtility.logSetter(getName(),"setAge",age,false);
		return false;
	}

	/**
	 * choose image of the right color turtle turning to the right side
	 * @return images
	 */

	protected String getRightMoveImageName() {
		String color = getColor();
		if (color == null)
			return "trt_n_1.png";
		if (color.equals("Red"))
			return "trt_r_1.png";
		if (color.equals("Blue"))
			return "trt_b_1.png";
		return "trt_n_1.png";
	}
	/**
	 * choose image of the right color turtle turning to the left side
	 * @return images
	 */

	protected String getLeftMoveImageName() {
		String color = getColor();
		if (color == null)
			return "trt_n_2.png";
		if (color.equals("Red"))
			return "trt_r_2.png";
		if (color.equals("Blue"))
			return "trt_b_2.png";
		return "trt_n_2.png";
	}
	public void setWeightAccordingToSize() {
		setWeight(getSize() * 0.5);
	}
}
