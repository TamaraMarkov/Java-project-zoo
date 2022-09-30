package animals;

import diet.Carnivore;
import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import mobility.Point;
import utilities.MessageUtility;
import animals.Animal;

import java.util.Random;

/**
 * This class contains functions that define a bear
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

public class Lion extends Roar {
	private int scarCount=0;
	public Lion() {
		super("", new Point(20, 0));
		MessageUtility.logConstractor("Lion",getName());
		setWeight(408.2);
		setDiet(new Carnivore());
	}
	public Lion(String name) {
		super(name, new Point(20, 0));
		setName(name);
		MessageUtility.logConstractor("Lion",getName());
		setWeight(408.2);
		setDiet(new Carnivore());
	}

	/**
	 * @see  Animal#Animal(String point, Point location)
	 * @param name
	 * @param location
	 */
	public Lion(String name, Point location) {
		super(name, location);

	}

	/**
	 * @return int scarCount
	 *
	 */
	public int getScarCount() {
		return scarCount;
	}

	/**
	 *
	 * @param scarCount
	 * @see utilities.MessageUtility#logSetter(String name, String funcName, Object scarCount, boolean)
	 * @return
	 */

	public boolean setScars(int scarCount) {
		this.scarCount = scarCount;
		MessageUtility.logSetter(getName(),"setScars",scarCount,true);
		return true;
	}

	/**
	 *
	 * @param food
	 * @see Animal#eat(IEdible food)
	 * 50% possibility than the lion will get scar after food
	 * @return boolean if the food is appropriate for lion
	 */
	public boolean eat(IEdible food) {
		boolean eatResult = super.eat(food);
		if (eatResult) {
			Random ran = new Random();
			int choice = ran.nextInt(2);
			if(choice==1)
				setScars(scarCount + 1);
		}
		return eatResult;
	}

	/**
	 * Function defines if the what diet if for the lion carnivore,omnivore or herbivore
	 * @param diet
	 *  @see utilities.MessageUtility#logSetter(String name, String funcName, Object diet, boolean)
	 * @return boolean
	 */
	@Override
	public boolean setDiet(IDiet diet) {
		if(diet instanceof Carnivore){
			return super.setDiet(diet);
		}
		return false;
	}

	@Override
	/**
	 * Which sound lion makes
	 * @see Roar
	 */
	public void makeSound() {
		roar("Roars, then stretches and shakes its mane");
	}
	@Override
	/**
	 * What is the type of food lion is
	 * @see MessageUtility#logGetter(String name, String funcName, Object EFoodType)
	 */
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(getName(), "getFoodType", EFoodType.NOTFOOD);
		return EFoodType.NOTFOOD;
	}
	/**
	 * choose image of the right color lion turning to the right side
	 * @return images
	 */

	protected String getRightMoveImageName() {
		String color = getColor();
		if (color == null)
			return "lio_n_1.png";
		if (color.equals("Red"))
			return "lio_r_1.png";
		if (color.equals("Blue"))
			return "lio_b_1.png";
		return "lio_n_1.png";
	}
	/**
	 * choose image of the right color lion turning to the left side
	 * @return images
	 */

	protected String getLeftMoveImageName() {
		String color = getColor();
		if (color == null)
			return "lio_n_2.png";
		if (color.equals("Red"))
			return "lio_r_2.png";
		if (color.equals("Blue"))
			return "lio_b_2.png";
		return "lio_n_2.png";
	}
	public void setWeightAccordingToSize() {
		setWeight(getSize() * 0.8);
	}
}
