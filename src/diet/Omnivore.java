package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import privateutil.helpDiet;

/**
 * This class refers to a omnivore
 * @see privateutil.helpDiet
 * @author Leah Brodsky
 * @author Ziv Gimani
 */
public class Omnivore extends helpDiet {
    @Override
    /**
     * The function receives food and checks if the food is of type meat or vegetable
     * if the animal is omnivore
     * @param EFoodType food
     * @return boolean
     */
    public boolean canEat(EFoodType food) {
        if(food==EFoodType.VEGETABLE || food==EFoodType.MEAT)
            return true;
        return false;
    }

}
