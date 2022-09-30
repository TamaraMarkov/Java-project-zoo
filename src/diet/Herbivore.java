package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import privateutil.helpDiet;

/**
 * This class refers to a herbivore
 * @see privateutil.helpDiet
 * @author Leah Brodsky
 * @author Ziv Gimani
 */
public class Herbivore extends helpDiet {
    @Override
/**
 * The function receives food and checks if the food is of type vegetable
 * if the animal is herbivore
 * @param EFoodType food
 * @return boolean
 */
    public boolean canEat(EFoodType food) {
        if(food==EFoodType.VEGETABLE )
            return true;
        return false;
    }
    /**
     * The function computes the weight the animal
     * has to add to the current weight after eating
     * @param animal
     * @param food
     * @return the weight the herbivore has to add to current weight after eating
     */

    public  double  eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodtype()))
            return animal.getWeight() * 0.07;
        return 0;
    }

}
