package privateutil;
import animals.Animal;
import diet.IDiet;
import food.EFoodType;
import food.IEdible;

/**
 * This class will be parent of the diet classes
 * @authors Leah Brodsky
 * @author Ziv Gimani
 * @see IDiet
 */
public abstract class helpDiet implements IDiet {

    /**
     * Function checks if the food is appropriate
     * @param food
     * @see  IDiet
     * @return boolean
     */
    public abstract boolean canEat(EFoodType food) ;

    /**
     *
     * @param animal
     * @param food
     * @return the weight the animal has to add to the current weight after eating
     */

    public  double  eat(Animal animal, IEdible food){
        if(canEat(food.getFoodtype()))
        {

            if(food.getFoodtype() == EFoodType.MEAT)
                return animal.getWeight()*0.1;
            else if (food.getFoodtype() == EFoodType.VEGETABLE)
                return animal.getWeight()*0.07;
        }
        return  0;
    };

    /**
     *
     * @return String whick contain the class name (carnivore, omnivore or herbivore)
     */
    public String toString()
    {
        return "["+this.getClass().getSimpleName()+"]";
    }





}
