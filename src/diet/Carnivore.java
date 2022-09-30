package diet;

import animals.Animal;//hello world
import food.EFoodType;
import food.IEdible;
import privateutil.helpDiet;


/**
 * This class refers to a carnivore
 * @see privateutil.helpDiet
 * @author Leah Brodsky
 * @author Ziv Gimani
 */
public class Carnivore extends helpDiet {
    @Override
    /**
     * The function receives food and checks if the food is of type meat
     * if the animal is carnivore
     * @param EFoodType food
     * @return boolean
     */
    public boolean canEat(EFoodType food) {
        if(food==EFoodType.MEAT)
            return true;
        return false;
    }

    /**
     * The function computes the weight the animal
     * has to add to the current weight after eating
     * @param animal
     * @param food
     * @return the weight carnivore has to add to current weight after eating
     */
    public  double  eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodtype()))
            return animal.getWeight() * 0.1;
        return 0;
    }

}
