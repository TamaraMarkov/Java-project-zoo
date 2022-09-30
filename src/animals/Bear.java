package animals;

import diet.IDiet;
import diet.Omnivore;
import food.EFoodType;
import food.IEdible;
import mobility.Point;
import utilities.MessageUtility;

import java.util.Locale;

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

public class Bear extends Roar{
    /**
     * fur color of the bear
     */
    private String furColor="GRAY";

    /**
     *
     * @return furcolor of the bear
     */

    public String getFurColor() {

        return furColor;
    }

    /**
     *
     * @param furColor
     * @see utilities.MessageUtility#logSetter(String name, String funcName, Object furColor, boolean)
     * @see #getName()

     * @return boolean if the function succeeded
     */
    public boolean setFurColor(String furColor) {
        //furColor.toUpperCase();
        if(furColor.equals("BLACK") || furColor.equals("WHITE") ||furColor.equals("GRAY") ){

            MessageUtility.logSetter(getName(),"setFurColor",furColor,true);
            return true;
        }

        MessageUtility.logSetter(getName(),"setFurColor",furColor,false);
        return false;
    }

    public Bear() {
        super("", new Point(100, 5));
        MessageUtility.logConstractor("Bear",getName());
        setWeight(308.2);
        setFurColor("Natural");
        setDiet(new Omnivore());
    }
    /**
     * Constructor
     * @param name
     * @param furColor
     * @see #getName()
     * @see #setDiet(IDiet diet)
     * @see #setFurColor(String furColor)
     * @see #setName(String name)
     * @see #setWeight(double weight)
     * @see MessageUtility#logConstractor(String className, String name)
     *
     */
    public Bear(String name,String furColor) {
        super(name, new Point(100, 5));
        setName(name);
        MessageUtility.logConstractor("Bear",getName());
        setWeight(308.2);
        setFurColor(furColor);
        setDiet(new Omnivore());
    }
    /**
     * @param name
     * @param furColor
     * @see #setLocation(Point)
     */

    public Bear(String name, Point location,String furColor) {
        super(name, location);

    }

    /**
     *
     * @param diet
     * @see utilities.MessageUtility#logSetter(String name, String funcName, Object diet, boolean)
     * @see Animal#setDiet(IDiet diet)
     * @return boolean
     */
    @Override
    public boolean setDiet(IDiet diet) {
    	if (diet instanceof Omnivore) {
			return super.setDiet(diet);
        }
        return false;
    }

    /**
     * What sound bear makes
     * @see Roar
     */
	@Override
	public void makeSound() {
		roar("Stands on its hind legs, roars and scratches its belly");
	}

    /**
     * choose image of the right color bear turning to the right side
     * @return images
     */
    protected String getRightMoveImageName() {
		String color = getColor();
		if (color == null)
			return "bea_n_1.png";
		if (color.equals("Red"))
			return "bea_r_1.png";
		if (color.equals("Blue"))
			return "bea_b_1.png";
		return "bea_n_1.png";
	}
    /**
     * choose image of the right color bear turning to the left side
     * @return images
     */
	protected String getLeftMoveImageName() {
		String color = getColor();
		if (color == null)
			return "bea_n_2.png";
		if (color.equals("Red"))
			return "bea_r_2.png";
		if (color.equals("Blue"))
			return "bea_b_2.png";
		return "bea_n_2.png";
	}
	public void setWeightAccordingToSize() {
		setWeight(getSize() * 1.5);
	}
}
